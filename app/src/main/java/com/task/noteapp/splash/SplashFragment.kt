package com.task.noteapp.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.task.noteapp.BaseFragment
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


private const val SPLASH_TAG = "SPLASHFRATAG"
private const val HAVE_IT_YOUR_WAY= "Have It Your Way"
/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : BaseFragment() , CoroutineScope {

    private val SPLASH_TIME_OUT =  4000L
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()


    //Navigation Component controller
    private lateinit var navController: NavController

    //View Binding
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUIInit()
    }

    private fun setUIInit() {
        binding.TWSplashTitle.text = HAVE_IT_YOUR_WAY
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.bounce)
        binding.TWSplashTitle.animation = animation

        launch {
            delay(SPLASH_TIME_OUT)
            withContext(Dispatchers.Main){
                navController.navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment SplashFragment.
         */

        @JvmStatic
        fun newInstance() =
            SplashFragment().apply {
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}