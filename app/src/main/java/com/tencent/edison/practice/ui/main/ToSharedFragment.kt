package com.tencent.edison.practice.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tencent.edison.practice.R

class ToSharedFragment : Fragment() {

    companion object {
        fun newInstance() = ToSharedFragment()
    }

    private lateinit var viewModel: ShareViewModel
    private lateinit var listView: RecyclerView
    private val adapter: ToSharedListAdapter by lazy {
        ToSharedListAdapter(mutableListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.to_shared_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = activity?.intent?.getIntExtra(SharedViewHolder.KEY_USER_ID, 101) ?: 101
        listView = view.findViewById(R.id.toSharedList)
        listView.setHasFixedSize(true)
        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = adapter
        viewModel = ViewModelProvider(this).get(ShareViewModel::class.java)
        viewModel.getUserModel().observe(viewLifecycleOwner, Observer {
            adapter.putUserData(it)
            listView.scrollToPosition(adapter.getPositionByUserId(userId))
        })
    }
}