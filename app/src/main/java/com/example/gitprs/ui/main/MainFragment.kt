package com.example.gitprs.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitprs.R
import com.example.gitprs.common.Instance
import com.example.gitprs.common.Utils
import com.example.gitprs.model.PullRequest
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var pullRequestRVAdapter: PullRequestRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this, MainViewModel.AppViewModelFactory(Instance.getApiService())
        ).get(MainViewModel::class.java)

        initAdapter()
        initListener()
        observeLiveData()

    }

    private fun initAdapter() {
        pullRequestRVAdapter = PullRequestRVAdapter(requireContext(), emptyList())
        rv_issue.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_issue.adapter = pullRequestRVAdapter
    }

    private fun initListener() {
        btn_search.setOnClickListener {

            val ownerName = et_owner.text.toString()
            if (ownerName.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.owner_validation),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            val repoName = et_repo.text.toString()
            if (repoName.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.repo_validation),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            getOpenPullRequest(ownerName, repoName)
        }
    }

    private fun observeLiveData() {
        viewModel.observePullRequestLiveData().observe(viewLifecycleOwner, Observer {
            hideProgressBar()
            if (it.isNullOrEmpty()) {
                showErrorView(requireContext().getString(R.string.no_pull_request))
                return@Observer
            }
            showPullRequests(it)
        })

        viewModel.observeFailure().observe(viewLifecycleOwner, Observer {
            hideProgressBar()
            showErrorView(requireContext().getString(R.string.something_went_wrong))
        })
    }

    private fun getOpenPullRequest(ownerName: String, repoName: String) {
        if (Utils.isInternetConnected(requireContext())) {
            showProgressBar()
            viewModel.getOpenPullRequest(ownerName, repoName)
        } else {
            showErrorView(requireContext().getString(R.string.no_internet))
        }
    }

    private fun showProgressBar() {
        pb_issue.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        pb_issue.visibility = View.GONE
    }

    private fun showPullRequests(it: List<PullRequest>) {
        rv_issue.visibility = View.VISIBLE
        iv_result.visibility = View.GONE
        pullRequestRVAdapter?.updateData(it)
    }

    private fun showErrorView(message: String) {
        rv_issue.visibility = View.GONE
        pb_issue.visibility = View.GONE
        iv_result.visibility = View.VISIBLE
//        requireContext().getString(R.string.owner_validation)
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}
