package com.example.e_comm.ui.vm

import androidx.lifecycle.ViewModel
import com.example.e_comm.data.model.User
import com.example.e_comm.data.utils.Constants.USER_COLLECTION
import com.example.e_comm.data.utils.RegisterFieldsState
import com.example.e_comm.data.utils.RegisterValidation
import com.example.e_comm.data.utils.Resource
import com.example.e_comm.data.utils.validateEmail
import com.example.e_comm.data.utils.validatePassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
       private val firebaseAuth: FirebaseAuth,
       private val dbFirestore: FirebaseFirestore
) : ViewModel() {

    private val _register =
           MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val register: Flow<Resource<User>> = _register

    private val _validation = Channel<RegisterFieldsState>()
    val validation = _validation.receiveAsFlow()

    fun createAccountWithEmailAndPassword(user: User, password: String) {
        if (checkValidation(user, password)) {
            runBlocking {
                _register.emit(Resource.Loading())
            }
            firebaseAuth.createUserWithEmailAndPassword(
                   user.email,
                   password
            )
                   .addOnSuccessListener {
                       it.user?.let {
                           saveUserInfo(it.uid, user)

                       }
                   }
                   .addOnFailureListener {
                       _register.value = Resource.Error(it.message.toString())
                   }
        } else {
            val registerFieldState = RegisterFieldsState(
                   email = validateEmail(user.email),
                   password = validatePassword(password)
            )
            runBlocking {
                _validation.send(registerFieldState)
            }
        }


    }

    private fun saveUserInfo(userUid: String, user: User) {
        dbFirestore
               .collection(USER_COLLECTION)
               .document(userUid)
               .set(user)
               .addOnSuccessListener {
                   _register.value = Resource.Success(user)
               }
               .addOnFailureListener {
                   _register.value = Resource.Error(it.message.toString())
               }
    }

    private fun checkValidation(user: User, password: String): Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)
        val shouldRegister = emailValidation is RegisterValidation.Success &&
                             passwordValidation is RegisterValidation.Success

        return shouldRegister
    }
}