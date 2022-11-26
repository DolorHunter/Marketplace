package com.example.marketplace

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class MarketActivity : AppCompatActivity(), MarketAdapter.MyItemClickListener{

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        // Get support action bar
        val appBar = supportActionBar
        // set App Title
        appBar!!.title = "Marketplace"
        // set sub title
        appBar.subtitle = "Exploring.."
        // Display app icon in toolbar
        appBar.setDisplayShowHomeEnabled(true)
        appBar.setLogo(R.mipmap.ic_launcher)
        appBar.setDisplayUseLogoEnabled(true)


        val rview_market = findViewById<RecyclerView>(R.id.rview_market)
        rview_market.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this )

        val rAdapter = MarketAdapter(ProductList().productList, ProductList().posterMap)
        rAdapter.setMyItemClickListener(this)
        rview_market.adapter = rAdapter

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                var filteredList = mutableListOf<ProductData>()
                ProductList().productList.forEach {
                    if (it.name.lowercase().contains(query.lowercase())){
                        filteredList.add(it)
                    }
                }

                if (filteredList.isEmpty()){
                    Toast.makeText(applicationContext, "Not found $query.", Toast.LENGTH_SHORT).show()
                }else{
                    rAdapter.setFilteredList(filteredList)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                var filteredList = mutableListOf<ProductData>()
                ProductList().productList.forEach {
                    if (it.name.lowercase().contains(newText.lowercase())){
                        filteredList.add(it)
                    }
                }

                if (filteredList.isEmpty()){
                    //Toast.makeText(applicationContext, "Not found $newText.", Toast.LENGTH_SHORT).show()
                }else{
                    rAdapter.setFilteredList(filteredList)
                }
                return false
            }
        })


    }


    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, MarketItemDetailActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}

