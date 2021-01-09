package com.task.noteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.task.noteapp.database.model.UserModel
import com.task.noteapp.manager.SharedPreferencesManager.get
import com.task.noteapp.manager.SharedPreferencesManager.set
import com.task.noteapp.utils.*

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

    //Account Actions
    fun logOutAction(){

        MainApplication.sharedPreferencesManager[USER_EMAIL] = ""
        MainApplication.sharedPreferencesManager[USER_PASSWORD] = ""
        MainApplication.sharedPreferencesManager[USER_NAME]= ""
        MainApplication.sharedPreferencesManager[USER_ID] = -1

    }

    fun getUserNameFromSharedPref() =  MainApplication.sharedPreferencesManager[USER_NAME, ""]

    fun getUserIdFromSharedPref() =  MainApplication.sharedPreferencesManager[USER_ID, -1]

    fun setUserOnSharedPref(user: UserModel) {
        MainApplication.sharedPreferencesManager[USER_EMAIL] = user.userEmail
        MainApplication.sharedPreferencesManager[USER_PASSWORD] = user.userPassword
        MainApplication.sharedPreferencesManager[USER_NAME]= user.userName
        MainApplication.sharedPreferencesManager[USER_ID]= user.userId
    }


}