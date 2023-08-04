package com.benice.testrecyclerview.presentation.features.v1

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.benice.testrecyclerview.presentation.SealedItem
import com.benice.testrecyclerview.presentation.features.v1.viewitems.BaseViewHolder
import com.benice.testrecyclerview.presentation.features.v1.viewitems.ViewItem

interface DataItemListener {
    fun onItemClicked(item: SealedItem)
    fun onDeleteClicked(item: SealedItem)
    fun onFavoriteClicked(item: SealedItem)
}

class RecyclerViewAdapterV1(
    private val viewItems: List<ViewItem<*, *>>
) : ListAdapter<SealedItem, BaseViewHolder<ViewBinding, SealedItem>>(DiffUtils) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, SealedItem> {
        return viewItems.find { it.layoutId() == viewType }
            ?.onCreateViewHolder(parent)
            ?.let { it as BaseViewHolder<ViewBinding, SealedItem> }
            ?: throw IllegalArgumentException("Unknown ViewType: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, SealedItem>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return viewItems.find { it.getViewTypeBy(item) }
            ?.layoutId()
            ?: throw IllegalArgumentException("View type not found: $item")
    }

    object DiffUtils : ItemCallback<SealedItem>() {
        override fun areItemsTheSame(oldItem: SealedItem, newItem: SealedItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SealedItem, newItem: SealedItem): Boolean {
            return oldItem == newItem
        }
    }

}
