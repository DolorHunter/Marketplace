package com.example.marketplace

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MarketActivity : AppCompatActivity(), MarketAdapter.MyItemClickListener{

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var rAdapter: MarketAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market)

        firebaseAuth = FirebaseAuth.getInstance()
        val userId = UserList().userMap[firebaseAuth.currentUser?.uid.toString()]
        if (userId == null){
            firebaseAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        // Get support action bar
        val appBar = supportActionBar
        // set App Title
        appBar!!.title = "Marketplace"
        // Display app icon in toolbar
        appBar.setDisplayShowHomeEnabled(true)
        appBar.setLogo(R.mipmap.ic_launcher)
        appBar.setDisplayUseLogoEnabled(true)

        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottom_nav.selectedItemId = R.id.action_explore
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

        val rview_market = findViewById<RecyclerView>(R.id.rview_market)
        rview_market.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this )

        rAdapter = MarketAdapter(ProductList().productList, ProductList().posterMap, userId!!)
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


    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser == null){
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, MarketItemDetailActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}

