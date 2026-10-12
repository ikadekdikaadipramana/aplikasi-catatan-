package com.example.coba

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CatatanViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val repository = CatatanRepository(database.catatanDao())

    val daftarCatatan: StateFlow<List<Catatan>> =
        repository.ambilSemua().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun tambahCatatan(judul: String, isi: String, kategori: String) {
        viewModelScope.launch {
            repository.tambah(
                Catatan(
                    judul = judul,
                    isi = isi,
                    kategori = kategori
                )
            )
        }
    }

    fun hapusCatatan(catatan: Catatan) {
        viewModelScope.launch {
            repository.hapus(catatan)
        }
    }

    fun editCatatan(catatan: Catatan) {
        viewModelScope.launch {
            repository.update(catatan)
        }
    }

    fun cariCatatan(kunci: String): Flow<List<Catatan>> {
        return repository.cari(kunci)
    }
}
