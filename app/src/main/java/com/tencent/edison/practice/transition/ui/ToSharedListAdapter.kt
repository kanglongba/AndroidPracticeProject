package com.tencent.edison.practice.transition.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tencent.edison.practice.R
import com.tencent.edison.practice.transition.model.UserContact

/**
 * author: qonyqian
 * created on: 2021/9/9 10:12 下午
 * version：1.0
 * description:
 */
class ToSharedListAdapter(var userList: MutableList<UserContact>) :
    RecyclerView.Adapter<SharedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SharedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_to_shared_fragment_list, parent, false);
        return SharedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SharedViewHolder, position: Int) {
        holder.bindData(userList[position])
        //4.设置共享元素Id。需与第2步设置的共享元素id相同。
        holder.setTransitionName(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun putUserData(data: List<UserContact>) {
        userList.addAll(data)
        notifyDataSetChanged()
    }

    fun getPositionByUserId(userId: Int): Int {
        for (index in 0 until userList.size) {
            if (userList[index].id == userId) {
                return index
            }
        }
        return 0
    }
}