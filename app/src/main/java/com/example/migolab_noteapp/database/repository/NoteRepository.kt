package com.example.migolab_noteapp.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.migolab_noteapp.database.NoteDatabase
import com.example.migolab_noteapp.database.dao.NoteDao
import com.example.migolab_noteapp.model.Note

class NoteRepository(application: Application) {

    private val noteDao: NoteDao
    init {
        val noteDatabase: NoteDatabase = NoteDatabase.getInstance(application.applicationContext)
        noteDao = noteDatabase.getNoteDao()
    }

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    fun getAllNote(): LiveData<List<Note>> = noteDao.getAllNote()

}