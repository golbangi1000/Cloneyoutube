package com.example.ch12pratice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Video
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch12pratice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var videoAdapter : VideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        videoAdapter = VideoAdapter(context = this)

        binding.videoListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
                adapter = videoAdapter
        }


        val videoList = readData("videos.json", VideoList::class.java) ?: VideoList(emptyList())
        videoAdapter.submitList(videoList.videos)

        binding.playerRecyclerView
    }
}