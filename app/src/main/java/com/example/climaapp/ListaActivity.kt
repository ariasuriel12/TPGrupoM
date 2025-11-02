package com.example.climaapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ListaActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var climas: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val prefs = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)

        val nombreUsuario = prefs.getString("username", "Usuario")

        Toast.makeText(this, "¡Bienvenido, $nombreUsuario! ☀️", Toast.LENGTH_LONG).show()
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        climas = listOf(
            "☀️ Soleado - 28°C",
            "⛅ Parcialmente nublado - 24°C",
            "🌧️ Lluvias aisladas - 19°C",
            "🌩️ Tormentas - 21°C",
            "❄️ Nevando - -2°C",
            "🌫️ Niebla - 12°C",
            "💨 Viento fuerte - 15°C"
        )

        listView = findViewById(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, climas)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetalleActivity::class.java)
            intent.putExtra("nombre", climas[position])
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menubar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Abrir ajustes", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_logout -> {
                val prefs = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)

                val nombreUsuario = prefs.getString("username", "Usuario")

                Toast.makeText(this, "¡Hasta pronto, $nombreUsuario! 👋", Toast.LENGTH_LONG).show()
                prefs.edit()
                    .putBoolean("remember", false)
                    .remove("username")
                    .remove("password")
                    .apply()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
