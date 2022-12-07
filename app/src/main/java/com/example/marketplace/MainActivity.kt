package com.example.marketplace


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)

        // 测试 直接跳转到Recycle Activity
        //val intent = Intent(this, MarketActivity::class.java)
        //startActivity(intent)

        //val intent = Intent(this, MarketItemAddActivity::class.java)
        //startActivity(intent)

        //val intent = Intent(this, UserProfileActivity::class.java)
        //startActivity(intent)
    }
}