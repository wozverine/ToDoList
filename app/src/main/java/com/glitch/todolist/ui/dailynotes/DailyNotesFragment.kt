package com.glitch.todolist.ui.dailynotes

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.glitch.todolist.R
import com.glitch.todolist.data.source.Database
import com.glitch.todolist.databinding.DialogAddNoteBinding
import com.glitch.todolist.databinding.FragmentDailyNotesBinding
import java.util.Locale

class DailyNotesFragment : Fragment(R.layout.fragment_daily_notes) {

    private var _binding: FragmentDailyNotesBinding? = null
    private lateinit var sharedPref: SharedPreferences

    private val binding get() = _binding!!
    private val dailyNotesAdapter = DailyNotesAdapter(
        onNoteClick = ::onNoteClick/*onNoteClick = {description -> Toast.makeText(requireContext(), description, Toast.LENGTH_SHORT).show()}*/
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDailyNotesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val mail = sharedPref.getString("mail", "name@gmail.com") ?: "y/n"

        with(binding) {
            dailyNotesRv.adapter = dailyNotesAdapter
            dailyNotesAdapter.updateList(Database.getDailyNotes())
            floatingActionButton2.setOnClickListener {
                showAddDialog()
            }
            name.text = buildString {
                append(getString(R.string.hello))
                append(" ")
                append(mail.substringBefore("@").replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                })
            }

        }/*binding.dailyNotesRv.adapter = dailyNotesAdapter

        dailyNotesAdapter.updateList(Database.getDailyNotes())*/

    }

    private fun onNoteClick(desc: String) {
        Toast.makeText(requireContext(), desc, Toast.LENGTH_SHORT).show()
    }

    private fun showAddDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogBinding = DialogAddNoteBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)
        val dialog = builder.create()

        with(dialogBinding) {
            button.setOnClickListener() {
                val title = textView.text.toString()
                val desc = textView3.text.toString()

                if (title.isNotEmpty() && desc.isNotEmpty()) {
                    Database.addDailyNotes(title, desc)
                    //binding.dailyNotesRv.adapter = dailyNotesAdapter
                    dailyNotesAdapter.updateList(Database.getDailyNotes())
                    dialog.dismiss()
                } else {
                    Toast.makeText(
                        requireContext(), getString(R.string.fill_blanks), Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}