package com.benice.testrecyclerview.presentation.features.v1.viewitems

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import coil.load
import coil.transform.CircleCropTransformation
import com.benice.testrecyclerview.R
import com.benice.testrecyclerview.databinding.ItemDataBinding
import com.benice.testrecyclerview.databinding.ItemHeaderBinding
import com.benice.testrecyclerview.presentation.SealedItem

abstract class BaseViewHolder<T : ViewBinding, I : SealedItem>(
    private val binding: T
) : ViewHolder(binding.root) {

    abstract fun bind(item : I)

}

class HeaderViewHolder(
    private val binding: ItemHeaderBinding
) : BaseViewHolder<ItemHeaderBinding, SealedItem.HeaderItem>(binding) {

    override fun bind(item: SealedItem.HeaderItem) {
        with(binding) {
            titleTextView.text = item.name
        }
    }

}

class ElementViewHolder(
    private val binding: ItemDataBinding
) : BaseViewHolder<ItemDataBinding, SealedItem.ElementItem>(binding) {

    override fun bind(item: SealedItem.ElementItem) {
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
