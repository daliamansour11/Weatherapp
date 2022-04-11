package com.example.weatherapp.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Repository.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingViewModel constructor(var repository: RepositoryInterface): ViewModel() {
    fun refreshData(){

        viewModelScope.launch (Dispatchers.IO ){
            repository.refreshDataBase()

        }
    }
}