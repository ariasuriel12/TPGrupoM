package com.example.climaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
        val etConfirmPass = findViewById<EditText>(R.id.etRegistroConfirmPass)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val textLogin = findViewById<TextView>(R.id.textLogin)

        btnRegistrar.setOnClickListener {
            val username = etUser.text.toString().trim()
            val password = etPass.text.toString().trim()
            val confirmPassword = etConfirmPass.text.toString().trim()

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Las contraseñas no coinciden. Inténtalo de nuevo.", Toast.LENGTH_LONG).show()
                etPass.text.clear()
                etConfirmPass.text.clear()
                return@setOnClickListener
            }

            val db = DatabaseProvider.getDatabase(this)
            val userDao = db.userDao()

            lifecycleScope.launch {
                val existingUser = userDao.getUser(username)
                if (existingUser == null) {
                    userDao.insertUser(User(username = username, password = password))
                    Toast.makeText(
                        this@RegistroActivity,
                        "Usuario $username registrado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        this@RegistroActivity,
                        "El usuario ya existe",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        textLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)

            finish()
        }
    }
}