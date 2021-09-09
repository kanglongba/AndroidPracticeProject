package com.tencent.edison.practice.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tencent.edison.practice.R
import com.tencent.edison.practice.model.UserContact

class ShareViewModel : ViewModel() {

    private var userModel: MutableLiveData<List<UserContact>> = MutableLiveData()

    fun getUserModel(): LiveData<List<UserContact>> {
        userModel.value = createListData()
        return userModel
    }

    fun createListData(): List<UserContact> {
        val dataList = ArrayList<UserContact>()
        dataList.add(
            UserContact(
                101,
                "布鲁克", "百度", 18, "浙江大学", "音乐家",
                "黄泉果实", R.drawable.brook
            )
        )
        dataList.add(
            UserContact(
                102,
                "弗兰奇", "美团", 19, "上海交通大学", "机械师",
                "钢蛋果实", R.drawable.franky
            )
        )
        dataList.add(
            UserContact(
                103,
                "红发", "拼多多", 20, "复旦大学", "船长",
                "面子果实", R.drawable.hongfa
            )
        )
        dataList.add(
            UserContact(
                104,
                "路飞", "腾讯", 21, "清华大学", "船长",
                "橡胶果实", R.drawable.lufei
            )
        )
        dataList.add(
            UserContact(
                105,
                "罗", "网易", 22, "武汉大学", "医生",
                "手术果实", R.drawable.luo
            )
        )
        dataList.add(
            UserContact(
                106,
                "罗宾", "虾皮", 23, "中山大学", "历史学家",
                "老婆果实", R.drawable.luobin
            )
        )
        dataList.add(
            UserContact(
                107,
                "绿发", "得物", 24, "同济大学", "脑残粉",
                "屏障果实", R.drawable.lvfa
            )
        )
        dataList.add(
            UserContact(
                108,
                "娜美", "小红书", 25, "北京大学", "航海士",
                "老婆果实", R.drawable.namei
            )
        )
        dataList.add(
            UserContact(
                109,
                "女帝", "阿里巴巴", 26, "电子科技大学", "船长",
                "老婆果实", R.drawable.ncdi
            )
        )
        dataList.add(
            UserContact(
                110,
                "粉发", "陌陌", 27, "华中科技大学", "脑残粉",
                "小姨子果实", R.drawable.perona
            )
        )
        dataList.add(
            UserContact(
                111,
                "山治", "滴滴", 28, "北京航空航天大学", "厨师",
                "绅士果实", R.drawable.shanzhi
            )
        )
        dataList.add(
            UserContact(
                112,
                "索隆", "小米", 29, "南京大学", "剑豪",
                "迷路果实", R.drawable.suolong
            )
        )
        dataList.add(
            UserContact(
                113,
                "鹰眼", "字节跳动", 30, "四川大学", "剑豪",
                "装逼果实", R.drawable.yingyan
            )
        )
        return dataList
    }


}