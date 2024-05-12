package com.example.migolab_noteapp.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.migolab_noteapp.R
import com.example.migolab_noteapp.databinding.FragmentNoteBinding
import com.example.migolab_noteapp.fragment.adapter.NoteAdapter
import com.example.migolab_noteapp.viewmodel.NoteViewModel
import com.example.migolab_noteapp.viewmodel.viewmodelfactory.NoteViewModelFactory


class NoteFragment : Fragment() {

    private val noteViewModel by activityViewModels<NoteViewModel> { // dùng cách này để ủy quyền cho viewModels thực hiện khởi tạo viewModel
        NoteViewModelFactory(requireActivity().application)
    }

//    private val noteViewModel by lazy {
//        ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
//        )[NoteViewModel::class.java]
//    }

    private lateinit var fragmentNoteBinding: FragmentNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentNoteBinding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_note, container, false)
        return fragmentNoteBinding.root
    }

    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        initEvents()
        initControls()
    }

    private fun initEvents() {
        fragmentNoteBinding.btAdd.setOnClickListener{
            navController.navigate(R.id.action_noteFragment_to_addNoteFragment)
        }
    }

    private fun initControls() { //set layout
        val noteAdapter = NoteAdapter(
            requireActivity(),
            onClick = {
                noteViewModel.selectedNote = it
                navController.navigate(R.id.action_noteFragment_to_updateNoteFragment)
            },
            onDelete = {
                noteViewModel.deleteNote(it)
            }
        )

        fragmentNoteBinding.rvNote.setHasFixedSize(true)
        fragmentNoteBinding.rvNote.layoutManager = LinearLayoutManager(
            context, // màn hình hiển thị
            LinearLayoutManager.VERTICAL, // chiều hiển thị
            false // đảo ngược danh sách
        )
        fragmentNoteBinding.rvNote.adapter = noteAdapter

        noteViewModel.getAllNote().observe(viewLifecycleOwner) {
            Log.e("mycodeisblocking", "aaaaaaaaaaaaa")
            noteAdapter.setNotes(it)
        }

    }

}