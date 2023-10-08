package com.glitch.todolist.ui.dailynotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glitch.todolist.data.model.Note
import com.glitch.todolist.databinding.ItemDailyNoteBinding

class DailyNotesAdapter(
    private val onNoteClick: (String) -> Unit
) : RecyclerView.Adapter<DailyNotesAdapter.DailyNotesViewHolder>() {

    private val noteList = mutableListOf<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyNotesViewHolder {
        val binding =
            ItemDailyNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyNotesViewHolder(binding, onNoteClick)
    }

    override fun onBindViewHolder(holder: DailyNotesViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    class DailyNotesViewHolder(
        private val binding: ItemDailyNoteBinding, val onNoteClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                tv1.text = note.title
                tv2.text = note.description

                root.setOnClickListener {
                    onNoteClick(note.description)
                }
            }
        }
    }

    fun updateList(list: List<Note>) {
        noteList.clear()
        noteList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }
}