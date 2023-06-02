package com.naze.imageslidetest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ImageSliderAdapter(
    private val context: Context,
    private val images: MutableList<Int>
): RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val item: ImageView = itemView.findViewById(R.id.imageView1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageResId = images[position]
        holder.item.setImageDrawable(ContextCompat.getDrawable(context, images[position]))

    }

    override fun getItemCount(): Int {
        return images.size
    }
}