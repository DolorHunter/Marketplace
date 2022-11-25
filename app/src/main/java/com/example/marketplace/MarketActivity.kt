package com.example.marketplace

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView

class MarketActivity : AppCompatActivity(), MarketAdapter.MyItemClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market)

        val rview_market = findViewById<RecyclerView>(R.id.rview_market)
        rview_market.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this )

        val rAdapter = MarketAdapter(ProductList().productList, ProductList().posterMap)
        rAdapter.setMyItemClickListener(this)
        rview_market.adapter = rAdapter

    }


    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, MarketItemDetailActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}

