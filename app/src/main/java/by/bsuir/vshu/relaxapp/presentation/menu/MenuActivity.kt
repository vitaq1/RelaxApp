package by.bsuir.vshu.relaxapp.presentation.menu

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import by.bsuir.vshu.relaxapp.R
import by.bsuir.vshu.relaxapp.presentation.help.HelpActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.pow
import kotlin.math.roundToInt


@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    private val model by viewModels<MenuViewModel>()

    private lateinit var helpButton: ImageView
    private lateinit var infoButton: ImageView
    private lateinit var nameText: EditText
    private lateinit var ageText: EditText
    private lateinit var weightText: EditText
    private lateinit var heightText: EditText
    private lateinit var pressureText: EditText
    private lateinit var saveButton: ImageView
    private lateinit var imtButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        model.loadUser(intent.extras?.get("id").toString())



        initViews()
        setListeners()
        setObservers()
    }

    private fun initViews() {

        helpButton = findViewById(R.id.helpButton)
        infoButton = findViewById(R.id.infoButton)
        nameText = findViewById(R.id.nameText)
        ageText = findViewById(R.id.ageText)
        weightText = findViewById(R.id.weightText)
        heightText = findViewById(R.id.heightText)
        pressureText = findViewById(R.id.pressureText)
        saveButton = findViewById(R.id.saveButton)
        imtButton = findViewById(R.id.imtButton)


    }

    private fun setListeners() {
        helpButton.setOnClickListener { openHelpActivity() }
        infoButton.setOnClickListener {  }
        saveButton.setOnClickListener { saveUser() }
        imtButton.setOnClickListener { calculateIMT() }
    }


    private fun setObservers() {

        model.user.observe(this) {
            nameText.setText(model.user.value?.name)
            ageText.setText(model.user.value?.age.toString())
            weightText.setText(model.user.value?.weight.toString())
            heightText.setText(model.user.value?.height.toString())
            pressureText.setText(model.user.value?.pressure.toString())
        }
    }

    private fun saveUser(){
        model.user.value!!.name = nameText.text.toString()
        model.user.value!!.age =  ageText.text.toString().toInt()
        model.user.value!!.weight = weightText.text.toString().toDouble()
        model.user.value!!.height = heightText.text.toString().toInt()
        model.user.value!!.pressure = pressureText.text.toString().toInt()
        model.updateUser()
    }

    private fun calculateIMT(){
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle("ИМТ")
        val v: View = layoutInflater.inflate(R.layout.alert_dialog_view, null)
        alert.setView(v)
        val imt: TextView = v.findViewById(R.id.recText)


        imt.text = "Ваш ИМТ: " + ((model.user.value!!.weight/((model.user.value!!.height/100.0).pow(2))) * 100.0).roundToInt() / 100.0
        alert.setNegativeButton("Close",
            DialogInterface.OnClickListener { dialog, id -> dialog.dismiss() })
        alert.show()
    }

    private fun openHelpActivity(){
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent)
    }

}