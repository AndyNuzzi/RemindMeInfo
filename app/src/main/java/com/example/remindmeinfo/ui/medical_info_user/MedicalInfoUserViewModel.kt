package com.example.remindmeinfo.ui.medical_info_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MedicalInfoUserViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Medical info Fragment"
    }
    val text: LiveData<String> = _text
}