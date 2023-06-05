package com.geekbrains.githubclient.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.githubclient.databinding.ActivityMainBinding
import com.geekbrains.githubclient.domain.MainPresenter
import com.geekbrains.githubclient.domain.MainView

private const val countersKey = "COUNTERS"

class MainActivity : AppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    private val counters = mutableListOf<Int>(0, 0, 0)
    private val presenter: MainPresenter = MainPresenter(this)

    private lateinit var idToIndex: Map<Int, Int>
    private lateinit var indexToButton: Map<Int, Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idToIndex = mapOf<Int, Int>(
            binding.counter1.id to 0,
            binding.counter2.id to 1,
            binding.counter3.id to 2
        )

        indexToButton = mapOf<Int, Button>(
            0 to binding.counter1,
            1 to binding.counter2,
            2 to binding.counter3
        )

        val clickListener = View.OnClickListener {
            presenter.onViewClick(it.id, idToIndex)
        }

        binding.counter1.setOnClickListener(clickListener)
        binding.counter2.setOnClickListener(clickListener)
        binding.counter3.setOnClickListener(clickListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray(countersKey, counters.toIntArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val savedCounters = savedInstanceState.getIntArray(countersKey)
        savedCounters?.toList()?.let {
            counters.clear()
            counters.addAll(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun setButtonText(counterIndex: Int, text: String) {
        indexToButton.get(counterIndex)?.let {
            it.text = text
        }
    }
}