package com.glitch.todolist.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.glitch.todolist.R
import com.glitch.todolist.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            loginNextBtn.setOnClickListener {
                val username = etNameLogin.text.toString()
                val password = etPasswordLogin.text.toString()

                if (checkFields(username = username,password = password)){

                } else {
                    Snackbar.make(it,getString(R.string.fill_blanks),Snackbar.LENGTH_SHORT).show()
                }

                findNavController().navigate(R.id.action_loginFragment_to_DailyNotesFragment2)
            }
        }
    }

    private fun checkFields(username: String, password: String): Boolean {
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}