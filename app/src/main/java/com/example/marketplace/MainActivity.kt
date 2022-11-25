package com.example.marketplace


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 测试 直接跳转到Recycle Activity
        val intent = Intent(this, MarketActivity::class.java)
        startActivity(intent)
    }
}