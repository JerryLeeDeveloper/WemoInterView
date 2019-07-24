package com.jerryli.wemointerview


import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ToDoListPage : Fragment() {

	private lateinit var uiGroup: Group
	private lateinit var uiList: RecyclerView
	private lateinit var uiFabAdd: FloatingActionButton
	private lateinit var uiEmpty: TextView

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?)
		: View? = inflater.inflate(R.layout.fragment_to_do_list_page, container, false).also {
		uiEmpty = it.findViewById(R.id.tv_empty)
		uiGroup = it.findViewById(R.id.group)
		uiList = it.findViewById(R.id.rlv_list)
		uiFabAdd = it.findViewById(R.id.fab_add)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		uiList.apply {
			layoutManager = LinearLayoutManager(requireActivity())
			setHasFixedSize(true)
		}

		val share : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
		val todo = share.getString("TO_DO", null)
		if(todo != null) {
			uiGroup.visibility = View.VISIBLE
			uiEmpty.visibility = View.GONE
			val type = genericType<List<ToDo>>()
			val item = Gson().fromJson<ToDo>(todo, ToDo::class.java)
			val list = mutableListOf<ToDo>()
			list.add(item)

			uiList.adapter = ToDoAdapter(list)
		} else {
			uiGroup.visibility = View.GONE
			uiEmpty.visibility = View.VISIBLE
		}
		uiFabAdd.setOnClickListener {
			requireActivity().supportFragmentManager.beginTransaction()
				.replace(R.id.fl_content, AddToDoListPage.newIntent())
				.commit()
		}
	}

	override fun onResume() {
		super.onResume()
		val share : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
		val todo = share.getString("TO_DO", null)
		if(todo != null) {
			uiGroup.visibility = View.VISIBLE
			uiEmpty.visibility = View.GONE
			val item = Gson().fromJson(todo, ToDo::class.java)
			val list = mutableListOf<ToDo>()
			list.add(item)
			uiList.adapter = ToDoAdapter(list)
		} else {
			uiGroup.visibility = View.GONE
			uiEmpty.visibility = View.VISIBLE
		}
	}

	companion object {
		inline fun <reified T> genericType() = object: TypeToken<T>() {}.type
		fun newIntent()= ToDoListPage()
	}

}
