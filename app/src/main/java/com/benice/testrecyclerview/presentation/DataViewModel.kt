package com.benice.testrecyclerview.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benice.testrecyclerview.domain.Element
import com.benice.testrecyclerview.domain.ElementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: ElementRepository
) : ViewModel() {

    companion object {
        const val CHUNKED_SIZE = 5
    }

    private val _stateLiveData = MutableLiveData<State>()
    val stateLiveData: LiveData<State> = _stateLiveData

    init {
        viewModelScope.launch {
            repository.getElements().collectLatest {
                _stateLiveData.value = merge(it)
            }
        }
    }

    private fun merge(list: List<Element>): State {
        return State(
            totalCount = list.size,
            items = list
                .chunked(5)
                .mapIndexed { index, elements ->
                    val fromIndex = index * CHUNKED_SIZE + 1
                    val toIndex = fromIndex + elements.size - 1
                    val header = SealedItem.HeaderItem("[$fromIndex .. $toIndex]")
                    val items = elements.map { SealedItem.ElementItem(it) }
                    listOf(header) + items
                }
                .flatten()
        )
    }

    class State(
        val totalCount: Int,
        val items: List<SealedItem>
    )

}
