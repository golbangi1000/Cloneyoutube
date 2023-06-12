package com.example.ch12pratice

data class PlayerHeader(
    override val id: String,
    val title: String,
    val channelName: String,
    val viewCount: String,
    val dateText: String,
    val channelThumb: String,
): PlayerVideoModel