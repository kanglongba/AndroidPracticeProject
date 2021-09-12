package com.tencent.edison.practice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tencent.edison.practice.transition.ui.FromSharedActivity

class MainActivity : AppCompatActivity() {

    lateinit var sharedElement: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClick()
    }

    fun setOnClick() {
        sharedElement = findViewById(R.id.sharedElement)
        sharedElement.setOnClickListener {
            val intent = Intent(this, FromSharedActivity::class.java)
            startActivity(intent)
        }

    }
}