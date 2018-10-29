package com.giles.cibd.ui.information

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.giles.cibd.databinding.InformationFragmentBinding
import com.giles.cibd.di.Injectable
import com.giles.cibd.ui.RepoAdapter
import com.giles.cibd.viewmodel.CIBDViewModelFactory
import kotlinx.android.synthetic.main.information_fragment.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class InformationFragment : Fragment(), Injectable {

    companion object {

        val TAG = "InformationFragment"

        fun newInstance(): InformationFragment {
            return InformationFragment()
        }
    }

    @Inject
    lateinit var factory: CIBDViewModelFactory

    private lateinit  var binding: InformationFragmentBinding

    private lateinit  var viewModel: InformationViewModel

    private val repoAdapter = RepoAdapter(ArrayList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = InformationFragmentBinding.inflate(inflater, container, false)
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

        btnRetry.setOnClickListener { doRetry() }

        recyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = repoAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(InformationViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
//        viewModel.repos.observe(this, Observer { resource -> Timber.d("ddddd")})


        viewModel.repos.observe(this, android.arch.lifecycle.Observer {
            Timber.d("repos it = $it")
        })

        viewModel.networkState.observe(this, android.arch.lifecycle.Observer{
            Timber.d("networkState it = $it")
        })
//        viewModel.getNetworkState().observe(this, android.arch.lifecycle.Observer {
//            Timber.d("network it = $it")
//        })


    }

    private fun doSearch() {
        val query = edtQuery.text.toString()
        viewModel.searchRepo(query)
        dismissKeyboard()
    }

    private fun doRetry() {
        viewModel.retry()
    }


    private fun dismissKeyboard() {
        val view = activity!!.currentFocus
        if (view != null) {
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}