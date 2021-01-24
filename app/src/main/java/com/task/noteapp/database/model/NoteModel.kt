package com.task.noteapp.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "NoteModel")
data class NoteModel(
    var noteTitle: String,
    var noteDesc: String,
    var noteCreateDate: String,
    var noteEditFlag: Int = 0,
    var userId: Int = 0
):Serializable{

    @PrimaryKey(autoGenerate = true)
    var noteId: Int = 0


    constructor():this("","","",0,0)
    override fun toString(): String {
        return "NoteModel(noteTitle='$noteTitle', noteDesc='$noteDesc', noteCreateDate='$noteCreateDate', noteEditFlag=$noteEditFlag, noteId=$noteId)"
    }
}