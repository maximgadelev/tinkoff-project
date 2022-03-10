package ru.itis.tinkoff_project.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.tinkoff_project.R
import com.example.tinkoff_project.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var controller: NavController
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller =
            (supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment).navController
        val bottom = findViewById<BottomNavigationView>(R.id.bnv_main)
        bottom.setupWithNavController(controller)

    }

    override fun onNavigateUp(): Boolean {
        return controller.navigateUp()
    }
}