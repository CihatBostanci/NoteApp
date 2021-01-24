package com.task.noteapp.home.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.R
import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.extensions.hide
import com.task.noteapp.extensions.show

class NoteAdapter(private val dataSet: MutableList<NoteModel>,
                  private val DeleteNoteItemClickListener:
                                NoteAdapter.ViewHolder.DeleteNoteItemClickListener,
                  private val EditNoteItemClickListener:
                                NoteAdapter.ViewHolder.EditNoteItemClickListener,
                  private val context : Context
) :

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
        val noteEditFlag: ImageView
        val cLNoteItem : ConstraintLayout


        init {
            // Define click listener for the ViewHolder's View.
            noteTitle = view.findViewById(R.id.TWNoteItemTitle)
            noteDescription = view.findViewById(R.id.TWNoteItemDecription)
            noteCreatedDate = view.findViewById(R.id.TWNoteItemCreatedDate)
            noteDelete = view.findViewById(R.id.IVNoteItemDelete)
            noteEdit = view.findViewById(R.id.IVNoteItemEdit)
            noteEditFlag = view.findViewById(R.id.IVEditFlag)
            cLNoteItem = view.findViewById(R.id.CLNoteItem)
        }

        interface DeleteNoteItemClickListener {
            fun deleteNoteItemClickListener(data: NoteModel)
        }

        interface EditNoteItemClickListener{
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
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your data set at this position and replace the
        // contents of the view with that element
        viewHolder.noteTitle.text = dataSet[position].noteTitle
        viewHolder.noteDescription.text = dataSet[position].noteDesc
        viewHolder.noteCreatedDate.text = dataSet[position].noteCreateDate
        when(dataSet[position].noteEditFlag){
            0 ->  { viewHolder.noteEditFlag.hide()}
            1 ->  { viewHolder.cLNoteItem.setBackgroundColor(context.getColor(R.color.greenLight))
                viewHolder.noteEditFlag.show()}
        }



        viewHolder.noteDelete.setOnClickListener {
           DeleteNoteItemClickListener.deleteNoteItemClickListener(dataSet[position])
        }
        viewHolder.noteEdit.setOnClickListener{
           EditNoteItemClickListener.editNoteItemClickListener(dataSet[position])
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

