package com.benice.testrecyclerview.presentation.features.v1.viewitems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.benice.testrecyclerview.R
import com.benice.testrecyclerview.databinding.ItemDataBinding
import com.benice.testrecyclerview.presentation.SealedItem
import com.benice.testrecyclerview.presentation.features.v1.DataItemListener

class ElementViewItem(
    private val listener: DataItemListener
) : ViewItem<ItemDataBinding, SealedItem.ElementItem>, View.OnClickListener {

    override fun layoutId() = R.layout.item_data

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<ItemDataBinding, SealedItem.ElementItem> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDataBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        binding.favoriteImageView.setOnClickListener(this)
        binding.deleteImageView.setOnClickListener(this)
        return ElementViewHolder(binding)
    }

    override fun onClick(v: View) {
        val item = v.tag as SealedItem
        when (v.id) {
            R.id.deleteImageView -> listener.onDeleteClicked(item)
            R.id.favoriteImageView -> listener.onFavoriteClicked(item)
            else -> listener.onItemClicked(item)
        }
    }

    override fun getViewTypeBy(item: SealedItem) = item is SealedItem.ElementItem

}
