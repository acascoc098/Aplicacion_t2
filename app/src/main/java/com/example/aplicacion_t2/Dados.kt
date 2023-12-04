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
    private var sum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dados)

        imagenbtn = findViewById(R.id.imageButton)
        imagen1 = findViewById(R.id.imagview_dado1)
        imagen2 = findViewById(R.id.imagview_dado2)
        imagen3 = findViewById(R.id.imagview_dado3)
        resultado = findViewById(R.id.imagview_resultado)

        initEvent()

        val btnVolver = findViewById<Button>(R.id.button5)
        btnVolver.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initEvent() {
        resultado.visibility = View.INVISIBLE
        imagenbtn.setOnClickListener {
            resultado.visibility = View.VISIBLE
            game()
        }
    }

    private fun game() {
        scheduleRun()
    }

    private fun scheduleRun() {
        val schedulerExecutor = Executors.newSingleThreadScheduledExecutor()
        val msc = 1000
        for (i in 1..5) {
            schedulerExecutor.schedule({
                throwDadoInTime()
            }, msc * i.toLong(), TimeUnit.MILLISECONDS)
        }

        schedulerExecutor.schedule({
            viewResult()
        }, msc * 7.toLong(), TimeUnit.MILLISECONDS)

        schedulerExecutor.shutdown()
    }

    private fun throwDadoInTime() {
        val numDados = Array(3) { Random.nextInt(1, 7) }
        val imagViews: Array<ImageView> = arrayOf(imagen1, imagen2, imagen3)

        sum = numDados.sum()
        for (i in 0 until 3) {
            selectView(imagViews[i], numDados[i])
        }
    }

    private fun selectView(imgV: ImageView, v: Int) {
        val drawableResource = when (v) {
            1 -> R.drawable.dado1
            2 -> R.drawable.dado2
            3 -> R.drawable.dado3
            4 -> R.drawable.dado4
            5 -> R.drawable.dado5
            else -> R.drawable.dado6
        }
        imgV.setImageResource(drawableResource)
    }

    private fun viewResult() {
        val drawableResource = when (sum) {
            1 -> R.drawable.as_poker
            2 -> R.drawable._5128583_poker_naipe_2_coraz_n
            3 -> R.drawable.depositphotos_83316660_stock_illustration_poker_playing_card_3_diamond
            4 -> R.drawable._4712945_gran__ndice_de_juego_de_cartas_4_de_espadas
            5 -> R.drawable._5128550_poker_naipe_5_coraz_n
            6 -> R.drawable.descarga
            7 -> R.drawable._5128571_cartas_de_p_quer_juego_7_del_club
            8 -> R.drawable.depositphotos_83317278_stock_illustration_poker_playing_card_8_diamond
            9 -> R.drawable._5128581_poker_tarjeta_de_juego_9_coraz_n
            10 -> R.drawable._5128553_poker_naipe_10_coraz_n
            11 -> R.drawable._4712993_gran__ndice_de_juego_de_cartas_de_diamantes
            12 -> R.drawable.dame_de_coeur
            13 -> R.drawable.af3050cb31bc90ad1952b782489fcfb5
            14 -> R.drawable._4712945_gran__ndice_de_juego_de_cartas_4_de_espadas + R.drawable._5128553_poker_naipe_10_coraz_n
            15 -> R.drawable._5128550_poker_naipe_5_coraz_n + R.drawable._5128553_poker_naipe_10_coraz_n
            16 -> R.drawable.descarga + R.drawable._5128553_poker_naipe_10_coraz_n
            17 -> R.drawable._5128571_cartas_de_p_quer_juego_7_del_club + R.drawable._5128553_poker_naipe_10_coraz_n
            else -> R.drawable.depositphotos_83317278_stock_illustration_poker_playing_card_8_diamond + R.drawable._5128553_poker_naipe_10_coraz_n
        }
        resultado.setImageResource(drawableResource)
    }
}
