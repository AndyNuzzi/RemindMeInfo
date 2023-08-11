package com.example.remindmeinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.remindmeinfo.ui.help.HelpFragment

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        supportActionBar!!.setIcon(R.mipmap.ic_launcher_round)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
}