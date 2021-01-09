package com.task.noteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.task.noteapp.utils.IOnBackPressed
import com.task.noteapp.utils.ProgressDisplay

const val BASEFRAGMENTTAG = "BASEFRATAG"

abstract class BaseFragment: Fragment(), ProgressDisplay, IOnBackPressed {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextView(activity).apply {
            setText("Base Fragment")
        }
    }


    override fun show() {
        if (requireActivity() is ProgressDisplay) {
            (activity as ProgressDisplay?)!!.show()
        }
    }

    override fun hide() {
        if (requireActivity() is ProgressDisplay) {
            (activity as ProgressDisplay?)!!.hide()
        }
    }

    override fun onBackPressed(): Boolean {
        requireActivity().onBackPressed()
        return true
    }


    fun showToast(message:String?){
        (requireActivity() as MainActivity).showToast(message)
    }
}