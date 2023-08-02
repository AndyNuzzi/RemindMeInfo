package com.example.remindmeinfo.ui.map_admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.remindmeinfo.databinding.FragmentMapAdminBinding

class MapAdminFragment : Fragment() {

    private var _binding: FragmentMapAdminBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mapViewModel =
            ViewModelProvider(this).get(MapAdminViewModel::class.java)

        _binding = FragmentMapAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMap
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