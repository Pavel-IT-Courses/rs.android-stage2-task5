package com.example.catapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.catapplication.OnCatScrollListener
import com.example.catapplication.DetailedActivity
import com.example.catapplication.R
import com.example.catapplication.entity.Cat

class CatAdapter(private val onCatScrollListener: OnCatScrollListener) : RecyclerView.Adapter<CatViewHolder>() {
    private val items = mutableListOf<Cat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, null)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val imageUrl = items[position].imageUrl ?: ""
        holder.bind(imageUrl)
    }

    // This method calls a new portion of pictures upload when the list is approaching the last item
    override fun onViewAttachedToWindow(holder: CatViewHolder) {
        super.onViewAttachedToWindow(holder)
        val layoutPosition = holder.layoutPosition
        if (layoutPosition == itemCount - 3) {
            onCatScrollListener.onCatScrolled()
        }
    }

    fun addItems(newItems: List<Cat>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = view.findViewById<ImageView>(R.id.imageView)

    fun bind(imageUrl: String) {
        imageView.load(imageUrl)
        val context = imageView.context
        imageView.setOnClickListener {
            val intent = Intent(context, DetailedActivity::class.java)
            intent.putExtra("url", imageUrl)
            context.startActivity(intent)
        }
    }
}
