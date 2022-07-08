package com.fauzimaulana.jsmapp.view.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzimaulana.jsmapp.core.vo.Resource
import com.fauzimaulana.jsmapp.databinding.FragmentHomeBinding
import com.fauzimaulana.jsmapp.view.home.UserAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userAdapter = UserAdapter()
        showUserList()

    }

    private fun showUserList() {
        homeViewModel.getAllUsers().observe(viewLifecycleOwner) { users ->
            when (users) {
                is Resource.Loading -> binding.viewLoading.root.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.viewLoading.root.visibility = View.GONE
                    userAdapter.submitList(users.data)
                }
                is Resource.Error -> {
                    binding.viewLoading.root.visibility = View.GONE
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}