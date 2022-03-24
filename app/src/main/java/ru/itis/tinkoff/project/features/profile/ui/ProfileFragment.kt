package ru.itis.tinkoff.project.features.profile.ui

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ProfileFragmentBinding
import ru.itis.tinkoff.project.di.DIContainer
import ru.itis.tinkoff.project.entity.User
import ru.itis.tinkoff.project.features.profile.ProfileViewModelFactory
import ru.itis.tinkoff.project.features.profile.data.ProfileOptionsRepository
import ru.itis.tinkoff.project.features.profile.viewModel.UserViewModel

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding by viewBinding(ProfileFragmentBinding::bind)
    private lateinit var alertDialog: AlertDialog
    private lateinit var viewModel: UserViewModel
    private var userId: Int = 0
    private val REQUEST_CODE_LOAD = 1001
    private val REQUEST_CODE_TAKE_PHOTO = 1002
    private var testK: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeToRefreshLayout()
        initFactory()
        initObservers()
        initRv()
        initUser(userId)
        with(viewBinding) {
            ivAvatar.setOnClickListener {
                showAlert()
            }
        }
    }

    private fun initUser(id : Int) {
        lifecycleScope.launch {
            viewModel.getUser(id)
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(context)
        val optionsForLoading = arrayOf<CharSequence>("Сделать фото", "Загрузить с устройства")
        builder
            .setTitle("Изменить аватар профиля")
            .setItems(optionsForLoading) { _, which ->
                if (which == 0) {
                    addAvatarByTakingPhoto()
                }
                if (which == 1) {
                    addAvatarByLoading()
                }
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            .show()
        alertDialog = builder.create()
    }

    private fun addAvatarByLoading() {
        if (checkGalleryPermissions() == true) {
            chooseImageGallery()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_LOAD)
        }
    }

    private fun addAvatarByTakingPhoto(){
        if (checkCameraPermissions() == true) {
            takeCameraPhoto()
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_TAKE_PHOTO)
        }
    }

    private fun checkGalleryPermissions(): Boolean? {
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
        return false
    }

    private fun checkCameraPermissions(): Boolean? {
        activity?.apply {
            return (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED)
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOAD -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chooseImageGallery()
            } else {
                Toast.makeText(context, "Доступ к галерее запрещен", Toast.LENGTH_SHORT).show()
            }
            REQUEST_CODE_TAKE_PHOTO -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               takeCameraPhoto()
            } else {
                Toast.makeText(context, "Доступ к камере запрещен", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun chooseImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_LOAD)
    }

    private fun takeCameraPhoto(){
        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(callCameraIntent, REQUEST_CODE_TAKE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            REQUEST_CODE_LOAD -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.data?.let { loadWithCoil(it) }
                }
            }
            REQUEST_CODE_TAKE_PHOTO -> {
                if (resultCode == Activity.RESULT_OK) {
                    with(viewBinding){
                        ivAvatar.setImageBitmap(data?.extras?.get("data") as Bitmap)
                    }
                }
            }
        }
    }

    private fun loadWithCoil(imageRoute: Uri) {
        with(viewBinding) {
            ivAvatar.load(imageRoute) {
                crossfade(true)
                placeholder(R.drawable.user_photo_default)
                error(R.drawable.user_photo_default)
            }
        }
        Toast.makeText(context, "Фото профиля успешно изменено", Toast.LENGTH_SHORT).show()
    }

    private fun initSwipeToRefreshLayout() {
        with(viewBinding) {
            swipeRefreshLayout.setOnRefreshListener {
                testK+=1
                if(testK == 2){
                    userId = 1
                }
                initUser(userId)
                initObservers()
                swipeRefreshLayout.isRefreshing = false
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

    private fun initObservers(){
        viewModel.user.observe(viewLifecycleOwner){ it ->
            it.fold(
                onSuccess =
                {
                    initUserInfo(it) },
                onFailure = {
                    Toast.makeText(context, "Невозможно загрузить данные", Toast.LENGTH_SHORT).show()
                })
        }
    }

    private fun initUserInfo(user : User){
        with(viewBinding){
            tvName.text = user.name
            tvSurname.text = user.surname
        }
    }

    private fun initFactory() {
        val factory = ProfileViewModelFactory(DIContainer)
        viewModel = ViewModelProvider(
            this,
            factory
        )[UserViewModel::class.java]
    }
}
