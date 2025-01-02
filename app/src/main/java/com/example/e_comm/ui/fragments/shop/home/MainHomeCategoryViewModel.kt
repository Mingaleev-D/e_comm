package com.example.e_comm.ui.fragments.shop.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_comm.data.model.Product
import com.example.e_comm.data.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainHomeCategoryViewModel @Inject constructor(
       private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _specialProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val specialProducts: StateFlow<Resource<List<Product>>> = _specialProducts

    init {
        fetchSpecialProducts()

    }

     fun fetchSpecialProducts() {
        viewModelScope.launch {
            _specialProducts.emit(Resource.Loading())
        }
        firestore
               .collection("categories")
              // .whereEqualTo("category", "Стул")
               .get()
               .addOnSuccessListener { result ->
                   val specialProducts = result.toObjects(Product::class.java)
                   viewModelScope.launch {
                       _specialProducts.emit(Resource.Success(specialProducts))
                   }
               }
               .addOnFailureListener {
                   viewModelScope.launch {
                       _specialProducts.emit(Resource.Error(it.message.toString()))
                   }
               }
    }
}
