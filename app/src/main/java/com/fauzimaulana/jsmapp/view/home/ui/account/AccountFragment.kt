package com.fauzimaulana.jsmapp.view.home.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.fauzimaulana.jsmapp.R
import com.fauzimaulana.jsmapp.databinding.FragmentAccountBinding
import com.fauzimaulana.jsmapp.view.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        setupView()
        setupAction()
    }

    private fun setupView() {
        val user = auth.currentUser
        val email = user?.email

        binding.emailTextView.text = email
    }

    private fun setupAction() {
        binding.logoutButton.setOnClickListener {
            showAlertLogout()
        }
    }

    private fun showAlertLogout() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        with(alertDialogBuilder) {
            setTitle(resources.getString(R.string.alert))
            setMessage(resources.getString(R.string.logout_confirmation))
            setCancelable(false)
            setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                auth.signOut()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            setNegativeButton(resources.getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}