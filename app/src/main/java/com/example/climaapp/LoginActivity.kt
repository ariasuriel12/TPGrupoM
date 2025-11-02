package com.example.climaapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.example.climaapp.data.DatabaseProvider
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var etUser: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtRegister: TextView
    private lateinit var chkRemember: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
        val isRemembered = prefs.getBoolean("remember", false)

        // ---------------- Saltar login si ya está guardado ----------------
        if (isRemembered) {
            startActivity(Intent(this, ListaActivity::class.java))
            finish()
            return
        }
        // -------------------------------------------------------------------

        setContentView(R.layout.activity_login)

        etUser = findViewById(R.id.etUser)
        etPass = findViewById(R.id.etPass)
        btnLogin = findViewById(R.id.btnLogin)
        txtRegister = findViewById(R.id.txtRegister)
        chkRemember = findViewById(R.id.chkRemember)

        val savedUser = prefs.getString("username", "")
        val savedPass = if (isRemembered) prefs.getString("password", "") else ""

        etUser.setText(savedUser)
        etPass.setText(savedPass)
        chkRemember.isChecked = false
        // --------------------------------------------------------------------------

        val db = DatabaseProvider.getDatabase(this)
        val userDao = db.userDao()

        btnLogin.setOnClickListener {
            val username = etUser.text.toString().trim()
            val password = etPass.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val user = userDao.login(username, password)
                    if (user != null) {
                        val editor = prefs.edit()
                        editor.putString("username", username)
                        if (chkRemember.isChecked) {
                            prefs.edit()
                                editor.putString("password", password)
                                editor.putBoolean("remember", true)
                        } else {
                            editor.putBoolean("remember", false)
                            editor.remove("password")
                        }

                        startActivity(Intent(this@LoginActivity, ListaActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        txtRegister.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menubar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Abrir ajustes", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
