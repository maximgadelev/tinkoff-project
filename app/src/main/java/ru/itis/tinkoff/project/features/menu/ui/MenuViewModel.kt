package ru.itis.tinkoff.project.features.menu.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*
import ru.itis.tinkoff.project.features.menu.data.MenuRepository
import ru.itis.tinkoff.project.features.menu.ui.renderer.CarouselRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.SnapRenderer
import ru.itis.tinkoff.project.utils.Item

class MenuViewModel(
private val menuRepository: MenuRepository
): ViewModel(
) {
private val _snapPromotions= MutableStateFlow<List<SnapRenderer.Promotion>>(emptyList())
    val snapPromotions =_snapPromotions.asStateFlow()

private val _carouselPromotions= MutableStateFlow<List<CarouselRenderer.Promotion>>(emptyList())
    val carouselPromotions = _carouselPromotions.asStateFlow()


    private val _item = MutableStateFlow<List<Item>>(emptyList())
     val item = _item.asStateFlow()

fun getSnapRendererPromotionsItem(){
   _snapPromotions.value=menuRepository.getPromotionsSnapRenderItem()
}
    fun getCarouselPromotionsItem(){
        _carouselPromotions.value=menuRepository.getPromotionsCarouselRenderItem()
    }
    fun getAllItem(){
        _item.value=menuRepository.getAllItems()
    }

}