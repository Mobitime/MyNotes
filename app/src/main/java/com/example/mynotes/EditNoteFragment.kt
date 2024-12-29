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

class EditNoteFragment : Fragment() {
    private lateinit var etEditNote: EditText
    private lateinit var note: NoteEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_note, container, false)

        etEditNote = view.findViewById(R.id.etEditNote)
        val btnEdit: Button = view.findViewById(R.id.btnEdit)


        note = arguments?.getParcelable("note")!!


        etEditNote.setText(note.text)

        btnEdit.setOnClickListener {
            updateNote()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateNote() {
        val updatedText = etEditNote.text.toString()
        if (updatedText.isNotEmpty()) {
            note.text = updatedText
            requireActivity().onBackPressed()
        }
    }

    companion object {
        fun newInstance(note: NoteEntity): EditNoteFragment {
            val fragment = EditNoteFragment()
            val args = Bundle()
            args.putParcelable("note", note)
            fragment.arguments = args
            return fragment
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