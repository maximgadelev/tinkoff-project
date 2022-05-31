package ru.itis.tinkoff.project.customViews

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import ru.itis.tinkoff.project.R

class CountButtonFavoritesCart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.count_button_product_card_favorites, this)
    }

    fun setCount(count: Int) {
        if (count <= 0) {
            findViewById<TextView>(R.id.textViewQuantity).text = "1"
        } else {
            findViewById<TextView>(R.id.textViewQuantity).text = count.toString()
        }
    }

    fun getCount(): Int {
        return findViewById<TextView>(R.id.textViewQuantity).text.toString().toInt()
    }
}
