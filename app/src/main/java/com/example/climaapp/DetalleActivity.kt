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

    // 🔑 Tu API key de Weatherstack
    private val apiKey = "be74d0c634efc8f470b5786d304f509c"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        // Configurar Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Referencias a vistas
        val tvCiudad = findViewById<TextView>(R.id.tvCiudad)
        val tvTemperatura = findViewById<TextView>(R.id.tvTemperatura)
        val tvDescripcion = findViewById<TextView>(R.id.tvDescripcion)
        val tvHumedad = findViewById<TextView>(R.id.tvHumedad)
        val imgIcono = findViewById<ImageView>(R.id.imgIcono)

        // Obtener la provincia desde ListaActivity
        val provincia = intent.getStringExtra("provincia")
        tvCiudad.text = provincia

        if (provincia != null) {
            // Llamada a la API
            ApiClient.instance.getWeather(apiKey, provincia)
                .enqueue(object : Callback<WeatherResponse> {
                    override fun onResponse(
                        call: Call<WeatherResponse>,
                        response: Response<WeatherResponse>
                    ) {
                        if (response.isSuccessful) {
                            val clima = response.body()
                            if (clima != null) {
                                tvTemperatura.text = "${clima.current.temperature}°C"
                                tvDescripcion.text = clima.current.weather_descriptions.firstOrNull() ?: "—"
                                tvHumedad.text = "Humedad: ${clima.current.humidity}%"

                                val iconUrl = clima.current.weather_icons.firstOrNull()
                                if (iconUrl != null) {
                                    Picasso.get().load(iconUrl).into(imgIcono)
                                }
                            } else {
                                tvDescripcion.text = "No se pudieron obtener datos"
                            }
                        } else {
                            tvDescripcion.text = "Error: ${response.message()}"
                        }
                    }

                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        tvDescripcion.text = "Error de conexión: ${t.message}"
                    }
                })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
