package com.example.climaapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val tvDetalle = findViewById<TextView>(R.id.tvDetalle)

        // Obtener el dato enviado desde ListaActivity
        val nombre = intent.getStringExtra("nombre")
        tvDetalle.text = nombre
    }
}
