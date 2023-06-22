package com.naze.coordinatorlayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naze.coordinatorlayout.databinding.ItemMenuBinding

class MenuAdapter(private var items: ArrayList<String>): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(private val binding: ItemMenuBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            binding.listMenu.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder((ItemMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false)))
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}