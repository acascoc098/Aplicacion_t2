package com.example.aplicacion_t2

import com.example.aplicacion_t2.databinding.ActivityMainBinding
import com.example.aplicacion_t2.databinding.ChistesBinding

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import kotlin.random.Random

class Chistes : AppCompatActivity() {

    private lateinit var binding : ChistesBinding
    private lateinit var textToSpeech: TextToSpeech  //descriptor de voz
    private val TOUCH_MAX_TIME = 500 // en milisegundos
    private var touchLastTime: Long = 0  //para saber el tiempo entre toque.
    private var touchNumber = 0   //numero de toques dado (por si acaso). De momento no nos hace falta.
    private lateinit var handler: Handler
    val MYTAG = "LOGCAT"  //para mirar logs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChistesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initHander()
        initEvent()
    }

    private fun initHander() {
        handler = Handler(Looper.getMainLooper())  //queremos que el tema de la IU, la llevemos al hilo principal.
        binding.progressBar.visibility = View.VISIBLE  //hacemos visible el progress
        binding.btnExample.visibility = View.GONE

        Thread{
            Thread.sleep(3000)
            handler.post{
                binding.progressBar.visibility = View.GONE  //ocultamos el progress
                Thread.sleep(4000)
                Log.i(MYTAG,"Se ejecuta correctamente el hilo")
                binding.btnExample.visibility = View.VISIBLE

            }
        }.start()
    }

    private fun initEvent() {
        val chiste = resources.getString(R.string.chiste)
        binding.btnExample.setOnClickListener{
            val currentTime = System.currentTimeMillis()

            if (currentTime - touchLastTime < TOUCH_MAX_TIME){
                executorDoubleTouch()
                Log.i(MYTAG,"Escuchamos el chiste")
            }
            else{
                //  touchNumber++
                Log.i(MYTAG,"Hemos pulsado 1 vez.")
                speakMeDescription("Botón para escuchar un chiste")
            }

            touchLastTime = currentTime

        }
    }

    //Habla
    private fun speakMeDescription(s: String) {
        Log.i(MYTAG,"Intenta hablar")
        textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun executorDoubleTouch() {
        val listaDeChistes = mutableListOf(
            "¿Por qué el libro de matemáticas estaba triste? Porque tenía demasiados problemas.",
            "¿Qué hace una abeja en el gimnasio? ¡Zum-ba!",
            "¿Cómo llama Superman a su papá? ¡Supermán!",
            "¿Por qué el esqueleto no pelea con nadie? Porque no tiene agallas.",
            "¿Por qué el océano no saluda al otro? Porque no se conocen a fondo.",
            "¿Cómo se llama el campeón de buceo japonés? ¡Tokofondo!",
            "¿Qué le dijo una impresora a otra?¿Esa hoja es tuya o es una impresión mía?",
            "¿Por qué el café fue al médico? Porque se sentía descafeinado.",
            "¿Qué hace una abeja en el gimnasio? ¡Zum-ba!"
        )

        val chiste = listaDeChistes[Random.nextInt(0,10)]
        speakMeDescription(chiste)

    }

    override fun onDestroy() {

        if (::textToSpeech.isInitialized){
            textToSpeech.stop()
            textToSpeech.shutdown()

        }
        super.onDestroy()
    }
}