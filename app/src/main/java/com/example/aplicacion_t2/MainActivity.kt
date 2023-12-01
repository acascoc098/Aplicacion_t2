package com.example.aplicacion_t2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var btnLlamada : Button
    private lateinit var btnURL : Button
    private lateinit var btnMaps : Button
    private lateinit var btnAlarma : Button
    private lateinit var handler: Handler
    private lateinit var progress: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress = findViewById(R.id.progressBar)
        progress.visibility = View.VISIBLE
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

        btnAlarma = findViewById(R.id.buttonAlarm)

        btnAlarma.setOnClickListener { view ->
            val intent3 = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "Despierta")
                putExtra(AlarmClock.EXTRA_HOUR, 7)
                putExtra(AlarmClock.EXTRA_MINUTES, 38)
            }

            startActivity(intent3)

        }
    }

    /*private fun initHander() {
        handler = Handler(Looper.getMainLooper())  //queremos que el tema de la IU, la llevemos al hilo principal.
        binding.progressBar.visibility = View.VISIBLE  //hacemos visible el progress
        binding.btnExample.visibility = View.GONE  //ocultamos el botón.

        Thread{
            Thread.sleep(3000)
            handler.post{
                binding.progressBar.visibility = View.GONE  //ocultamos el progress
                val description = getString(R.string.describe).toString()
                speakMeDescription(description)  //que nos comente de qué va esto...
                Thread.sleep(4000)
                Log.i(MYTAG,"Se ejecuta correctamente el hilo")
                binding.btnExample.visibility = View.VISIBLE

            }
        }.start()
    }*/
}