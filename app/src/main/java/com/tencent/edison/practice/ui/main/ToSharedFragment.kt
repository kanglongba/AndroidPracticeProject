package com.tencent.edison.practice.ui.main

import android.os.Bundle
import android.transition.Transition
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addTransitionListener()
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

    /**
     * 5.添加共享元素动画监听器。
     *
     */
    fun addTransitionListener() {
        val transition: Transition = requireActivity().window.sharedElementEnterTransition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(p0: Transition?) {
                Log.d("edison", "onTransitionStart")
            }

            override fun onTransitionEnd(p0: Transition?) {
                Log.d("edison", "onTransitionEnd")
                transition.removeListener(this)
            }

            override fun onTransitionCancel(p0: Transition?) {
                Log.d("edison", "onTransitionCancel")
            }

            override fun onTransitionPause(p0: Transition?) {
                Log.d("edison", "onTransitionPause")
                transition.removeListener(this)
            }

            override fun onTransitionResume(p0: Transition?) {
                Log.d("edison", "onTransitionResume")
            }
        })
    }
}