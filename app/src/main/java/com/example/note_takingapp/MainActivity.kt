package com.example.note_takingapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        val note = mutableListOf<Note>()
//        recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = NoteAdapter(this, note)

        FAB.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }


        //show all notes
        viewModel.showAllNotes(this).observe(this, Observer {
            note.clear()
            note.addAll(it)
            adapter.notifyDataSetChanged()
        })
        recycler_view.adapter = adapter

        //delete note
        adapter.setOnItemClickListener(object : NoteAdapter.OnItemClickListener {
            override fun onItemClick(note: Note) {
                viewModel.deleteNote(this@MainActivity, note)
                Toast.makeText(this@MainActivity, "Note deleted !!!", Toast.LENGTH_LONG).show()
            }
        })
    }

    //option menu of delete all notes
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteAll ->{
                viewModel.deleteAllNotes(this)
                Toast.makeText(this, "Deleted All Notes !!!", Toast.LENGTH_LONG).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}