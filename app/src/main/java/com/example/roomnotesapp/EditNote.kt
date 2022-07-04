package com.example.roomnotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.*

class EditNote : AppCompatActivity() {
    lateinit var noteTitle:EditText
    lateinit var noteDesc:EditText

    lateinit var notesVM:NotesViewModel
     var noteId:Int=-1
    lateinit var back:ImageView
    lateinit var saveBtn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        this.supportActionBar?.hide()
        noteTitle=findViewById(R.id.etNoteTitle)
        noteDesc=findViewById(R.id.etNoteDesc)
        saveBtn=findViewById(R.id.saveBtn)
        back=findViewById(R.id.back)


back.setOnClickListener {
    val intent:Intent= Intent(this, MainActivity::class.java)
    startActivity(intent)
}
       val noteType= intent.getStringExtra("noteType")

        notesVM=ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NotesViewModel::class.java)

        if (noteType.equals("Edit")){
            val title= intent.getStringExtra("noteTitle")
                val desc= intent.getStringExtra("noteDesc")
            noteId=     intent.getIntExtra("noteId", -1)

            noteTitle.setText(title)
            noteDesc.setText(desc)
        }else{
         Log.d("","")
        }

saveBtn.setOnClickListener {
    val title= noteTitle.text.toString()
    val desc= noteDesc.text.toString()

    if (noteType.equals("Edit")){
        if (noteTitle.text.isNotEmpty() && noteDesc.text.isNotEmpty()){
            val sdf=SimpleDateFormat("dd MMM, yyyy - HH:mm")
            val currentDate=sdf.format(Date())

            val update=Note(title, desc, currentDate)
            update.id= noteId
            notesVM.updateNote(update)
            Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
        }

    }else{
        if (noteTitle.text.isNotEmpty() && noteDesc.text.isNotEmpty()) {
            val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
            val currentDate = sdf.format(Date())
            notesVM.insertNote(Note(title, desc, currentDate))
            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
        }
    }

    val inte=Intent(this, MainActivity::class.java)
    startActivity(inte)
    this.finish()
}

    }
}