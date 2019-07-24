package com.jerryli.wemointerview

import com.google.gson.annotations.SerializedName

data class ToDo(
	@SerializedName("title")var title: String = "",
	@SerializedName("time")var time: Long = -1
)