package com.example.climaapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etUser = findViewById<EditText>(R.id.etRegistroUser)
        val etPass = findViewById<EditText>(R.id.etRegistroPass)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            val user = etUser.text.toString()
            val pass = etPass.text.toString()

            if(user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Aquí podrías guardar los datos en SharedPreferences, DB, etc.
                Toast.makeText(this, "Usuario $user registrado correctamente", Toast.LENGTH_SHORT).show()
                finish() // Cierra RegistroActivity y vuelve a LoginActivity
            }
        }
    }
}
