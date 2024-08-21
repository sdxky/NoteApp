package com.example.noteapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.ItemNoteBinding
import com.example.noteapp.interfaces.OnClickItem
import com.example.noteapp.ui.fragment.note.NoteFragment

class NoteAdapter(
    private val onLongClick: OnClickItem,
    private val onClick: OnClickItem
) : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffcallBack()) {

    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel?) {
            binding.txtTitle.text = item?.title
            binding.txtDescription.text = item?.description
            binding.txtDate.text = item?.date
            binding.txtTime.text = item?.time

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClick(getItem(position))
            true
        }
        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
        }
    }

    class DiffcallBack : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

    }
}