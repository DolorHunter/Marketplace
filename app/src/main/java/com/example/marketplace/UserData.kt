package com.example.marketplace

import com.google.gson.Gson

class UserList{
    val userList: List<UserData> = Gson().fromJson(users, Array<UserData>::class.java).asList()
    val avatarMap: MutableMap<Int, Int> = mutableMapOf()
    val userMap: MutableMap<String, Int> = mutableMapOf()

    init {
        avatarMap[0] = R.drawable.kyle
        avatarMap[1] = R.drawable.paul
        avatarMap[2] = R.drawable.sarah
        avatarMap[3] = R.drawable.lexie
        avatarMap[4] = R.drawable.alexander
        avatarMap[5] = R.drawable.maximillion

        userMap["F9fOq6JttZN9qlUuZikjecmntHL2"] = 0
        userMap["8C4yBlVgdFNkLz4vnirB5cGhq1g2"] = 1
        userMap["VfuhEbmt8ncJWbBmMibzyIcGqBh1"] = 2
        userMap["wJqDWByavdRnAUmU6DbGOw5cQ6l2"] = 3
        userMap["9OuLe8C2pAdkPeV5WvrC5m6h3YJ2"] = 4
        userMap["Xlsuew3d3phW08KV5kgd0Dm9ACx1"] = 5
    }
}

data class UserData(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val zip: String,
    val phone: String,
    val rating: Float,
    val status: String
)

val users = """
    [
        {
            "id": 0,
            "name": "Kyle",
            "email": "kyle@syr.edu",
            "password": "123456",
            "zip": "13210",
            "phone": "123456789",
            "rating": 3.5,
            "status": "1"
            
        },
        {
            "id": 1,
            "name": "Paul",
            "email": "paul@syr.edu",
            "password": "123456",
            "zip": "13210",
            "phone": "123456789",
            "rating": 3.7,
            "status": "1"
        },
        {
            "id": 2,
            "name": "Sarah LaVancher",
            "email": "sarah@syr.edu",
            "password": "123456",
            "zip": "13210",
            "phone": "123456789",
            "rating": 3.2,
            "status": "1"
        },
        {
            "id": 3,
            "name": "Lexie Meager",
            "email": "lexie@syr.edu",
            "password": "123456",
            "zip": "13210",
            "phone": "123456789",
            "rating": 4.3,
            "status": "1"
        },
        {
            "id": 4,
            "name": "Alexander Sustache",
            "email": "alex@syr.edu",
            "password": "123456",
            "zip": "13210",
            "phone": "123456789",
            "rating": 1.7,
            "status": "1"
        },
        {
            "id": 5,
            "name": "Maximillion Blumetti",
            "email": "max@syr.edu",
            "password": "123456",
            "zip": "13210",
            "phone": "123456789",
            "rating": 2.7,
            "status": "1"
        }
    ]
""".trimIndent()
