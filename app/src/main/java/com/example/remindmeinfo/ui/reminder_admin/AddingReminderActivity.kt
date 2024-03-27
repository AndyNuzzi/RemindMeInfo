package com.example.remindmeinfo.ui.reminder_admin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.remindmeinfo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddingReminderActivity : AppCompatActivity() {

    val currentUser = FirebaseAuth.getInstance().currentUser

    var date = ""
    var selectedItem = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_reminder)

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


        dbRegister.collection("reminders").document(email).set(hashMapOf(
            "title" to title,
            "subtitle" to subtitle,
            "email" to email
        ))

        if (check_verified){
            dbRegister.collection("reminders").document(email).update("date", date)
        }

        if (selectedItem != ""){
            dbRegister.collection("reminders").document(email).update("color", selectedItem)
        }

        dbRegister.collection("users").document(email)
            .get()
            .addOnSuccessListener{document ->
                if (document != null) {
                    val elder = document.getString("user_elder").toString()
                    dbRegister.collection("reminders").document(email).update("user_elder", elder)
                }
            }
    }
}