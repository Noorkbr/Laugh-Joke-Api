package com.example.laughjoke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fitglow.base.BaseFragment
import com.example.laughjoke.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override fun setListener() {
        binding.getstartedbtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_jokesFragment)

        }
    }

    override fun allObserver() {

    }

}