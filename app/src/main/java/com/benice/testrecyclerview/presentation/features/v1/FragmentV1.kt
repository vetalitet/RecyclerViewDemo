package com.benice.testrecyclerview.presentation.features.v1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.benice.testrecyclerview.R
import com.benice.testrecyclerview.databinding.FragmentV1Binding
import com.benice.testrecyclerview.presentation.DataViewModel
import com.benice.testrecyclerview.presentation.SealedItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentV1 : Fragment(R.layout.fragment_v1) {

    private val viewModel by viewModels<DataViewModel>()

    private val adapter by lazy {
        RecyclerViewAdapterV1(object : DataItemListener {
            override fun onItemClicked(item: SealedItem) {
                TODO("Not yet implemented")
            }

            override fun onDeleteClicked(item: SealedItem) {
                TODO("Not yet implemented")
            }

            override fun onFavoriteClicked(item: SealedItem) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentV1Binding.bind(view)
        binding.itemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.itemsRecyclerView.adapter = adapter

        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.items)
        }
    }

}
