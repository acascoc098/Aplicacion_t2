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
    private lateinit var resultado: ImageView
    /*private lateinit var imagen1c: ImageView
    private lateinit var imagen2c: ImageView
    private lateinit var imagen3c: ImageView
    private lateinit var imagen4: ImageView
    private lateinit var imagen5: ImageView
    private lateinit var imagen6: ImageView
    private lateinit var imagen7: ImageView
    private lateinit var imagen8: ImageView
    private lateinit var imagen9: ImageView
    private lateinit var imagen10: ImageView
    private lateinit var imagenj: ImageView
    private lateinit var imagenq: ImageView
    private lateinit var imagenk: ImageView*/
    private var sum : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imagenbtn = findViewById(R.id.imageButton)
        imagen1 = findViewById(R.id.imagview_dado1)
        imagen2 = findViewById(R.id.imagview_dado2)
        imagen3 = findViewById(R.id.imagview_dado3)
        resultado = findViewById(R.id.imagview_resultado)

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
        for (i in 1..5){
            schedulerExecutor.schedule(
                {
                    throwDadoInTime()
                },
                msc * i.toLong(), TimeUnit.MILLISECONDS)
        }

        schedulerExecutor.schedule({
            viewResult()
        },
            msc  * 7.toLong(), TimeUnit.MILLISECONDS)

        schedulerExecutor.shutdown()

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

        sum = numDados.sum()
        for (i in 0..3)
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
        when(sum){
            1 -> resultado.setImageResource(R.drawable.as_poker)
            2 -> resultado.setImageResource(R.drawable._5128583_poker_naipe_2_coraz_n)
            3 -> resultado.setImageResource(R.drawable.depositphotos_83316660_stock_illustration_poker_playing_card_3_diamond)
            4 -> resultado.setImageResource(R.drawable._4712945_gran__ndice_de_juego_de_cartas_4_de_espadas)
            5 -> resultado.setImageResource(R.drawable._5128550_poker_naipe_5_coraz_n)
            6 -> resultado.setImageResource(R.drawable.descarga)
            7 -> resultado.setImageResource(R.drawable._5128571_cartas_de_p_quer_juego_7_del_club)
            8 -> resultado.setImageResource(R.drawable.depositphotos_83317278_stock_illustration_poker_playing_card_8_diamond)
            9 -> resultado.setImageResource(R.drawable._5128581_poker_tarjeta_de_juego_9_coraz_n)
            10 -> resultado.setImageResource(R.drawable._5128553_poker_naipe_10_coraz_n)
            11 -> resultado.setImageResource(R.drawable._4712993_gran__ndice_de_juego_de_cartas_de_diamantes)
            12 -> resultado.setImageResource(R.drawable.dame_de_coeur)
            13 -> resultado.setImageResource(R.drawable.af3050cb31bc90ad1952b782489fcfb5)
            14 -> resultado.setImageResource(R.drawable._4712945_gran__ndice_de_juego_de_cartas_4_de_espadas+R.drawable._5128553_poker_naipe_10_coraz_n)
            15 -> resultado.setImageResource(R.drawable._5128550_poker_naipe_5_coraz_n+R.drawable._5128553_poker_naipe_10_coraz_n)
            16 -> resultado.setImageResource(R.drawable.descarga +R.drawable._5128553_poker_naipe_10_coraz_n)
            17 -> resultado.setImageResource(R.drawable._5128571_cartas_de_p_quer_juego_7_del_club +R.drawable._5128553_poker_naipe_10_coraz_n)
            18 -> resultado.setImageResource(R.drawable.depositphotos_83317278_stock_illustration_poker_playing_card_8_diamond +R.drawable._5128553_poker_naipe_10_coraz_n)
        }
    }
}