package com.tencent.edison.practice.transition.ui

import android.app.SharedElementCallback
import android.content.Context
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
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
import com.tencent.edison.practice.transition.vm.ShareViewModel

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
        addReEnterTransitionListener()
        addReturnTransitionListener()
        addEnterSharedElementCallback()
        addExitSharedElementCallback()
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
     * 经过试验表明，只有这个监听能确保每次都调用。其余的那些监听（尤其是FromSharedActivity页面的）均不能
     * 保证调用，有的甚至一次都没调用。
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

    /**
     * 5.添加共享元素动画监听器。
     *
     */
    fun addReEnterTransitionListener() {
        val transition: Transition = requireActivity().window.sharedElementReenterTransition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(p0: Transition?) {
                Log.d("edison.to", "ReEnter onTransitionStart")
            }

            override fun onTransitionEnd(p0: Transition?) {
                Log.d("edison.to", "ReEnter onTransitionEnd")
                transition.removeListener(this)
            }

            override fun onTransitionCancel(p0: Transition?) {
                Log.d("edison.to", "ReEnter onTransitionCancel")
            }

            override fun onTransitionPause(p0: Transition?) {
                Log.d("edison.to", "ReEnter onTransitionPause")
                transition.removeListener(this)
            }

            override fun onTransitionResume(p0: Transition?) {
                Log.d("edison.to", "ReEnter onTransitionResume")
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
                Log.d("edison.to", "Return onTransitionStart")
            }

            override fun onTransitionEnd(p0: Transition?) {
                Log.d("edison.to", "Return onTransitionEnd")
                transition.removeListener(this)
            }

            override fun onTransitionCancel(p0: Transition?) {
                Log.d("edison.to", "Return onTransitionCancel")
            }

            override fun onTransitionPause(p0: Transition?) {
                Log.d("edison.to", "Return onTransitionPause")
                transition.removeListener(this)
            }

            override fun onTransitionResume(p0: Transition?) {
                Log.d("edison.to", "Return onTransitionResume")
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
                Log.d("edison.to", "enter onSharedElementStart")
            }

            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
                Log.d("edison.to", "enter onSharedElementEnd")
            }

            override fun onRejectSharedElements(rejectedSharedElements: MutableList<View>?) {
                super.onRejectSharedElements(rejectedSharedElements)
                Log.d("edison.to", "enter onRejectSharedElements")
            }

            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {
                super.onMapSharedElements(names, sharedElements)
                Log.d("edison.to", "enter onMapSharedElements")
            }

            override fun onCaptureSharedElementSnapshot(
                sharedElement: View?,
                viewToGlobalMatrix: Matrix?,
                screenBounds: RectF?
            ): Parcelable {
                Log.d("edison.to", "enter onCaptureSharedElementSnapshot")
                return super.onCaptureSharedElementSnapshot(
                    sharedElement,
                    viewToGlobalMatrix,
                    screenBounds
                )
            }

            override fun onCreateSnapshotView(context: Context?, snapshot: Parcelable?): View {
                Log.d("edison.to", "enter onCreateSnapshotView")
                return super.onCreateSnapshotView(context, snapshot)
            }

            override fun onSharedElementsArrived(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                listener: OnSharedElementsReadyListener?
            ) {
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
                Log.d("edison.to", "enter onSharedElementsArrived")
            }
        })
    }

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
                Log.d("edison.to", "exit onSharedElementStart")
            }

            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
                Log.d("edison.to", "exit onSharedElementEnd")
            }

            override fun onRejectSharedElements(rejectedSharedElements: MutableList<View>?) {
                super.onRejectSharedElements(rejectedSharedElements)
                Log.d("edison.to", "exit onRejectSharedElements")
            }

            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {
                super.onMapSharedElements(names, sharedElements)
                Log.d("edison.to", "exit onMapSharedElements")
            }

            override fun onCaptureSharedElementSnapshot(
                sharedElement: View?,
                viewToGlobalMatrix: Matrix?,
                screenBounds: RectF?
            ): Parcelable {
                Log.d("edison.to", "exit onCaptureSharedElementSnapshot")
                return super.onCaptureSharedElementSnapshot(
                    sharedElement,
                    viewToGlobalMatrix,
                    screenBounds
                )
            }

            override fun onCreateSnapshotView(context: Context?, snapshot: Parcelable?): View {
                Log.d("edison.to", "exit onCreateSnapshotView")
                return super.onCreateSnapshotView(context, snapshot)
            }

            override fun onSharedElementsArrived(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                listener: OnSharedElementsReadyListener?
            ) {
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
                Log.d("edison.to", "exit onSharedElementsArrived")
            }
        })
    }
}