package com.example.coba

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LayarBeranda(viewModel: CatatanViewModel) {
    val daftar by viewModel.daftarCatatan.collectAsStateWithLifecycle()
    var catatanEdit by remember { mutableStateOf<Catatan?>(null) }

    if (catatanEdit != null) {
        FormEditCatatan(
            catatan = catatanEdit!!,
            onSimpan = { judul, isi, kategori ->
                viewModel.editCatatan(
                    catatanEdit!!.copy(
                        judul = judul,
                        isi = isi,
                        kategori = kategori
                    )
                )
                catatanEdit = null
            },
            onBatal = { catatanEdit = null }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Daftar Catatan", style = MaterialTheme.typography.titleLarge)

            LazyColumn(contentPadding = PaddingValues(top = 8.dp)) {
                items(daftar) { catatan ->
                    KomponenCatatan(
                        catatan = catatan,
                        onEdit = { catatanEdit = catatan },
                        onHapus = { viewModel.hapusCatatan(catatan) }
                    )
                }
            }
        }
    }
}

@Composable
fun LayarTambah(viewModel: CatatanViewModel) {
    var judul by remember { mutableStateOf("") }
    var isi by remember { mutableStateOf("") }
    var kategori by remember { mutableStateOf("Kuliah") }
    val pilihanKategori = listOf("Kuliah", "Pribadi", "Pekerjaan")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Tambah Catatan")

        OutlinedTextField(
            value = judul,
            onValueChange = { judul = it },
            label = { Text("Judul") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = isi,
            onValueChange = { isi = it },
            label = { Text("Isi catatan") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Pilih kategori")

        pilihanKategori.forEach { pilihan ->
            Row(modifier = Modifier.fillMaxWidth()) {
                RadioButton(
                    selected = kategori == pilihan,
                    onClick = { kategori = pilihan }
                )
                Text(
                    text = pilihan,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        Button(
            onClick = {
                if (judul.isNotBlank() && isi.isNotBlank()) {
                    viewModel.tambahCatatan(
                        judul.trim(),
                        isi.trim(),
                        kategori
                    )
                    judul = ""
                    isi = ""
                }
            },
            enabled = judul.isNotBlank() && isi.isNotBlank()
        ) {
            Text("Simpan Catatan")
        }
    }
}

@Composable
fun LayarCari(viewModel: CatatanViewModel) {
    var kunci by remember { mutableStateOf("") }
    var catatanEdit by remember { mutableStateOf<Catatan?>(null) }

    val daftar by viewModel
        .cariCatatan(kunci)
        .collectAsStateWithLifecycle(initialValue = emptyList())

    if (catatanEdit != null) {
        FormEditCatatan(
            catatan = catatanEdit!!,
            onSimpan = { judul, isi, kategori ->
                viewModel.editCatatan(
                    catatanEdit!!.copy(
                        judul = judul,
                        isi = isi,
                        kategori = kategori
                    )
                )
                catatanEdit = null
            },
            onBatal = { catatanEdit = null }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            OutlinedTextField(
                value = kunci,
                onValueChange = { kunci = it },
                placeholder = { Text("Cari berdasarkan judul") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (daftar.isEmpty()) {
                Text(
                    text = "Catatan tidak ditemukan",
                    color = MaterialTheme.colorScheme.onBackground
                )
            } else {
                LazyColumn {
                    items(daftar) { catatan ->
                        KomponenCatatan(
                            catatan = catatan,
                            onEdit = { catatanEdit = catatan },
                            onHapus = { viewModel.hapusCatatan(catatan) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FormEditCatatan(
    catatan: Catatan,
    onSimpan: (String, String, String) -> Unit,
    onBatal: () -> Unit
) {
    var judul by remember(catatan.id) {
        mutableStateOf(catatan.judul)
    }
    var isi by remember(catatan.id) {
        mutableStateOf(catatan.isi)
    }
    var kategori by remember(catatan.id) {
        mutableStateOf(catatan.kategori)
    }

    val pilihanKategori = listOf("Kuliah", "Pribadi", "Pekerjaan")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Edit Catatan",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = judul,
            onValueChange = { judul = it },
            label = { Text("Judul") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = isi,
            onValueChange = { isi = it },
            label = { Text("Isi catatan") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Pilih kategori")

        pilihanKategori.forEach { pilihan ->
            Row(modifier = Modifier.fillMaxWidth()) {
                RadioButton(
                    selected = kategori == pilihan,
                    onClick = { kategori = pilihan }
                )
                Text(
                    text = pilihan,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    if (judul.isNotBlank() && isi.isNotBlank()) {
                        onSimpan(judul.trim(), isi.trim(), kategori)
                    }
                },
                enabled = judul.isNotBlank() && isi.isNotBlank()
            ) {
                Text("Simpan Perubahan")
            }

            OutlinedButton(onClick = onBatal) {
                Text("Batal")
            }
        }
    }
}
