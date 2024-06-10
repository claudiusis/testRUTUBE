package com.example.testrutube.ui.recycler

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomScrollListener() : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val first = layoutManager.findFirstVisibleItemPosition()
        val last = layoutManager.findLastVisibleItemPosition()

        for (position in first..last) {
            val view = layoutManager.findViewByPosition(position) ?: continue
            val viewHolder = recyclerView.getChildViewHolder(view)
            if (viewHolder is CitiesAdapter.CitiesVH)
                continue

            val viewY = view.y.toInt()
            val isVisible = viewY >= ((viewHolder as CitiesAdapter.HeaderVH).textView.measuredHeight/2-7)
            viewHolder.updateVisibility(isVisible)
        }
    }
}