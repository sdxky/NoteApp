package com.example.noteapp.ui.fragment.note

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.interfaces.OnClickItem
import com.example.noteapp.ui.adapter.NoteAdapter

class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(this, this, false)
    private var isLinearLayout = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        getData()
    }

    private fun initialize() {
        binding.rvNote.apply{
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListeners() {
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }

        binding.btnChangeLayout.setOnClickListener {
            if (isLinearLayout) {
                binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
                noteAdapter.setLayoutType(true)
            } else {
                binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
                noteAdapter.setLayoutType(false)
            }
            isLinearLayout = !isLinearLayout
        }
    }

    private fun getData() {
        App.appDataBase?.Dao()?.getAll()?.observe(viewLifecycleOwner){
            noteAdapter.submitList(it.reversed())
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder){
            setTitle("Вы точно хотите удалить запись?")
            setPositiveButton("Да"){ dialog, _ ->
                App.appDataBase?.Dao()?.deleteNote(noteModel)
            }
            setNegativeButton("Нет"){ dialog, _ ->
                dialog.cancel()
            }
            show()
        }
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}
