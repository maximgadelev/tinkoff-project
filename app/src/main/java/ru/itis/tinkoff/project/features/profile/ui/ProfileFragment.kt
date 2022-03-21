package ru.itis.tinkoff.project.features.profile.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding by viewBinding(ProfileFragmentBinding::bind)
    private val REQUEST_CODE = 1001

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeToRefreshLayout()
        initRv()

        with(viewBinding) {
            ivAvatar.setOnClickListener {
                addAvatar()
            }
        }
    }

    private fun addAvatar() {
        if (checkPermissions() == true) {
            chooseImageGallery()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
        }
    }

    private fun checkPermissions(): Boolean? {
        activity?.apply {
            return (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED)
        }
        return null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            chooseImageGallery()
        } else {
            Toast.makeText(context, "Доступ к фотографиям запрещен", Toast.LENGTH_SHORT).show()
        }
    }

    private fun chooseImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    private fun loadWithCoil(imageRoute: Uri) {
        with(viewBinding) {
            ivAvatar.load(imageRoute) {
                crossfade(true)
                placeholder(R.drawable.user_photo_default)
                error(R.drawable.user_photo_default)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { loadWithCoil(it) }
        }
    }

    private fun updateProfileData(): Boolean {
        return true
    }

    private fun initSwipeToRefreshLayout() {
        with(viewBinding) {
            swipeRefreshLayout.setOnRefreshListener {
                if (updateProfileData()) {
                    swipeRefreshLayout.isRefreshing = false
                }
            }
            swipeRefreshLayout.setColorSchemeResources(
                R.color.deep_orange
            )
        }
    }

    private fun initRv() {
        with(viewBinding) {
            rvOptions.adapter = ProfileOptionsAdapter(ProfileOptionsRepository.options)
        }
    }
}
