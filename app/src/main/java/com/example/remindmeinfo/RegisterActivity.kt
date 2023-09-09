package com.example.remindmeinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    val currentUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun saveRegister(view: View){
        var email = currentUser?.email.toString()
        var dbRegister = FirebaseFirestore.getInstance()

        var name = findViewById<EditText>(R.id.textName).getText().toString()

        var surname = findViewById<EditText>(R.id.textSurname).getText().toString()
        var age = findViewById<EditText>(R.id.textAge).getText().toString()
        var genre = findViewById<EditText>(R.id.textGenre).getText().toString()
        var info = findViewById<EditText>(R.id.textMedicalInfo).getText().toString()
        var familyName = findViewById<EditText>(R.id.familyName).getText().toString()

        var check = findViewById<CheckBox>(R.id.check_admin_app)
        var check_verified = check.isChecked


        dbRegister.collection("users").document(email).update(
            "bool_admin", check_verified)
        dbRegister.collection("users").document(email).update(
            "name", name)
        dbRegister.collection("users").document(email).update(
            "surname", surname)
        dbRegister.collection("users").document(email).update(
            "age", age)
        dbRegister.collection("users").document(email).update(
            "genre", genre)
        dbRegister.collection("users").document(email).update(
            "aditional_info", info)
        dbRegister.collection("users").document(email).update(
            "family", familyName)

        goNext()
    }

    fun goNext(){
        val intent = Intent(this, MainActivityAdmin::class.java)
        startActivity(intent)
    }
}