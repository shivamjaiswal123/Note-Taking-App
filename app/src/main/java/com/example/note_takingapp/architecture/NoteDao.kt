package com.example.note_takingapp.architecture

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.note_takingapp.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from Note")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("Delete from Note")
    fun deleteAllNotes()

    @Query("UPDATE Note SET title=:updatedTitle, notes=:updatedNote WHERE id = :noteId")
    fun updateNoteById(updatedTitle: String, updatedNote: String, noteId: Int)
}