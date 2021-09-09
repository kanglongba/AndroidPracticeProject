package com.tencent.edison.practice

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.tencent.edison.practice.ui.main.ToSharedFragment

class ToSharedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.开启专场动画。默认是开启的。必须在setContentView之前调用。
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        setContentView(R.layout.to_shared_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ToSharedFragment.newInstance())
                .commitNow()
        }
    }
}