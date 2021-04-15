package com.example.acronymapi.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapi.databinding.AcronymItemBinding
import com.example.acronymapi.model.Lf
import com.example.acronymapi.util.inflater

class AcronymAdapter : RecyclerView.Adapter<AcronymAdapter.AcronymViewHolder>() {

    private val dataSet = mutableListOf<Lf>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {
        val binding = AcronymItemBinding.inflate(parent.inflater(), parent, false)
        return AcronymViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        holder.loadMeaning(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    fun loadData(meanings: List<Lf>) {
        dataSet.clear()
        dataSet.addAll(meanings)
        notifyDataSetChanged()
    }

    class AcronymViewHolder(
        private val binding: AcronymItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun loadMeaning(lf: Lf) = with(binding) {
            meaning = lf
            executePendingBindings()
        }
    }
}
