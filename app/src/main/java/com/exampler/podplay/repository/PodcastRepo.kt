package com.exampler.podplay.repository

import com.exampler.podplay.model.Podcast
import com.exampler.podplay.service.RssFeedService

class PodcastRepo {

    fun getPodcast(feedUrl: String,
    callback: (Podcast?) -> Unit) {

        val rssFeedService = RssFeedService()

        rssFeedService.getFeed(feedUrl) {

        }

        callback(
            Podcast(feedUrl, "No Name", "No description", "no image"))
    }
}