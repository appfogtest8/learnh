package com.exampler.podplay.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exampler.podplay.viewmodel.SerchViewModel
import kotlinx.android.synthetic.main.search_item.view.*

class PodcastListAdapter(
    private var podcastSummaryViewList: List<SerchViewModel.PodcastSummaryViewData>?,
    private val podcastListAdapterListener:
            PodcastListAdapterListener,
    private val parentActivity: Activity) :
        RecyclerView.Adapter<PodcastListAdapter.ViewHolder>() {

    interface PodcastListAdapterListener {
        fun onShowDetails(podcastSummaryViewData: SerchViewModel.PodcastSummaryViewData)
    }

    inner class ViewHolder(v: View,
    private val podcastListAdapterListener: PodcastListAdapterListener) :
            RecyclerView.ViewHolder(v) {

        var podcastSummaryViewData = SerchViewModel.PodcastSummaryViewData? = null
                val nameTextView: TextView = v.podcastNameTextView
        val listUpdatedTextView: TextView = v.podcastLastUpdatedTextView
        val podcastImageView: ImageView = v.podcastImage

        init {
            v.setOnClickListener {
                podcastSummaryViewData?.let {
                    podcastListAdapterListener.onShowDetails(it)
                }
            }
        }
    }

    fun setSearchData(podcastSummaryViewData: List<SerchViewModel.PodcastSummaryViewData>) {
        podcastSummaryViewList = podcastSummaryViewData
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PodcastListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item, parent, false),
        podcastListAdapterListener)
    }

    override fun onBindViewHolder(holder: PodcastListAdapter.ViewHolder, position: Int) {
        val searchViewList = podcastSummaryViewList ?: return
        val searchView = searchViewList[position]
        holder.podcastSummaryViewData = searchView
        holder.nameTextView.text = searchView.name
        holder.lastUpdatedTextView.text = searchView.lastUpdated

        Glide.with(parentActivity)
            .load(searchView.imageUrl)
            .into(holder.podcastImageView)
    }

    override fun getItemCount(): Int {
        return podcastSummaryViewList?. size ?: 0
    }
}