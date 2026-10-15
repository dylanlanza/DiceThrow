package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val DIE_SIDES = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Top die
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) !is DieFragment)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment.newInstance(DIE_SIDES))
                .commit()

        // Bottom die (new fragment)
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) !is DieFragment)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView2, DieFragment.newInstance(DIE_SIDES))
                .commit()

        // Roll both dice
        findViewById<Button>(R.id.rollDiceButton).setOnClickListener {
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? DieFragment)?.throwDie()
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as? DieFragment)?.throwDie()
        }
    }
}
