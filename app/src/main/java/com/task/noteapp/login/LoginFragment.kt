package com.task.noteapp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.task.noteapp.BaseFragment
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentHomeBinding
import com.task.noteapp.databinding.FragmentLoginBinding
import com.task.noteapp.utils.EMAIL_ADDRESS_PATTERN
import com.task.noteapp.utils.EMAIL_INVALID_MESSAGE
import com.task.noteapp.utils.PASSWORD_INVALID_MESSAGE
import com.task.noteapp.utils.isValidPassword


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : BaseFragment(),View.OnClickListener {

    //Navigation Component controller
    private lateinit var navController: NavController

    //View Binding
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUIInit()
    }

    private fun setUIInit() {
        binding.BTNLogin.setOnClickListener(this)
    }

    private fun checkValidation(): Boolean {
        var validationFlag = true
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
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {

            }
    }

    override fun onClick(p0: View?) {
        p0?.let{
            when(p0.id){
                binding.BTNLogin.id -> submitAction()
            }
        }
    }

    private fun submitAction() {
        if(checkValidation())
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
    }
}