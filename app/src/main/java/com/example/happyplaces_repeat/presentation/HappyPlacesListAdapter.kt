package com.example.happyplaces_repeat.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.happyplaces_repeat.R
import com.example.happyplaces_repeat.domain.HappyPlace

class HappyPlacesListAdapter: ListAdapter<HappyPlace, HappyPlaceViewHolder>(HappyPlaceDiffCallback()) {

    var onPlaceClickListener :((HappyPlace)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HappyPlaceViewHolder {
        val layout = R.layout.happy_place_item
        val view = LayoutInflater.from(parent.context).inflate(layout,parent, false)
        return HappyPlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: HappyPlaceViewHolder, position: Int) {
        val happyPlace = getItem(position)
        holder.view.setOnClickListener{
            onPlaceClickListener?.invoke(happyPlace)
        }
        holder.ivPhoto.setImageURI(happyPlace.image.toUri())
        holder.tvDescription.text = happyPlace.description
        holder.tvTitle.text = happyPlace.title
    }

}