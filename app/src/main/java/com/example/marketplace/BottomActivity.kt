package com.example.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
/*
class BottomActivity : AppCompatActivity(), LayoutFragment.OnLayoutManagerInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom)

        val myToolbar = findViewById<Toolbar>(R.id.bottom_nav)
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        bottom_nav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.action_linear -> {
                    val fr = LayoutFragment.newInstance(1)
                    myToolbar.title = "Linear Layout"
                    supportFragmentManager.beginTransaction().replace(R.id.lmContainer, fr).commit()
                }
                R.id.action_grid -> {
                    val fr = LayoutFragment.newInstance(2)
                    myToolbar.title = "Grid Layout"
                    supportFragmentManager.beginTransaction().replace(R.id.lmContainer, fr).commit()
                }
                R.id.action_staggered -> {
                    val fr = LayoutFragment.newInstance(3)
                    myToolbar.title = "Staggered Grid Layout"
                    supportFragmentManager.beginTransaction().replace(R.id.lmContainer, fr).commit()
                }
            }
        }
// default - linear layout manager
        val fr = LayoutFragment.newInstance(1)
        myToolbar.title = "Layout Manager"
        supportFragmentManager.beginTransaction().replace(R.id.lmContainer, fr).commit()
    }
}

 */