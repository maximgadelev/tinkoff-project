package ru.itis.tinkoff.project.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.itis.tinkoff.project.features.entity.Promotion
import ru.itis.tinkoff.project.features.utils.PromotionService

class MenuViewModel(
private val promotionService: PromotionService
): ViewModel() {
private val _promotions=MutableLiveData<List<Promotion>>()
    val promotions:LiveData<List<Promotion>> =_promotions


    fun loadPromotions(){

    }

    override fun onCleared() {
        super.onCleared()
    }
}