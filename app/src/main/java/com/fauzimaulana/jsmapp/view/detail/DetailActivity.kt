package com.fauzimaulana.jsmapp.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.fauzimaulana.jsmapp.R
import com.fauzimaulana.jsmapp.core.domain.model.UserModel
import com.fauzimaulana.jsmapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val user = intent.getParcelableExtra<UserModel>(EXTRA_DATA)
        showDetailUser(user!!)
    }

    private fun showDetailUser(user: UserModel) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load(user.avatar)
                .into(avatar)
            textViewEmailBody.text = user.email
            textViewFirstNameBody.text = user.firstName
            textViewLastNameBody.text = user.lastName
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}