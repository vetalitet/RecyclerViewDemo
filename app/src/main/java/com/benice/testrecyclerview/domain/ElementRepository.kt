package com.benice.testrecyclerview.domain

import kotlinx.coroutines.flow.Flow

interface ElementRepository {

    fun getElements(): Flow<List<Element>>

}
