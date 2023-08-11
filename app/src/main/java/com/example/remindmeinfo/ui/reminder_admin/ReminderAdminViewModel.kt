package com.example.remindmeinfo.ui.reminder_admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReminderAdminViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is reminder Fragment"
    }
    val text: LiveData<String> = _text
}