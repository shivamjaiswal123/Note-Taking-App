package com.example.note_takingapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.note_takingapp.NoteActivity
import com.example.note_takingapp.R
import com.example.note_takingapp.model.Note
import com.example.note_takingapp.utils.ColorPicker
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class NoteAdapter(val context: Context, val notes: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = notes[position].title
        holder.note.text = notes[position].notes

        holder.card.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        val currD = getDate()
        holder.date.text = currD

        holder.itemView.setOnClickListener {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra("title", notes[position].title)
            intent.putExtra("note", notes[position].notes)
            intent.putExtra("note_id", notes[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.tv_title
        val note = itemView.tv_note
        val card = itemView.card_container
        val date = itemView.tv_date

    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
        val currentDate = sdf.format(Date())
        return currentDate
    }
}