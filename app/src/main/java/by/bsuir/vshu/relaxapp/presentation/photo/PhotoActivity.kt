package by.bsuir.vshu.relaxapp.presentation.photo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import by.bsuir.vshu.relaxapp.R
import by.bsuir.vshu.relaxapp.presentation.login.LoginViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotoActivity : AppCompatActivity() {

    private val model by viewModels<PhotoViewModel>()

    private lateinit var deleteButton: TextView
    private lateinit var closeButton: TextView
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        initViews()
        setListeners()
        loadPhoto()
    }


    private fun initViews() {

        deleteButton = findViewById(R.id.deleteButton)
        closeButton = findViewById(R.id.closeButton)
        image = findViewById(R.id.imagePlaceHolder)

    }

    private fun setListeners() {
        deleteButton.setOnClickListener {
            model.deletePhoto(intent.extras!!.get("id") as Int)
            finish()
        }
        closeButton.setOnClickListener { finish() }
    }

    private fun loadPhoto(){
        Glide.with(this).load(Uri.parse(model.getPhoto(intent.extras!!.get("id") as Int).uri)).centerCrop()
            .into(image)
    }



}