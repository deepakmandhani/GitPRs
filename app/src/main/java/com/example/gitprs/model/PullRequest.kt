package com.example.gitprs.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Deepak Mandhani on 2020-07-22.
 */

data class PullRequest(
    @SerializedName("url") val url: String,
    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val node_id: String,
    @SerializedName("html_url") val html_url: String,
    @SerializedName("diff_url") val diff_url: String,
    @SerializedName("patch_url") val patch_url: String,
    @SerializedName("issue_url") val issue_url: String,
    @SerializedName("number") val number: Int,
    @SerializedName("state") val state: String,
    @SerializedName("locked") val locked: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("user") val user: User,
    @SerializedName("body") val body: String,
    @SerializedName("created_at") val created_at: Date,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("merge_commit_sha") val merge_commit_sha: String,
    @SerializedName("draft") val draft: Boolean,
    @SerializedName("commits_url") val commits_url: String,
    @SerializedName("review_comments_url") val review_comments_url: String,
    @SerializedName("review_comment_url") val review_comment_url: String,
    @SerializedName("comments_url") val comments_url: String,
    @SerializedName("statuses_url") val statuses_url: String,
    @SerializedName("author_association") val author_association: String
)