package com.glitch.todolist.ui.dailynotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glitch.todolist.data.model.Note
import com.glitch.todolist.databinding.ItemDailyNoteBinding

class DailyNotesAdapter(

) : RecyclerView.Adapter<DailyNotesAdapter.DailyNotesViewHolder>() {

    private val noteList = mutableListOf<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyNotesViewHolder {
        val binding =
            ItemDailyNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyNotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyNotesViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    class DailyNotesViewHolder(private val binding: ItemDailyNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {

            }
        }
    }

}