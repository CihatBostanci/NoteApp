package com.task.noteapp.home

import com.task.noteapp.database.NoteDAO
import com.task.noteapp.database.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val noteDAO: NoteDAO) {

    fun fetchAllNotes(userId: Int): MutableList<NoteModel> {
        return noteDAO.findAll(userId)
    }

    suspend fun deleteNote(noteId: Int) {
        withContext(Dispatchers.IO) {
            noteDAO.deleteByNoteId(noteId)
        }
    }



}