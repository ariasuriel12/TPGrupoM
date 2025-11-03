package com.example.climaapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.climaapp.model.WeatherResponse
import com.example.climaapp.network.ApiClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalleActivity : AppCompatActivity() {

    private val apiKey = "be74d0c634efc8f470b5786d304f509c"

    private lateinit var tvCiudad: TextView
    private lateinit var tvTemperatura: TextView
    private lateinit var tvDescripcion: TextView
    private lateinit var tvHumedad: TextView
    private lateinit var imgIcono: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tvCiudad = findViewById<TextView>(R.id.tvCiudad)
        tvTemperatura = findViewById<TextView>(R.id.tvTemperatura)
        tvDescripcion = findViewById<TextView>(R.id.tvDescripcion)
        tvHumedad = findViewById<TextView>(R.id.tvHumedad)
        imgIcono = findViewById<ImageView>(R.id.imgIcono)

        val provincia = intent.getStringExtra("provincia")
        tvCiudad.text = provincia

        if (provincia != null) {
            fetchWeatherData(provincia)
        }
    }

    private fun fetchWeatherData(provincia: String) {
        ApiClient.instance.getWeather(apiKey, provincia)
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        val clima = response.body()
                        if (clima != null) {
                            val apiDescription = clima.current.weather_descriptions.firstOrNull()
                            val humidityValue = clima.current.humidity
                            val iconUrl = clima.current.weather_icons.firstOrNull()

                            val stringId = getWeatherStringKey(apiDescription)
                            if (stringId != 0) {
                                tvDescripcion.text = getString(stringId)
                            } else {
                                tvDescripcion.text = apiDescription ?: "—"
                            }

                            tvTemperatura.text = "${clima.current.temperature}°C"
                            val humedadLabel = getString(R.string.humidity_label)
                            tvHumedad.text = "$humedadLabel: ${humidityValue}%"

                            if (iconUrl != null) {
                                Picasso.get().load(iconUrl).into(imgIcono)
                            }

                        } else {
                            tvDescripcion.text = getString(R.string.error_api_data)
                        }
                    } else {
                        tvDescripcion.text = getString(R.string.error_api_response) + response.code()
                    }
                }
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    tvDescripcion.text = getString(R.string.error_connection) + t.message
                }
            })
    }

    private fun getWeatherStringKey(apiDescription: String?): Int {

        return when (apiDescription?.lowercase()?.trim()) {
            "partly cloudy" -> R.string.partly_cloudy_key
            "clear", "sunny" -> R.string.clear_sky_key
            "rain", "light rain" -> R.string.rain_key
            "cloudy" -> R.string.cloudy_key
            else -> 0
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
