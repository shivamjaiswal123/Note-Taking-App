package com.example.note_takingapp.architecture

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.note_takingapp.model.Note
import kotlinx.coroutines.Deferred

class NotesViewModel : ViewModel() {
    fun insertNote(context: Context, note: Note) {
        NoteRepository.insert(context, note)
    }

    fun deleteNote(context: Context, note: Note) {
        NoteRepository.delete(context, note)
    }

    fun showAllNotes(context: Context): LiveData<List<Note>> {
        return NoteRepository.getAllNotes(context)
    }

    fun deleteAllNotes(context: Context) {
        NoteRepository.deleteAllNotes(context)
    }

    fun updateNote(context: Context, updatedTitle: String, updatedNote: String, noteId: Int){
        NoteRepository.updateNoteById(context, updatedTitle, updatedNote,noteId)
    }
}