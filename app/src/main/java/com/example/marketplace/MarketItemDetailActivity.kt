package com.example.marketplace

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MarketItemDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    @SuppressLint("SetTextI18n", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_item_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        // Get support action bar
        val appBar = supportActionBar
        // set App Title
        appBar!!.title = "Marketplace"
        // set sub title
        appBar.subtitle = "Details"
        // Display app icon in toolbar
        appBar.setDisplayShowHomeEnabled(true)
        appBar.setLogo(R.mipmap.ic_launcher)
        appBar.setDisplayUseLogoEnabled(true)

        toolbar.inflateMenu(R.menu.menu_upper)

        val extras = intent.extras
        if (extras != null) {
            val position = extras.getInt("position")
            val curItem = ProductList().productList[position]

            val itemImage: ImageView = findViewById(R.id.market_item_detail_image)
            val itemName: TextView = findViewById(R.id.market_item_detail_name)
            val itemDescription: TextView = findViewById(R.id.market_item_detail_description)
            val itemPrice: TextView = findViewById(R.id.market_item_detail_price)
            val itemCondition: TextView = findViewById(R.id.market_item_detail_condition)
            val itemListedDate: TextView = findViewById(R.id.market_item_detail_listedDate)

            appBar.subtitle  = curItem.name

            itemName.text = curItem.name
            itemDescription.text = curItem.description
            itemPrice.text = "$" + String.format("%.2f", curItem.price)

            setupBottomToolbarItemSelected(curItem.name, curItem.description)

            //itemImage.setImageResource(R.mipmap.ic_launcher_foreground)
            ProductList().posterMap[curItem.name]?.let { itemImage.setImageResource(it) }

            itemCondition.text = curItem.condition
            when (curItem.condition){
                "Used - Like New" -> {
                    itemCondition.setTextColor(Color.parseColor("#04930d"))
                }
                "Used - Good" -> {
                    itemCondition.setTextColor(Color.parseColor("#2240fc"))
                }
                "Used - Fair" -> {
                    itemCondition.setTextColor(Color.parseColor("#a644e5"))
                }
                else -> {
                    itemCondition.setTextColor(Color.parseColor("#000000"))
                }
            }
            itemListedDate.text = curItem.listedDate

            // Google Maps
            val mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
            mapFragment.getMapAsync(this)


            val itemSellerName: TextView = findViewById(R.id.market_item_detail_seller)
            val itemSellerRating: RatingBar = findViewById(R.id.market_item_detail_seller_rating)
            val itemSellerImage: ImageView = findViewById(R.id.market_item_detail_seller_image)
            val itemContactSeller: Button = findViewById(R.id.market_item_detail_contact_seller)

            itemSellerName.text = curItem.sellerName
            itemSellerRating.rating = 3.5F

            //itemSellerImage.setImageResource(R.mipmap.ic_launcher)
            UserList().avatarMap[curItem.sellerId]?.let { itemSellerImage.setImageResource(it) }

            itemContactSeller.setOnClickListener {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:123456789")
                startActivity(callIntent)
            }
        }else{
            Toast.makeText(this, "No position for detail.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {  // not (menu: Menu?)
        // inflate the menu into toolbar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_upper, menu)

        return super.onCreateOptionsMenu(menu)
    }

    fun setupBottomToolbarItemSelected(name: String, desc: String){
        val myBottomToolbar = findViewById<Toolbar>(R.id.toolbar)
        myBottomToolbar.setOnMenuItemClickListener{ it ->
            when(it.itemId){
                R.id.action_chat -> {
                    Toast.makeText(this, "Chat with seller.", Toast.LENGTH_SHORT).show()

                    val callIntent = Intent(Intent.ACTION_DIAL)
                    callIntent.data = Uri.parse("tel:123456789")
                    startActivity(callIntent)

                    return@setOnMenuItemClickListener true
                }
                R.id.action_share -> {
                    Toast.makeText(this, "Share the items.", Toast.LENGTH_SHORT).show()

                    val textIntent = Intent(Intent.ACTION_SEND)
                    textIntent.type = "text/plain"
                    textIntent.putExtra(Intent.EXTRA_TEXT, "What do you think of $name?\n\nDescription:\n$desc")
                    startActivity(textIntent)

                    return@setOnMenuItemClickListener true
                }
                else -> {
                    return@setOnMenuItemClickListener false
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val SYR = LatLng(43.039, -76.137)
        mMap.addMarker(MarkerOptions().position(SYR))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SYR))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12f))

    }

}