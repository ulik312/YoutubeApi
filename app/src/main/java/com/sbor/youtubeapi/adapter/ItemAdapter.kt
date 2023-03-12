package com.sbor.youtubeapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sbor.youtubeapi.databinding.ItemsSelectedBinding
import com.sbor.youtubeapi.model.Item
import com.bumptech.glide.Glide

class ItemAdapter(): RecyclerView.Adapter<ItemAdapter.OnClickedViewHolder>() {
    private val selectedvideos = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnClickedViewHolder {
        return OnClickedViewHolder(ItemsSelectedBinding.inflate(LayoutInflater.from(parent.context),
            parent,false))
    }
    fun setselectedList(list: List<Item>){
        selectedvideos.addAll(list)
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: OnClickedViewHolder, position: Int) {
        holder.bind(selectedvideos[position])
    }

    override fun getItemCount(): Int {
        return selectedvideos.size
    }
    inner class OnClickedViewHolder(private val binding: ItemsSelectedBinding) : RecyclerView.ViewHolder
        (binding.root){
        fun bind(items: Item) {
            Glide.with(binding.imgSelectVideo).load(items.snippet.thumbnails.medium.url).into(binding.imgSelectVideo)
//            binding.numOfVideos.text = items.contentDetails.itemCount.toString()
//            binding.tvDesc.text = items.snippet.title

        }

    }
}