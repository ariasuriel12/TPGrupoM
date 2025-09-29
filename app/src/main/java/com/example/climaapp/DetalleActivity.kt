package com.example.climaapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        // ---------------- Configurar Toolbar ----------------
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Mostrar flecha de retroceso
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // -----------------------------------------------------

        val tvDetalle = findViewById<TextView>(R.id.tvDetalle)

        // Obtener el dato enviado desde ListaActivity
        val nombre = intent.getStringExtra("nombre")
        tvDetalle.text = nombre
    }

    // Manejar la flecha atrás en la toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
