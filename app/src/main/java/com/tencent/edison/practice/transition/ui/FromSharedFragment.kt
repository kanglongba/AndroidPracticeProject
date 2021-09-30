package com.tencent.edison.practice.transition.ui

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.app.SharedElementCallback
import android.content.Context
import android.content.Intent
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
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
        addReEnterTransitionListener()
        addReturnTransitionListener()
        addEnterSharedElementCallback()
        addExitSharedElementCallback()
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

    /**
     * 5.添加共享元素动画监听器。
     *
     */
    fun addReEnterTransitionListener() {
        val transition: Transition = requireActivity().window.sharedElementReenterTransition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(p0: Transition?) {
                Log.d("edison.from", "ReEnter onTransitionStart")
            }

            override fun onTransitionEnd(p0: Transition?) {
                Log.d("edison.from", "ReEnter onTransitionEnd")
                transition.removeListener(this)
            }

            override fun onTransitionCancel(p0: Transition?) {
                Log.d("edison.from", "ReEnter onTransitionCancel")
            }

            override fun onTransitionPause(p0: Transition?) {
                Log.d("edison.from", "ReEnter onTransitionPause")
                transition.removeListener(this)
            }

            override fun onTransitionResume(p0: Transition?) {
                Log.d("edison.from", "ReEnter onTransitionResume")
            }
        })
    }

    /**
     * 5.添加共享元素动画监听器。
     *
     */
    fun addReturnTransitionListener() {
        val transition: Transition = requireActivity().window.sharedElementReturnTransition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(p0: Transition?) {
                Log.d("edison.from", "Return onTransitionStart")
            }

            override fun onTransitionEnd(p0: Transition?) {
                Log.d("edison.from", "Return onTransitionEnd")
                transition.removeListener(this)
            }

            override fun onTransitionCancel(p0: Transition?) {
                Log.d("edison.from", "Return onTransitionCancel")
            }

            override fun onTransitionPause(p0: Transition?) {
                Log.d("edison.from", "Return onTransitionPause")
                transition.removeListener(this)
            }

            override fun onTransitionResume(p0: Transition?) {
                Log.d("edison.from", "Return onTransitionResume")
            }
        })
    }

    fun addEnterSharedElementCallback() {
        requireActivity().setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onSharedElementStart(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementStart(
                    sharedElementNames,
                    sharedElements,
                    sharedElementSnapshots
                )
                Log.d("edison.from", "enter onSharedElementStart")
            }

            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
                Log.d("edison.from", "enter onSharedElementEnd")
            }

            override fun onRejectSharedElements(rejectedSharedElements: MutableList<View>?) {
                super.onRejectSharedElements(rejectedSharedElements)
                Log.d("edison.from", "enter onRejectSharedElements")
            }

            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {
                super.onMapSharedElements(names, sharedElements)
                Log.d("edison.from", "enter onMapSharedElements")
            }

            override fun onCaptureSharedElementSnapshot(
                sharedElement: View?,
                viewToGlobalMatrix: Matrix?,
                screenBounds: RectF?
            ): Parcelable {
                Log.d("edison.from", "enter onCaptureSharedElementSnapshot")
                return super.onCaptureSharedElementSnapshot(
                    sharedElement,
                    viewToGlobalMatrix,
                    screenBounds
                )
            }

            override fun onCreateSnapshotView(context: Context?, snapshot: Parcelable?): View {
                Log.d("edison.from", "enter onCreateSnapshotView")
                return super.onCreateSnapshotView(context, snapshot)
            }

            override fun onSharedElementsArrived(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                listener: OnSharedElementsReadyListener?
            ) {
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
                Log.d("edison.from", "enter onSharedElementsArrived")
            }
        })
    }

    /**
     * 5.添加共享元素动画监听器。
     * 实验表明，SharedElementCallback 比 TransitionListener 好用很多。
     * 1.在FromSharedActivity跳转到ToSharedActivity的过程中：
     * * FromSharedActivity的 setExitSharedElementCallback 会得到调用
     * * ToSharedActivity的 setEnterSharedElementCallback 和 sharedElementEnterTransition 会得到调用
     * 2.在ToSharedActivity返回 FromSharedActivity 的过程中：
     * * FromSharedActivity的 setExitSharedElementCallback 会得到调用
     * * ToSharedActivity的 setEnterSharedElementCallback 和sharedElementReturnTransition 会得到调用
     *
     */
    fun addExitSharedElementCallback() {
        requireActivity().setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onSharedElementStart(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementStart(
                    sharedElementNames,
                    sharedElements,
                    sharedElementSnapshots
                )
                Log.d("edison.from", "exit onSharedElementStart")
            }

            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
                Log.d("edison.from", "exit onSharedElementEnd")
            }

            override fun onRejectSharedElements(rejectedSharedElements: MutableList<View>?) {
                super.onRejectSharedElements(rejectedSharedElements)
                Log.d("edison.from", "exit onRejectSharedElements")
            }

            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {
                super.onMapSharedElements(names, sharedElements)
                Log.d("edison.from", "exit onMapSharedElements")
            }

            override fun onCaptureSharedElementSnapshot(
                sharedElement: View?,
                viewToGlobalMatrix: Matrix?,
                screenBounds: RectF?
            ): Parcelable {
                Log.d("edison.from", "exit onCaptureSharedElementSnapshot")
                return super.onCaptureSharedElementSnapshot(
                    sharedElement,
                    viewToGlobalMatrix,
                    screenBounds
                )
            }

            override fun onCreateSnapshotView(context: Context?, snapshot: Parcelable?): View {
                Log.d("edison.from", "exit onCreateSnapshotView")
                return super.onCreateSnapshotView(context, snapshot)
            }

            override fun onSharedElementsArrived(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                listener: OnSharedElementsReadyListener?
            ) {
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
                Log.d("edison.from", "exit onSharedElementsArrived")
            }
        })
    }
}