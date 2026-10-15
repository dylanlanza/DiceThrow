package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val DIE_SIDES = 50
    private val TOP_DIE = "top_die"
    private val BOTTOM_DIE = "bottom_die"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Top die
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) !is DieFragment)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment.newInstance(DIE_SIDES, TOP_DIE))
                .commit()

        // Bottom die
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) !is DieFragment)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView2, DieFragment.newInstance(DIE_SIDES, BOTTOM_DIE))
                .commit()

        // ROLL button rolls both dice through their ViewModels
        findViewById<Button>(R.id.rollDiceButton).setOnClickListener {
            ViewModelProvider(this)[TOP_DIE, DieViewModel::class.java].rollDie()
            ViewModelProvider(this)[BOTTOM_DIE, DieViewModel::class.java].rollDie()
        }
    }
}
