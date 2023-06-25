package com.example.happyplaces_repeat.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.example.happyplaces_repeat.R
import com.example.happyplaces_repeat.domain.HappyPlace
import com.google.android.material.textfield.TextInputLayout

class HappyPlaceFragment(
    private val screenMode: String = MODE_UNKNOWN,
    private val placeId: Int = HappyPlace.UNDEFINED_ID
) : Fragment() {
    private lateinit var viewModel: HappyPlaceViewModel
    private lateinit var tilTitle: TextInputLayout
    private lateinit var tilDescription: TextInputLayout
    private lateinit var tilDate: TextInputLayout
    private lateinit var flImage: FrameLayout
    private lateinit var tilLocation: TextInputLayout

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var etDate: EditText
    private lateinit var ivImage: ImageView
    private lateinit var etLocation: EditText
    private lateinit var btnSave: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_happy_place, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseParams()
        viewModel = ViewModelProvider(this)[HappyPlaceViewModel::class.java]
        initViews(view)
        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.errorInputTitle.observe(viewLifecycleOwner){
            val message = if(it) {
                "Invalid title"
            }
            else null
            tilTitle.error= message
        }
        viewModel.errorInputDescription.observe(viewLifecycleOwner){
            val message = if(it) {
                "Invalid description"
            }
            else null
            tilDescription.error= message
        }
        viewModel.errorInputDate.observe(viewLifecycleOwner){
            val message = if(it) {
                "Invalid date"
            }
            else null
            tilDate.error= message
        }
        viewModel.errorInputLocation.observe(viewLifecycleOwner){
            val message = if(it) {
                "Invalid location"
            }
            else null
            tilLocation.error= message
        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner){
            activity?.onBackPressed()
        }
    }

    private fun addTextChangeListeners(){
        etTitle.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorTitle()
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })
        etDescription.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorDescription()
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })
        etDate.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorDate()
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })
        etLocation.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorLocation()
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun launchRightMode(){
        when(screenMode){
            MODE_ADD-> launchAddMode()
            MODE_EDIT-> launchEditMode()
        }
    }

    private fun launchAddMode(){
        btnSave.setOnClickListener {
            viewModel.addHappyPlace(etTitle.text.toString(),etDescription.text.toString(),
                etDate.text.toString(), etLocation.text.toString(),ivImage.toString())
        }
    }

    private fun launchEditMode(){
        viewModel.getHappyPlace(placeId)
        viewModel.happyPlace.observe(viewLifecycleOwner){
            etTitle.setText(it.title)
            etDate.setText(it.date)
            etDescription.setText(it.description)
            etLocation.setText(it.location)
            ivImage.setImageURI(it.image.toUri())
        }
        btnSave.setOnClickListener {
            viewModel.editHappyPlace(etTitle.text.toString(),etDescription.text.toString(),
            etDate.text.toString(), etLocation.text.toString(),ivImage.toString())
        }
    }

    private fun parseParams(){
        if(screenMode != MODE_EDIT && screenMode != MODE_ADD){
            throw java.lang.RuntimeException("Param screen mode is absent")
        }
        if(screenMode == MODE_EDIT&& placeId == HappyPlace.UNDEFINED_ID)
            throw java.lang.RuntimeException("Param happy place id is absent")
    }

    private fun initViews(view: View){
        etTitle = view.findViewById(R.id.et_title)
        etDescription = view.findViewById(R.id.et_description)
        etDate = view.findViewById(R.id.et_date)
        etLocation = view.findViewById(R.id.et_location)
        ivImage = view.findViewById(R.id.iv_photo)
    }

    companion object{
        private const val MODE_UNKNOWN = ""
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val EXTRA_SCREEN_MODE = "extra_screen_mode"
        private const val EXTRA_PLACE_ID = "extra_place_id"
    }
}