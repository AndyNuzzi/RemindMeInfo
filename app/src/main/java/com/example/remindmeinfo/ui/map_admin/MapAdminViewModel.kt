package com.example.remindmeinfo.ui.map_admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapAdminViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Map Fragment"
    }
    val text: LiveData<String> = _text
}