package com.example.noteapp.ui.fragment.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private val noteDate: String = SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date())
    private val noteTime: String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    private var noteId: Int? = null
    var color = "black"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateNote()
        colorChange()
        initialize()
        setupListeners()
    }

    private fun updateNote() {
        arguments.let {
            noteId = it?.getInt("noteId", -1)
        }
        if (noteId != -1){
            val argsNote = App.appDataBase?.Dao()?.getByID(noteId!!)
            argsNote?.let {
                binding.etTitle.setText(it.title)
                binding.etDescription.setText(it.description)
            }
        }
    }

    private fun colorChange() {
        binding.colorRed.setOnClickListener{
            color = "red"
            binding.dotRed.visibility = View.VISIBLE
            binding.dotWhite.visibility = INVISIBLE
            binding.dotBlack.visibility = INVISIBLE

        }
        binding.colorWhite.setOnClickListener{
            color = "white"
            binding.dotWhite.visibility = View.VISIBLE
            binding.dotRed.visibility = INVISIBLE
            binding.dotBlack.visibility = INVISIBLE

        }
        binding.colorBlack.setOnClickListener{
            color = "black"
            binding.dotBlack.visibility = View.VISIBLE
            binding.dotWhite.visibility = INVISIBLE
            binding.dotRed.visibility = INVISIBLE

        }
    }

    private fun initialize() {
        binding.dotRed.visibility = INVISIBLE
        binding.dotWhite.visibility = INVISIBLE
        binding.tvDate.apply {
            binding.tvDate.text = noteDate
        }
        binding.tvTime.apply {
            binding.tvTime.text = noteTime
        }
    }

    private fun setupListeners() {
        binding.tvSave.setOnClickListener{
            val etTitle = binding.etTitle.text.toString().trim()
            val etDescription = binding.etDescription.text.toString().trim()

            if (noteId != -1){
                val updateNote = NoteModel(etTitle, etDescription, noteTime, noteDate)
                updateNote.id = noteId!!
                App.appDataBase?.Dao()?.updateNote(updateNote)
            }else{
                App.appDataBase?.Dao()?.insert(NoteModel(etTitle, etDescription, noteTime, noteDate))
            }

            findNavController().navigateUp()
        }
        binding.toolbarDetail.setNavigationOnClickListener{
            findNavController().navigateUp()
        }
    }
}