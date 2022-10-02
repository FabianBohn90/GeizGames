package com.example.geizgames

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.gameFragmentContainer) as NavHostFragment

        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        setupWithNavController(bottomNavigationView, navController)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        clearFocusOnOutsideClick()
        return super.dispatchTouchEvent(ev)
    }

    /*
    * Clear focus on outside click
    * */
    private fun clearFocusOnOutsideClick() {
        currentFocus?.apply {
            if (this is EditText) {
                clearFocus()
            }
            // Hide keyboard
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}
