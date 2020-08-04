package com.example.ted

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.ted.model.data.TedPresentation

class TedAdapter() : RecyclerView.Adapter<TedViewHolder>() {
    private val items = mutableListOf<TedPresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, null)
        return TedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TedViewHolder, position: Int) {
        val imageUrl = items[position].imageUrl ?: ""
        val titleText = items[position].title ?: ""
        val durationText = items[position].duration ?: ""
        holder.bind(imageUrl, titleText, durationText)
    }


    fun addItems(newItems: List<TedPresentation>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class TedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = view.findViewById<ImageView>(R.id.imageView)
    private val authorView = view.findViewById<TextView>(R.id.authorView)
    private val titleView = view.findViewById<TextView>(R.id.titleView)
    private val durationView = view.findViewById<TextView>(R.id.durationView)

    fun bind(imageUrl: String, titleText: String, durationText: String) {
        imageView.load(imageUrl)
        val author = titleText.split("|")[1].trim()
        authorView.text = author
        val title = titleText.split("|")[0].trim()
        titleView.text = title
        durationView.text = durationText
    }
}
