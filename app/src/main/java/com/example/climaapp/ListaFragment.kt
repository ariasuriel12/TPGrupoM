package com.example.climaapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ListaFragment: Fragment() {
    private lateinit var listView: ListView
    private lateinit var provincias: List<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout que contendrá el ListView
        val view = inflater.inflate(R.layout.fragment_lista, container, false) // Puedes renombrar el layout a fragment_lista.xml

        // 🔹 Lista de provincias argentinas (la lógica se mantiene aquí)
        provincias = listOf(
            "Buenos Aires",
            "Córdoba",
            "Santa Fe",
            "Mendoza",
            "Salta",
            "Tucumán",
            "Entre Ríos",
            "San Juan",
            "Corrientes",
            "Misiones",
            "Chaco",
            "Formosa",
            "La Rioja",
            "San Luis",
            "Jujuy",
            "Neuquén",
            "Río Negro",
            "Chubut",
            "Santa Cruz",
            "Tierra del Fuego"
        )

        // 🔹 Configurar la lista
        listView = view.findViewById(R.id.listView) // Asumiendo que el ID del ListView sigue siendo 'listView'
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, provincias)
        listView.adapter = adapter

        // 🔹 Al hacer clic, abrir DetalleActivity con el nombre de la provincia
        listView.setOnItemClickListener { _, _, position, _ ->
            val provinciaSeleccionada = provincias[position]
            val intent = Intent(requireContext(), DetalleActivity::class.java)
            intent.putExtra("provincia", provinciaSeleccionada)
            startActivity(intent)
        }

        return view
    }
}