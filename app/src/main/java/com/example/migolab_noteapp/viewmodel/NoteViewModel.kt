package com.example.migolab_noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.migolab_noteapp.database.repository.NoteRepository
import com.example.migolab_noteapp.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val noteRepository: NoteRepository = NoteRepository(application)

    fun insertNote(note: Note) = viewModelScope.launch{ //tao coroutin chay tren Main và vong doi cua nó phụ thuộc vào viewModel khong cần phải tự huy
        noteRepository.insertNote(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch{ //tao coroutin chay tren Main và vong doi cua nó phụ thuộc vào viewModel khong cần phải tự huy
        noteRepository.updateNote(note)
    }
    fun deleteNote(note: Note) = viewModelScope.launch{ //tao coroutin chay tren Main và vong doi cua nó phụ thuộc vào viewModel khong cần phải tự huy
        noteRepository.deleteNote(note)
    }

    fun getAllNote(): LiveData<List<Note>> {
//        var liveData: MutableLiveData<List<Note>> = MutableLiveData()
//        liveData.value = (noteRepository.getAllNote())
        return noteRepository.getAllNote()
    }

    var selectedNote  = Note("ahihi","ahihi")
}