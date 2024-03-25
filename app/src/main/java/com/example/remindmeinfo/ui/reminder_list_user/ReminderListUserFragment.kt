package com.example.remindmeinfo.ui.reminder_list_user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentReminderListUserBinding

class ReminderListUserFragment : Fragment() {

    private var _binding: FragmentReminderListUserBinding? = null

    private lateinit var message1: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_reminder_list_user, container, false)

        message1 = view.findViewById(R.id.text_reminder_list)

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}