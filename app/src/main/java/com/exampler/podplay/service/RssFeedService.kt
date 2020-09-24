package com.exampler.podplay.service

import okhttp3.*
import java.io.IOException

class RssFeedService : FeedService {
    override fun getFeed(xmlFileURL: String,
    callBack: (RssFeedResponse?) -> Unit) {

        val client = OkHttpClient()

        val request = Request.Builder()
            .url(xmlFileURL)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                callBack(null)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        println(responseBody.string());
                        return
                    }
                }
                callBack(null)
            }
        })
    }
}

interface FeedService {
    fun getFeed(xmlFileURL: String,
    callBack: (RssFeedResponse?) -> Unit)

    companion object {
        val instance: FeedService by lazy {
            RssFeedService()
        }
    }
}