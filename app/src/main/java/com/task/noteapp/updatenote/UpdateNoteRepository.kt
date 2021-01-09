package com.task.noteapp.updatenote

import com.task.noteapp.database.NoteDAO
import com.task.noteapp.database.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateNoteRepository(private val noteDAO: NoteDAO) {

    suspend fun updateNote(note: NoteModel) {
        withContext(Dispatchers.IO) {
            noteDAO.updateNoteTitleDescriptionAndCreateDateById(
                noteTitle = note.noteTitle,
                noteDescription = note.noteDesc,
                noteCreateDate = note.noteCreateDate,
                id = note.noteId
            )
        }
    }
}