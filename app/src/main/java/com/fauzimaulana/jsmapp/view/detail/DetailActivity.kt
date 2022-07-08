package com.fauzimaulana.jsmapp.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.fauzimaulana.jsmapp.R
import com.fauzimaulana.jsmapp.core.domain.model.UserModel
import com.fauzimaulana.jsmapp.databinding.ActivityDetailBinding
import com.fauzimaulana.jsmapp.view.addupdate.AddUpdateActivity

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
        setupAction(user)
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

    private fun setupAction(user: UserModel) {
        binding.fabEdit.setOnClickListener {
            val intent = Intent(this, AddUpdateActivity::class.java)
            intent.putExtra(AddUpdateActivity.EXTRA_USER, user)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteStore -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
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