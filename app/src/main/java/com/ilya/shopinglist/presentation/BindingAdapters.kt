package com.ilya.shopinglist.presentation

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ilya.shopinglist.R

@BindingAdapter("errorInputName")
fun bindErrorInputName(til: TextInputLayout, stateError: Boolean) {
    val message = if (stateError) {
        til.context.getString(R.string.error_input_name)
    } else {
        null
    }
    til.error = message
}

@BindingAdapter("errorInputCount")
fun bindErrorInputCount(til: TextInputLayout, stateError: Boolean) {
    val message = if (stateError) {
        til.context.getString(R.string.error_input_count)
    } else {
        null
    }
    til.error = message
}

@BindingAdapter("numberToString")
fun bindNumberToString(editText: TextInputEditText, number: Int) {
    editText.setText(number.toString())
}