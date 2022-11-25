package com.example.marketplace

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.MapView


class MarketItemDetailActivity : AppCompatActivity() {
    private lateinit var sellerInfo: List<UserData>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_item_detail)

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


            itemName.text = curItem.name
            itemDescription.text = curItem.description
            itemPrice.text = "$" + String.format("%.2f", curItem.price)

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
            val itemMap: MapView = findViewById(R.id.mapView)


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
        }
    }
}