package by.bsuir.vshu.relaxapp.presentation.main.home

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.bsuir.vshu.relaxapp.R
import by.bsuir.vshu.relaxapp.presentation.main.MainActivity
import by.bsuir.vshu.relaxapp.presentation.main.SharedViewModel
import by.bsuir.vshu.relaxapp.presentation.menu.MenuActivity
import by.bsuir.vshu.relaxapp.util.Mood
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val model by activityViewModels<SharedViewModel>()
    private var radioGroup: MutableList<RadioButton> = mutableListOf()

    private lateinit var welcomeText: TextView
    private lateinit var menuButton: ImageView
    private lateinit var exitButton: ShapeableImageView

    private lateinit var calmRadioButton: RadioButton
    private lateinit var relaxRadioButton: RadioButton
    private lateinit var focusRadioButton: RadioButton
    private lateinit var excitedRadioButton: RadioButton
    private lateinit var funRadioButton: RadioButton

    private lateinit var horoscopeBlockButton: Button
    private lateinit var moodBlockButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setListeners()
        setObservers()


    }


    private fun initViews() {
        menuButton = requireView().findViewById(R.id.menuButton)
        exitButton = requireView().findViewById(R.id.exitButton)
        welcomeText = requireView().findViewById(R.id.welcomeText)
        calmRadioButton = requireView().findViewById(R.id.calmRadioButton)
        relaxRadioButton = requireView().findViewById(R.id.relaxRadioButton)
        focusRadioButton = requireView().findViewById(R.id.focusRadioButton)
        excitedRadioButton = requireView().findViewById(R.id.excitedRadioButton)
        funRadioButton = requireView().findViewById(R.id.funRadioButton)
        radioGroup.add(calmRadioButton)
        radioGroup.add(relaxRadioButton)
        radioGroup.add(focusRadioButton)
        radioGroup.add(excitedRadioButton)
        radioGroup.add(funRadioButton)

        horoscopeBlockButton = requireView().findViewById(R.id.horoscopeBlockButton)
        moodBlockButton = requireView().findViewById(R.id.moodBlockButton)
    }

    private fun setListeners() {
        menuButton.setOnClickListener { openMenuActivity() }
        calmRadioButton.setOnClickListener { checkRadioButton(it) }
        relaxRadioButton.setOnClickListener { checkRadioButton(it) }
        focusRadioButton.setOnClickListener { checkRadioButton(it) }
        excitedRadioButton.setOnClickListener { checkRadioButton(it) }
        funRadioButton.setOnClickListener { checkRadioButton(it) }

        horoscopeBlockButton.setOnClickListener { showHoroscope() }
        moodBlockButton.setOnClickListener { showMoodRecommendation() }
    }


    private fun setObservers() {
        model.horoscope.observe(viewLifecycleOwner) {
            println(it)
        }
        model.user.observe(viewLifecycleOwner) {
            welcomeText.apply { text = "С возвращением, ${model.user.value?.name}" }
            if (model.user.value?.image != "") {
                Glide.with(this).load(Uri.parse(model.user.value?.image)).centerCrop()
                    .into(exitButton)
            }
        }
    }

    private fun checkRadioButton(view: View) {
        for (item: RadioButton in radioGroup) {
            if (item.id != view.id) item.isChecked = false
        }

        model.mood.value = getSelectedMood()
        model.addMood(
            by.bsuir.vshu.relaxapp.domain.model.Mood(
                0,
                model.user.value!!.mail,
                getSelectedMood()!!.ordinal,
                SimpleDateFormat("dd-MM-yyyy", Locale.US).format(
                    Calendar.getInstance().time
                ).toString()
            )
        )
    }

    private fun showHoroscope() {

        val alert: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        alert.setTitle("Гороскоп")
        val v: View = layoutInflater.inflate(R.layout.alert_dialog_view, null)
        alert.setView(v)
        alert.setNegativeButton("Close",
            DialogInterface.OnClickListener { dialog, id -> dialog.dismiss() })
        alert.show()
        val rec: TextView = v.findViewById(R.id.recText)
        rec.text = model.getHoroscope()

    }

    private fun getSelectedMood(): Mood? {
        var mood: Mood? = null
        for (item: RadioButton in radioGroup) {
            if (item.isChecked) {
                mood = Mood.values()[radioGroup.indexOf(item) % 5]
                break
            }
        }
        return mood
    }


    private fun showMoodRecommendation() {

        val mood: Mood? = getSelectedMood()
        if (mood != null) {
            val alert: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            alert.setTitle("Рекомендации по настроению")
            val v: View = layoutInflater.inflate(R.layout.alert_dialog_view, null)
            alert.setView(v)
            alert.setNegativeButton("Close",
                DialogInterface.OnClickListener { dialog, id -> dialog.dismiss() })
            alert.show()
            val rec: TextView = v.findViewById(R.id.recText)
            rec.text = model.getRecommendationByMood(mood)
        } else {
            val toast = Toast.makeText(requireContext(), "Выберите настроение", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

    private fun openMenuActivity() {
        val intent = Intent(requireContext(), MenuActivity::class.java)
        intent.putExtra("id", model.user.value!!.mail)
        startActivity(intent)
    }

}