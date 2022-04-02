package ru.itis.tinkoff.project.features.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.android.synthetic.main.menu_fragment.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.haroncode.aquarius.core.decorators.SpaceRuleItemDecoration
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.MenuFragmentBinding
import ru.itis.tinkoff.project.features.common.ProductCardItemType
import ru.itis.tinkoff.project.features.main.ui.renderer.CarouselRenderer
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.common.utils.dp
import ru.itis.tinkoff.project.features.main.ui.renderer.SnapRenderer
import ru.itis.tinkoff.project.features.main.ui.renderer.TitleRenderer
import ru.itis.tinkoff.project.features.main.utils.MenuItem
import ru.itis.tinkoff.project.features.main.utils.PromotionItemSize

@SuppressWarnings("MagicNumber")
class MainFragment : Fragment(R.layout.menu_fragment) {
    private val viewBinding by viewBinding(MenuFragmentBinding::bind)
    private val viewModel: MainViewModel by viewModel<MainViewModel>()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<MenuItem>()
            .renderer(
                MenuItem.SnapMenuItem::class,
                SnapRenderer(PromotionItemSize.SQUARE_200, true)
            )
            .renderer(MenuItem.Title::class, TitleRenderer())
            .renderer(MenuItem.ProductListMenuItem::class, ProductCardListRenderer(ProductCardItemType.MAIN))
            .renderer(
                MenuItem.CarouselMenuItem::class,
                CarouselRenderer(PromotionItemSize.LONG)
            )
            .renderer(
                MenuItem.ThreePromotionsCardMenuItem::class,
                SnapRenderer(PromotionItemSize.SQUARE_100, false)
            )
            .build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createMainList()
        viewModel.item.onEach {
            itemAdapter.differ.submitList(it)
        }
            .launchIn(lifecycleScope)
    }

    private fun createMainList() {
        val titleDecoration = SpaceRuleItemDecoration.Builder<MenuItem>()
            .addRule {
                paddingVertical(10.dp)
                with {
                    viewType(MenuItem.Title::class)
                }
            }.create()
        val threePromotionsCardDecoration = SpaceRuleItemDecoration.Builder<MenuItem>()
            .addRule {
                paddingHorizontal(30.dp)
                paddingVertical(40.dp)
                with {
                    viewType(MenuItem.ThreePromotionsCardMenuItem::class)
                }
            }.create()
        val carouselDecoration = SpaceRuleItemDecoration.Builder<MenuItem>()
            .addRule {
                paddingVertical(-30.dp)
                with {
                    viewType(MenuItem.CarouselMenuItem::class)
                }
            }.create()

        with(recyclerView) {
            setHasFixedSize(true)
            adapter = itemAdapter
            addItemDecoration(titleDecoration)
            addItemDecoration(threePromotionsCardDecoration)
            addItemDecoration(carouselDecoration)
        }
    }

}
