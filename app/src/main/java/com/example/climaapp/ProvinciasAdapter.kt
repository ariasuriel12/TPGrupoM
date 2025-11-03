package com.example.climaapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProvinciasAdapter(
    private val provincias: List<String>,
    private val itemClickListener: (String) -> Unit
) : RecyclerView.Adapter<ProvinciasAdapter.ProvinciaViewHolder>() {

    inner class ProvinciaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.textView_nombre_provincia)
        fun bind(provincia: String) {
            nombreTextView.text = provincia
            itemView.setOnClickListener {
                itemClickListener(provincia)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinciaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_provincia, parent, false)
        return ProvinciaViewHolder(view)
    }

    // 3. ENLAZAR DATOS
    override fun onBindViewHolder(holder: ProvinciaViewHolder, position: Int) {
        holder.bind(provincias[position])
    }

    override fun getItemCount() = provincias.size
}