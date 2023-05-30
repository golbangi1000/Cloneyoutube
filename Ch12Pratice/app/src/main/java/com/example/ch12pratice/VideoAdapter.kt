package com.example.ch12pratice
import android.content.Context
import android.provider.MediaStore.Video
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ch12pratice.VideoAdapter.ViewHolder.Companion.diffUtil
import com.example.ch12pratice.databinding.ItemVideoBinding

class VideoAdapter(private val context: Context): ListAdapter<VideoItem, VideoAdapter.ViewHolder>(diffUtil){

    inner class ViewHolder(private val binding : ItemVideoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item : VideoItem){
            binding.titleTextView.text = item.title
            binding.subTitleTextView.text = context.getString(R.string.sub_title_video_info) "${item.channelName}    조회수 ${item.viewCount}   ${item.dataText}"
            binding.channelLogoImageView
            binding.videoThumbnailImageView

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<VideoItem>() {
            override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}