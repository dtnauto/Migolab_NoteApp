package com.example.migolab_noteapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.migolab_noteapp.R
import com.example.migolab_noteapp.model.Note
import com.example.migolab_noteapp.viewmodel.NoteViewModel
import com.example.migolab_noteapp.viewmodel.viewmodelfactory.NoteViewModelFactory

class AddNoteFragment : Fragment() {

    private val noteViewModel by viewModels<NoteViewModel> { // dùng cách này để ủy quyền cho viewModels thực hiện khởi tạo viewModel
        NoteViewModelFactory(requireActivity().application)
    }

//    private val noteViewModel by lazy {
//        ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
//        )[NoteViewModel::class.java]
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.bt_add).setOnClickListener{
            val note = Note(
                view.findViewById<EditText>(R.id.tv_title).text.toString(),
                view.findViewById<EditText>(R.id.tv_description).text.toString()
            )
            noteViewModel.insertNote(note)
            navController.navigate(R.id.action_addNoteFragment_to_noteFragment)
        }
    }
}