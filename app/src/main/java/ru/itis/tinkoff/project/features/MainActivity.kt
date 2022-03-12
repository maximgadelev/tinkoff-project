package ru.itis.tinkoff.project.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var controller: NavController
    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment)
        controller = (navHostFragment as NavHostFragment).navController

        val bottom = viewBinding.bottomNavigation
        bottom.setupWithNavController(controller)
    }

    override fun onNavigateUp(): Boolean {
        return controller.navigateUp()
    }
}
