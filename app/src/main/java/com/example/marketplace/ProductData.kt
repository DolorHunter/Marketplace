package com.example.marketplace

import com.google.gson.Gson


class ProductList {
    val productList: MutableList<ProductData> = Gson().fromJson(products, Array<ProductData>::class.java).toMutableList()
    val posterMap: MutableMap<String, Int> = mutableMapOf()

    init {
        posterMap["Vizio 36” TV"] = R.drawable.tv
        posterMap["Mountain bike"] = R.drawable.bike
        posterMap["Teal green velvet like couch"] = R.drawable.couch
        posterMap["2017 Toyota RAV4-DEALER EN PAGOS—"] = R.drawable.toyota
        posterMap["Large Wheeled Container - Husky 45 Gallon Wheeled Tote"] = R.drawable.container
        posterMap["Dresser"] = R.drawable.dresser
        posterMap["demo"] = R.drawable.demo_product
    }
}

fun findIndex(product: ProductData): Int{
    val id = product.id
    ProductList().productList.forEachIndexed  { index, element ->
        if (element.id == id)
            return index
    }
    return -1
}

data class ProductData(
    var id: Int,
    var name: String,
    var price: Float,
    var condition: String,
    var description: String,
    var listedDate: String,
    var zip: String,
    var sellerId: Int,
    var sellerName: String,
    var status: String
)
var products = """
[
    {
        "id": 3000,
        "name": "Vizio 36” TV",
        "price": 25,
        "condition": "Used - Good",
        "description": "Good condition, we need the space. It was kept in the playroom.\n\nPickup in Concord off Ayers. No holds.",
        "listedDate": "2022-11-05 12:34:56",
        "zip": 13210,
        "sellerId": 0,
        "sellerName": "Kyle",
        "status": "1"
    },
    {
        "id": 3001,
        "name": "Mountain bike",
        "price": 0,
        "condition": "Used - Fair",
        "description": "Been sitting outside. May need new tires. Free. Pickup in Suisun",
        "listedDate": "2022-11-05 12:34:56",
        "zip": 13210,
        "sellerId": 1,
        "sellerName": "Paul",
        "status": "1"
    },
    {
        "id": 3002,
        "name": "Teal green velvet like couch",
        "price": 40,
        "condition": "Used - Like New",
        "description": "The is sofa is velvet like more teal than green 28hx 27dx 68. This is new but definitely a cheap sofa- price reflects this. The framing is metal. Sofa is firm. Would be great for an office or girls room. Cushions zippered and can be removed for cleaning.",
        "listedDate": "2022-11-05 12:34:56",
        "zip": 13210,
        "sellerId": 2,
        "sellerName": "Sarah LaVancher",
        "status": "1"
    },
    {
        "id": 3003,
        "name": "2017 Toyota RAV4-DEALER EN PAGOS—",
        "price": 3000,
        "condition": "Used - Like New",
        "description": "🚗F1NANCIAMIENT0  💥\n🚙3000$ D0WN PAYM3NT, TITULO LIMPIO 🤩\n\n✅SIN CREDIT0🎉\n✅MAL CREDITO🎉\n\nTENEMOS VARIOS CARROS PARA TI:\n🚗SEDAN\n🚗SUV\n🚗 TRUCK\n🚗MINI VAN\n\n✔️💯SOLO CON TU PASAPORTE O LICENCIA, COMPROBANTE DE INGRES0S Y DE DOMICILIO. 👍\n\n📞📞LIAME AL *510* *633* *7724*📲📱",
        "listedDate": "2022-11-05 12:34:56",
        "zip": 13210,
        "sellerId": 3,
        "sellerName": "Lexie Meager",
        "status": "1"
    },
    {
        "id": 3004,
        "name": "Large Wheeled Container - Husky 45 Gallon Wheeled Tote",
        "price": 10,
        "condition": "Used - Good",
        "description": "Great if you need a large box for storage or to ship. We purchased it for $40 to ship a 120 lb carpet to our home. Has wheels and is fairly mobile.",
        "listedDate": "2022-11-05 12:34:56",
        "zip": 13210,
        "sellerId": 4,
        "sellerName": "Alexander Sustache",
        "status": "1"
    },
    {
        "id": 3005,
        "name": "Dresser",
        "price": 100,
        "condition": "Used - Good",
        "description": "$100. Size 49”x18”x35”. White/Brown dresser. Delivery available for additional fee. Check us out on IG @ramirezgoodsforsale ",
        "listedDate": "2022-11-05 12:34:56",
        "zip": 13210,
        "sellerId": 5,
        "sellerName": "Maximillion Blumetti",
        "status": "1"
    }
]
""".trimIndent()

