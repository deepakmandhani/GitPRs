package com.example.gitprs.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitprs.R
import com.example.gitprs.model.PullRequest
import kotlinx.android.synthetic.main.view_pull_request_row.view.*

/**
 * Created by Deepak Mandhani on 2020-07-22.
 */
class PullRequestRVAdapter(private val context: Context, var prList: List<PullRequest>): RecyclerView.Adapter<PullRequestRVAdapter.PullRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder(LayoutInflater.from(context).inflate(R.layout.view_pull_request_row, parent, false))
    }

    override fun getItemCount(): Int = prList.size

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateData(newList: List<PullRequest>) {
        prList = newList
        notifyDataSetChanged()
    }

    fun addData(newList: List<PullRequest>) {
        if (prList.isNullOrEmpty())
            prList = newList
        else
            (prList as MutableList<PullRequest>).addAll(newList)
        notifyDataSetChanged()
    }

    inner class PullRequestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val pullRequest = prList[position]

            itemView.tv_title.text = pullRequest.title
            itemView.tv_author.text = pullRequest.author_association
            itemView.tv_created_at.text = pullRequest.created_at
        }

    }
}