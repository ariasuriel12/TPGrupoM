package com.example.climaapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView // ¡Importante!

class ListaFragment: Fragment() {

    private lateinit var provincias: List<String>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_lista, container, false)

        provincias = listOf(
            "Buenos Aires",
            "Córdoba",
            "Santa Fe",
            "Mendoza",
            "Salta",
            "Tucumán",
            "Entre Ríos",
            "San Juan",
            "Catamarca",
            "La Pampa",
            "Santiago del Estero",
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

        recyclerView = view.findViewById(R.id.recyclerView_provincias)

        val onItemClick: (String) -> Unit = { provinciaSeleccionada ->
            val intent = Intent(requireContext(), DetalleActivity::class.java)
            intent.putExtra("provincia", provinciaSeleccionada)
            startActivity(intent)
        }

        val adapter = ProvinciasAdapter(provincias, onItemClick)
        recyclerView.adapter = adapter

        return view
    }
}