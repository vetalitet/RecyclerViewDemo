package com.benice.testrecyclerview.presentation.features.v1.viewitems

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.benice.testrecyclerview.presentation.SealedItem

interface ViewItem<T : ViewBinding, I : SealedItem> {

    fun layoutId(): Int
    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<T, I>
    fun getViewTypeBy(item: SealedItem): Boolean

}
