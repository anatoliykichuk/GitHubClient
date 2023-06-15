package com.geekbrains.githubclient.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.githubclient.data.GithubUser
import com.geekbrains.githubclient.databinding.FragmentUserBinding

class UserFragment() : Fragment() {

    companion object {
        private const val CURRENT_USER = "current_data"

        fun newInstance(user: GithubUser?) : UserFragment {
            val bundle = Bundle().apply {
                putParcelable(CURRENT_USER, user)
            }

            return UserFragment().apply {
                arguments = bundle
            }
        }
    }

    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<GithubUser>(CURRENT_USER)?.let {
            binding.userLogin.setText(it.login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}