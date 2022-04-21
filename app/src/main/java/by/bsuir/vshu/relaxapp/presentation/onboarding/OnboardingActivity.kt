package by.bsuir.vshu.relaxapp.presentation.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import by.bsuir.vshu.relaxapp.R
import by.bsuir.vshu.relaxapp.presentation.login.LoginActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var signInButton: Button
    private lateinit var signUpButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        initViews()
    }


    private fun initViews() {

        signInButton = findViewById(R.id.signInButton)
        signInButton.apply { setOnClickListener { openLoginActivity(0) } }
        signUpButton = findViewById(R.id.signUpButton)
        signUpButton.apply { setOnClickListener { openLoginActivity(1) } }
    }

    private fun openLoginActivity(authType: Int) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("auth_type", authType)
        startActivity(intent)
    }

}