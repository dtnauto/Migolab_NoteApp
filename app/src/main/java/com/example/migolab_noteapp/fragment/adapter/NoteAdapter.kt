package com.example.migolab_noteapp.fragment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.migolab_noteapp.R
import com.example.migolab_noteapp.model.Note

class NoteAdapter(  // 3
    var context: Context,
    var onClick: (Note) -> Unit,
    var onDelete: (Note) -> Unit
): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() { // 2

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){  // 1 //để inner để truy suất ra class bên ngoài
        //8
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        private val tvDescription= itemView.findViewById<TextView>(R.id.tv_description)
        private val btDelete = itemView.findViewById<Button>(R.id.bt_delete)
        private val onClick = itemView.findViewById<Button>(R.id.bt_update)
        fun onBind(note: Note){
            tvTitle.text = note.title
            tvDescription.text = note.description

            btDelete.setOnClickListener{
                onDelete(note)
            }

            onClick.setOnClickListener{
                onClick(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_note_item,parent,false) //4
        return NoteViewHolder(itemView)
    }

    private var notes: List<Note> = listOf() //5
    fun setNotes(notes: List<Note>){
        this.notes= notes
        notifyDataSetChanged() //thông báo khi có sự thay đổi
    }

    override fun getItemCount(): Int {
        return notes.size //6
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(notes[position]) //7
    }
}