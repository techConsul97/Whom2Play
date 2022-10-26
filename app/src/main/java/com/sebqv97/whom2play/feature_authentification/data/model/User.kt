package com.sebqv97.whom2play.feature_authentification.data.model

data class User(
    val email: String,
    val password: String,
    val userName:String="",
    val contactNumber:String="",
    val preferredSports: Sports = Sports.Jogging,
    val address:String?=""

){
   enum class Sports{
        Jogging, Football, Tennis, PingPong, Chess, Backgammon
    }
}


