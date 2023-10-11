package com.glitch.todolist.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.glitch.todolist.R
import com.glitch.todolist.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (isLogin) {
            findNavController().navigate(R.id.action_loginFragment_to_DailyNotesFragment2)
        }

        with(binding) {
            loginNextBtn.setOnClickListener {
                val email = etMailLogin.text.toString()
                val password = etPasswordLogin.text.toString()
                val test = checkFields(email = email, password = password)

                if (!test[0]) {
                    Snackbar.make(it, getString(R.string.fill_blanks), Snackbar.LENGTH_SHORT).show()
                } else if (!test[2]) {
                    Snackbar.make(it, getString(R.string.password_short), Snackbar.LENGTH_SHORT)
                        .show()
                } else if (!test[1]) {
                    Snackbar.make(it, getString(R.string.email_format), Snackbar.LENGTH_SHORT)
                        .show()
                } else {
                    sharedPref.edit().putBoolean("isLogin", true).apply()
                    sharedPref.edit().putString("mail", email).apply()
                    findNavController().navigate(R.id.action_loginFragment_to_DailyNotesFragment2)

                }
            }
        }
    }

    private fun checkFields(email: String, password: String): (BooleanArray) {
        var boolArray = booleanArrayOf(false, false, false)
        boolArray[0] = !(password.isEmpty() || email.isEmpty())
        boolArray[1] = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        boolArray[2] = password.length >= 6

        return boolArray
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}