package by.bsuir.vshu.relaxapp.presentation.menu

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import by.bsuir.vshu.relaxapp.R
import by.bsuir.vshu.relaxapp.domain.model.Mood
import by.bsuir.vshu.relaxapp.presentation.help.HelpActivity
import by.bsuir.vshu.relaxapp.presentation.info.InfoActivity
import by.bsuir.vshu.relaxapp.util.Util
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.pow
import kotlin.math.roundToInt


@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    private val model by viewModels<MenuViewModel>()

    private lateinit var table: TableLayout
    private lateinit var recText: TextView
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
        model.loadMoods(intent.extras?.get("id").toString())

        initViews()
        setListeners()
        setObservers()
    }

    private fun initViews() {

        table = findViewById(R.id.table)
        recText = findViewById(R.id.recText)
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
        infoButton.setOnClickListener { openInfoActivity() }
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

        model.moods.observe(this) {
            fillTable(it)
        }
    }

    private fun fillTable(moods: List<Mood>) {

        var k = 0
        var funCount = 0
        var focusCount = 0
        var elseCount = 0
        for (mood in moods) {

            if (by.bsuir.vshu.relaxapp.util.Mood.values()[mood.mood] == by.bsuir.vshu.relaxapp.util.Mood.FUN) funCount++
            else if (by.bsuir.vshu.relaxapp.util.Mood.values()[mood.mood] == by.bsuir.vshu.relaxapp.util.Mood.FOCUS) focusCount++
            else elseCount++

            val tableRow = TableRow(this).apply { }
            val date = TextView(this).apply {
                setText("       " + mood.date)
                gravity = Gravity.CENTER
                textSize = 16f
                setTextColor(resources.getColor(R.color.white))
            }
            val empty = TextView(this).apply {
                setText("                       ")
                gravity = Gravity.CENTER
                textSize = 16f
                setTextColor(resources.getColor(R.color.white))
            }
            val desc = TextView(this).apply {
                setText(by.bsuir.vshu.relaxapp.util.Mood.values()[mood.mood].toString())
                textSize = 16f
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(R.color.white))
            }
            tableRow.addView(date, 0)
            tableRow.addView(empty, 1)
            tableRow.addView(desc, 2)
            if (k % 2 == 0) tableRow.setBackgroundResource(R.color.app_gray)
            table.addView(tableRow)
            k++
        }

        if (funCount > focusCount) recText.text = "It's better to relax"
        else if (funCount <= focusCount) recText.text = "It's time to relax"
        else recText.text = "Keep balance between relax and fun"

    }

    private fun saveUser() {
        if (nameText.text.toString().length < 10 && ageText.text.toString().length <= 3 && weightText.text.toString().length <= 4 && heightText.text.toString().length < 4 && pressureText.text.toString().length < 4) {
            model.user.value!!.name = nameText.text.toString()
            model.user.value!!.age = ageText.text.toString().toInt()
            model.user.value!!.weight = weightText.text.toString().toDouble()
            model.user.value!!.height = heightText.text.toString().toInt()
            model.user.value!!.pressure = pressureText.text.toString().toInt()
            model.updateUser()
            Toast.makeText(this, "Данные успешно сохранены", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(this, "Некорректные данные", Toast.LENGTH_SHORT).show()
    }

    private fun calculateIMT() {

        if (model.user.value!!.height != 0) {
            val alert: AlertDialog.Builder = AlertDialog.Builder(this)
            alert.setTitle("ИМТ")
            val v: View = layoutInflater.inflate(R.layout.alert_dialog_view, null)
            alert.setView(v)
            val imt: TextView = v.findViewById(R.id.recText)


            imt.text =
                "Ваш ИМТ: " + ((model.user.value!!.weight / ((model.user.value!!.height / 100.0).pow(
                    2
                ))) * 100.0).roundToInt() / 100.0
            alert.setNegativeButton("Close",
                DialogInterface.OnClickListener { dialog, id -> dialog.dismiss() })
            alert.show()
        } else Toast.makeText(this, "Некорректные данные", Toast.LENGTH_SHORT).show()
    }

    private fun openHelpActivity() {
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent)
    }

    private fun openInfoActivity() {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }

}