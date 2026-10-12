package com.example.coba

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catatan")
data class Catatan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val isi: String,
    val kategori: String,
    val dibuatPada: Long = System.currentTimeMillis()
)