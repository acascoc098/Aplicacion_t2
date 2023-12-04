package com.example.aplicacion_t2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
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
    private lateinit var swich : Switch

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
        swich = findViewById(R.id.switch1)

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
                showToast("CheckBox 1 seleccionado")
            } else {
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


        chk3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("CheckBox 3 seleccionado")
            } else {
                showToast("CheckBox 3 deseleccionado")
            }
        }

        //Spinner
        setupSpinner()

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = findViewById(checkedId)
            val radioText = radioButton.text.toString()
            showToast("RadioButton seleccionado: $radioText")
        }

        swich.setOnCheckedChangeListener{_, ischecked ->
            if(ischecked){
                swich.setText("Repetidor")
            }else{
                swich.setText("No repetidor")
            }
        }
    }

    private fun setupSpinner() {
        val names = listOf("1º A", "1º B", "2º A", "2º B")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, names)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}