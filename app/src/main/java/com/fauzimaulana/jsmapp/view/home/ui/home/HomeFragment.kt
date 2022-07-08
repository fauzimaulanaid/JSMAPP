package com.fauzimaulana.jsmapp.view.home.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzimaulana.jsmapp.R
import com.fauzimaulana.jsmapp.core.vo.Resource
import com.fauzimaulana.jsmapp.databinding.FragmentHomeBinding
import com.fauzimaulana.jsmapp.view.addupdate.AddUpdateActivity
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

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        userAdapter = UserAdapter()
        showUserList()

    }

    private fun showUserList() {
        homeViewModel.getAllUsers().observe(viewLifecycleOwner) { users ->
            when (users) {
                is Resource.Loading -> {
                    binding.viewLoading.root.visibility = View.VISIBLE
                    binding.rvUser.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.viewLoading.root.visibility = View.GONE
                    binding.rvUser.visibility = View.VISIBLE
                    userAdapter.submitList(users.data)
                }
                is Resource.Error -> {
                    binding.viewLoading.root.visibility = View.GONE
                    Toast.makeText(context, "Error, Something Went Wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }


    @Suppress("DEPRECATION")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                val intent = Intent(requireContext(), AddUpdateActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}