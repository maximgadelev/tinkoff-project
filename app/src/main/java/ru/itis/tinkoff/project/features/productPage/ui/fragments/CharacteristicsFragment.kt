package ru.itis.tinkoff.project.features.productPage.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.android.synthetic.main.description_fragment.view.*
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.CharacteristicsFragmentBinding
import ru.itis.tinkoff.project.entity.Characteristic
import ru.itis.tinkoff.project.features.productPage.ui.renderer.CharacteristicListRenderer
import ru.itis.tinkoff.project.features.productPage.utils.ProductPageItem

class CharacteristicsFragment : Fragment(R.layout.characteristics_fragment) {
    private val viewBinding by viewBinding(CharacteristicsFragmentBinding::bind)
    private val itemAdapter by lazy {
        RenderAdapterBuilder<ProductPageItem>().renderer(
            ProductPageItem.CharacteristicListItem::class,
            CharacteristicListRenderer()
        ).build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characteristicsMap: List<Characteristic>
        arguments?.takeIf { it.containsKey("characteristic") }?.apply {
            characteristicsMap = getSerializable("characteristic") as List<Characteristic>
            viewBinding.recyclerViewCharacteristic.adapter = itemAdapter
            val list = mapToList(characteristicsMap)
            itemAdapter.differ.submitList(list)
        }
    }

    private fun mapToList(list: List<Characteristic>): List<ProductPageItem> {
        return list.map { i ->
            ProductPageItem.CharacteristicListItem(
                i.type,
                i.characteristic
            )
        }
    }
}
