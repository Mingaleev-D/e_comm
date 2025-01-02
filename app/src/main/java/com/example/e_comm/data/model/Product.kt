package com.example.e_comm.data.model

data class Product(
       val id: String,
       val title: String,
       val category: String,
       val price: Float,
      // val offerPercentage: Float? = null,
       val description: String? = null,
      // val colors: List<Int>? = null,
      // val sizes: List<String>? = null,
       val image: String
) {
    //constructor():this("0","","",0f,images = listOf(""))

}
