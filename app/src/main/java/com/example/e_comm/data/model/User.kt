package com.example.e_comm.data.model

data class User(
       val firstName: String,
       val lastName: String,
       val email: String,
      // val password: String,
       val imagePath:String = ""
){
    constructor(): this("","","","")
}
