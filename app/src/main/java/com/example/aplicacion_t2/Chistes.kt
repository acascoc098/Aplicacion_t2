package com.example.aplicacion_t2

import android.content.Intent
import com.example.aplicacion_t2.databinding.ChistesBinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button

class Chistes : AppCompatActivity() {

    private lateinit var binding: ChistesBinding
    private lateinit var textToSpeech: TextToSpeech
    private val TOUCH_MAX_TIME = 500
    private var touchLastTime: Long = 0
    private lateinit var handler: Handler
    private val TXT = "LOGCAT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChistesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initHandler()
        initEvent()

        val btnVolver = findViewById<Button>(R.id.button5)
        btnVolver.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initHandler() {
        handler = Handler(Looper.getMainLooper())
        binding.progressBar.visibility = View.VISIBLE
        binding.btnExample.visibility = View.GONE

        Thread {
            Thread.sleep(3000)
            handler.post {
                binding.progressBar.visibility = View.GONE
                Thread.sleep(4000)
                Log.i(TXT, "El hilo se ejecuta correctamente")
                binding.btnExample.visibility = View.VISIBLE
            }
        }.start()
    }

    private fun initEvent() {
        val chiste = resources.getString(R.string.chiste)
        binding.btnExample.setOnClickListener {
            val currentTime = System.currentTimeMillis()

            if (currentTime - touchLastTime < TOUCH_MAX_TIME) {
                executeDoubleTouch()
                Log.i(TXT, "Escuchamos el chiste")
            } else {
                Log.i(TXT, "Hemos pulsado 1 vez.")
                speakDescription("Botón para escuchar un chiste")
            }

            touchLastTime = currentTime
        }
    }

    private fun speakDescription(s: String) {
        Log.i(TXT, "Intenta hablar")
        textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun executeDoubleTouch() {
        val listaDeChistes = listOf(
            "¿Por qué el libro de matemáticas estaba triste? Porque tenía demasiados problemas.",
            "¿Qué hace una abeja en el gimnasio? ¡Zum-ba!",
            "¿Cómo llama Superman a su papá? ¡Supermán!",
            "¿Por qué el esqueleto no pelea con nadie? Porque no tiene agallas.",
            "¿Por qué el océano no saluda al otro? Porque no se conocen a fondo.",
            "¿Cómo se llama el campeón de buceo japonés? ¡Tokofondo!",
            "¿Qué le dijo una impresora a otra? ¿Esa hoja es tuya o es una impresión mía?",
            "¿Por qué el café fue al médico? Porque se sentía descafeinado.",
            "¿Qué hace una abeja en el gimnasio? ¡Zum-ba!"
        )

        val chiste = listaDeChistes.random()
        speakDescription(chiste)
    }

    override fun onDestroy() {
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}
