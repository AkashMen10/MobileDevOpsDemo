package com.example.todolistmvcapplication.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolistmvcapplication.R
import com.example.todolistmvcapplication.models.Event
import com.example.todolistmvcapplication.viewholders.EventViewHolder
import com.example.todolistmvcapplication.viewholders.ViewHolder

class EventAdapter(private val eventList: MutableList<Event>,var listener : View.OnClickListener) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event_list, parent, false)
            ,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.onBindView(eventList[pos])
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

}