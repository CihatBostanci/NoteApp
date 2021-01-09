package com.task.noteapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.R
import com.task.noteapp.database.model.NoteModel

class NoteAdapter(private val dataSet: MutableList<NoteModel>,
                  private val deleteNoteItemClickListener:
                                NoteAdapter.ViewHolder.deleteNoteItemClickListener,
                  private val editNoteItemClickListener:
                                NoteAdapter.ViewHolder.editNoteItemClickListener) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noteTitle: TextView
        val noteDescription:TextView
        val noteCreatedDate: TextView
        val noteDelete: ImageView
        val noteEdit: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            noteTitle = view.findViewById(R.id.TWNoteItemTitle)
            noteDescription = view.findViewById(R.id.TWNoteItemDecription)
            noteCreatedDate = view.findViewById(R.id.TWNoteItemCreatedDate)
            noteDelete = view.findViewById(R.id.IVNoteItemDelete)
            noteEdit = view.findViewById(R.id.IVNoteItemEdit)
        }

        interface deleteNoteItemClickListener {
            fun deleteNoteItemClickListener(data: NoteModel)
        }

        interface editNoteItemClickListener{
            fun editNoteItemClickListener(data: NoteModel)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item_note, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.noteTitle.text = dataSet[position].noteTitle
        viewHolder.noteDescription.text = dataSet[position].noteDesc
        viewHolder.noteCreatedDate.text = dataSet[position].noteCreateDate

        viewHolder.noteDelete.setOnClickListener {
           deleteNoteItemClickListener.deleteNoteItemClickListener(dataSet[position])
        }
        viewHolder.noteEdit.setOnClickListener{
           editNoteItemClickListener.editNoteItemClickListener(dataSet[position])
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

