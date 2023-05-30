package com.geekbrains.githubclient.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.githubclient.databinding.ActivityMainBinding

private const val countersKey = "COUNTERS"

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    private val counters = mutableListOf<Int>(0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCountersClickListener()
        showCountersResults()
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

    private fun setCountersClickListener() {
        binding.counter1.setOnClickListener {
            counters[0]++
            showCountersResults()
        }

        binding.counter2.setOnClickListener {
            counters[1]++
            showCountersResults()
        }

        binding.counter3.setOnClickListener {
            counters[2]++
            showCountersResults()
        }
    }

    private fun showCountersResults() {
        binding.counter1.text = counters[0].toString()
        binding.counter2.text = counters[1].toString()
        binding.counter3.text = counters[2].toString()
    }
}