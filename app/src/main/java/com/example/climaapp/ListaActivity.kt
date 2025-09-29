package com.example.climaapp

import android.content.SharedPreferences
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ListaActivity : AppCompatActivity() {
    private val elementos = mutableListOf("Elemento 1", "Elemento 2", "Elemento 3", "Cerrar Sesión")

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)

        mostrarMensajeBienvenida()

        val listView = findViewById<ListView>(R.id.listView)

        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            elementos
        )
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val seleccionado = elementos[position]

            if (seleccionado == "Cerrar Sesión") {
                cerrarSesion()
            } else {
                val intent = Intent(this, DetalleActivity::class.java)
                intent.putExtra("nombre", seleccionado)
                startActivity(intent)
            }
        }
    }

    private fun mostrarMensajeBienvenida() {
        val userName = sharedPref.getString("username", "Usuario")

        Toast.makeText(
            this,
            "¡Bienvenido de vuelta, $userName! ☀️",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun cerrarSesion() {
        val editor = sharedPref.edit()

        editor.remove("username")
        editor.remove("is_logged_in")
        editor.apply()

        Toast.makeText(this, "Sesión cerrada con éxito.", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LoginActivity::class.java)
        // Opcional: Flags para limpiar la pila de actividades y evitar volver atrás
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Cierra ListaActivity
    }
}