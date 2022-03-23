package ru.itis.tinkoff.project.features.menu.data

import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Promotion
import ru.itis.tinkoff.project.features.menu.ui.renderer.CarouselRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.ProductCardListRenderer
import java.math.BigDecimal

object TestRepo {
    fun getPromotions() = arrayListOf<Promotion>(
        Promotion(
            "https://kartinkin.net/uploads/posts/2021-07/1625779006_25-kartinkin-com-p-krutie-oranzhevie-oboi-krasivie-27.jpg",
            "8 марта"
        ),
        Promotion(
            "https://w-dog.ru/wallpapers/0/14/332299036323254/siamskie-kotyata-u-cvetov-na-stole.jpg",
            "23 февраля"
        ), Promotion(
            "https://rozetked.me/images/uploads/dwoilp3BVjlE.jpg", "23 февраля"
        ),
        Promotion(
            "https://s0.rbk.ru/v6_top_pics/media/img/7/65/755540270893657.jpg",
            "8 марта"
        ),
        Promotion(
            "https://i05.fotocdn.net/s114/42b1a5899e67aa9f/user_xl/2582268942.jpg",
            "23 февраля"
        ),
        Promotion(
            "https://gorod48.ru/upload/iblock/cbd/cbdddbed4705f37ccb30322e7ffb9da9.JPG",
            "9 мая"
        )
    )
    fun getProducts()= arrayListOf<Product>(
       Product("Фен",
            "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
            BigDecimal(5999)
        ),
       Product("Фен",
            "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
            BigDecimal(5999)
        ),
        Product("Фен",
            "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
            BigDecimal(5999)
        ), Product("Фен",
            "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
            BigDecimal(5999)
        ),
        Product("Фен",
            "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
            BigDecimal(5999)
        ),
       Product("Фен",
            "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
            BigDecimal(5999)
        )
    )
    fun getThreePromotions()= arrayListOf<Promotion>(
        Promotion(
            "https://kartinkin.net/uploads/posts/2021-07/1625779006_25-kartinkin-com-p-krutie-oranzhevie-oboi-krasivie-27.jpg",
            "8 марта"
        ),
        Promotion(
            "https://w-dog.ru/wallpapers/0/14/332299036323254/siamskie-kotyata-u-cvetov-na-stole.jpg",
            "23 февраля"
        ), Promotion(
            "https://rozetked.me/images/uploads/dwoilp3BVjlE.jpg", "23 февраля"
        )
    )
}