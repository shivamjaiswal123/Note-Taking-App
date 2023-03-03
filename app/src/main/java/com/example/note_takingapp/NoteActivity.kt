package com.example.note_takingapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import com.example.note_takingapp.architecture.NotesViewModel
import com.example.note_takingapp.model.Note
import com.example.note_takingapp.utils.ColorPicker
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {
    lateinit var viewModel: NotesViewModel
    private var forUpdating = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        val noteId = intent.getIntExtra("note_id", -1)
        val title = intent.getStringExtra("title")
        val note = intent.getStringExtra("note")

        if (noteId != -1) {
            et_title.setText(title)
            et_note.setText(note)
            forUpdating = true
        }


        btn_check.setOnClickListener {
            if (forUpdating) {
                updateNote(noteId)
            } else {
                insertNote()
            }
        }

        btn_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateNote(noteId: Int) {
        val updatedTitle = et_title.text.toString()
        val updatedNote = et_note.text.toString()
        if (inputCheck(updatedTitle, updatedNote)) {
            viewModel.updateNote(this, updatedTitle, updatedNote, noteId)
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Note Updated !!!", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun insertNote() {
        val title = et_title.text.toString()
        val note = et_note.text.toString()
        val cardColor = ColorPicker.getColor()
        val date = getDate()
        if (inputCheck(title, note)) {
            viewModel.insertNote(this, Note(title, note, cardColor, date))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Note Added !!!", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun inputCheck(title: String, note: String): Boolean {
        if (title.isEmpty() || note.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields !!!", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
        val currentDate = sdf.format(Date())
        return currentDate
    }
}