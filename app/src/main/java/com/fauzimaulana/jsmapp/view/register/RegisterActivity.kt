package com.fauzimaulana.jsmapp.view.register

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.fauzimaulana.jsmapp.R
import com.fauzimaulana.jsmapp.core.utils.CheckNetworkConnection
import com.fauzimaulana.jsmapp.core.utils.Utils
import com.fauzimaulana.jsmapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.registerButton.setOnClickListener {
            val isConnected: Boolean = CheckNetworkConnection().networkCheck(this)
            if (isConnected) {
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()
                when {
                    email.isEmpty() -> {
                        binding.emailEditTextLayout.error = resources.getString(R.string.email_error_message)
                    }
                    password.isEmpty() -> {
                        binding.passwordEditTextLayout.error = resources.getString(R.string.password_message)
                    }
                    password.length < 6 || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                        Toast.makeText(this, resources.getString(R.string.registration_failed), Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                    }
                }


            } else {
                Utils.showAlertNoInternet(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}