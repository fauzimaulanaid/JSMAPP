package com.fauzimaulana.jsmapp.view.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fauzimaulana.jsmapp.R
import com.fauzimaulana.jsmapp.databinding.ActivityHomeBinding
import com.fauzimaulana.jsmapp.view.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() =  _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_account
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
        val user = auth.currentUser
        if (user == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
    }

    private fun showAlertLogout() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(resources.getString(R.string.alert))
            setMessage(resources.getString(R.string.logout_confirmation))
            setCancelable(false)
            setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                auth.signOut()
                val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            setNegativeButton(resources.getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                showAlertLogout()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}