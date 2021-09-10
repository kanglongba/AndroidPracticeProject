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
        addEnterTransitionListener()
        addExitTransitionListener()
        //6.如果跳转过来时，共享元素还没有渲染好，就不会有动画效果。这时可以使用postponeEnterTransition方法
        //暂停动画，等共享元素渲染好，再开启动画。因为本例的共享元素动画是设置在Activity上的，所以暂停也要
        //用Activity的方法，否则无效。
        activity?.postponeEnterTransition()
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
            listView.post {
                //7.由于RecyclerView使用了LiveData，有一个渲染延时，所以需要在它渲染后好后，再调用方法开启动画。
                //View.post方法正好可以满足这个需求，它会在View绘制完成后才调用。
                activity?.startPostponedEnterTransition()
            }
        })
    }

    /**
     * 5.添加共享元素动画监听器。
     *
     */
    fun addEnterTransitionListener() {
        val transition: Transition = requireActivity().window.sharedElementEnterTransition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(p0: Transition?) {
                Log.d("edison.to", "Enter onTransitionStart")
            }

            override fun onTransitionEnd(p0: Transition?) {
                Log.d("edison.to", "Enter onTransitionEnd")
                transition.removeListener(this)
            }

            override fun onTransitionCancel(p0: Transition?) {
                Log.d("edison.to", "Enter onTransitionCancel")
            }

            override fun onTransitionPause(p0: Transition?) {
                Log.d("edison.to", "Enter onTransitionPause")
                transition.removeListener(this)
            }

            override fun onTransitionResume(p0: Transition?) {
                Log.d("edison.to", "Enter onTransitionResume")
            }
        })
    }

    /**
     * 5.添加共享元素动画监听器。
     *
     */
    fun addExitTransitionListener() {
        val transition: Transition = requireActivity().window.sharedElementExitTransition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(p0: Transition?) {
                Log.d("edison.to", "Exit onTransitionStart")
            }

            override fun onTransitionEnd(p0: Transition?) {
                Log.d("edison.to", "Exit onTransitionEnd")
                transition.removeListener(this)
            }

            override fun onTransitionCancel(p0: Transition?) {
                Log.d("edison.to", "Exit onTransitionCancel")
            }

            override fun onTransitionPause(p0: Transition?) {
                Log.d("edison.to", "Exit onTransitionPause")
                transition.removeListener(this)
            }

            override fun onTransitionResume(p0: Transition?) {
                Log.d("edison.to", "Exit onTransitionResume")
            }
        })
    }
}