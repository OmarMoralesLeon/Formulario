package com.example.formulario

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val opciones: RadioGroup = findViewById(R.id.opciones)
        opciones.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.hombre -> showToast("Hombre")
                R.id.mujer -> showToast("Mujer")
                R.id.otro -> showToast("Otro")
                else -> showToast("Opción desconocida")
            }
        }

        val spinner = findViewById<Spinner>(R.id.spinner)
        val items = resources.getStringArray(R.array.opciones_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                showToast(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        btnEnviar.setOnClickListener { texDatos() }

        findViewById<EditText>(R.id.texName)
        findViewById<EditText>(R.id.texCorreo)
        findViewById<EditText>(R.id.fecha)
        findViewById<EditText>(R.id.texNum)

        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnLimpiar.setOnClickListener {
            limpiarCampos()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun texDatos() {
        val texName = findViewById<EditText>(R.id.texName)
        val texCorreo = findViewById<EditText>(R.id.texCorreo)
        val fecha = findViewById<EditText>(R.id.fecha)
        val texNum = findViewById<EditText>(R.id.texNum)

        if (texName.text.toString().isEmpty()) {
            mostrarAlerta("Error", "No has ingresado tu nombre")
        } else if (texCorreo.text.toString().isEmpty()) {
            mostrarAlerta("Error", "No has ingresado tu correo")
        } else if (fecha.text.toString().isEmpty()) {
            mostrarAlerta("Error", "No has ingresado tu fecha de nacimiento")
        } else if (texNum.text.toString().isEmpty()) {
            mostrarAlerta("Error", "No has ingresado tu número de telefono")
        } else {
            mostrarAlerta(
                " * * * Datos Ingresados * * *",
                "Nombre: ${texName.text}\nCorreo: ${texCorreo.text}\nFecha de Nacimiento: ${fecha.text}\nNúmero de Contacto: ${texNum.text}"
            )
        }
    }

    private fun mostrarAlerta(titulo: String, mensaje: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(titulo)
        alertDialogBuilder.setMessage(mensaje)
        alertDialogBuilder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun limpiarCampos() {
        val texCorreo = findViewById<EditText>(R.id.texCorreo)
        val texName = findViewById<EditText>(R.id.texName)
        val fecha = findViewById<EditText>(R.id.fecha)
        val texNum = findViewById<EditText>(R.id.texNum)
        val opciones = findViewById<RadioGroup>(R.id.opciones)

        texCorreo.text.clear()
        texName.text.clear()
        fecha.text.clear()
        texNum.text.clear()
        opciones.clearCheck()
    }
}