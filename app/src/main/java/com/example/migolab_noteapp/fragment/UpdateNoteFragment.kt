package com.example.migolab_noteapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.migolab_noteapp.R
import com.example.migolab_noteapp.model.Note
import com.example.migolab_noteapp.viewmodel.NoteViewModel
import com.example.migolab_noteapp.viewmodel.viewmodelfactory.NoteViewModelFactory

class UpdateNoteFragment : Fragment() {

    private val noteViewModel by activityViewModels<NoteViewModel> { // dùng cách này để ủy quyền cho viewModels thực hiện khởi tạo viewModel
        NoteViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_note, container, false)
    }

    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        var note = noteViewModel.selectedNote
        view.findViewById<EditText>(R.id.tv_title).setText(note.title)
        view.findViewById<EditText>(R.id.tv_description).setText(note.description)

        view.findViewById<Button>(R.id.bt_update).setOnClickListener{
            note.title = view.findViewById<EditText>(R.id.tv_title).text.toString()
            note.description = view.findViewById<EditText>(R.id.tv_description).text.toString()
            noteViewModel.updateNote(note)
            Log.e("mycodeisblocking", "$note")
            navController.navigate(R.id.action_updateNoteFragment_to_noteFragment)
        }

    }
}