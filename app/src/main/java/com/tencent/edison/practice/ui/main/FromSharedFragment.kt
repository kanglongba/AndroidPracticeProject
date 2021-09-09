package com.tencent.edison.practice.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tencent.edison.practice.R
import com.tencent.edison.practice.ToSharedActivity

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
                    //2.设置共享元素
                    val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity!!,
                        holder.avatar,
                        "${SharedViewHolder.SHARED_AVATAR}$userId"
                    )
                    //3.跳转Activity
                    ActivityCompat.startActivity(activity!!, intent, activityOptions.toBundle())
                }
            }
        }
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

}