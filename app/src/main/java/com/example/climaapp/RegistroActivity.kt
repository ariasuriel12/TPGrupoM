package com.example.climaapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.climaapp.data.DatabaseProvider
import com.example.climaapp.data.entities.User
import kotlinx.coroutines.launch

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etUser = findViewById<EditText>(R.id.etRegistroUser)
        val etPass = findViewById<EditText>(R.id.etRegistroPass)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            val username = etUser.text.toString().trim()
            val password = etPass.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val db = DatabaseProvider.getDatabase(this)
                val userDao = db.userDao()

                // Usamos corutina para no bloquear la UI
                lifecycleScope.launch {
                    val existingUser = userDao.getUser(username)
                    if (existingUser == null) {
                        userDao.insertUser(User(username = username, password = password))
                        Toast.makeText(
                            this@RegistroActivity,
                            "Usuario $username registrado correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish() // Cierra RegistroActivity y vuelve a LoginActivity
                    } else {
                        Toast.makeText(
                            this@RegistroActivity,
                            "El usuario ya existe",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
