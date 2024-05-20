package com.example.remindmeinfo.ui.calendar_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.remindmeinfo.R
import com.example.remindmeinfo.SpacesItemDecorationGrid
import com.example.remindmeinfo.databinding.FragmentItemListCalendarUserBinding
import com.example.remindmeinfo.ui.calendar_user.placeholder.PlaceholderContent
import com.example.remindmeinfo.ui.reminder_list_user.DetailUserFragment
import java.text.SimpleDateFormat
import java.util.*

class ItemCalendarUserFragment : Fragment() {
    private var _binding: FragmentItemListCalendarUserBinding? = null
    private val binding get() = _binding!!

    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    private fun loadReminders() {
        PlaceholderContent.createPlaceholderItem {
            view?.findViewById<RecyclerView>(R.id.list)?.adapter?.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListCalendarUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateToday = SimpleDateFormat("dd/MM/yyyy").format(Date())
        binding.tvTitle.text = dateToday

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(binding.list) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            addItemDecoration(SpacesItemDecorationGrid(10, 10, true))
            adapter=
                MyItemCalendarUserRecyclerViewAdapter(PlaceholderContent.ITEMS){itemId->
                    val detailFragment = DetailUserFragment().apply{
                        arguments=Bundle().apply{
                            putString("documentId",itemId)
                        }
                    }
                    requireFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, detailFragment)
                        .addToBackStack(null)
                        .commit()
                }
        }
        loadReminders()
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemCalendarUserFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}