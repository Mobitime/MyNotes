package com.example.mynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val notes: List<NoteEntity>,
    private val onNoteClick: (NoteEntity) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val noteText: TextView = itemView.findViewById(R.id.tvNoteText)
        val timestamp: TextView = itemView.findViewById(R.id.tvTimestamp)
        val noteNumber: TextView = itemView.findViewById(R.id.tvNoteNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.noteText.text = note.text
        holder.timestamp.text = note.timestamp
        holder.checkBox.isChecked = note.isChecked
        holder.noteNumber.text = (position + 1).toString()

        holder.itemView.setOnClickListener {
            onNoteClick(note)
        }
    }

    override fun getItemCount() = notes.size
}