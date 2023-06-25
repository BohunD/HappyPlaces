package com.example.happyplaces_repeat.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.happyplaces_repeat.R
import com.example.happyplaces_repeat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var placesAdapter:HappyPlacesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fabAdd.setOnClickListener {

        }


    }

    private fun parseIntent(){

    }
    private fun setupRecyclerView(){

    }
    private fun launchFragment(fragment: Fragment){
        val fragment = when()
    }
    private fun setupClickListener(){

    }
}