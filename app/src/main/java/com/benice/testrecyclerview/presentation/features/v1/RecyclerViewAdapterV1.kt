package com.benice.testrecyclerview.presentation.features.v1

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.CircleCropTransformation
import com.benice.testrecyclerview.R
import com.benice.testrecyclerview.databinding.ItemDataBinding
import com.benice.testrecyclerview.databinding.ItemHeaderBinding
import com.benice.testrecyclerview.presentation.SealedItem

interface DataItemListener {
    fun onItemClicked(item: SealedItem)
    fun onDeleteClicked(item: SealedItem)
    fun onFavoriteClicked(item: SealedItem)
}

class RecyclerViewAdapterV1(
    private val listener: DataItemListener
) : ListAdapter<SealedItem, ViewHolder>(DiffUtils),
    View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val binding = ItemHeaderBinding.inflate(inflater, parent, false)
                HeaderViewHolder(binding)
            }

            VIEW_TYPE_ITEM -> {
                val binding = ItemDataBinding.inflate(inflater, parent, false)
                binding.root.setOnClickListener(this)
                binding.favoriteImageView.setOnClickListener(this)
                binding.deleteImageView.setOnClickListener(this)
                ItemViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Unknown ViewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is HeaderViewHolder -> {
                holder.bind(item as SealedItem.HeaderItem)
            }
            is ItemViewHolder -> {
                holder.bind(item as SealedItem.ElementItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SealedItem.HeaderItem -> VIEW_TYPE_HEADER
            is SealedItem.ElementItem -> VIEW_TYPE_ITEM
        }
    }

    class HeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: SealedItem.HeaderItem) {
            with(binding) {
                titleTextView.text = item.name
            }
        }
    }

    class ItemViewHolder(
        private val binding: ItemDataBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: SealedItem.ElementItem) {
            with(binding) {
                root.tag = item
                favoriteImageView.tag = item
                deleteImageView.tag = item

                itemNameTextView.text = item.name
                catImageView.load(item.photo) {
                    transformations(CircleCropTransformation())
                    placeholder(R.drawable.circle)
                }
                favoriteImageView.setImageResource(R.drawable.ic_favorite_not)
                favoriteImageView.imageTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        root.context,
                        R.color.action
                    )
                )
            }
        }
    }

    object DiffUtils : ItemCallback<SealedItem>() {
        override fun areItemsTheSame(oldItem: SealedItem, newItem: SealedItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SealedItem, newItem: SealedItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onClick(v: View) {
        val item = v.tag as SealedItem
        when (v.id) {
            R.id.deleteImageView -> listener.onDeleteClicked(item)
            R.id.favoriteImageView -> listener.onFavoriteClicked(item)
            else -> listener.onItemClicked(item)
        }
    }

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1
    }

}
