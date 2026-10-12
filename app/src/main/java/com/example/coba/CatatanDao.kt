
package com.example.coba

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CatatanDao {

    @Insert
    suspend fun sisipkan(catatan: Catatan)

    @Delete
    suspend fun hapus(catatan: Catatan)

    @Update
    suspend fun update(catatan: Catatan)

    @Query("SELECT * FROM catatan ORDER BY dibuatPada DESC")
    fun ambilSemua(): Flow<List<Catatan>>

    @Query("SELECT * FROM catatan WHERE judul LIKE '%' || :kunci || '%' ORDER BY dibuatPada DESC")
    fun cari(kunci: String): Flow<List<Catatan>>
}
