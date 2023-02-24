package com.example.note_takingapp.architecture

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.note_takingapp.model.Note
import kotlinx.coroutines.*

class NoteRepository {
    companion object {
        private var noteDatabase: NoteDatabase? = null

        private fun initializeDB(context: Context): NoteDatabase {
            return NoteDatabase.getInstance(context)
        }

        fun insert(context: Context, note: Note) {
            noteDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                noteDatabase!!.noteDao().insert(note)
            }
        }

        fun delete(context: Context, note: Note) {
            noteDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                noteDatabase!!.noteDao().delete(note)
            }
        }

        fun getAllNotes(context: Context): LiveData<List<Note>> {
            noteDatabase = initializeDB(context)

            return noteDatabase!!.noteDao().getAllNotes()
        }

        fun deleteAllNotes(context: Context) {
            noteDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                noteDatabase!!.noteDao().deleteAllNotes()
            }
        }

        fun updateNoteById(context: Context,updatedTitle: String, updatedNote: String, noteId: Int){
            noteDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                noteDatabase!!.noteDao().updateNoteById(updatedTitle, updatedNote, noteId)
            }
        }
    }
}