package com.task.noteapp.createanote

import com.task.noteapp.database.NoteDAO
import com.task.noteapp.database.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateNoteRepository(private val noteDAO: NoteDAO) {

    suspend fun addNote( note: NoteModel ) {
        withContext(Dispatchers.IO) {
            noteDAO.add(note)
        }
    }
}