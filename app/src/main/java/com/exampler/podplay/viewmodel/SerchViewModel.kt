package com.exampler.podplay.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.exampler.podplay.repository.ItunesRepo
import com.exampler.podplay.service.PodcastResponse
import com.exampler.podplay.util.DateUtils

class SerchViewModel(application: Application) : AndroidViewModel(application) {

    var iTunesRepo: ItunesRepo? = null

    data class PodcastSummaryViewData(
        var name: String? = "",
        var lastUpdated: String? = "",
        var imageUrl: String? = "",
        var feedUrl: String? = ""
    )

    private fun itunesPodcastToPodcastSummaryView(
        itunesPodcast: PodcastResponse.ItunesPodcast):
            PodcastSummaryViewData {
        return  PodcastSummaryViewData(
            itunesPodcast.collectionCensoredName,
            DateUtils.jsonDateToShortDate(itunesPodcast.releaseDate),
            itunesPodcast.artworkUrl30,
            itunesPodcast.feedUrl
        )
    }

    fun searchPodcasts(term: String, callback: (List<PodcastSummaryViewData>) -> Unit) {

        iTunesRepo?.searchByTerm(term) { results ->
            if (results == null) {
                callback(emptyList())
            } else {
                val serchViews = results.map { podcast ->
                    itunesPodcastToPodcastSummaryView(podcast)
                }
                callback(serchViews)
            }
        }
    }
}