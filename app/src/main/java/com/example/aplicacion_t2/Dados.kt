package com.example.aplicacion_t2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Dados : AppCompatActivity() {
    private lateinit var imagenbtn: ImageButton
    private lateinit var imagen1: ImageView
    private lateinit var imagen2: ImageView
    private lateinit var imagen3: ImageView
    private lateinit var resultado: TextView
    private var sum : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imagenbtn = findViewById(R.id.imageButton)
        imagen1 = findViewById(R.id.imagview_dado1)
        imagen2 = findViewById(R.id.imagview_dado2)
        imagen3 = findViewById(R.id.imagview_dado3)
        resultado = findViewById(R.id.resultado)
        setContentView(R.layout.dados)
        initEvent()

        var btnVolver = findViewById<Button>(R.id.button5)
        btnVolver.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }



    private fun initEvent() {
        resultado.visibility = View.INVISIBLE
        imagenbtn.setOnClickListener{
            resultado.visibility = View.VISIBLE
            game()

        }
    }

    private fun game(){
        sheduleRun()
    }


    private fun sheduleRun() {

        val schedulerExecutor = Executors.newSingleThreadScheduledExecutor()
        val msc = 1000
        for (i in 1..5){//lanzamos 5 veces el dado
            schedulerExecutor.schedule(
                {
                    throwDadoInTime()  //Lanzo los tres dados.
                },
                msc * i.toLong(), TimeUnit.MILLISECONDS)
        }

        schedulerExecutor.schedule({//El último hilo, es mostrar el resultado.
            viewResult()
        },
            msc  * 7.toLong(), TimeUnit.MILLISECONDS)

        schedulerExecutor.shutdown()  //Ya no aceptamos más hilos.

    }

    /*
    Método que lanza los tres dados a partir de 3 aleatorios.
     */
    private fun throwDadoInTime() {
        val numDados = Array(3){ Random.nextInt(1, 6)}
        val imagViews : Array<ImageView> = arrayOf<ImageView>(
            imagen1,
            imagen2,
            imagen3)

        sum = numDados.sum() //me quedo con la suma actual
        for (i in 0..3) //cambio las imagenes, a razón de los aleatorios.
            selectView(imagViews[i], numDados[i])

    }


    /*
    Método que dependiendo de la vista, carga una imagen de dado u otro.
     */
    private fun selectView(imgV: ImageView, v: Int) {
        when (v){
            1 -> imgV.setImageResource(R.drawable.dado1);
            2 -> imgV.setImageResource(R.drawable.dado2);
            3 -> imgV.setImageResource(R.drawable.dado3);
            4 -> imgV.setImageResource(R.drawable.dado4);
            5 -> imgV.setImageResource(R.drawable.dado5);
            6 -> imgV.setImageResource(R.drawable.dado6);
        }

    }


    /*
    Muestra los resultados, que es la suma de la última tirada.
     */
    private fun viewResult() {
        resultado.text = sum.toString()
        println(sum)
    }
}