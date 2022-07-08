package com.fauzimaulana.jsmapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fauzimaulana.jsmapp.R
import com.fauzimaulana.jsmapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}