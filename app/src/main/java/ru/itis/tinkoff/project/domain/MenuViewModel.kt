package ru.itis.tinkoff.project.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.itis.tinkoff.project.entity.Promotion

class MenuViewModel(

): ViewModel() {
private val _promotions=MutableLiveData<List<Promotion>>()
    val promotions:LiveData<List<Promotion>> =_promotions


    fun loadPromotions(){

    }

    override fun onCleared() {
        super.onCleared()
    }
}