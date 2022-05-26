package ru.itis.tinkoff.project.features

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.android.synthetic.main.activity_main.*
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var controller: NavController
    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment)
        controller = (navHostFragment as NavHostFragment).navController
        changeBottomNavigationOnFragment(controller)
        val bottom = viewBinding.bottomNavigation
        bottom.setupWithNavController(controller)
    }

    override fun onNavigateUp(): Boolean {
        return controller.navigateUp()
    }

    private fun changeBottomNavigationOnFragment(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.favourites,
                R.id.catalog,
                R.id.menu,
                R.id.profile,
                R.id.productPageFragment,
                R.id.cart -> bottomNavigation?.visibility = View.VISIBLE
                else -> bottomNavigation?.visibility = View.GONE
            }
        }
    }
}
