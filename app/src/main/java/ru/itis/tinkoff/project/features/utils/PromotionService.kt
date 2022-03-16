package ru.itis.tinkoff.project.features.utils

import ru.itis.tinkoff.project.features.entity.Promotion

class PromotionService {
    var promotions_ = mutableListOf<Promotion>()

    fun getPromotions() {
        promotions_ = mutableListOf(
            Promotion(
                "https://i.pinimg.com/originals/f4/d2/96/f4d2961b652880be432fb9580891ed62.png",
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
    }


}