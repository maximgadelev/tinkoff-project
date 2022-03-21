package ru.itis.tinkoff.project.features.profile.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding by viewBinding(ProfileFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeToRefreshLayout()
        initRv()

        with(viewBinding){

            ivAvatar.setOnClickListener {
                val imageView: ImageView = ivAvatar
                imageView.load("https://trikky.ru/wp-content/blogs.dir/1/files/2018/10/26/cutebunnies17.jpg"){
                    crossfade(true)
                    placeholder(R.drawable.user_photo_default)
                    error(R.drawable.user_photo_default)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    private fun updateProfileData(): Boolean{
        return true
    }

    private fun initSwipeToRefreshLayout(){
        with(viewBinding){
            swipeRefreshLayout.setOnRefreshListener {
                if(updateProfileData()){
                    swipeRefreshLayout.isRefreshing = false
                }
            }
            swipeRefreshLayout.setColorSchemeResources(
                R.color.deep_orange
            )
        }
    }

    private fun initRv(){
        with(viewBinding){
            rvOptions.adapter = ProfileOptionsAdapter(ProfileOptionsRepository.options)
        }
    }
}
