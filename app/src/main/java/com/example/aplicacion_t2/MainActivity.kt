package com.example.aplicacion_t2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btnLlamada : Button
    private lateinit var btnURL : Button
    private lateinit var btnMaps : Button
    private lateinit var btnAlarma : Button
    private lateinit var btnDados : Button
    private lateinit var progress : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress = findViewById(R.id.progressBar)
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
            val latitude = 37.9214200 // Coordenadas de Cazorla, pero no son exactas
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

        btnDados = findViewById(R.id.buttonDados)

        btnDados.setOnClickListener { view ->
            val intent = Intent(this, Dados::class.java)
            startActivity(intent)
        }

        vemosProgress()
    }

    private fun vemosProgress() {
        val maxProgress = 100
        val progressTime = 4000L

        val progressIncrement = progressTime / maxProgress

        Thread {
            var currentProgress = 0
            while (currentProgress <= maxProgress) {
                Thread.sleep(progressIncrement)
                currentProgress++
                runOnUiThread {
                    progress.progress = currentProgress
                }
            }
            runOnUiThread {
                progress.visibility = View.GONE
            }
        }.start()
    }
}