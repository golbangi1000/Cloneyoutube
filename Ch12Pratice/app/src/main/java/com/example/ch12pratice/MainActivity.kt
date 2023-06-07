package com.example.ch12pratice

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Video
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch12pratice.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var videoAdapter : VideoAdapter

    private var player : ExoPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        videoAdapter = VideoAdapter(context = this){videoItem ->
            binding.motionLayout.setTransition(R.id.collapse,R.id.expand)
            binding.motionLayout.transitionToEnd()

            play(videoItem)
        }

        binding.motionLayout.jumpToState(R.id.collapse)

        binding.videoListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
                adapter = videoAdapter
        }


        val videoList = readData("videos.json", VideoList::class.java) ?: VideoList(emptyList())
        videoAdapter.submitList(videoList.videos)

        binding.playerRecyclerView

    }

    private fun initExoplayer(){
            player = ExoPlayer.Builder(this).build()
                .also { exoPlayer ->
                    binding.playerView.player = exoPlayer
                }
    }

    private fun play(videoItem : VideoItem){
        player?.setMediaItem(MediaItem.fromUri(Uri.parse(videoItem.videoUrl)))
        player?.prepare()
        player?.play()
    }

    override fun onStart() {
        super.onStart()
        if(player == null){
            initExoplayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if(player == null)  {
            initExoplayer()
        }
    }

    override fun onStop() {
        super.onStop()
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }
}