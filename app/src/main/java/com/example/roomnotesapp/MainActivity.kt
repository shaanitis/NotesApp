package com.example.roomnotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NotesAdapter.NoteDltClickInterface, NotesAdapter.OnNoteClickInterface{
    lateinit var notesRV:RecyclerView
    lateinit var addNoteBtn:FloatingActionButton
    lateinit var noteVM:NotesViewModel
    lateinit var notesAdapter:NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
this.supportActionBar?.hide()
        notesRV=findViewById(R.id.notesRV)
        addNoteBtn=findViewById(R.id.addNoteBtn)

        notesRV.layoutManager=LinearLayoutManager(this)
        notesAdapter= NotesAdapter(this, this, this)
        notesRV.adapter=notesAdapter

        noteVM=ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)).get(NotesViewModel::class.java)

        noteVM.allNotes.observe(this, Observer {
            it.let {
                notesAdapter.updateList(it)
            }
        })

addNoteBtn.setOnClickListener {
    val intent=Intent(this, EditNote::class.java)
    startActivity(intent)
}

    }

    override fun onDeleteIconClick(note: Note) {
        noteVM.deleteNote(note)
        Toast.makeText(this, "Deleted: "+ note.noteTitle, Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(note: Note) {
        val intent=Intent(this, EditNote::class.java)
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDesc", note.noteDesc)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteId", note.id)
        startActivity(intent)

    }
}