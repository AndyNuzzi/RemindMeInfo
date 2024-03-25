package com.example.remindmeinfo.ui.reminder_admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentReminderAdminBinding

class ReminderAdminFragment : Fragment() {

    private var _binding: FragmentReminderAdminBinding? = null

    private lateinit var message1: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_reminder_admin, container, false)

        message1 = view.findViewById(R.id.text_reminder)

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}