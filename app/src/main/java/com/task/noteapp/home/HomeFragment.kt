package com.task.noteapp.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.noteapp.BaseFragment
import com.task.noteapp.R

import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.databinding.FragmentHomeBinding
import com.task.noteapp.home.adapter.NoteAdapter
import com.task.noteapp.utils.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.android.viewmodel.ext.android.viewModel


private const val HOME_TAG = "HOMEFRATAG"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment(), View.OnClickListener,
    NoteAdapter.ViewHolder.editNoteItemClickListener,
    NoteAdapter.ViewHolder.deleteNoteItemClickListener{


    private val homeViewModel by viewModel<HomeViewModel>()

    private var noteAdapter: NoteAdapter? = null

    //Navigation Component controller
    private lateinit var navController: NavController

    //View Binding
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EventBus.getDefault().register(this);
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    fun onEvent(event : String){
        getUserIdFromSharedPref()?.let { homeViewModel.fetchDataAllNotes(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUIInit()
        initObservers()

    }

    private fun initObservers() {
        homeViewModel.noteListLiveData.observe(viewLifecycleOwner, _noteListObserver)
        homeViewModel.deleteNoteLiveData.observe(viewLifecycleOwner, _deleteNoteItemObserver)

    }

    private fun setUIInit() {
        val user = getUserNameFromSharedPref()
        binding.FABCreateANote.bringToFront()
        binding.TWHomeTitle.text = "$user Notes"
        binding.IWHomeLogOut.setOnClickListener(this)
        binding.FABCreateANote.setOnClickListener(this)

        getUserIdFromSharedPref()?.let { homeViewModel.fetchDataAllNotes(it) }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id) {
                binding.IWHomeLogOut.id -> logOutActionFromHome()
                binding.FABCreateANote.id -> createANoteAction()
            }
        }
    }

    private fun createANoteAction() {

        navController.navigate(R.id.action_homeFragment_to_createANoteModalBottomSheetFragment)
    }

    private fun logOutActionFromHome() {
        super.logOutAction()
        navController.navigate(R.id.action_homeFragment_to_loginFragment)

    }

    //Click Listeners
    override fun editNoteItemClickListener(data: NoteModel) {
        val bundle = bundleOf(
            UPDATE_KEY to data)

        navController.navigate(R.id.action_homeFragment_to_updateNoteModalBottomSheetFragment,bundle)
    }

    override fun deleteNoteItemClickListener(data: NoteModel) {
        //showToast(data.toString())
        homeViewModel.deleteNoteItem(data.noteId)
    }

    //Observers
    private val _noteListObserver = Observer<Resource<MutableList<NoteModel>>> {
        when (it.status) {
            Status.LOADING -> show()
            Status.ERROR -> {
                hide()
                showToast(it.message)
            }
            Status.SUCCESS -> {
                it.data?.let { noteListNonNull ->
                    hide()
                    Log.d(HOME_TAG, it.data.toString())
                    noteAdapter = NoteAdapter(
                        noteListNonNull,
                        this,
                        this
                    )
                    val layoutManager = LinearLayoutManager(requireContext())
                    binding.RVNoteList.layoutManager = layoutManager
                    binding.RVNoteList.itemAnimator = DefaultItemAnimator()
                    binding.RVNoteList.adapter = noteAdapter
                }
                //showToast(it.data.toString())
            }
        }
    }

    private val _deleteNoteItemObserver = Observer<Resource<Boolean>> {
        when (it.status) {
            Status.LOADING -> show()
            Status.ERROR -> {
                hide()
                showToast(it.message)
            }
            Status.SUCCESS -> {
                it.data?.let { noteListNonNull ->
                    hide()
                    Log.d(HOME_TAG, it.data.toString())
                    showToast(DELETE_MESSAGE)
                    getUserIdFromSharedPref()?.let { homeViewModel.fetchDataAllNotes(it) }
                }
            }
        }
    }


}