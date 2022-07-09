package com.fauzimaulana.jsmapp.view.addupdate

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.fauzimaulana.jsmapp.R
import com.fauzimaulana.jsmapp.core.domain.model.UserModel
import com.fauzimaulana.jsmapp.core.utils.CheckNetworkConnection
import com.fauzimaulana.jsmapp.core.utils.Utils
import com.fauzimaulana.jsmapp.databinding.ActivityAddUpdateBinding
import com.fauzimaulana.jsmapp.view.home.HomeActivity
import java.io.File

class AddUpdateActivity : AppCompatActivity() {

    private var _binding: ActivityAddUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var currentPhotoPath: String
    private var getFile: File? = null

    private var isEdit = false
    private var user: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (!allPermissionGranted()) {
            ActivityCompat.requestPermissions(this, REQUIRES_PERMISSION, REQUEST_CODE_PERMISSION)
        }

        user = intent.getParcelableExtra(EXTRA_USER)
        if (user != null) {
            isEdit = true
        } else {
            user = null
        }

        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = resources.getString(R.string.edit)
            btnTitle = resources.getString(R.string.update)
            if (user != null) {
                user?.let { user ->
                    Glide.with(this)
                        .load(user.avatar)
                        .into(binding.previewAvatar)
                    binding.emailEditText.setText(user.email)
                    binding.firstNameEditText.setText(user.firstName)
                    binding.lastNameEditText.setText(user.lastName)
                }
            }
        } else {
            actionBarTitle = resources.getString(R.string.add)
            btnTitle = resources.getString(R.string.save)
        }

        supportActionBar?.title = actionBarTitle

        binding.buttonSubmit.text = btnTitle

        binding.buttonCamera.setOnClickListener {
            startTakePhoto()
        }
        binding.buttonGallery.setOnClickListener {
            startGallery()
        }
        binding.buttonSubmit.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            when {
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = resources.getString(R.string.email_empty)
                }
                firstName.isEmpty() -> {
                    binding.firstNameEditTextLayout.error = resources.getString(R.string.first_name_empty)
                }
                lastName.isEmpty() -> {
                    binding.lastNameEditTextLayout.error = resources.getString(R.string.last_name_empty)
                }
                else -> {
                    val isConnected: Boolean = CheckNetworkConnection().networkCheck(this)
                    if (isConnected) {
                        if (isEdit) {
                            supportActionBar?.setDisplayHomeAsUpEnabled(false)
                            binding.contentAddUpdate.visibility = View.GONE
                            binding.viewSuccess.root.visibility = View.VISIBLE
                            val screenTime = 3000L
                            Handler(mainLooper).postDelayed({
                                val intent = Intent(this@AddUpdateActivity, HomeActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                finish()
                            }, screenTime)
                        } else {
                            if (getFile != null) {
                                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                                binding.contentAddUpdate.visibility = View.GONE
                                binding.viewSuccess.root.visibility = View.VISIBLE
                                val screenTime = 3000L
                                Handler(mainLooper).postDelayed({
                                    val intent = Intent(this@AddUpdateActivity, HomeActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(intent)
                                    finish()
                                }, screenTime)
                            } else {
                                Toast.makeText(this, "Please choose image first", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Utils.showAlertNoInternet(this)
                    }
                }
            }
        }
    }

    private fun allPermissionGranted() = REQUIRES_PERMISSION.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (!allPermissionGranted()) {
                Toast.makeText(this, "Did not get permission to access camera", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)

        Utils.createCustomTemptFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@AddUpdateActivity,
                "com.fauzimaulana.jsmapp",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            val result = BitmapFactory.decodeFile(myFile.path)
            getFile = myFile
            binding.previewAvatar.setImageBitmap(result)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFIle = Utils.uriToFile(selectedImg, this@AddUpdateActivity)
            getFile = myFIle
            binding.previewAvatar.setImageURI(selectedImg)
        }
    }

    private fun showAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(resources.getString(R.string.changed_lost))
            setMessage(resources.getString(R.string.confirmation))
            setCancelable(false)
            setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                finish()
            }
            setNegativeButton(resources.getString(R.string.no)) { dialog, _ -> dialog.cancel()}
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onBackPressed() {
        showAlertDialog()
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
        private val REQUIRES_PERMISSION = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSION = 10
        private const val STORE_CHILD = "store"
        const val EXTRA_USER = "extra_user"
    }
}