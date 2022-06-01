package ru.itis.tinkoff.project.features.reviewsPage.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.android.synthetic.main.reviews_page_fragment.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ReviewsPageFragmentBinding
import ru.itis.tinkoff.project.features.ExceptionDialogFragment
import ru.itis.tinkoff.project.features.reviewsPage.ui.renderer.ReviewCardListRenderer
import ru.itis.tinkoff.project.features.reviewsPage.utils.ReviewsItem

class ReviewsFragment : Fragment(R.layout.reviews_page_fragment) {
    private val viewBinding by viewBinding(ReviewsPageFragmentBinding::bind)
    private val viewModel: ReviewsFragmentViewModel by viewModel()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<ReviewsItem>()
            .renderer(
                ReviewsItem.ReviewsCardListItem::class,
                ReviewCardListRenderer()
            ).build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect {
                showDialog()
            }
        }
            val id = arguments?.getInt("id")
            val rating = arguments?.getDouble("rating")
            if (rating != null) {
                viewBinding.ratingBar.rating = rating.toFloat()
                Log.e("123",rating.toString())
                Log.e("123",id.toString())
            }
            id?.let { viewModel.onViewCreated(it) }
            createReviewsList()
            createMainInformation()
    }

    private fun createReviewsList() {
        with(recyclerView) {
            setHasFixedSize(true)
            adapter = itemAdapter
        }
    }

    private fun createMainInformation() {
        viewModel.item.onEach {
            itemAdapter.differ.submitList(it)
        }.launchIn(lifecycleScope)
        viewModel.reviewsListSize.onEach {
            viewBinding.textViewReviewsCount.text = it.toString() + "отзыв(а)"
        }.launchIn(lifecycleScope)
    }

    private fun showDialog() {
        val dialog = ExceptionDialogFragment()
        dialog.show(parentFragmentManager, "dialog")
    }
}
