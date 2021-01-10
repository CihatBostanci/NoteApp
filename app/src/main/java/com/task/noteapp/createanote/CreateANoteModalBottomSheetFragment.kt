package com.task.noteapp.createanote

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.task.noteapp.MainActivity
import com.task.noteapp.MainApplication
import com.task.noteapp.R
import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.databinding.FragmentCreateANoteModalBottomSheetBinding
import com.task.noteapp.manager.SharedPreferencesManager.get
import com.task.noteapp.utils.*
import org.greenrobot.eventbus.EventBus
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

private const val CREATE_A_NOTE_FRA_TAG = "CREATENOTEFRATAG"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateANoteModalBottomSheetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateANoteModalBottomSheetFragment:  BottomSheetDialogFragment(), View.OnClickListener{

    private val createANoteViewModel by viewModel<CreateANoteViewModel>()


    //View Binding
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentCreateANoteModalBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateANoteModalBottomSheetBinding.inflate(inflater, container, false)
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
        createANoteViewModel.createANoteLiveData.observe(this, _createANoteObserver)

    }

    private fun setUIInit() {
        binding.BTNCreateANote.setOnClickListener(this)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateANoteModalBottomSheetFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            CreateANoteModalBottomSheetFragment().apply {

            }
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when(p0.id){
                binding.BTNCreateANote.id -> createNoteAction()
            }
        }
    }
    private fun checkValidation(): Boolean {
        var validationFlag = true
        if (binding.ETAddNoteTitle.text?.length !in 2..12 )
        {
            binding.TILAddNoteTitle.error = NOTE_TITLE_INVALID_MESSAGE
            validationFlag = false
            return validationFlag
        } else {
            binding.TILAddNoteTitle.isErrorEnabled = false
        }
        if (binding.ETAddNoteDescription.text?.length !in 2..100)
        {
            binding.ETAddNoteDescription.error = NOTE_DESCRIPTION_INVALID_MESSAGE
            validationFlag = false
            return validationFlag
        } else {
            binding.ETAddNoteDescription.isEnabled = false
        }

        return validationFlag
    }

    private fun createNoteAction() {
            if(checkValidation()){
                getNoteModel()?.let {
                    createANoteViewModel.addNote(it)
                }
            }
    }

    private fun getNoteModel() =
        MainApplication.sharedPreferencesManager[USER_ID, -1]?.let {
            NoteModel(binding.ETAddNoteTitle.text.toString(),
                binding.ETAddNoteDescription.text.toString(),
                getTimeNow(),0, it
            )
        }

    //Observers
    private val _createANoteObserver = Observer<Resource<Boolean>>  {

        when(it.status){
            Status.LOADING -> (requireActivity() as MainActivity).show()
            Status.ERROR -> {
                (requireActivity() as MainActivity).hide()
                (requireActivity() as MainActivity).showToast(it.message)
            }
            Status.SUCCESS -> {
                (requireActivity() as MainActivity).hide()
                Log.d(CREATE_A_NOTE_FRA_TAG, SUCCESS_MESSAGE)
                ((requireActivity() as MainActivity)).showToast(SUCCESS_MESSAGE)
                EventBus.getDefault().post("Dismiss")
                this.dismissAllowingStateLoss()
            }
        }
    }
}