package com.benice.testrecyclerview.presentation.features.v1.viewitems

import android.view.LayoutInflater
import android.view.ViewGroup
import com.benice.testrecyclerview.R
import com.benice.testrecyclerview.databinding.ItemHeaderBinding
import com.benice.testrecyclerview.presentation.SealedItem

class HeaderViewItem : ViewItem<ItemHeaderBinding, SealedItem.HeaderItem> {

    override fun layoutId(): Int = R.layout.item_header

    override fun onCreateViewHolder(parent: ViewGroup): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHeaderBinding.inflate(inflater, parent, false)
        return HeaderViewHolder(binding)
    }

    override fun getViewTypeBy(item: SealedItem) = item is SealedItem.HeaderItem

}
