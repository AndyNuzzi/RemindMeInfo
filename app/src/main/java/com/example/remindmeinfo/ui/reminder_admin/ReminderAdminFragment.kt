package com.example.remindmeinfo.ui.reminder_admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.remindmeinfo.databinding.FragmentReminderAdminBinding

class ReminderAdminFragment : Fragment() {

    private var _binding: FragmentReminderAdminBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val reminderViewModel =
            ViewModelProvider(this).get(ReminderAdminViewModel::class.java)

        _binding = FragmentReminderAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textReminder
        reminderViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}