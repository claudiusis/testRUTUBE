package com.example.testrutube.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testrutube.R
import com.example.testrutube.data.model.City

class CitiesAdapter(val cities : List<City>, private val clickFunc : (city : City) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_CONTENT = 1
    }

    fun isHeader(position: Int) : Boolean {
        return position==0 || cities[position].city[0] != cities[position-1].city[0]
    }

    override fun getItemViewType(position: Int): Int {
        return if (isHeader(position)) TYPE_HEADER else TYPE_CONTENT
    }

    override fun getItemCount(): Int = cities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType== TYPE_HEADER){
            HeaderVH(inflater.inflate(R.layout.city_vh, parent, false))
        } else {
            CitiesVH(inflater.inflate(R.layout.city_vh, parent, false))
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderVH) {
            holder.onBind(cities[position])
        } else if (holder is CitiesVH) {
            holder.onBind(cities[position])
        }
    }

    inner class CitiesVH(view: View): RecyclerView.ViewHolder(view){
        private val textView : TextView = view.findViewById(R.id.cityName)

        fun onBind(city : City){
            textView.text = city.city

            textView.setOnClickListener {
                clickFunc.invoke(city)
            }

        }

    }

    inner class HeaderVH(view: View) : RecyclerView.ViewHolder(view){
        private val textView : TextView = view.findViewById(R.id.letter)
        private val cityView : TextView = view.findViewById(R.id.cityName)

        fun onBind(city : City){
            textView.text = city.city[0].toString()
            cityView.text = city.city

            cityView.setOnClickListener {
                clickFunc.invoke(city)
            }
        }

    }
}