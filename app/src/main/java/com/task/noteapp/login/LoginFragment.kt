package com.task.noteapp.login


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.task.noteapp.BaseFragment
import com.task.noteapp.R
import com.task.noteapp.database.model.UserModel
import com.task.noteapp.databinding.FragmentLoginBinding
import com.task.noteapp.utils.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val LOGIN_FRAGMENT_TAG = "LOGINFRATAG"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : BaseFragment(), View.OnClickListener {

    //Navigation Component controller
    private lateinit var navController: NavController

    //ViewModel
    private val loginViewModel by viewModel<LoginViewModel>()

    //View Binding
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUIInit()
        initObservers()
    }

    private fun initObservers() {
        loginViewModel.allUserLiveData.observe(viewLifecycleOwner, _allUserObserver)
        loginViewModel.getUserLiveData.observe(viewLifecycleOwner, _getUserObserver)
        loginViewModel.addUserLiveData.observe(viewLifecycleOwner, _addUserObserver)
    }

    private fun setUIInit() {
        binding.BTNLogin.bringToFront()
        val userEmail = getUserNameFromSharedPref()
        if (userEmail != null && userEmail.isNotEmpty()) {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
        binding.BTNLogin.setOnClickListener(this)
    }

    private fun checkValidation(): Boolean {
        var validationFlag = true
        if (binding.ETLoginUserName.text?.length !in 2..12) {
            binding.TILLoginUserName.error = USER_NAME_INVALID_MESSAGE
            validationFlag = false
            return validationFlag
        } else {
            binding.TILLoginUserName.isErrorEnabled = false
        }
        if (!EMAIL_ADDRESS_PATTERN.matcher(binding.ETLoginEmail.text.toString()).matches()) {
            binding.TILLoginEmail.error = EMAIL_INVALID_MESSAGE
            validationFlag = false
            return validationFlag
        } else {
            binding.TILLoginEmail.isErrorEnabled = false
        }

        if (binding.ETLoginPassword.text?.length !in 5..12 &&
            !isValidPassword(binding.ETLoginPassword.text.toString())
        ) {
            binding.TILLoginPassword.error = PASSWORD_INVALID_MESSAGE
            validationFlag = false
            return validationFlag
        } else {
            binding.TILLoginPassword.isErrorEnabled = false
        }
        return validationFlag
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment LoginFragment.
         */
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {

            }
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (p0.id) {
                binding.BTNLogin.id -> submitAction()
            }
        }
    }

    private fun submitAction() {
        if (checkValidation()) {
            loginViewModel.fetchDataAllUsers()
        }
    }
    private fun checkUserInDatabase( userListNonNull: MutableList<UserModel>):Boolean{
        var userInDatabase = false
        for (i in userListNonNull) {
            if (i.userEmail == binding.ETLoginEmail.text.toString()
                && i.userPassword == binding.ETLoginPassword.text.toString()
            ) {
                userInDatabase = true
                break
            }
        }
        return userInDatabase
    }
    private fun userInDatabaseAction() {
        loginViewModel.getUserByEmailAndPassword(
            binding.ETLoginEmail.text.toString(),
            binding.ETLoginPassword.text.toString()
        )
    }

    private fun userInNotDatabaseAction() {
        val userModel = UserModel().apply {
            userEmail = binding.ETLoginEmail.text.toString()
            userPassword = binding.ETLoginPassword.text.toString()
            userName = binding.ETLoginUserName.text.toString()
        }
        loginViewModel.addUser(userModel)
    }

    //Observers
    private val _allUserObserver = Observer<Resource<MutableList<UserModel>>> {

        when (it.status) {
            Status.LOADING -> show()
            Status.ERROR -> {
                hide()
                showToast(it.message)
            }
            Status.SUCCESS -> {
                it.data?.let { userListNonNull ->
                    hide()
                    Log.d(LOGIN_FRAGMENT_TAG, userListNonNull.toString())
                    if(checkUserInDatabase(userListNonNull)){
                        userInDatabaseAction()
                    } else {
                        userInNotDatabaseAction()
                    }
                }
            }
        }
    }

    private val _getUserObserver = Observer<Resource<UserModel>> {
        when (it.status) {
            Status.LOADING -> show()
            Status.ERROR -> {
                hide()
                showToast(it.message)
            }
            Status.SUCCESS -> {
                it.data?.let { user ->
                    hide()
                    Log.d(LOGIN_FRAGMENT_TAG, it.data.toString())
                    showToast(SUCCESS_MESSAGE)
                    setUserOnSharedPref(user)
                    navController.navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        }
    }
    private val _addUserObserver = Observer<Resource<Boolean>> {
        when (it.status) {
            Status.LOADING -> show()
            Status.ERROR -> {
                hide()
                showToast(it.message)
            }
            Status.SUCCESS -> {
                it.data?.let { _ ->
                    hide()
                    Log.d(LOGIN_FRAGMENT_TAG, it.data.toString())
                    showToast(ADDED_SUCCESS)
                    loginViewModel.getUserByEmailAndPassword(
                        binding.ETLoginEmail.text.toString(),
                        binding.ETLoginPassword.text.toString()
                    )
                }
            }
        }
    }
}