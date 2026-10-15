package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DieViewModel : ViewModel() {
    private val currentRoll = MutableLiveData<Int>().apply {
        value = 0
    }

    var dieSides: Int = 6

    fun setRoll (roll: Int) {
        currentRoll.value = roll
    }

    fun getRoll () : LiveData<Int> = currentRoll

    fun rollDie() {
        setRoll(Random.nextInt(dieSides) + 1)
    }
}
