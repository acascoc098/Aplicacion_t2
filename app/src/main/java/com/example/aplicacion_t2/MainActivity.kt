package com.example.aplicacion_t2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btnLlamada : Button
    private lateinit var btnURL : Button
    private lateinit var btnMaps : Button
    private lateinit var btnAlarma : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLlamada = findViewById(R.id.button);

        btnLlamada.setOnClickListener { view ->
            val intent = Intent(this, Llamada::class.java)
            startActivity(intent)
        }

        btnURL = findViewById(R.id.button6)

        btnURL.setOnClickListener {view ->
            val url = "https://www.twitch.tv"
            val uri = Uri.parse(url)
            val intent2 = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent2)

        }

        btnMaps = findViewById(R.id.button2)

        btnMaps.setOnClickListener {view ->
            val latitude = 37.9214200 // Coordenad de Cazorla, pero no son exactas
            val longitude = -2.9788000
            val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

            mapIntent.setPackage("com.google.android.apps.maps")

            if (mapIntent.resolveActivity(packageManager) == null) {
                startActivity(mapIntent)
            }else{
                Toast.makeText( this, "Error al abrir Google Maps",
                    Toast.LENGTH_LONG).show()
            }
        }

        btnAlarma = findViewById(R.id.button3)

        btnAlarma.setOnClickListener { view ->
            val intent3 = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "Despierta")
                putExtra(AlarmClock.EXTRA_HOUR, 7)
                putExtra(AlarmClock.EXTRA_MINUTES, 38)
            }

            startActivity(intent3)

        }
    }
}