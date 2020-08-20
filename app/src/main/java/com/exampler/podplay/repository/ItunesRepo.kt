package com.exampler.podplay.repository

import com.exampler.podplay.service.ItunesService
import com.exampler.podplay.service.PodcastResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItunesRepo (private val itunesService: ItunesService) {

    fun searchByTerm(term: String, callBack: (List<PodcastResponse.ItunesPodcast>?) -> Unit) {

        val podcastCall = itunesService.searchPodcastByTerm(term)

        podcastCall.enqueue(object : Callback<PodcastResponse> {

            override fun onFailure(call: Call<PodcastResponse>, t: Throwable) {
                callBack(null)
            }

            override fun onResponse(
                call: Call<PodcastResponse>?,
                response: Response<PodcastResponse>?
            ) {
                val body = response?.body()

                callBack(body?.results)
            }
        })
    }
}