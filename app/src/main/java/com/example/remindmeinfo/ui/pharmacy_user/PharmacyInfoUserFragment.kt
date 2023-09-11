package com.example.remindmeinfo.ui.pharmacy_user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.remindmeinfo.databinding.FragmentPharmacyInfoUserBinding

class PharmacyInfoUserFragment : Fragment() {
    private var _binding: FragmentPharmacyInfoUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mapViewModel =
            ViewModelProvider(this).get(PharmacyInfoUserViewModel::class.java)

        _binding = FragmentPharmacyInfoUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPharma
        mapViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}