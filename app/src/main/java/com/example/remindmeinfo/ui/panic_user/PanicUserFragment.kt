package com.example.remindmeinfo.ui.panic_user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentPanicUserBinding
import com.example.remindmeinfo.ui.map_admin.MapAdminViewModel

class PanicUserFragment : Fragment() {

    private var _binding: FragmentPanicUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mapViewModel =
            ViewModelProvider(this).get(PanicUserViewModel::class.java)

        _binding = FragmentPanicUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPanic
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