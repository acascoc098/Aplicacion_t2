package com.example.aplicacion_t2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast

class Login : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var chk1: CheckBox
    private lateinit var chk2: CheckBox
    private lateinit var chk3: CheckBox
    private lateinit var spinner: Spinner
    private lateinit var radioGroup: RadioGroup

    private val MYUSSER = "acascoc098"
    private val MYPASS = "acascoc"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.log_in)

        usernameEditText = findViewById(R.id.usuario)
        passwordEditText = findViewById(R.id.contraseña)
        loginButton = findViewById(R.id.boton)
        chk1 = findViewById(R.id.chk_1)
        chk2 = findViewById(R.id.chk_2)
        chk3 = findViewById(R.id.chk_3)
        spinner = findViewById(R.id.spinner)
        radioGroup = findViewById(R.id.radioGroup)

        loginButton.setOnClickListener {
            val usernameInput = usernameEditText.text.toString()
            val passwordInput = passwordEditText.text.toString()

            if (usernameInput == MYUSSER && passwordInput == MYPASS) {
                Toast.makeText(this, "Acceso correcto", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Usuario o contarseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        chk1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Acción cuando se selecciona chk1
                showToast("CheckBox 1 seleccionado")
            } else {
                // Acción cuando se deselecciona chk1
                showToast("CheckBox 1 deseleccionado")
            }
        }

        // Evento para CheckBox 2
        chk2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Acción cuando se selecciona chk2
                showToast("CheckBox 2 seleccionado")
            } else {
                // Acción cuando se deselecciona chk2
                showToast("CheckBox 2 deseleccionado")
            }
        }

        // Evento para CheckBox 3
        chk3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Acción cuando se selecciona chk3
                showToast("CheckBox 3 seleccionado")
            } else {
                // Acción cuando se deselecciona chk3
                showToast("CheckBox 3 deseleccionado")
            }
        }

        // Evento para Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                showToast("Elemento seleccionado: $selectedItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                showToast("Ningún elemento seleccionado en el Spinner")
            }
        }

        // Evento para RadioButtons
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = findViewById(checkedId)
            val radioText = radioButton.text.toString()
            showToast("RadioButton seleccionado: $radioText")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}