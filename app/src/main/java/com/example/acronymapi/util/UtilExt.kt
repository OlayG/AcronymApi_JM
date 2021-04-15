package com.example.acronymapi.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)

val <T>Resource<T>?.isSuccess: Boolean
    get() = this is Resource.Success

val <T>Resource<T>?.isError: Boolean
    get() = this is Resource.Error

val <T>Resource<T>?.isLoading: Boolean
    get() = this is Resource.Loading

fun View.closeKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}
