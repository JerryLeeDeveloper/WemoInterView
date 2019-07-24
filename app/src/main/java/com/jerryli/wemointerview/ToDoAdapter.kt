package com.jerryli.wemointerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ToDoAdapter(
	private val lists: List<ToDo>
) : RecyclerView.Adapter<ToDoViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder
		= ToDoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_list, parent, false))

	override fun getItemCount(): Int = lists.size

	override fun onBindViewHolder(holder: ToDoViewHolder, position: Int): Unit = with(holder) {
		val todo = lists[position]
		uiTitle.text = todo.title
		uiTime.text = String.format("更新於：%s", SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.TAIWAN).format(todo.time))
	}
}