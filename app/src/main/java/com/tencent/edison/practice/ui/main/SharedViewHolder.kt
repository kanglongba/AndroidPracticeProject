package com.tencent.edison.practice.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.tencent.edison.practice.R
import com.tencent.edison.practice.model.UserContact

/**
 * author: qonyqian
 * created on: 2021/9/9 8:01 下午
 * version：1.0
 * description:
 */
class SharedViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {

    companion object {
        const val KEY_USER_ID = "userId"
        const val SHARED_AVATAR = "shareAvatar"
    }

    lateinit var avatar: ImageView
    lateinit var name: TextView
    lateinit var company: TextView
    lateinit var profession: TextView

    init {
        initView()
    }

    fun initView() {
        avatar = item.findViewById(R.id.avatar)
        name = item.findViewById(R.id.name)
        company = item.findViewById(R.id.company)
        profession = item.findViewById(R.id.profession)
    }

    fun bindData(user: UserContact) {
        avatar.setImageResource(user.avatar)
        name.text = user.name
        company.text = user.company
        profession.text = user.profession
    }

    fun setTransitionName(user: UserContact) {
        ViewCompat.setTransitionName(avatar, SHARED_AVATAR + user.id)
    }
}