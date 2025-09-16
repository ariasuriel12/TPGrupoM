package com.example.climaapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListaActivity  : AppCompatActivity() {
    private val elementos = listOf("Elemento 1", "Elemento 2", "Elemento 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val listView = findViewById<ListView>(R.id.listView)

        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            elementos
        )
        listView.adapter = adapter



        listView.setOnItemClickListener {_,_, position,_->
            val intent = Intent(this, DetalleActivity::class.java)
            intent.putExtra("nombre", elementos[position])
            startActivity(intent)
        }
    }
}
