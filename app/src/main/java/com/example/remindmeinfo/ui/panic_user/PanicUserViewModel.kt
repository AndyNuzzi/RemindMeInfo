package com.example.remindmeinfo.ui.panic_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PanicUserViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Panic Bottom Fragment"
    }
    val text: LiveData<String> = _text
}