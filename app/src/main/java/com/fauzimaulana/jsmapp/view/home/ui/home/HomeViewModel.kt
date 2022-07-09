package com.fauzimaulana.jsmapp.view.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fauzimaulana.jsmapp.core.domain.model.UserModel
import com.fauzimaulana.jsmapp.core.domain.usecase.JSMUseCase
import com.fauzimaulana.jsmapp.core.vo.Resource

class HomeViewModel(private val JSMUseCase: JSMUseCase) : ViewModel() {
    fun getAllUsers(): LiveData<Resource<List<UserModel>>> =
        JSMUseCase.getAllUser().asLiveData()
}