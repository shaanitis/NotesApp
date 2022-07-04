package com.example.roomnotesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val context:Context,
val noteClickInterFace:OnNoteClickInterface,
val noteDltClickInterface:NoteDltClickInterface):
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var allNotes=ArrayList<Note>()

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
  val noteTitle=itemView.findViewById<TextView>(R.id.noteTitleEd)
        val noteTime=itemView.findViewById<TextView>(R.id.noteTimeEd)
        val noteDltBtn=itemView.findViewById<ImageView>(R.id.deleteNoteBtn)

    }

    interface NoteDltClickInterface{
        fun onDeleteIconClick(note: Note)
    }
    interface OnNoteClickInterface{
        fun onNoteClick(note: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle.setText(allNotes.get(position).noteTitle)
        holder.noteTime.setText("Last Updated on: "+allNotes.get(position).noteTime)

        holder.noteDltBtn.setOnClickListener {
            noteDltClickInterface.onDeleteIconClick(allNotes.get(position))
        }
        holder.itemView.setOnClickListener {
            noteClickInterFace.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}