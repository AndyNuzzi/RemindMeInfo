package com.example.remindmeinfo.ui.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentSecondHelpBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondHelpFragment : Fragment() {

    private var _binding: FragmentSecondHelpBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondHelpBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondHelpFragment_to_mainActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}