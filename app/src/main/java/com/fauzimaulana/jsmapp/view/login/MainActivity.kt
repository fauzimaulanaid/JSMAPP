package com.fauzimaulana.jsmapp.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fauzimaulana.jsmapp.R
import com.fauzimaulana.jsmapp.databinding.ActivityMainBinding
import com.fauzimaulana.jsmapp.view.home.HomeActivity
import com.fauzimaulana.jsmapp.view.register.RegisterActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonTest.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}