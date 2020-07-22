package com.example.gitprs.ui.main

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitprs.R
import com.example.gitprs.model.PullRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_pull_request_row.view.*


/**
 * Created by Deepak Mandhani on 2020-07-22.
 */
class PullRequestRVAdapter(private val context: Context, var prList: List<PullRequest>) :
    RecyclerView.Adapter<PullRequestRVAdapter.PullRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.view_pull_request_row,
                parent,
                false
            )
        )
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

    inner class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val pullRequest = prList[position]

            val str = SpannableString("#${pullRequest.number}: ${pullRequest.title}")
            val end = pullRequest.number.toString().length + 1
            str.setSpan(StyleSpan(Typeface.BOLD), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            str.setSpan(ForegroundColorSpan(Color.BLUE), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            itemView.tv_title.setText(str, TextView.BufferType.SPANNABLE)
            itemView.tv_author.text = pullRequest.user.login
            itemView.tv_created_at.text = pullRequest.created_at.toString()

            Picasso.get().load(pullRequest.user.avatar_url).noFade().fit()
                .placeholder(R.drawable.ic_git_user)
                .error(R.drawable.ic_git_default)
                .into(itemView.iv_photo)
        }
    }
}