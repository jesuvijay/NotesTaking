package com.jesu.notestaking.db

import androidx.room.*

@Dao
interface Notedao {

    @Insert
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM note order by id desc")
    suspend fun getAllNotes(): List<Note>

    @Insert
    suspend fun mutipleNotes(vararg note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}