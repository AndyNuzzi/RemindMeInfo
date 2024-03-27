package com.example.remindmeinfo.ui.reminder_admin

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.FragmentManager
import com.example.remindmeinfo.MainActivityAdmin
import com.example.remindmeinfo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddingReminderActivity : AppCompatActivity() {

    val currentUser = FirebaseAuth.getInstance().currentUser

    private lateinit var fragmentManager: FragmentManager

    var date = ""
    var selectedItem = ""

    var idDocumentoEncontrado = ""

    var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_reminder)

        fragmentManager = supportFragmentManager

        var check = findViewById<CheckBox>(R.id.check_admin_reminder)
        val editTextDate = findViewById<EditText>(R.id.editTextDate)

        check.setOnClickListener{
            editTextDate.visibility = if (check.isChecked) View.VISIBLE else View.GONE
        }

        editTextDate.setOnClickListener {
            showDatePickerDialog()
        }

        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.dropdown_items,
            R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // Listener para manejar la selección de elementos
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position > 0) {
                    selectedItem = parent.getItemAtPosition(position).toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Opcional: código para cuando no se selecciona ningún elemento
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
            // Formatear la fecha seleccionada y asignarla al EditText
            val selectedDate = "${dayOfMonth}/${monthOfYear + 1}/$year"
            findViewById<EditText>(R.id.editTextDate).setText(selectedDate)

            date = selectedDate
        }, year, month, day)

        dpd.show()
    }

    override fun onBackPressed() {
        // No llamar a super.onBackPressed() para ignorar el evento
    }

    fun saveNewRemind(view:View){
        var email = currentUser?.email.toString()
        var dbRegister = FirebaseFirestore.getInstance()

        var title = findViewById<EditText>(R.id.textTitle).getText().toString()
        var subtitle = findViewById<EditText>(R.id.textSubtitle).getText().toString()

        var check_b = findViewById<CheckBox>(R.id.check_admin_reminder)

        var check_verified = check_b.isChecked

        val  deb = dbRegister.collection("reminders").document(email).collection("remind").document()

        deb.set(hashMapOf(
            "title" to title,
            "subtitle" to subtitle,
            "email" to email
        ))

        val id_doc = deb.id

        if (check_verified){
            dbRegister.collection("reminders").document(email).collection("remind").document(id_doc).update("date", date)
        }

        if (selectedItem != ""){
            when (selectedItem) {
                "Rosa"       -> dbRegister.collection("reminders").document(email).collection("remind").document(id_doc).update("color", "#FFFFD2E1")
                "Amarillo"    -> dbRegister.collection("reminders").document(email).collection("remind").document(id_doc).update("color", "#FFFFF9C3")
                "Azul"    -> dbRegister.collection("reminders").document(email).collection("remind").document(id_doc).update("color", "#FFB0E6FF")
                "Verde" -> dbRegister.collection("reminders").document(email).collection("remind").document(id_doc).update("color", "#FFDEFFB8")
                else       -> "Unknown" // Caso por defecto, similar a default en un switch
            }
        }

        dbRegister.collection("users").document(email)
            .get()
            .addOnSuccessListener{document ->
                if (document != null) {
                    val elder = document.getString("user_elder").toString()
                    dbRegister.collection("reminders").document(email).collection("remind").document(id_doc).update("user_elder", elder)
                }
            }

        val intent = Intent(this, MainActivityAdmin::class.java)
        startActivity(intent)
    }
}