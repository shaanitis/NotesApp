package com.example.roomnotesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {

    val allNotes:LiveData<List<Note>>
    val repo:NotesRepository
    init {

val dao=NotesDatabase.getDatabase(application).getNotesDao()
 repo= NotesRepository(dao)
        allNotes=repo.allNotes
    }

    fun deleteNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repo.delete(note)
    }
    fun updateNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repo.update(note)
    }
    fun insertNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repo.insert(note)
    }
}