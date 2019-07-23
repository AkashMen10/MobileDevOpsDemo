package com.example.todolistmvcapplication.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBindView(model: Any)
}