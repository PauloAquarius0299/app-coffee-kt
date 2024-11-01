package com.example.bolosapp.ViewModel

import androidx.lifecycle.LiveData
import com.example.bolosapp.Model.Items.Model

class MainViewModel {
    private val repository=MainViewModel()

    fun loadFiltered(id:Int):LiveData<MutableList<Model>>{
        return repository.loadFiltered(id)
    }
}