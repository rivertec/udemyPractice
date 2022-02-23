package com.liam.udemypractice.ui.common

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.liam.udemypractice.R
import java.text.DecimalFormat
import kotlin.math.roundToInt

@BindingAdapter("priceAmount")
fun applyPriceFormat(view: TextView, price: Int) {
    val decimalFormat = DecimalFormat("#,###")
    view.text = view.context.getString(R.string.unit_discount_currency, decimalFormat.format(price))
}

@BindingAdapter("priceAmount", "discountRate")
fun applyPriceDiscountRate(view: TextView, price: Int, discountRate: Int) {
    val discountedPrice = (price - (price * discountRate) / 100.0).roundToInt()
    applyPriceFormat(view, discountedPrice)
}

@BindingAdapter("priceAmount", "strikeThrough")
fun applyPriceAndStrikeStyle(view: TextView, price: Int, strikeThrough: Boolean) {
    applyPriceFormat(view, price)
    if (strikeThrough) {
        view.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }
}