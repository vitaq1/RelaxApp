package by.bsuir.vshu.relaxapp.presentation.main.home

import android.content.DialogInterface
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
import by.bsuir.vshu.relaxapp.presentation.main.SharedViewModel
import by.bsuir.vshu.relaxapp.util.Mood
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val model by activityViewModels<SharedViewModel>()

    private var radioGroup: MutableList<RadioButton> = mutableListOf()

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
        calmRadioButton.apply { setOnClickListener { checkRadioButton(it) } }
        relaxRadioButton.apply { setOnClickListener { checkRadioButton(it) } }
        focusRadioButton.apply { setOnClickListener { checkRadioButton(it) } }
        excitedRadioButton.apply { setOnClickListener { checkRadioButton(it) } }
        funRadioButton.apply { setOnClickListener { checkRadioButton(it) } }

        horoscopeBlockButton.apply { setOnClickListener { showHoroscope() } }
        moodBlockButton.apply { setOnClickListener { showMoodRecommendation() } }
    }


    private fun setObservers() {
        model.horoscope.observe(viewLifecycleOwner) {
            println(it)
        }
    }

    private fun checkRadioButton(view: View) {
        for (item: RadioButton in radioGroup) {
            if (item.id != view.id) item.isChecked = false
        }
    }

    private fun showHoroscope(){

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


    private fun showMoodRecommendation() {

        var mood: Mood? = null

        for (item: RadioButton in radioGroup) {
            if (item.isChecked) {
                mood = Mood.values()[radioGroup.indexOf(item)]
                break
            }
        }
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
        }
        else {
            val toast = Toast.makeText(requireContext(), "Выберите настроение", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

}