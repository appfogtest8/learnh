package com.exampler.podplay.repository

import com.exampler.podplay.model.Podcast

class PodcastRepo {

    fun getPodcast(feedUrl: String,
    callback: (Podcast?) -> Unit) {
        callback(
            Podcast(feedUrl, "No Name", "No description", "no image"))
    }
}