package com.example.remindmeinfo.ui.reminder_list_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReminderListUserViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is reminder list Fragment"
    }
    val text: LiveData<String> = _text
}