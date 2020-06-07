package com.jesu.notestaking.ui

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jesu.notestaking.R
import com.jesu.notestaking.db.NotesDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_notes.setHasFixedSize(true)
        rv_notes.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                val note = NotesDatabase(it).getNoteeDao().getAllNotes()
                rv_notes.adapter = NoteAdapter(note)


            }

        }
        btn_add.setOnClickListener {
            val action = HomeFragmentDirections.actionAddMore()
            Navigation.findNavController(it).navigate(action)
        }
    }
}