package com.geekbrains.githubclient.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekbrains.githubclient.data.GithubRepository
import com.geekbrains.githubclient.databinding.FragmentRepositoryBinding
import com.geekbrains.githubclient.domain.repository.RepositoryPresenter
import com.geekbrains.githubclient.domain.repository.RepositoryView
import com.geekbrains.githubclient.ui.App
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView {

    companion object {
        private const val CURRENT_REPOSITORY = "current_repository"

        fun newInstance(repository: GithubRepository?): RepositoryFragment {
            return RepositoryFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CURRENT_REPOSITORY, repository)
                }
            }
        }
    }

    private val presenter: RepositoryPresenter by moxyPresenter {
        val repository = arguments?.getParcelable<GithubRepository>(CURRENT_REPOSITORY)!!

        RepositoryPresenter(repository).apply {
            App.instance.repositorySubcomponent?.inject(this)
        }
    }

    private var _binding: FragmentRepositoryBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<GithubRepository>(CURRENT_REPOSITORY)?.let {
            binding.repositoryName.setText(it.name)
            binding.repositoryDescription.setText(it.description)
            binding.repositoryForksCount.setText(it.forksCount.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}