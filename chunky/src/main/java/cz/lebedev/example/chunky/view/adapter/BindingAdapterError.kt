package cz.lebedev.example.chunky.view.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import cz.lebedev.example.chunky.R

@BindingAdapter("app:errorMessage")
fun goneUnless(view: TextView, error: String?) {
    if(error==null || error.length==0){
        view.text = view.context.getString(R.string.loading)
    }else{
        view.text = error
    }
}