package ru.itis.tinkoff.project.features.productPage.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ProductPageFragmentBinding

class ProductPageFragment : Fragment(R.layout.product_page_fragment) {
    private val viewBinding by viewBinding(ProductPageFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = PagerAdapter(this)
        viewBinding.pager.adapter = pagerAdapter
        TabLayoutMediator(viewBinding.tabLayout, viewBinding.pager) { tab, position ->
            if (position == 0) {
                tab.text = getString(R.string.description)
            } else {
                tab.text = getString(R.string.charactreristics)
            }
        }.attach()
    }
}
