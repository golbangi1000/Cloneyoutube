package com.example.ch12pratice

import com.google.gson.annotations.SerializedName

data class VideoList(
    val videos : List<VideoItem>

)


data class VideoItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("sources")
    val videoUrl : String,
    @SerializedName("channelName")
    val channelName : String,
    @SerializedName("viewCount")
    val viewCount : String,
    @SerializedName("dataText")
    val dataText : String,
    @SerializedName("channelThumb")
    val channelThumb : String,
    @SerializedName("thumb")
    val videoThumb : String,
    )


/*

      "channelThumb": "https://picsum.photos/seed/Blender/40",
      "thumb": "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg",
 */