package com.tencent.edison.practice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tencent.edison.practice.ui.main.ToSharedFragment

class ToSharedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.to_shared_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ToSharedFragment.newInstance())
                .commitNow()
        }
    }
}