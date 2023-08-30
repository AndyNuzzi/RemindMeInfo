package com.example.remindmeinfo.ui.pharmacy_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PharmacyInfoUserViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is pharmacy info Fragment"
    }
    val text: LiveData<String> = _text
}