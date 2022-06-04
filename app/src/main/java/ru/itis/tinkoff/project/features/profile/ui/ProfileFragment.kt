package ru.itis.tinkoff.project.features.profile.ui

import android.Manifest
import android.app.AlertDialog
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
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
    private val galleryLoadingPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        when {
            it -> {
                galleryGetContent.launch(getString(R.string.image_gallery_uri))
            }
            else -> Toast.makeText(context, getString(R.string.access_to_gallery_forbidden), Toast.LENGTH_SHORT).show()
        }
    }
    private val cameraLoadingPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        when {
            it -> {
                cameraTakePicture.launch()
            }
            else -> Toast.makeText(context, getString(R.string.access_to_camera_forbidden), Toast.LENGTH_SHORT).show()
        }
    }
    private val cameraTakePicture = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
            bitmap:Bitmap? -> bitmap?.let{
                viewBinding.ivAvatar.setImageBitmap(bitmap)
                Toast.makeText(context, getString(R.string.profile_photo_successfully_loaded), Toast.LENGTH_SHORT).show()
            }
    }
    private val galleryGetContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
            uri:Uri? -> uri?.let {
                loadWithCoil(it)
                Toast.makeText(context, getString(R.string.profile_photo_successfully_loaded), Toast.LENGTH_SHORT).show()
            }
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
        val optionsForLoading = arrayOf<CharSequence>(getString(R.string.take_picture),getString(R.string.load_from_device))
        builder
            .setTitle(getString(R.string.change_avatar))
            .setItems(optionsForLoading){_, which->
                if (which == 0) {
                    addAvatarByTakingPhoto()
                }
                if (which == 1) {
                    addAvatarByLoading()
                }
            }
            .setNegativeButton(getString(R.string.cancel)){dialog, _->
                dialog.cancel()
            }
            .show()
        alertDialog= builder.create()
    }

    private fun addAvatarByLoading() {
       galleryLoadingPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun addAvatarByTakingPhoto() {
        cameraLoadingPermission.launch(Manifest.permission.CAMERA)
    }

    private fun loadWithCoil(imageRoute: Uri) {
        with(viewBinding){
            ivAvatar.load(imageRoute){
                crossfade(true)
                placeholder(R.drawable.user_photo_default)
                error(R.drawable.user_photo_default)
            }
        }
    }
}
