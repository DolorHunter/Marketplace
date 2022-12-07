package com.example.marketplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class UserProfileActivity : AppCompatActivity(), UserProfileAdapter.MyItemClickListener {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        firebaseAuth = FirebaseAuth.getInstance()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        // Get support action bar
        val appBar = supportActionBar
        // set App Title
        appBar!!.title = "Profile"
        // Display app icon in toolbar
        appBar.setDisplayShowHomeEnabled(true)
        appBar.setLogo(R.mipmap.ic_launcher)
        appBar.setDisplayUseLogoEnabled(true)

        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottom_nav.selectedItemId = R.id.action_home
        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_explore -> {
                    val intent = Intent(this, MarketActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
                    startActivity(intent)
                    finish()
                }
                R.id.action_add -> {
                    val intent = Intent(this, MarketItemAddActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
                    startActivity(intent)
                    finish()
                }
                R.id.action_home -> {
                    val intent = Intent(this, UserProfileActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
                    startActivity(intent)
                    finish()
                }
            }
            true
        }

        val user_profile_rview = findViewById<RecyclerView>(R.id.user_profile_rview)
        user_profile_rview.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this )

        val rAdapter = UserProfileAdapter(ProductList().productList, ProductList().posterMap)
        rAdapter.setMyItemClickListener(this)
        user_profile_rview.adapter = rAdapter
    }


    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, MarketItemDetailActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}
