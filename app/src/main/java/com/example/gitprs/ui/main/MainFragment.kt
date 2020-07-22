package com.example.gitprs.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gitprs.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
                .create(MainViewModel::class.java)

        initListener()

    }

    private fun initListener() {
        btn_search.setOnClickListener {

        }
    }



    private fun showIssues() {
        rv_issue.visibility = View.VISIBLE
        pb_issue.visibility = View.GONE
        iv_result.visibility = View.GONE


    }


    private fun showErrorView() {
        rv_issue.visibility = View.GONE
        pb_issue.visibility = View.GONE
        iv_result.visibility = View.VISIBLE


    }

}
