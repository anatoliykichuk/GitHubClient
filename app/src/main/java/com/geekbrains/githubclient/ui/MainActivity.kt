package com.geekbrains.githubclient.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.githubclient.data.GithubUserRepo
import com.geekbrains.githubclient.databinding.ActivityMainBinding
import com.geekbrains.githubclient.domain.MainPresenter
import com.geekbrains.githubclient.domain.MainView
import com.geekbrains.githubclient.ui.adapter.UsersAdapter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val presenter by moxyPresenter { MainPresenter(GithubUserRepo()) }
    private var adapter: UsersAdapter? = null

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun init() {
        adapter = UsersAdapter(presenter.userListPresenter)

        binding.users?.layoutManager = LinearLayoutManager(this)
        binding.users?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}