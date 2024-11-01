package com.example.bolosapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bolosapp.Activity.DetailActivity
import com.example.bolosapp.Model.Items.Model
import com.example.bolosapp.databinding.ViewholderItemBinding

class ListItemAdapter(private val items: List<Model>) :
    RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ViewholderItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Model) {
            binding.apply {
                textTitle.text = item.title
                priceText.text = "$${item.price}"
                textSubtitle.text = item.extra
                Glide.with(imag.context)
                    .load(item.picUrl[0])
                    .into(imag)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                    putExtra("object", item)
                }
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
