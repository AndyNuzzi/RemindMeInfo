package com.example.remindmeinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.remindmeinfo.databinding.ActivityVitalControlBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class VitalControlActivity : AppCompatActivity() {

    val currentUser = FirebaseAuth.getInstance().currentUser

    private lateinit var binding: ActivityVitalControlBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVitalControlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }

    fun saveVitalInfo(view: View){
        var email = currentUser?.email.toString()
        var dbSave = FirebaseFirestore.getInstance()

        var diabText = findViewById<EditText>(R.id.textDataDiab).getText().toString()
        var heartText = findViewById<EditText>(R.id.textDataHeart).getText().toString()
        var tensionHText = findViewById<EditText>(R.id.textDataTension).getText().toString()
        var tensionLText = findViewById<EditText>(R.id.textDataTension2).getText().toString()

        var dateRegister = SimpleDateFormat("dd/MM/yyyy").format(Date())

        dbSave.collection("vital_data").document(email).set(hashMapOf(
            "diabetes_data" to diabText,
            "heart_data" to heartText,
            "tension_high" to tensionHText,
            "tension_low" to tensionLText,
            "current_date" to dateRegister
        ))

        sendEmail(email, diabText, heartText, tensionHText, tensionLText)

    }

    private fun sendEmail(email: String, diabText: String, heartText: String, tensionHText: String, tensionLText: String) {

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("rmeinfotfg@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Información vital del usuario " + email)
        intent.putExtra(Intent.EXTRA_TEXT, """Buenos días, 
        La información vital recopilada del usuario es la siguiente: 
         - Datos de diabetes --> ${diabText}
         - Datos de pulso cardíaco --> ${heartText}
         - Datos de la tensión --> ${tensionHText} (alta) y ${tensionLText} (baja)
        Un saludo,
        Gracias!!"""
        )
        startActivity(intent)
    }
}