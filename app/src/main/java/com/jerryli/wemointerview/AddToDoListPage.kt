package com.jerryli.wemointerview


import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson


class AddToDoListPage : Fragment() {

	private lateinit var uiTask: EditText
	private lateinit var uiFabSubmit: FloatingActionButton

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?)
		: View? = inflater.inflate(R.layout.fragment_add_to_do_list_page, container, false).also {

		uiTask = it.findViewById(R.id.et_task)
		uiFabSubmit = it.findViewById(R.id.fab_submit)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
		uiFabSubmit.setOnClickListener {
			if(uiTask.text.isNotBlank()) {

				saveTask(ToDo(
					title = uiTask.text.toString(),
					time = System.currentTimeMillis()
				))
			}
			requireActivity().supportFragmentManager.beginTransaction()
				.replace(R.id.fl_content, ToDoListPage.newIntent())
				.commit()
		}
	}

	private fun saveTask(toDo: ToDo) {
		val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
		val save = Gson().toJson(toDo)

		sharedPref.edit().putString("TO_DO", save).apply()
	}


	companion object {

		fun newIntent() = AddToDoListPage()
	}
}
