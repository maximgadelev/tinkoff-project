package ru.itis.tinkoff.project.features.profile.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding by viewBinding(ProfileFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        initRv()
    }

    private fun updateProfileData(): Boolean{
        return true
    }

    private fun initRv(){
        with(viewBinding){
            rvOptions.adapter = ProfileOptionsAdapter(ProfileOptionsRepository.options)
        }
    }
}
