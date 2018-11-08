package cz.lebedev.example.chunky.view.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if(imageUrl!=null){
        Picasso.get()
            .load(imageUrl)
            .into(view)
    }
}