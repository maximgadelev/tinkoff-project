package ru.itis.tinkoff.project.data.mapper

import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Promotion
import ru.itis.tinkoff.project.features.menu.ui.renderer.CarouselRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.SnapRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.ThreePromotionsCardRenderer

class EntityMapper {
    fun mapPromotionToSnapRenderItem(promotions: List<Promotion>): List<SnapRenderer.Promotion> {
        val newList = mutableListOf<SnapRenderer.Promotion>()
        for (i in promotions) {
            newList.add(SnapRenderer.Promotion(i.name, i.image))
        }
        return newList
    }

    fun mapPromotionToCarouselRenderItem(promotions: List<Promotion>): List<CarouselRenderer.Promotion> {
        val newList = mutableListOf<CarouselRenderer.Promotion>()
        for (i in promotions) {
            newList.add(CarouselRenderer.Promotion(i.name, i.image))
        }
        return newList
    }

    fun mapPromotionToThreePromotionsItem(promotions: List<Promotion>): List<ThreePromotionsCardRenderer.Promotion> {
    val newList = mutableListOf<ThreePromotionsCardRenderer.Promotion>()
    for (i in promotions)
    {
        newList.add(ThreePromotionsCardRenderer.Promotion(i.name, i.image))
    }
    return newList
}
    fun mapProductToProductList(products: List<Product>):List<ProductCardListRenderer.Product> {
        val newList = mutableListOf<ProductCardListRenderer.Product>()
        for(i in products) {
        newList.add(ProductCardListRenderer.Product(i.name,i.image,i.price))
        }
      return newList
    }

}