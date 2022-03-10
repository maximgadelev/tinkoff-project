package ru.itis.tinkoff_project.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tinkoff_project.R
import com.example.tinkoff_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var controller: NavController
    private val viewBinding by viewBinding(ActivityMainBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller =
            (supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment).navController
        val bottom = viewBinding.bnvMain
        bottom.setupWithNavController(controller)
    }

    override fun onNavigateUp(): Boolean {
        return controller.navigateUp()
    }
}