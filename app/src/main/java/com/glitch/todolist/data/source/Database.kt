package com.glitch.todolist.data.source

import com.glitch.todolist.data.model.Note

object Database {
    private val dailyNotes = mutableListOf<Note>()

    fun getDailyNotes(): List<Note> {
        return dailyNotes
    }

    fun addDailyNotes(title: String, description: String) {
        val newNote = Note(
            id = (dailyNotes.lastOrNull()?.id ?: 0) + 1,
            title = title,
            description = description
        )
        dailyNotes.add(newNote)
    }
}