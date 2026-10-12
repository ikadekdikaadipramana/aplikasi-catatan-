
package com.example.coba

import kotlinx.coroutines.flow.Flow

class CatatanRepository(
    private val catatanDao: CatatanDao
) {

    fun ambilSemua(): Flow<List<Catatan>> {
        return catatanDao.ambilSemua()
    }

    fun cari(kunci: String): Flow<List<Catatan>> {
        return catatanDao.cari(kunci)
    }

    suspend fun tambah(catatan: Catatan) {
        catatanDao.sisipkan(catatan)
    }

    suspend fun hapus(catatan: Catatan) {
        catatanDao.hapus(catatan)
    }

    suspend fun update(catatan: Catatan) {
        catatanDao.update(catatan)
    }
}
