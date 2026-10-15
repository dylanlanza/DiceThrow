package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class DieFragment : Fragment() {

    companion object {
        const val DIESIDE = "sidenumber"
        const val DIE_KEY = "die_key"

        // Each die gets its own key so it gets its own ViewModel
        fun newInstance(sides: Int, key: String) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(DIESIDE, sides)
                putString(DIE_KEY, key)
            }
        }
    }

    val dieViewModel : DieViewModel by lazy {
        ViewModelProvider(requireActivity())[dieKey, DieViewModel::class.java]
    }

    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var dieKey: String = "die"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dieSides = it.getInt(DIESIDE, 6)
            dieKey = it.getString(DIE_KEY, "die")
        }
        dieViewModel.dieSides = dieSides
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dieViewModel.getRoll().observe(viewLifecycleOwner) { rollValue ->
            dieTextView.text = rollValue.toString()
        }

        // Roll once on first launch
        if (dieViewModel.getRoll().value == 0)
            throwDie()

        view.setOnClickListener{
            throwDie()
        }
    }

    fun throwDie() {
        dieViewModel.rollDie()
    }
}
