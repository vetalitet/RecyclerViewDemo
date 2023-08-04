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
import com.benice.testrecyclerview.presentation.ElementItem

interface DataItemListener {
    fun onItemClicked(item: ElementItem)
    fun onDeleteClicked(item: ElementItem)
    fun onFavoriteClicked(item: ElementItem)
}

class RecyclerViewAdapterV1(
    private val listener: DataItemListener
) : ListAdapter<ElementItem, RecyclerViewAdapterV1.ItemHolder>(DiffUtils),
    View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDataBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.favoriteImageView.setOnClickListener(this)
        binding.deleteImageView.setOnClickListener(this)

        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
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

    class ItemHolder(
        val binding: ItemDataBinding
    ) : ViewHolder(binding.root) {

    }

    object DiffUtils : ItemCallback<ElementItem>() {
        override fun areItemsTheSame(oldItem: ElementItem, newItem: ElementItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ElementItem, newItem: ElementItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onClick(v: View) {
        val item = v.tag as ElementItem
        when (v.id) {
            R.id.deleteImageView -> listener.onDeleteClicked(item)
            R.id.favoriteImageView -> listener.onFavoriteClicked(item)
            else -> listener.onItemClicked(item)
        }
    }

}
