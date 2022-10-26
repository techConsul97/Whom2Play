package com.sebqv97.whom2play.feature_authentification.data.model

data class User(
    val email: String,
    val password: String,
    val userName:String="",
    val contactNumber:String="",
    val preferredSports: Sports = Sports.Jogging,
    val address:String=""

){
   enum class Sports{
        Jogging, Football, Tennis, PingPong, Chess, Backgammon
    }

    fun getDetailsIntoHashMap():HashMap<String,String> = hashMapOf(
        "email" to email,
        "password" to password,
        "username" to userName,
        "phone" to contactNumber,
        "sports" to listOf(preferredSports).toString(),
        "address" to address
    )
}


