package com.tencent.edison.practice.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tencent.edison.practice.R
import com.tencent.edison.practice.model.UserContact

/**
 * author: qonyqian
 * created on: 2021/9/9 8:00 下午
 * version：1.0
 * description:
 */
class FromSharedListAdapter(var userList: MutableList<UserContact>) :
    RecyclerView.Adapter<SharedViewHolder>() {

    lateinit var itemClickListener: OnFromListItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SharedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_from_shared_fragment_list, parent, false);
        return SharedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SharedViewHolder, position: Int) {
        holder.bindData(userList[position])
        //2.设置共享元素id。貌似From页面不设置也可以生效，但是为了避免为题，仍然设置上，且与To页面id相同。
        holder.setTransitionName(userList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position, holder)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun putUserData(data: List<UserContact>) {
        userList.addAll(data)
        notifyDataSetChanged()
    }

    interface OnFromListItemClickListener {

        fun onItemClick(post: Int, holder: SharedViewHolder)
    }
}