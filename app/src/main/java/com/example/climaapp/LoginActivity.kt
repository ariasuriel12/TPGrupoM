package com.example.climaapp

import android.content.SharedPreferences
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)

        val shouldSkipLogin = sharedPref.getBoolean("is_logged_in", false)

        if (shouldSkipLogin) {

            startActivity(Intent(this, ListaActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)


        val etUser = findViewById<EditText>(R.id.etUser)
        val etPass = findViewById<EditText>(R.id.etPass)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val txtRegister = findViewById<TextView>(R.id.txtRegister)
        val cbRememberMe = findViewById<CheckBox>(R.id.cbRememberMe)

        val savedUserName: String? = sharedPref.getString("username", null)

        if (!savedUserName.isNullOrEmpty()) {
            etUser.setText(savedUserName)
            cbRememberMe.isChecked = true
        }

        btnLogin.setOnClickListener {
            val user = etUser.text.toString()
            val pass = etPass.text.toString()

            if (user == "admin" && pass == "1234") {
                val editor = sharedPref.edit()

                if (cbRememberMe.isChecked) {
                    editor.putString("username", user)
                    editor.putBoolean("is_logged_in", true)
                } else {
                    editor.remove("username")
                    editor.remove("is_logged_in")
                }

                editor.apply()

                startActivity(Intent(this, ListaActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        txtRegister.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }
}