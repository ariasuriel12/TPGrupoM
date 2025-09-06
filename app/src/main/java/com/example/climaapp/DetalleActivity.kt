package com.example.climaapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val nombre = intent.getStringExtra("nombre")

        val tvDetalle = findViewById<TextView>(R.id.tvDetalle)
        tvDetalle.text = "Mostrando datos completos de: $nombre"
    }
}
