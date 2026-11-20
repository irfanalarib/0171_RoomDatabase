package com.example.pertemuan_9.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pertemuan_9.repositori.RepositoriSiswa

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa) : ViewModel(){}