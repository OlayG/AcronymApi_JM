package com.example.acronymapi.util

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * DataBinding Method
 * Used to set adapter for RecyclerView
 */
@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    setHasFixedSize(true)
    this.adapter = adapter
}

/**
 * DataBinding Method
 * Used to set visibility state using boolean or null
 */
@BindingAdapter(value = ["isVisible"])
fun View.isVisible(visible: Boolean?) {
    visibility = when (visible) {
        true -> View.VISIBLE
        false -> View.GONE
        else -> View.INVISIBLE
    }
}

/**
 * DataBinding Method
 * Used to show or hide error state for TextInputLayout
 */
@BindingAdapter("setErrorMessage")
fun <T> TextInputLayout.setErrorMessage(data: Resource<T>?) {
    if (data.isError) error = data?.error
    isErrorEnabled = data.isError
}

/**
 * DataBinding Method
 * Used to hide error state for TextInputLayout
 */
@BindingAdapter("resetErrorEnabled")
fun <T> TextInputLayout.resetErrorEnabled(reset: Boolean) {
    if (reset) isErrorEnabled = false
}

/**
 * DataBinding Method
 * Used to listen to "enter" or "done" actions via keyboard
 */
@BindingAdapter("onEditorEnterAction")
fun TextInputEditText.onEditorEnterAction(f: Function0<Unit>?) {

    if (f == null) setOnEditorActionListener(null)
    else setOnEditorActionListener { _, actionId, event ->

        val imeAction = when (actionId) {
            EditorInfo.IME_ACTION_DONE,
            EditorInfo.IME_ACTION_SEND,
            EditorInfo.IME_ACTION_GO -> true
            else -> false
        }

        val keyDownEvent = event?.keyCode == KeyEvent.KEYCODE_ENTER
                && event.action == KeyEvent.ACTION_DOWN

        return@setOnEditorActionListener if (imeAction or keyDownEvent) true.also { f();closeKeyboard() }
        else false
    }
}

/**
 * DataBinding Method
 * Used to listen to button actions
 */
@BindingAdapter("onButtonClicked")
fun MaterialButton.onButtonClicked(f: Function0<Unit>?) = setOnClickListener {
    f?.invoke();closeKeyboard()
}