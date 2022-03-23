package ru.itis.tinkoff.project.features.menu.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.android.synthetic.main.menu_fragment.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.haroncode.aquarius.core.clicker.DefaultClicker
import ru.haroncode.aquarius.core.decorators.SpaceRuleItemDecoration
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.MenuFragmentBinding
import ru.itis.tinkoff.project.di.DIContainer
import ru.itis.tinkoff.project.features.menu.MenuViewModelFactory
import ru.itis.tinkoff.project.features.menu.ui.renderer.*
import ru.itis.tinkoff.project.utils.*

class MenuFragment : Fragment(R.layout.menu_fragment) {
    private val viewBinding by viewBinding(MenuFragmentBinding::bind)
    private lateinit var viewModel: MenuViewModel
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
        initFactory()
        viewModel.getAllItem()
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
            setHasFixedSize(true)
            adapter = itemAdapter
            addItemDecoration(titleDecoration)
            addItemDecoration(threePromotionsCardDecoration)
            addItemDecoration(carouselDecoration)
            viewModel.item.onEach {
                itemAdapter.differ.submitList(it)
            }
                .launchIn(lifecycleScope)
//            addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                }
//            })
        }
    }

    private fun onClickButton(renderContract: CarouselRenderer.RenderContract) {
    }

    private fun initFactory() {
        val factory = MenuViewModelFactory(DIContainer)
        viewModel = ViewModelProvider(
            this,
            factory
        )[MenuViewModel::class.java]
    }

}
