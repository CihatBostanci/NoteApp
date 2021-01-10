package com.task.noteapp.updatenote

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.task.noteapp.MainActivity
import com.task.noteapp.MainApplication
import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.databinding.FragmentUpdateNoteModalBottomSheetBinding
import com.task.noteapp.manager.SharedPreferencesManager.get
import com.task.noteapp.utils.*
import org.greenrobot.eventbus.EventBus
import org.koin.android.viewmodel.ext.android.viewModel

private const val UPDATE_A_NOTE_FRA_TAG = "UPDATENOTEFRATAG"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateNoteModalBottomSheetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateNoteModalBottomSheetFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private val updateNoteViewModel by viewModel<UpdateNoteViewModel>()

    //View Binding
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentUpdateNoteModalBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var noteModel:NoteModel? = NoteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
             noteModel = it.getSerializable(UPDATE_KEY) as? NoteModel
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateNoteModalBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUIInit()
        initObservers()
    }

    private fun initObservers() {
        updateNoteViewModel.updatedNoteLiveData.observe(viewLifecycleOwner,_updateNoteObserver)
    }

    private fun setUIInit() {
        noteModel?.let {
            binding.ETUpdateNoteTitle.setText(noteModel?.noteTitle)
            binding.ETUpdateNoteDescription.setText(noteModel?.noteDesc)
        }
        binding.BTNUpdateANote.setOnClickListener(this)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UpdateNoteModalBottomSheetFragment.
         */
        @JvmStatic
        fun newInstance() =
            UpdateNoteModalBottomSheetFragment().apply {
            }
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when(p0.id){
                binding.BTNUpdateANote.id -> updateNoteAction()
            }
        }
    }

    private fun updateNoteAction() {
        var _noteModel = noteModel?.apply {
            noteTitle      = binding.ETUpdateNoteTitle.text.toString()
            noteDesc       = binding.ETUpdateNoteDescription.text.toString()
            noteCreateDate = getTimeNow()
            noteEditFlag   = 1
            MainApplication.sharedPreferencesManager[USER_ID, -1]?.let {
                userId = it
            }
        }
        _noteModel?.let {
            updateNoteViewModel.updateNoteItem(it)
        }

    }

    private val _updateNoteObserver = Observer<Resource<Boolean>>{
        when(it.status){
            Status.LOADING -> (requireActivity() as MainActivity).show()
            Status.ERROR -> {
                (requireActivity() as MainActivity).hide()
                (requireActivity() as MainActivity).showToast(it.message)
            }
            Status.SUCCESS -> {
                (requireActivity() as MainActivity).hide()
                Log.d(UPDATE_A_NOTE_FRA_TAG, SUCCESS_MESSAGE)
                ((requireActivity() as MainActivity)).showToast(SUCCESS_MESSAGE)
                EventBus.getDefault().post("Dismiss")
                this.dismissAllowingStateLoss()
            }
        }
    }
}