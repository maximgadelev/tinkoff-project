package ru.itis.tinkoff.project.features.menu.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.android.synthetic.main.menu_fragment.*
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.haroncode.aquarius.core.clicker.DefaultClicker
import ru.haroncode.aquarius.core.decorators.SpaceRuleItemDecoration
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.MenuFragmentBinding
import ru.itis.tinkoff.project.features.menu.ui.renderer.*
import ru.itis.tinkoff.project.features.utils.*

class MenuFragment : Fragment(R.layout.menu_fragment) {
    private val viewBinding by viewBinding(MenuFragmentBinding::bind)
    private val itemAdapter by lazy {
        RenderAdapterBuilder<Item>()
            .renderer(Item.SnapItem::class, SnapRenderer("Square_200"))
            .renderer(Item.Title::class, TitleRenderer())
            .renderer(Item.ProductListItem::class, ProductCardListRenderer())
            .renderer(Item.CarouselItem::class, CarouselRenderer("Long"),DefaultClicker(::onClickButton))
            .renderer(Item.ThreePromotionsCardItem::class,ThreePromotionsCardRenderer("Square_100"))
            .build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleDecoration = SpaceRuleItemDecoration.Builder<Item>()
            .addRule {
                paddingVertical(30)
                with {
                    viewType(Item.Title::class)
                }
            }.create()
        val threePromotionsCardDecoration = SpaceRuleItemDecoration.Builder<Item>()
            .addRule {
                paddingHorizontal(80)
                paddingVertical(120)
                with {
                    viewType(Item.ThreePromotionsCardItem::class)
                }
            }.create()
        val carouselDecoration = SpaceRuleItemDecoration.Builder<Item>()
            .addRule {
                paddingVertical(-80)
                with {
                    viewType(Item.CarouselItem::class)
                }
            }.create()


        with(recyclerView) {
            adapter = itemAdapter
            setHasFixedSize(true)
            addItemDecoration(titleDecoration)
            addItemDecoration(threePromotionsCardDecoration)
            addItemDecoration(carouselDecoration)
            itemAdapter.differ.submitList(ItemFactory.staticItems(requireContext()))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                }
            })
        }
    }

    private fun onClickButton(renderContract: CarouselRenderer.RenderContract) {
    }

    private fun loadMore() {
        itemAdapter.differ.submitList(itemAdapter.differ.currentList + ItemFactory.loadMore())
    }
}
