package com.sbor.youtubeapi.core.ui.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sbor.youtubeapi.data.remote.model.Item
import com.sbor.youtubeapi.databinding.ItemVideoBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.reflect.KFunction1

class VideoAdapter(val onVideoClick: KFunction1<Item, Unit>) : RecyclerView.Adapter<VideoAdapter.OnClickedViewHolder>() {
    private val selectedvideos = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnClickedViewHolder {
        return OnClickedViewHolder(ItemVideoBinding.inflate(LayoutInflater.from(parent.context),
            parent,false))
    }
    fun setselectedList(list: List<Item>) {
        selectedvideos.addAll(list)
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: OnClickedViewHolder, position: Int) {
        holder.bind(selectedvideos[position])
    }

    override fun getItemCount(): Int {
        return selectedvideos.size
    }
    inner class OnClickedViewHolder(private val binding: ItemVideoBinding) : RecyclerView.ViewHolder
        (binding.root){
        fun bind(items: Item) {
            Glide.with(binding.imgSelectVideo).load(items.snippet.thumbnails.medium.url).into(binding.imgSelectVideo)
            val date : Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(items.snippet.publishedAt)
            val duration =  SimpleDateFormat("HH:mm").format(date)
            binding.timeOfVideo.text = duration
            binding.tvDesc.text = items.snippet.title

            itemView.setOnClickListener {
                onVideoClick(items)
            }

        }

    }
}