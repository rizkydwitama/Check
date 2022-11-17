package com.example.check

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val authState = FirebaseUserLiveData()
}