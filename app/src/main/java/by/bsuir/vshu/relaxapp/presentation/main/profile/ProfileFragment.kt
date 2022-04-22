package by.bsuir.vshu.relaxapp.presentation.main.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.bsuir.vshu.relaxapp.R
import by.bsuir.vshu.relaxapp.presentation.main.SharedViewModel
import by.bsuir.vshu.relaxapp.presentation.photo.PhotoActivity
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : Fragment() {

    private val model by activityViewModels<SharedViewModel>()

    private val SELECT_PROFILE_PICTURE = 1
    private val SELECT_PICTURE = 2

    private lateinit var photoGrid: GridLayout
    private lateinit var profileImage: ShapeableImageView
    private lateinit var nameText: TextView
    private lateinit var ageText: TextView
    private lateinit var weightText: TextView
    private lateinit var pressureText: TextView
    private lateinit var addPhotoButton: CardView
    private lateinit var pic1: CardView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setListeners()
        setObservers()
    }

    private fun initViews() {

        photoGrid = requireView().findViewById(R.id.photoGrid)
        profileImage = requireView().findViewById(R.id.profileImage)
        nameText = requireView().findViewById(R.id.nameTextView)
        ageText = requireView().findViewById(R.id.ageText)
        weightText = requireView().findViewById(R.id.weightText)
        pressureText = requireView().findViewById(R.id.pressureText)
        addPhotoButton = requireView().findViewById(R.id.addPhotoButton)
        pic1 = requireView().findViewById(R.id.pic1)

    }

    private fun setListeners() {
        profileImage.setOnClickListener { selectProfilePicture() }
        addPhotoButton.setOnClickListener { selectPicture() }
    }

    private fun setObservers() {

        model.user.observe(viewLifecycleOwner) {
            nameText.text = "${model.user.value?.name}"
            ageText.text = "Возраст: ${model.user.value?.age}"
            weightText.text = "Вес: ${model.user.value?.weight}"
            pressureText.text = "Давление: ${model.user.value?.pressure}"
            if (model.user.value?.image != "") {
                Glide.with(this).load(Uri.parse(model.user.value?.image)).centerCrop()
                    .into(profileImage)
            }
        }
    }

    private fun selectProfilePicture() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            SELECT_PROFILE_PICTURE
        )
    }

    private fun selectPicture() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {

                val picView: View = LayoutInflater.from(requireContext()).inflate(R.layout.pic_view, photoGrid,false)

                photoGrid.addView(picView,photoGrid.childCount - 1)

                val text: TextView = picView.findViewById(R.id.txtView)
                text.text =  SimpleDateFormat("HH:mm", Locale.US).format(Calendar.getInstance().time).toString()

                Glide.with(this).load(data!!.data).centerCrop()
                    .into(picView.findViewById(R.id.imView))

            }
            if (requestCode == SELECT_PROFILE_PICTURE) {

                Glide.with(this).load(data!!.data).centerCrop()
                    .into(profileImage)

                model.user.value!!.image = data.data.toString()
                model.updateUser()

            }
        }
    }

    public fun openPhotoActivity(id: Int) {
        val intent = Intent(requireContext(), PhotoActivity::class.java)
        intent.putExtra("photoId", id)
        startActivity(intent)
    }


}