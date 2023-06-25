package com.example.happyplaces_repeat.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.happyplaces_repeat.domain.HappyPlace

class HappyPlaceDiffCallback: DiffUtil.ItemCallback<HappyPlace>() {
    override fun areItemsTheSame(oldItem: HappyPlace, newItem: HappyPlace): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HappyPlace, newItem: HappyPlace): Boolean {
        return oldItem == newItem
    }
}