package com.tencent.edison.practice

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.tencent.edison.practice.ui.main.FromSharedFragment

/**
 * 共享元素文章：
 * @see <a href="https://developer.android.com/training/transitions/start-activity?hl=zh-cn">使用动画启动 Activity</a>
 * @see <a href="https://github.com/android/animation-samples/tree/master/ActivitySceneTransitionBasic">官方共享元素动画demo</a>
 *
 */
class FromSharedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.开启专场动画。默认是开启的。必须在setContentView之前调用。
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        setContentView(R.layout.from_shared_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FromSharedFragment.newInstance())
                .commitNow()
        }
    }
}