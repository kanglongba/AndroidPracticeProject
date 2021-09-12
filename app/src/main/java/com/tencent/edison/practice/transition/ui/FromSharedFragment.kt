package com.tencent.edison.practice.transition.ui

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Transition
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tencent.edison.practice.R
import com.tencent.edison.practice.transition.vm.ShareViewModel

class FromSharedFragment : Fragment() {

    companion object {
        fun newInstance() = FromSharedFragment()
    }

    private lateinit var viewModel: ShareViewModel
    private lateinit var listView: RecyclerView
    private val adapter: FromSharedListAdapter by lazy {
        FromSharedListAdapter(mutableListOf()).apply {
            itemClickListener = object : FromSharedListAdapter.OnFromListItemClickListener {
                override fun onItemClick(post: Int, holder: SharedViewHolder) {
                    Toast.makeText(
                        activity,
                        viewModel.getUserModel().value?.get(post)?.name,
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(activity, ToSharedActivity::class.java)
                    val userId = viewModel.getUserModel().value?.get(post)?.id ?: 101
                    intent.putExtra(
                        SharedViewHolder.KEY_USER_ID,
                        userId
                    )
                    //2.设置共享元素。如有多个共享元素，可以用Pair对象。
                    val activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        activity!!,
                        holder.avatar,
                        SharedViewHolder.SHARED_AVATAR + userId
                    )
                    //3.跳转Activity
                    startActivity(intent, activityOptions.toBundle())
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addExitTransitionListener()
        addEnterTransitionListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.from_shared_fragment, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById(R.id.fromSharedList)
        listView.setHasFixedSize(true)
        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = adapter
        viewModel = ViewModelProvider(this).get(ShareViewModel::class.java)
        viewModel.getUserModel().observe(viewLifecycleOwner, Observer {
            adapter.putUserData(it)
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
                Log.d("edison.from", "Enter onTransitionStart")
            }

            override fun onTransitionEnd(p0: Transition?) {
                Log.d("edison.from", "Enter onTransitionEnd")
                transition.removeListener(this)
            }

            override fun onTransitionCancel(p0: Transition?) {
                Log.d("edison.from", "Enter onTransitionCancel")
            }

            override fun onTransitionPause(p0: Transition?) {
                Log.d("edison.from", "Enter onTransitionPause")
                transition.removeListener(this)
            }

            override fun onTransitionResume(p0: Transition?) {
                Log.d("edison.from", "Enter onTransitionResume")
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
                Log.d("edison.from", "Exit onTransitionStart")
            }

            override fun onTransitionEnd(p0: Transition?) {
                Log.d("edison.from", "Exit onTransitionEnd")
                transition.removeListener(this)
            }

            override fun onTransitionCancel(p0: Transition?) {
                Log.d("edison.from", "Exit onTransitionCancel")
            }

            override fun onTransitionPause(p0: Transition?) {
                Log.d("edison.from", "Exit onTransitionPause")
                transition.removeListener(this)
            }

            override fun onTransitionResume(p0: Transition?) {
                Log.d("edison.from", "Exit onTransitionResume")
            }
        })
    }
}