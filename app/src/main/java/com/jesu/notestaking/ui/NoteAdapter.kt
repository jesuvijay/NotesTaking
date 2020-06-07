package com.jesu.notestaking.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jesu.notestaking.R
import com.jesu.notestaking.db.Note
import kotlinx.android.synthetic.main.note_list.view.*

class NoteAdapter(val note: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return note.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.view.textView_title.text = note[position].title
        holder.view.textView_desc.text = note[position].note
        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionAddMore()
            action.note = note[position]
            Navigation.findNavController(it).navigate(action)
        }

    }
}