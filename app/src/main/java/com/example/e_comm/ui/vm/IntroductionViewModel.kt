package com.example.e_comm.ui.vm

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_comm.R
import com.example.e_comm.data.utils.Constants.INTRODUCTION_KEY
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
       private val sharedPreferences: SharedPreferences,
       private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    private val _navigate = MutableStateFlow(0)
    val navigate: StateFlow<Int> = _navigate

    companion object {

        var ACCOUNT_OPTIONS_FRAGMENTS = R.id.action_introdcutionFragment_to_accountOptionsFragment
        const val SHOPPING_ACTIVITY = 23
    }

    init {
        val isBtnClicked = sharedPreferences.getBoolean(INTRODUCTION_KEY, false)
        val user = firebaseAuth.currentUser

        if (user != null) {
            viewModelScope.launch {
                _navigate.emit(SHOPPING_ACTIVITY)
            }
        } else if (isBtnClicked) {
            viewModelScope.launch {
                _navigate.emit(ACCOUNT_OPTIONS_FRAGMENTS)
            }
        } else {
            Unit
        }
    }

    fun startBtnClick() {
        sharedPreferences.edit().putBoolean(INTRODUCTION_KEY, true).apply()
    }
}
