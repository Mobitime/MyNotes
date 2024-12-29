package com.example.mynotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class NotesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter
    private val notes: MutableList<NoteEntity> = mutableListOf()
    private lateinit var etNote: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        etNote = view.findViewById(R.id.etNote)
        val btnAdd: Button = view.findViewById(R.id.btnAdd)
        recyclerView = view.findViewById(R.id.recyclerView)

        adapter = NoteAdapter(notes) { note ->

            val editNoteFragment = EditNoteFragment.newInstance(note)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, editNoteFragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        btnAdd.setOnClickListener {
            addNote()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun addNote() {
        val noteText = etNote.text.toString()
        if (noteText.isNotEmpty()) {
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            val note = NoteEntity(
                id = notes.size + 1,
                text = noteText,
                timestamp = timestamp
            )
            notes.add(note)
            adapter.notifyDataSetChanged()
            etNote.text.clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                requireActivity().finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}