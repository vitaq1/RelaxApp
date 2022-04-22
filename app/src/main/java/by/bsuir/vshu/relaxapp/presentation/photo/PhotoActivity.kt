package by.bsuir.vshu.relaxapp.presentation.photo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import by.bsuir.vshu.relaxapp.R

class PhotoActivity : AppCompatActivity() {

    private lateinit var deleteButton: TextView
    private lateinit var closeButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        initViews()
        setListeners()
    }


    private fun initViews() {

        deleteButton = findViewById(R.id.deleteButton)
        closeButton = findViewById(R.id.closeButton)

    }

    private fun setListeners() {
        deleteButton.setOnClickListener {  }
        closeButton.setOnClickListener {  }
    }



}