package com.example.remindmeinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar!!.setIcon(R.mipmap.ic_launcher_round)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }
}