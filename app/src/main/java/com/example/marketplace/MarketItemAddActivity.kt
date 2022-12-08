package com.example.marketplace

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.text.InputType
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import android.Manifest.permission
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.example.marketplace.databinding.ActivityMarketItemAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MarketItemAddActivity : AppCompatActivity(){

    private lateinit var firebaseAuth: FirebaseAuth


    private lateinit var binding: ActivityMarketItemAddBinding
    lateinit var userData : UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketItemAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val userId = UserList().userMap[firebaseAuth.currentUser?.uid.toString()]

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        // Get support action bar
        val appBar = supportActionBar
        // set App Title
        appBar!!.title = "Marketplace"
        // set sub title
        appBar.subtitle = "Add item"
        // Display app icon in toolbar
        appBar.setDisplayShowHomeEnabled(true)
        appBar.setLogo(R.mipmap.ic_launcher)
        appBar.setDisplayUseLogoEnabled(true)

        if (ActivityCompat.checkSelfPermission(this, permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(permission.CAMERA), 111)
        }

        binding.addItemImage.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 101)
        }

        binding.addItemCondition.inputType = InputType.TYPE_NULL;
        binding.addItemCondition.setOnClickListener {
            val popup = PopupMenu(this, binding.addItemCondition)
            val menuInflater = popup.menuInflater
            menuInflater.inflate(R.menu.menu_product_condition, popup.menu)
            popup.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_used_like_new -> {
                        binding.addItemCondition.text = "Used - Like New"
                        return@setOnMenuItemClickListener true
                    }
                    R.id.action_used_good -> {
                        binding.addItemCondition.text = "Used - Good"
                        return@setOnMenuItemClickListener true
                    }
                    R.id.action_used_fair -> {
                        binding.addItemCondition.text = "Used - Fair"
                        return@setOnMenuItemClickListener true
                    }
                    else ->{
                        return@setOnMenuItemClickListener false
                    }
                }
            }
            popup.show()
        }

        binding.addItemSubmit.setOnClickListener {
            // add to db
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatted = current.format(formatter)


            var product = ProductList().productList[0].copy()
            product.name = binding.addItemTitle.text.toString()
            product.condition = binding.addItemCondition.text.toString()
            product.description = binding.addItemCondition.text.toString()
            product.price = binding.addItemPrice.text.toString().toFloat()
            product.listedDate = formatted

            product.sellerId = userId!!
            product.sellerName = UserList().userList[userId].name


            var productList: MutableList<ProductData> = Gson().fromJson(products, Array<ProductData>::class.java).toMutableList()
            productList.add(product)
            products = Gson().toJson(productList)

            val title = binding.addItemTitle.text.toString()
            Toast.makeText(this, "$title has submitted to Marketplace.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MarketActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val addItemImage = findViewById<ImageView>(R.id.add_item_image)
        if (requestCode == 101){
            var pic = data?.getParcelableExtra<Bitmap>("data")
            addItemImage.setImageBitmap(pic)
        }
    }

    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser == null){
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }


}