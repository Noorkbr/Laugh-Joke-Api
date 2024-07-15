package com.example.laughjoke



import androidx.navigation.fragment.findNavController
import com.example.laughjoke.base.BaseFragment
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