package com.example.juegos_kotlin.adivinanumero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.juegos_kotlin.databinding.ActivityAdivinaNumeroBinding
import java.util.*
import kotlin.properties.Delegates

class AdivinaNumeroActivity : AppCompatActivity() {

    private lateinit var random: Random
    private var searchNum by Delegates.notNull<Int>()
    var numEnc by Delegates.notNull<Int>()

    private lateinit var binding: ActivityAdivinaNumeroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdivinaNumeroBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        inicializar()
    }

    fun inicializar() {

        random = Random()
        searchNum = (1..10).random()
        binding.btnAdivinarNumero.setOnClickListener {
            Adinivar()
        }
    }

    fun Adinivar() {
        numEnc = Integer.parseInt(binding.numIngresado.text.toString())
        if (numEnc == searchNum) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Ganaste")
            builder.setMessage(
                "Felicidades Ganaste \n" + "El número era $searchNum"
            )
            builder.setPositiveButton("Volver a jugar") { dialogInterface, id -> AdivinaNumeroActivity() }
            builder.setNegativeButton("Salir") { dialogInterface, id -> finish() }
            //
            builder.show()
            binding.numIngresado.setText("")
            searchNum = (1..10).random()
        } else if (searchNum < numEnc) {
            binding.ayuda.setText("El numero es mas bajo")
            binding.numIngresado.setText("")
        } else {
            binding.ayuda.setText("El número es mas alto")
            binding.numIngresado.setText("")
        }
    }
}