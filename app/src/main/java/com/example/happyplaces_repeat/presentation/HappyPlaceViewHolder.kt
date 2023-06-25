package com.example.happyplaces_repeat.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.happyplaces_repeat.R

class HappyPlaceViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    val tvTitle = view.findViewById<TextView>(R.id.tv_title)
    val tvDescription = view.findViewById<TextView>(R.id.tv_description)
    val ivPhoto = view.findViewById<ImageView>(R.id.iv_photo)
}