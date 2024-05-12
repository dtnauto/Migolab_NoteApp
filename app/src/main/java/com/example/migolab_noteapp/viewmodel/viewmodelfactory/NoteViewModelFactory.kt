package com.example.migolab_noteapp.viewmodel.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.migolab_noteapp.viewmodel.NoteViewModel

class NoteViewModelFactory(var application: Application):
    ViewModelProvider.AndroidViewModelFactory() { // dùng để khởi tạo application cho viewModel

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){ //kiểm tra xem modelClass này có phải là thể hiện của một viewmodel hay ko? gần giống instanceOf trong java nhưng ở java thì so sánh đối tượng. trong kotlin thì so sánh obj
            NoteViewModel(application) as T // ép kiểu T
        }
        throw IllegalArgumentException("Unable construct viewModel")
    }
}