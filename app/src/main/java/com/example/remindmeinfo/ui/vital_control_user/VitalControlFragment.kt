package com.example.remindmeinfo.ui.vital_control_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentVitalControlUserBinding
import com.google.firebase.auth.FirebaseAuth

class VitalControlFragment : Fragment() {
    private var _binding: FragmentVitalControlUserBinding? = null

    val currentUser = FirebaseAuth.getInstance().currentUser

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVitalControlUserBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun saveVitalInfo(view: View){

    }
}