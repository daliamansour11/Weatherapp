package com.example.weatherapp.home.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Repository.RepositoryInterface
import io.grpc.Context
import java.lang.IllegalArgumentException

class HomeModelFactory(private val _irepostory: RepositoryInterface) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeModelView::class.java)) {
            HomeModelView(_irepostory) as T
        } else {
            throw IllegalArgumentException("ViewModel Class not found")
        }
    }
}