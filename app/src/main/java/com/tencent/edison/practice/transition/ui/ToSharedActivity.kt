package com.tencent.edison.practice.transition.ui

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.tencent.edison.practice.R

class ToSharedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.开启专场动画。默认是开启的。必须在setContentView之前调用。
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        //如果不设置转场动画和共享元素动画，那么默认的转场动画是Fade，默认的共享元素动画是ChangeBounds。
        //设置从FromSharedActivity跳转过来(进入)时的转场动画。可用excludeTarget方法排除不想参与动画的View。
        window.enterTransition = Slide(Gravity.RIGHT)
        //设置返回到FromSharedActivity(离开)时的转场动画。可用excludeTarget方法排除不想参与动画的View。
        window.returnTransition = Slide(Gravity.RIGHT)
        //设置共享元素动画
        window.sharedElementEnterTransition = ChangeBounds().setDuration(200)
        window.sharedElementExitTransition = ChangeBounds().setDuration(200)
        window.sharedElementReenterTransition = ChangeBounds().setDuration(200)
        window.sharedElementReturnTransition = ChangeBounds().setDuration(200)

        //设置不允许动画重叠
        window.allowEnterTransitionOverlap = false
        window.allowReturnTransitionOverlap = false

        setContentView(R.layout.to_shared_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ToSharedFragment.newInstance())
                .commitNow()
        }
    }
}