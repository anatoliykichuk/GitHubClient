package com.geekbrains.githubclient.ui

import android.os.Bundle
import com.geekbrains.githubclient.data.Counters
import com.geekbrains.githubclient.databinding.ActivityMainBinding
import com.geekbrains.githubclient.domain.MainPresenter
import com.geekbrains.githubclient.domain.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter { MainPresenter(Counters()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.counter1.setOnClickListener { presenter.counterOneClick() }
        binding.counter2.setOnClickListener { presenter.counterTwoClick() }
        binding.counter3.setOnClickListener { presenter.counterTreeClick() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun setButtonOneText(text: String) {
        binding.counter1.text = text
    }

    override fun setButtonTwoText(text: String) {
        binding.counter2.text = text
    }

    override fun setButtonTreeText(text: String) {
        binding.counter3.text = text
    }
}