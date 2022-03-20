package ru.itis.tinkoff.project.features.utils
import ru.itis.tinkoff.project.features.menu.ui.renderer.CarouselRenderer.Promotion
import android.content.Context
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.features.menu.ui.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.SnapRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.ThreePromotionsCardRenderer

object  ItemFactory {

     fun staticItems(context: Context): List<Item> {
         val resultList = mutableListOf<Item>()
         resultList+= snap()
         resultList+= Item.Title(R.string.title)
         resultList+= generateProductCards()
         resultList += carousel()
         resultList += generatePromotionsThreeElements()
         return resultList
     }

    private fun carousel(): Item.CarouselItem {
        return Item.CarouselItem(
            promotions = listOf(
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
        )
    }
    private fun snap(): Item.SnapItem {
        return Item.SnapItem(
            promotions = listOf(
                SnapRenderer.Promotion(
                    "https://kartinkin.net/uploads/posts/2021-07/1625779006_25-kartinkin-com-p-krutie-oranzhevie-oboi-krasivie-27.jpg",
                    "8 марта"
                ),
                SnapRenderer.Promotion(
                    "https://kartinkin.net/uploads/posts/2021-07/1625779006_25-kartinkin-com-p-krutie-oranzhevie-oboi-krasivie-27.jpg",
                    "23 февраля"
                ), SnapRenderer.Promotion(
                    "https://kartinkin.net/uploads/posts/2021-07/1625779006_25-kartinkin-com-p-krutie-oranzhevie-oboi-krasivie-27.jpg", "23 февраля"
                ),
                SnapRenderer.Promotion(
                    "https://kartinkin.net/uploads/posts/2021-07/1625779006_25-kartinkin-com-p-krutie-oranzhevie-oboi-krasivie-27.jpg",
                    "8 марта"
                ),
                SnapRenderer.Promotion(
                    "https://i05.fotocdn.net/s114/42b1a5899e67aa9f/user_xl/2582268942.jpg",
                    "23 февраля"
                ),
                SnapRenderer.Promotion(
                    "https://gorod48.ru/upload/iblock/cbd/cbdddbed4705f37ccb30322e7ffb9da9.JPG",
                    "9 мая"
                )
            )
        )
    }
    private fun generateProductCards():Item.ProductListItem{
        return Item.ProductListItem(
            products = listOf(
                ProductCardListRenderer.Product("Фен",
                    "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
                    5999),
                ProductCardListRenderer.Product("Фен",
                    "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
                    5999),
                ProductCardListRenderer.Product("Фен",
                    "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
                    5999), ProductCardListRenderer.Product("Фен",
                    "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
                    5999),
                ProductCardListRenderer.Product("Фен",
                    "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
                    5999),
                ProductCardListRenderer.Product("Фен",
                    "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg",
                    5999)
            )
        )
    }
    private fun generatePromotionsThreeElements():Item.ThreePromotionsCardItem{
        return Item.ThreePromotionsCardItem(
            promotions = listOf(
                ThreePromotionsCardRenderer.Promotion(
                    "https://kartinkin.net/uploads/posts/2021-07/1625779006_25-kartinkin-com-p-krutie-oranzhevie-oboi-krasivie-27.jpg", "23 февраля"
                ),
                ThreePromotionsCardRenderer.Promotion(
                    "https://kartinkin.net/uploads/posts/2021-07/1625779006_25-kartinkin-com-p-krutie-oranzhevie-oboi-krasivie-27.jpg", "23 февраля"
                ),
                ThreePromotionsCardRenderer.Promotion(
                    "https://kartinkin.net/uploads/posts/2021-07/1625779006_25-kartinkin-com-p-krutie-oranzhevie-oboi-krasivie-27.jpg", "23 февраля"
                )
            )
        )
    }
    fun loadMore(): List<Item> {
        val resultList = mutableListOf<Item>()
        resultList += carousel()
        resultList+= Item.Title(R.string.title)
        resultList+= generateProductCards()
        resultList+= snap()
        return resultList
    }
}