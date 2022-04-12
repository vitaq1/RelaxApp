package by.bsuir.vshu.relaxapp.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import by.bsuir.vshu.relaxapp.R
import by.bsuir.vshu.relaxapp.presentation.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var signInButton: Button
    private lateinit var signUpButton: TextView
    private lateinit var profileButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
    }


    private fun initViews() {

        signInButton = findViewById(R.id.signInButton)
        signInButton.apply { setOnClickListener { openMainActivity() } }
        signUpButton = findViewById(R.id.signUpButton)
        signUpButton.apply { setOnClickListener { openMainActivity() } }
        profileButton = findViewById(R.id.profileButton)
        profileButton.apply { setOnClickListener { openMainActivity() } }
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}