package com.example.note_takingapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    var title: String,
    var notes: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
