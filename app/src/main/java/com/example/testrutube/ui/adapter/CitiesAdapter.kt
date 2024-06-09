package com.example.testrutube.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testrutube.R
import com.example.testrutube.data.model.City

class CitiesAdapter(private val cities : List<City>, private val clickFunc : (city : City) -> Unit): RecyclerView.Adapter<CitiesAdapter.CitiesVH>() {

    override fun getItemCount(): Int = cities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesVH {
        val inflater = LayoutInflater.from(parent.context)
        return CitiesVH(inflater.inflate(R.layout.city_vh, parent, false))
    }

    override fun onBindViewHolder(holder: CitiesVH, position: Int) {
        holder.textView.text = cities[position].city

        holder.textView.setOnClickListener {
            clickFunc.invoke(cities[position])
        }

    }

    inner class CitiesVH(view: View): RecyclerView.ViewHolder(view){
        val textView : TextView = view.findViewById(R.id.cityName)
    }
}