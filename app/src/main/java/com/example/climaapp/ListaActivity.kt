package com.example.climaapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        // Lista de elementos de ejemplo
        val elementos = arrayOf("Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4")

        // Inicializar ListView y adapter
        val listView = findViewById<ListView>(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, elementos)
        listView.adapter = adapter

<<<<<<< HEAD
        // Listener de click en cada elemento
        listView.setOnItemClickListener { _, _, position, _ ->
            val nombreElemento = elementos[position]
            val intent = Intent(this, DetalleActivity::class.java)
            intent.putExtra("nombre", nombreElemento) // enviar dato a DetalleActivity
=======
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this@TuActivity, DetalleActivity::class.java).apply {
                putExtra("nombre", elementos[position])
            }
>>>>>>> 90baa64 (ajustes ListaActivity)
            startActivity(intent)
        }
    }
}

