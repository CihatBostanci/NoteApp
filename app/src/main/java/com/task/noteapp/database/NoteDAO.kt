package com.task.noteapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.noteapp.database.model.NoteModel

@Dao
interface NoteDAO {

    @Query("SELECT * FROM NoteModel WHERE userId= :userId")
    fun findAll(userId: Int): MutableList<NoteModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(note: NoteModel)

    @Query("DELETE FROM NoteModel WHERE noteId = :noteId")
    fun deleteByNoteId(noteId: Int)

    @Query("UPDATE NoteModel SET noteTitle = :noteTitle, noteDesc = :noteDescription , noteCreateDate = :noteCreateDate, noteEditFlag = 1 WHERE noteId =:id")
    fun updateNoteTitleDescriptionAndCreateDateById(noteTitle: String, noteDescription: String, noteCreateDate:String , id: Int)

}