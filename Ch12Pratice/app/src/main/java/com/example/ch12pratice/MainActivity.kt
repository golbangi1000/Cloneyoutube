package com.example.ch12pratice

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Video
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch12pratice.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var videoAdapter : VideoAdapter

    private var player : ExoPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initMotionLayout()
        initVideoRecyclerView()

        initControlButton()
        binding.hideButton.setOnClickListener{
            binding.motionLayout.transitionToState(R.id.hide)
            player?.pause()
        }

    }

    private fun initControlButton() {
        binding.controlButton.setOnClickListener {

            player?.let {
                if (it.isPlaying) {
                    it.pause()
                } else {
                    it.play()
                }
            }
        }
    }

    private fun initVideoRecyclerView() {
        videoAdapter = VideoAdapter(context = this) { videoItem ->
            binding.motionLayout.setTransition(R.id.collapse, R.id.expand)
            binding.motionLayout.transitionToEnd()

            play(videoItem)
        }
        binding.videoListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
        }


        val videoList = readData("videos.json", VideoList::class.java) ?: VideoList(emptyList())
        videoAdapter.submitList(videoList.videos)
    }

    private fun initMotionLayout() {
        binding.motionLayout.targetView = binding.videoPlayerContainer
        binding.motionLayout.jumpToState(R.id.hide)

        binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                binding.playerView.useController = false

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                    binding.playerView.useController =   (currentId == R.id.expand)
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }

        })
    }

    private fun initExoplayer(){
            player = ExoPlayer.Builder(this).build()
                .also { exoPlayer ->
                    binding.playerView.player = exoPlayer
                    binding.playerView.useController = false

                    exoPlayer.addListener(object: Player.Listener {
                        override fun onIsPlayingChanged(isPlaying: Boolean) {
                            super.onIsPlayingChanged(isPlaying)

                            if(isPlaying){
                                binding.controlButton.setImageResource(R.drawable.baseline_pause_24)
                            } else{
                                binding.controlButton.setImageResource(R.drawable.baseline_play_arrow_24)
                            }
                        }
                    })
                }
    }

    private fun play(videoItem : VideoItem){
        player?.setMediaItem(MediaItem.fromUri(Uri.parse(videoItem.videoUrl)))
        player?.prepare()
        player?.play()

        binding.videoTitleTextView.text = videoItem.title
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