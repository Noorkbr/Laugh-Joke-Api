package com.example.laughjoke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.laughjoke.adapters.JokesAdapter
import com.example.laughjoke.base.BaseFragment
import com.example.laughjoke.databinding.FragmentFavouritesBinding
import com.example.laughjoke.db.JokesData


class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate), JokesAdapter.HandleUserClick {

    private val viewModel by viewModels<FavouriteViewModel>()
    private lateinit var adapter: JokesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setListener()
    }

    private fun setupToolbar() {
        binding.prev1.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun setListener() {
        viewModel.getAllJokes.observe(viewLifecycleOwner) { list ->
            adapter = JokesAdapter(list, this)
            binding.recyclerViewJokes.adapter = adapter
        }
    }

    override fun onDeleteClick(jokesData: JokesData) {
        viewModel.deleteJokes(jokesData)
    }

    override fun allObserver() {
        // Implement any additional observers here
    }
}