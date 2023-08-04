package com.benice.testrecyclerview.presentation

import com.benice.testrecyclerview.domain.Element

sealed class SealedItem {

    data class HeaderItem(
        val name: String
    ): SealedItem()

    data class ElementItem(
        val element: Element
    ): SealedItem() {
        val id: Long get() = element.id
        val name: String get() = element.name
        val photo: String get() = element.photoUrl
    }

}
