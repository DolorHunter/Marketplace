package com.example.marketplace

import android.graphics.Color
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth

class MarketAdapter(private var itemList: List<ProductData>, private val posterList: MutableMap<String, Int>, private val myId: Int) :
    RecyclerView.Adapter<MarketAdapter.ViewHolder>() {

    var myListener: MyItemClickListener? = null
    interface MyItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setMyItemClickListener (listener: MarketActivity){
        this.myListener = listener
    }

    fun setFilteredList(filteredList: List<ProductData>){
        this.itemList = filteredList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemCardView: CardView = itemView.findViewById(R.id.card_view)
        val itemImage: ImageView = itemView.findViewById(R.id.market_item_image)
        val itemName: TextView = itemView.findViewById(R.id.market_item_name)
        val itemDescription: TextView = itemView.findViewById(R.id.market_item_description)
        val itemPrice: TextView = itemView.findViewById(R.id.market_item_price)
        val itemCondition: TextView = itemView.findViewById(R.id.market_item_condition)
        val itemListedDate: TextView = itemView.findViewById(R.id.market_item_listedDate)

        init {
            itemCardView.setOnClickListener {
                if (myListener != null) {
                    myListener!!.onItemClick(it, adapterPosition)
                }
            }
        }
    }



    override fun getItemViewType(position: Int): Int {
        return if (itemList[position].sellerId == myId){
            1
        } else {
            0
        }
    }

    // Two types of layout for market items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = when(viewType){
            1 -> {
                layoutInflater.inflate(R.layout.activity_market_item2, parent, false)
            }
            else -> {
                layoutInflater.inflate(R.layout.activity_market_item, parent, false)
            }
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curItem = itemList[position]

        if (curItem.name.length < 17) {
            holder.itemName.text = curItem.name
        }
        else{
            holder.itemName.text = curItem.name.substring(0, 17) + "..."
        }

        if (curItem.description.length  < 130){
            holder.itemDescription.text = curItem.description
        }
        else{
            holder.itemDescription.text = curItem.description.substring(0, 130) + "..."
        }
        holder.itemPrice.text = "$" + String.format("%.2f", curItem.price)

        //itemImage.setImageResource(R.mipmap.ic_launcher_foreground)
        ProductList().posterMap[curItem.name]?.let { holder.itemImage.setImageResource(it) }

        holder.itemCondition.text = curItem.condition
        when (curItem.condition){
            "Used - Like New" -> {
                holder.itemCondition.setTextColor(Color.parseColor("#04930d"))
            }
            "Used - Good" -> {
                holder.itemCondition.setTextColor(Color.parseColor("#2240fc"))
            }
            "Used - Fair" -> {
                holder.itemCondition.setTextColor(Color.parseColor("#a644e5"))
            }
            else -> {
                holder.itemCondition.setTextColor(Color.parseColor("#000000"))
            }
        }

        holder.itemListedDate.text = curItem.listedDate.split(" ")[0]
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}