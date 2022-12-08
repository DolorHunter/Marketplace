package com.example.marketplace

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class UserProfileActivity : AppCompatActivity(){

    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        firebaseAuth = FirebaseAuth.getInstance()
        val userId = UserList().userMap[firebaseAuth.currentUser?.uid.toString()]

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        // Get support action bar
        val appBar = supportActionBar
        // set App Title
        appBar!!.title = "Home"
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

        val user_profile_image = findViewById<ImageView>(R.id.user_profile_image)
        val user_profile_rating = findViewById<RatingBar>(R.id.user_profile_rating)
        val user_profile_username = findViewById<TextView>(R.id.user_profile_username)

        UserList().avatarMap[userId]?.let { user_profile_image.setImageResource(it) }

        user_profile_username.text = UserList().userList[userId!!].name
        user_profile_rating.rating = 3.5F

        val user_profile_signout = findViewById<Button>(R.id.user_profile_signout)
        user_profile_signout.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }


        val user_profile_rview = findViewById<RecyclerView>(R.id.user_profile_rview)
        user_profile_rview.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this )


        val my_listing = ProductList().productList.filter { it.sellerId == userId }
        val rAdapter = UserProfileAdapter(my_listing, ProductList().posterMap, userId)
        user_profile_rview.adapter = rAdapter


        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                var filteredList = mutableListOf<ProductData>()
                my_listing.forEach {
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
                my_listing.forEach {
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
}
