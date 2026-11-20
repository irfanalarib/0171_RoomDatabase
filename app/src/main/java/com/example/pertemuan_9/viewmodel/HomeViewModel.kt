package com.example.pertemuan_9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan_9.repositori.RepositoriSiswa
import com.example.pertemuan_9.room.Siswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val repositoriSiswa: RepositoriSiswa) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoriSiswa.getAllSiswaStream()
        .filterNotNull()
        .map { HomeUiState(listSiswa = it.toList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    fun deleteSiswa(siswa: Siswa) {
        viewModelScope.launch {
            repositoriSiswa.deleteSiswa(siswa)
        }
    }

    data class HomeUiState(
        val listSiswa: List<Siswa> = listOf()
    )
}
