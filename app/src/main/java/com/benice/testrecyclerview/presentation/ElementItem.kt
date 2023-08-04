package com.benice.testrecyclerview.presentation

import com.benice.testrecyclerview.domain.Element

data class ElementItem(
    val element: Element
) {
    val id: Long get() = element.id
    val name: String get() = element.name
    val photo: String get() = element.photoUrl
}
