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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ProfileFragmentBinding
import ru.itis.tinkoff.project.entity.Profile
import ru.itis.tinkoff.project.features.profile.ui.renderer.ProfileOptionListRenderer
import ru.itis.tinkoff.project.features.profile.ui.utils.OptionItemProvider
import ru.itis.tinkoff.project.features.profile.ui.utils.ProfileOptionItem

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding by viewBinding(ProfileFragmentBinding::bind)
    private val viewModel: UserViewModel by viewModel()
    private val optionItemProvider = OptionItemProvider()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<ProfileOptionItem>()
            .renderer(ProfileOptionItem::class, ProfileOptionListRenderer())
            .build(DifferStrategies.withDiffUtilComparable())
    }
    private lateinit var alertDialog: AlertDialog
    private val REQUEST_CODE_LOAD = 1001
    private val REQUEST_CODE_TAKE_PHOTO = 1002
    val photoLoadingPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            granted ->
        when {
            granted -> {
                gallery.launch("")
            }
            !shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                // доступ к камере запрещен, пользователь поставил галочку Don't ask again.
            }
            else -> {
                // доступ к камере запрещен, пользователь отклонил запрос
            }
        }
    }
    val gallery = registerForActivityResult(ActivityResultContracts.GetContent()) {
        loadWithCoil(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        initUser()
        initSwipeToRefreshLayout()
        viewBinding.outBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_authorizationFragment)
        }
        viewBinding.ivAvatar.setOnClickListener {
            showAlert()
        }
    }

    private fun initUser() {
        viewModel.user
            .onEach {
                fillUserInfo(it)
            }.launchIn(lifecycleScope)
    }

    private fun initRv() {
        with(viewBinding) {
            rvOptions.adapter = itemAdapter
        }
        itemAdapter.differ.submitList(optionItemProvider.getItemList())
    }

    private fun fillUserInfo(profile: Profile) {
        with(viewBinding) {
            tvName.text = profile.firstName
            tvSurname.text = profile.lastName
            ivAvatar.load(profile.profileImg){
                crossfade(true)
                placeholder(R.drawable.user_photo_default)
                error(R.drawable.user_photo_default)
            }
        }
    }

    private fun initSwipeToRefreshLayout() {
        with(viewBinding) {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.onViewCreated()
                swipeRefreshLayout.isRefreshing = false
            }
            swipeRefreshLayout.setColorSchemeResources(
                R.color.deep_orange
            )
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(context)
        val optionsForLoading = arrayOf<CharSequence>("Сделать фото","Загрузить с устройства")
        builder
            .setTitle("Изменить аватар профиля")
            .setItems(optionsForLoading){_, which->
                if (which == 0) {
                    addAvatarByTakingPhoto()
                }
                if (which == 1) {
                    addAvatarByLoading()
                }
            }
            .setNegativeButton("Отмена"){dialog, _->
                dialog.cancel()
            }
            .show()
        alertDialog= builder.create()
    }

    private fun addAvatarByLoading() {
       /* if (checkGalleryPermissions() == true) {
            chooseImageGallery()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),REQUEST_CODE_LOAD)
        }*/
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // доступ к камере запрещен, нужно объяснить зачем нам требуется разрешение
        } else {
            photoLoadingPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun addAvatarByTakingPhoto() {
        if (checkCameraPermissions() == true) {
            takeCameraPhoto()
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA),REQUEST_CODE_TAKE_PHOTO)
        }
    }

    /*private fun checkGalleryPermissions(): Boolean? {
        activity?.apply{
            return ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED&& ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
        return false
    }*/

    private fun checkCameraPermissions(): Boolean? {
        activity?.apply{
            return ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED&& ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOAD->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chooseImageGallery()
                } else {
                    Toast.makeText(context,"Доступ к галерее запрещен", Toast.LENGTH_SHORT).show()
                }
            REQUEST_CODE_TAKE_PHOTO->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takeCameraPhoto()
                } else {
                    Toast.makeText(context,"Доступ к камере запрещен", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun chooseImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent,REQUEST_CODE_LOAD)
    }

    private fun takeCameraPhoto() {
        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(callCameraIntent,REQUEST_CODE_TAKE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_LOAD-> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.data?.let{loadWithCoil(it)}
                }
            }
            REQUEST_CODE_TAKE_PHOTO->
                if (resultCode == Activity.RESULT_OK) {
                    with(viewBinding){
                        ivAvatar.setImageBitmap(data?.extras?.get("data") as Bitmap)
                    }
                }
        }
    }

    private fun loadWithCoil(imageRoute: Uri) {
        with(viewBinding){
            ivAvatar.load(imageRoute){
                crossfade(true)
                placeholder(R.drawable.user_photo_default)
                error(R.drawable.user_photo_default)
            }
        }
        Toast.makeText(context,"Фото профиля успешно изменено", Toast.LENGTH_SHORT).show()
    }
}
