package by.bsuir.vshu.relaxapp.presentation.login

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import by.bsuir.vshu.relaxapp.R
import by.bsuir.vshu.relaxapp.domain.model.Photo
import by.bsuir.vshu.relaxapp.domain.model.User
import by.bsuir.vshu.relaxapp.presentation.main.MainActivity
import by.bsuir.vshu.relaxapp.presentation.main.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigInteger
import java.security.MessageDigest

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val model by viewModels<LoginViewModel>()

    private var authType: Int? = null

    private lateinit var mailText: EditText
    private lateinit var passText: EditText
    private lateinit var authTypeText: TextView
    private lateinit var signInButton: Button
    private lateinit var signUpButton: TextView
    private lateinit var profileButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()

        authType = intent.extras?.get("auth_type") as Int

        if (authType == 1) {
            authTypeText.text = "Sign up"
            signInButton.text = "Sign Up"
            signUpButton.text = "authorization"
            signInButton.apply { setOnClickListener { register() } }
            signUpButton.setOnClickListener { openLoginActivity(0) }
        }

    }


    private fun initViews() {
        authTypeText = findViewById(R.id.authTypeText)
        signInButton = findViewById(R.id.signInButton)
        signInButton.apply { setOnClickListener { authenticate() } }
        signUpButton = findViewById(R.id.signUpButton)
        signUpButton.apply { setOnClickListener { openLoginActivity(1) } }
        profileButton = findViewById(R.id.profileButton)
        profileButton.apply { setOnClickListener { openMainActivity("1") } }
        mailText = findViewById(R.id.mailText)
        passText = findViewById(R.id.passText)
    }

    private fun openMainActivity(id: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun openLoginActivity(authType: Int) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("auth_type", authType)
        startActivity(intent)
    }

    private fun authenticate() {
        val user: User = model.getUser(mailText.text.toString())
        if (user != null) {
            if (user.password == md5(passText.text.toString())) {
                println("true")
                openMainActivity(user.mail)
            } else println("incorrect pass")
        }
    }

    private fun register() {
        val mail = mailText.text.toString()
        val pass = md5(passText.text.toString())
        val status: Long = model.addUser(User(mail = mail, password = pass))
        if (status == 1L){
            model.addPhoto(Photo(0,mail, resourceUri(R.drawable.im1).toString(),"11:00"))
            model.addPhoto(Photo(0,mail, resourceUri(R.drawable.im2).toString(),"15:55"))
            model.addPhoto(Photo(0,mail, resourceUri(R.drawable.im3).toString(),"19:11"))
            model.addPhoto(Photo(0,mail, resourceUri(R.drawable.im4).toString(),"11:11"))
        }

    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    private fun Context.resourceUri(resourceId: Int): Uri = with(resources) {
        Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(getResourcePackageName(resourceId))
            .appendPath(getResourceTypeName(resourceId))
            .appendPath(getResourceEntryName(resourceId))
            .build()
    }

}