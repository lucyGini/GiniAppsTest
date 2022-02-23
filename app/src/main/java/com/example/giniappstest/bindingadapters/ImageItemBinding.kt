package com.example.giniappstest.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.giniappstest.R

class ImageItemBinding {
    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

        @JvmStatic
        @BindingAdapter("setNumberOfLikes")
        fun setNumberOfLikes(textView: TextView, likes: Int) {
            textView.text = likes.toString()
        }

        @JvmStatic
        @BindingAdapter("setNumberOfComments")
        fun setNumberOfComments(textView: TextView, comments: Int) {
            textView.text = comments.toString()
        }
    }
}