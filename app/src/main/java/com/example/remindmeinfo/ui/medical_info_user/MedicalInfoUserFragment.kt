package com.example.remindmeinfo.ui.medical_info_user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentMedicalInfoUserBinding
import com.example.remindmeinfo.ui.map_admin.MapAdminViewModel

class MedicalInfoUserFragment : Fragment() {
    private var _binding: FragmentMedicalInfoUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mapViewModel =
            ViewModelProvider(this).get(MedicalInfoUserViewModel::class.java)

        _binding = FragmentMedicalInfoUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMedicalInfo
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