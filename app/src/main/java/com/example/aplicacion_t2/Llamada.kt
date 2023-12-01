package com.example.aplicacion_t2

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Llamada : AppCompatActivity() {
    companion object{
        const val NUMERO = "697105928"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.llamada_layout)

        var btnVolver = findViewById<Button>(R.id.button5)
        btnVolver.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var btnLlamar = findViewById<Button>(R.id.button4)
        btnLlamar.setOnClickListener { view ->
            if (view.id==R.id.button4) {
                requestPermissions()
            }
        }
    }

    private fun requestPermissions(){
        if (Build.VERSION. SDK_INT >= Build.VERSION_CODES. M){
            if (PermissionPhone()){
                call()
            }else{
                requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
            }
        }else{
            call()
        }
    }

    private fun call() {
        val intent = Intent(Intent. ACTION_CALL).apply {
            data = Uri.parse( "tel:${Companion.NUMERO}")
        }
        startActivity(intent)
    }

    private fun PermissionPhone(): Boolean =
        ContextCompat.checkSelfPermission( this,
            android.Manifest.permission.CALL_PHONE) == PackageManager. PERMISSION_GRANTED

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) {
            isGranted->
        if (isGranted) {
            call()
        }
        else {
            Toast.makeText( this, "Necesitas habilitar los permisos",
                Toast.LENGTH_LONG).show()
        }
    }
}