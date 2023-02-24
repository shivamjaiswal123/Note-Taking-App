package com.example.note_takingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.note_takingapp.adapter.NoteAdapter
import com.example.note_takingapp.architecture.NotesViewModel
import com.example.note_takingapp.model.Note
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NotesViewModel
    lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        val note = mutableListOf<Note>()
//        recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = NoteAdapter(this, note)

        FAB.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }


        viewModel.showAllNotes(this).observe(this, Observer {
            note.clear()
            note.addAll(it)
        })
        recycler_view.adapter = adapter
    }

}