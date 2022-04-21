package by.bsuir.vshu.relaxapp.presentation.main.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.bsuir.vshu.relaxapp.R
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView


class ProfileFragment : Fragment() {

    private val SELECT_PICTURE = 1

    private lateinit var profileImage: ShapeableImageView

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

    }

    private fun initViews() {

        profileImage = requireView().findViewById(R.id.profileImage)

    }

    private fun setListeners() {
        profileImage.apply { setOnClickListener { selectPhoto() } }

    }

    private fun selectPhoto(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {

                Glide.with(this).load(data!!.data).centerCrop()
                    .into(profileImage)
            }
        }
    }


}