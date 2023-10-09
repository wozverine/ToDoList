package com.glitch.todolist.ui.login

import android.os.Bundle
import android.util.Patterns
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
                val email = etMailLogin.text.toString()
                val password = etPasswordLogin.text.toString()

                if (checkFields(email = email, password = password)) {
                    findNavController().navigate(R.id.action_loginFragment_to_DailyNotesFragment2)
                } else {
                    Snackbar.make(it, getString(R.string.fill_blanks), Snackbar.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun checkFields(email: String, password: String): Boolean {
        return when {
            Patterns.EMAIL_ADDRESS.matcher(email).matches().not() -> false
            password.isEmpty() -> false
            password.length < 6 -> false
            else -> true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}