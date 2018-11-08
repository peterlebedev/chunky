package cz.lebedev.example.chunky.view.adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visibleGone")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}