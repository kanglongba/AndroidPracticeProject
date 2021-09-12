package com.tencent.edison.practice.transition.ui

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.tencent.edison.practice.R

/**
 * 共享元素文章：
 * @see <a href="https://developer.android.com/training/transitions/start-activity?hl=zh-cn">使用动画启动 Activity</a>
 * @see <a href="https://github.com/android/animation-samples/tree/master/ActivitySceneTransitionBasic">官方共享元素动画demo</a>
 * @see <a href="https://www.jianshu.com/p/1995507aa240">高逼格Android转场动画(写得非常好)</a>
 * @see <a href="https://zhuanlan.zhihu.com/p/159372313">炫酷的Android过渡动画，让APP更富有生机(写得非常好)</a>
 * @see <a href="https://smuyyh.top/2020/10/19/android-transition/#more">深入理解 Android Transition 场景动画(盒马兄弟写的)</a>
 *
 * 注：共享元素动画(ChangeBounds、ChangeTransform、ChangeClipBounds、ChangeImageTransform)是Transition动画
 * 的一种，Transition动画包含了四种共享元素动画和三种转场动画(Fade、Slide、Explode)。AutoTransition是默认动画，ChangeBounds、Fade动画的集合。
 */
class FromSharedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.开启转场动画。默认是开启的。必须在setContentView之前调用。
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        //如果不设置转场动画和共享元素动画，那么默认的转场动画是Fade，默认的共享元素动画是ChangeBounds。
        //设置跳转到ToSharedActivity(离开)时的转场动画。可用excludeTarget方法排除不想参与动画的View。
        window.exitTransition = Slide(Gravity.LEFT)
        //设置从ToSharedActivity返回(进入)时的转场动画。可用excludeTarget方法排除不想参与动画的View。
        window.reenterTransition = Slide(Gravity.LEFT)
        //设置共享元素动画
        window.sharedElementEnterTransition = ChangeBounds().setDuration(200)

        //设置不允许转场动画重叠
        window.allowEnterTransitionOverlap = false
        window.allowReturnTransitionOverlap = false

        setContentView(R.layout.from_shared_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FromSharedFragment.newInstance())
                .commitNow()
        }
    }
}