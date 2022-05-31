package ru.itis.tinkoff.project.customViews

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import ru.itis.tinkoff.project.R

class CountButtonProductPage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.count_button_product_page_view, this)
    }

    fun onClick() {
        findViewById<ImageButton>(R.id.imageButton_plusQuantity).setOnClickListener {
            setCount(getCount() + 1)
        }
        findViewById<ImageButton>(R.id.imageButton_minusQuantity).setOnClickListener {
            setCount(getCount() - 1)
        }
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
