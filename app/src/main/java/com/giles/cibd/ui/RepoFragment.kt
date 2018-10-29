package com.giles.cibd.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.giles.cibd.databinding.RepoFragmentBinding
import com.giles.cibd.di.Injectable
import com.giles.cibd.viewmodel.CIBDViewModelFactory
import kotlinx.android.synthetic.main.repo_fragment.*
import java.util.*
import javax.inject.Inject

class RepoFragment : Fragment(), Injectable {

    companion object {

        val TAG = "Repo"

        fun newInstance(): RepoFragment {
            return RepoFragment()
        }
    }

    @Inject
    lateinit var factory: CIBDViewModelFactory

    private lateinit  var binding: RepoFragmentBinding

    private lateinit  var viewModel: RepoViewModel

    private val repoAdapter = RepoAdapter(ArrayList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RepoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edtQuery.setOnKeyListener(View.OnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch()
                return@OnKeyListener true
            }
            false
        })

        btnSearch.setOnClickListener { doSearch() }

        recyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = repoAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this, factory).get(RepoViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
//        viewModel.repos.observe(this, Observer { resource -> Timber.d("ddddd")})


//        viewModel.repos.observe(this, android.arch.lifecycle.Observer {
//            Timber.d("repos it = $it")
//        })
//
//        viewModel.getNetworkState().observe(this, android.arch.lifecycle.Observer {
//            Timber.d("network it = $it")
//        })


    }

    private fun doSearch() {
        val query = edtQuery.text.toString()
//        viewModel.searchRepo(query)
        dismissKeyboard()
    }

    private fun dismissKeyboard() {
        val view = activity!!.currentFocus
        if (view != null) {
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}