package com.example.laughjoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.laughjoke.databinding.FragmentJokesBinding
import com.example.laughjoke.db.JokesViewModel


class JokesFragment : Fragment() {

    private lateinit var binding: FragmentJokesBinding
    private val viewModel by viewModels<JokesViewModel>()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_jokes, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val categoriesList = arrayOf("Any", "Programming", "Miscellaneous", "Dark", "Pun", "Spooky", "Christmas")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoriesList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter

        setListener()
        allObserver()

        // Initialize NavController
        navController = findNavController()
        val bottomNavigation = binding.bottomNavigation
        bottomNavigation.setupWithNavController(navController)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.jokesFragment -> {
                    navController.navigate(R.id.jokesFragment)
                    true
                }
                R.id.favouritesFragment -> {
                    navController.navigate(R.id.favouritesFragment)
                    true
                }
                else -> false
            }
        }

        return binding.root
    }

    private fun setListener() {
        binding.btnNext.setOnClickListener {
            val selectedCategory = binding.spinnerCategory.selectedItem.toString()
            viewModel.getRandomJokes(selectedCategory)
        }
        binding.btnFavorite.setOnClickListener {
            viewModel.saveJokes()
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_24)
        }
    }

    private fun allObserver() {
        viewModel._responseJokesData.observe(viewLifecycleOwner) { data ->
            data?.let {
                binding.tvGenerateJokes.visibility = View.GONE
                if (data.joke == null) {
                    viewModel.jokeSetup.postValue(data.setup ?: "")
                    viewModel.delivery.postValue(data.delivery ?: "")
                } else {
                    viewModel.jokeSetup.postValue("")
                    viewModel.delivery.postValue(data.joke)
                }
                viewModel.categories.postValue(data.category ?: "")
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite_border_24)
            }
        }
    }
}