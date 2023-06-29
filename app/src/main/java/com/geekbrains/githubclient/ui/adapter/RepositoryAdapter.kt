package com.geekbrains.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.databinding.RepositoryItemBinding
import com.geekbrains.githubclient.domain.repository.IRepositoryItemView
import com.geekbrains.githubclient.domain.repository.IRepositoryListPresenter

class RepositoryAdapter(
    val presenter: IRepositoryListPresenter
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: RepositoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root), IRepositoryItemView {

        override fun setName(name: String) = with(binding) {
            repositoryName.text = name
        }

        override var itemPosition = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RepositoryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return presenter.bindView(
            holder.apply {
                itemPosition = position
            }
        )
    }
}