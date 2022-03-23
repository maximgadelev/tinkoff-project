package ru.itis.tinkoff.project.features.menu.data

import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.data.mapper.EntityMapper
import ru.itis.tinkoff.project.features.menu.ui.renderer.CarouselRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.SnapRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.ThreePromotionsCardRenderer
import ru.itis.tinkoff.project.utils.Item


class MenuRepository(
    private val mapper: EntityMapper,
    private val testRepo:TestRepo
) {
    fun getPromotionsSnapRenderItem(): List<SnapRenderer.Promotion> {
     return mapper.mapPromotionToSnapRenderItem(testRepo.getPromotions())
    }
  fun getPromotionsCarouselRenderItem():List<CarouselRenderer.Promotion>{
      return mapper.mapPromotionToCarouselRenderItem(testRepo.getPromotions())
  }
    fun getPromotionsThreeCardsRenderItem():List<ThreePromotionsCardRenderer.Promotion>{
       return mapper.mapPromotionToThreePromotionsItem(testRepo.getThreePromotions())
    }
    fun getProducts():List<ProductCardListRenderer.Product>{
        return mapper.mapProductToProductList(testRepo.getProducts())
    }
    fun getAllItems():List<Item>{
        val resultList = mutableListOf<Item>()
        resultList+=Item.SnapItem(getPromotionsSnapRenderItem())
        resultList+=Item.Title(R.string.title)
        resultList+=Item.ProductListItem(getProducts())
        resultList+=Item.CarouselItem(getPromotionsCarouselRenderItem())
        resultList+=Item.ThreePromotionsCardItem(getPromotionsThreeCardsRenderItem())
        return resultList
    }
}