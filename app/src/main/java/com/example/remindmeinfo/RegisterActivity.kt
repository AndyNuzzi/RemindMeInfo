package com.example.remindmeinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    val currentUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var check = findViewById<CheckBox>(R.id.check_admin_app)
        var extraField = findViewById<EditText>(R.id.extraField)

        var check_cipa = findViewById<CheckBox>(R.id.check_cipa)
        var cipa_num = findViewById<EditText>(R.id.cipaData)

        var age = findViewById<EditText>(R.id.textAge)
        var genre = findViewById<EditText>(R.id.textGenre)
        var info = findViewById<EditText>(R.id.textMedicalInfo)

        check.setOnClickListener{
            extraField.visibility = if (check.isChecked) View.VISIBLE else View.GONE
            check_cipa.visibility = if (check.isChecked) View.GONE else View.VISIBLE
        }

        check_cipa.setOnClickListener {
            cipa_num.visibility = if (check_cipa.isChecked) View.VISIBLE else View.GONE
            age.visibility = if (check_cipa.isChecked) View.VISIBLE else View.GONE
            genre.visibility = if (check_cipa.isChecked) View.VISIBLE else View.GONE
            info.visibility = if (check_cipa.isChecked) View.VISIBLE else View.GONE
            //ocultar check del admin
            check.visibility = if (check_cipa.isChecked) View.GONE else View.VISIBLE
        }
    }

    override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }

    fun saveRegister(view: View){
        var email = currentUser?.email.toString()
        var dbRegister = FirebaseFirestore.getInstance()

        var name = findViewById<EditText>(R.id.textName).getText().toString()

        var surname = findViewById<EditText>(R.id.textSurname).getText().toString()
        var age = findViewById<EditText>(R.id.textAge).getText().toString()
        var genre = findViewById<EditText>(R.id.textGenre).getText().toString()
        var info = findViewById<EditText>(R.id.textMedicalInfo).getText().toString()

        //user admin
        var extraField_b = findViewById<EditText>(R.id.extraField).getText().toString()

        //dato user
        var cipa_user = findViewById<EditText>(R.id.cipaData).getText().toString()

        var check_b = findViewById<CheckBox>(R.id.check_admin_app)
        var check_cipa_bool = findViewById<CheckBox>(R.id.check_cipa)

        var check_admin_verified = check_b.isChecked

        var check_cipa_verified = check_cipa_bool.isChecked

        if (check_admin_verified){
            if (extraField_b == ""){
                Toast.makeText(this, "Introduzca el email del usuario receptor", Toast.LENGTH_LONG).show()
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            } else {
                dbRegister.collection("users").document(email).update(
                    "user_elder", extraField_b)
                dbRegister.collection("users").document(extraField_b).update(
                    "user_admin", email)
            }
        }

        if (check_cipa_verified){
            dbRegister.collection("users").document(email).update(
                "cipa_number", cipa_user)
        }

        dbRegister.collection("users").document(email).update(
            "bool_admin", check_admin_verified)
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

        goNext()
    }

    fun goNext(){
        var email = currentUser?.email.toString()
        var db = FirebaseFirestore.getInstance()
        db.collection("users").document(email).get()
            .addOnSuccessListener { documento ->
                if (documento.exists()) {
                    val bool = documento.get("bool_admin")

                    if (bool == true){
                        val intent = Intent(this, MainActivityAdmin::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this, MainActivityUser::class.java)
                        startActivity(intent)
                    }
                }
            }


    }
}