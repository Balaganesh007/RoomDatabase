package com.example.roomdatabase.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.roomdatabase.database.Model

@BindingAdapter("Id")
fun TextView.setId(item : Model?){
    item?.let {
        text = item.Id.toString()
    }
}
@BindingAdapter("Names")
fun TextView.setNames(item : Model?){
    item?.let {
       text = item.Data
    }
}