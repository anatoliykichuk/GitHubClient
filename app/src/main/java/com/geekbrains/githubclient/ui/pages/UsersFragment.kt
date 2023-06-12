package com.geekbrains.githubclient.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.githubclient.data.GithubUserRepo
import com.geekbrains.githubclient.databinding.FragmentUsersBinding
import com.geekbrains.githubclient.domain.UsersPresenter
import com.geekbrains.githubclient.domain.UsersView
import com.geekbrains.githubclient.ui.App
import com.geekbrains.githubclient.ui.adapter.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUserRepo(), App.instance.router)
    }

    private var adapter: UsersAdapter? = null

    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        adapter = UsersAdapter(presenter.userListPresenter)

        binding.users.layoutManager = LinearLayoutManager(context)
        binding.users.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}