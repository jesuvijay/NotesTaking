package com.jesu.notestaking.ui

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.jesu.notestaking.R
import com.jesu.notestaking.db.Note
import com.jesu.notestaking.db.Notedao
import com.jesu.notestaking.db.NotesDatabase
import kotlinx.android.synthetic.main.fragment_add_more.*
import kotlinx.coroutines.launch

class AddMoreFragment : BaseFragment() {

    private var note: Note? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_more, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            note = AddMoreFragmentArgs.fromBundle(it).note
            et_title.setText(note?.title)
            et_desc.setText(note?.note)
        }
        btn_done.setOnClickListener { view ->
            val noteTitle = et_title.text.toString().trim()
            val noteDesc = et_desc.text.toString().trim()
            if (noteTitle.isEmpty()) {
                et_title.error = "Title required"
                et_title.requestFocus()
                return@setOnClickListener
            }
            if (noteDesc.isEmpty()) {
                et_desc.error = "Title required"
                et_desc.requestFocus()
                return@setOnClickListener
            }

//            saveNote(note)
            launch {
                context?.let {
                    val mNote = Note(noteTitle, noteDesc)
                    if (note == null) {
                        NotesDatabase(it).getNoteeDao().addNote(mNote)
                                it.toast("Note saved")
                    } else {
                        mNote.id=note!!.id
                        NotesDatabase(it).getNoteeDao().updateNote(mNote)
                        it.toast("Note updated")
                    }


                    val action = AddMoreFragmentDirections.actionSavemore()
                    Navigation.findNavController(view).navigate(action)
                }

            }
        }

    }


    // save note
//    private fun saveNote(note: Note) {
//
//        class SaveNote : AsyncTask<Void, Void, Void>() {
//            override fun doInBackground(vararg params: Void?): Void? {
//
//
//                return null
//            }
//
//            override fun onPostExecute(result: Void?) {
//                super.onPostExecute(result)
//                Toast.makeText(activity, "Note Saved", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//        SaveNote().execute()
//
//    }
}