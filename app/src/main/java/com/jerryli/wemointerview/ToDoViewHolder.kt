package com.jerryli.wemointerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
	val uiTitle: TextView = itemView.findViewById(R.id.tv_title)
	val uiTime: TextView = itemView.findViewById(R.id.tv_time)
}