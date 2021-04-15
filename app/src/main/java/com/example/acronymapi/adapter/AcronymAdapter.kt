package com.example.acronymapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapi.R
import com.example.acronymapi.data.remote.model.Lf
import com.example.acronymapi.databinding.AcronymItemBinding

class AcronymAdapter(private val dataSet: List<Lf>) : RecyclerView.Adapter<AcronymAdapter.AcronymViewHolder>() {

    class AcronymViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = AcronymItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.acronym_item, parent, false)
        return AcronymViewHolder(view)
    }

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        val meaning = dataSet[position]
        with(holder) {
            binding.tvName.text = meaning.lf
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}