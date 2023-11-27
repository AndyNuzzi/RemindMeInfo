package com.example.remindmeinfo.ui.help


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import com.example.remindmeinfo.R
import com.example.remindmeinfo.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private var _binding: FragmentHelpBinding? = null

    private lateinit var message1: TextView
    private lateinit var message2: TextView
    private lateinit var message3: TextView
    private lateinit var message4: TextView
    private lateinit var message5: TextView
    private lateinit var message6: TextView
    private lateinit var message7: TextView
    private lateinit var message8: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        message1 = view.findViewById(R.id.message1)
        message2 = view.findViewById(R.id.message2)
        message3 = view.findViewById(R.id.message3)
        message4 = view.findViewById(R.id.message4)
        message5 = view.findViewById(R.id.message5)
        message6 = view.findViewById(R.id.message6)
        message7 = view.findViewById(R.id.message7)
        message8 = view.findViewById(R.id.message8)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}