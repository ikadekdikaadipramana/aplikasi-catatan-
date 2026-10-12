package com.example.coba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coba.ui.theme.CobaTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CobaTheme(
                darkTheme = true,
                dynamicColor = false
            ) {
                val catatanViewModel: CatatanViewModel = viewModel()
                var halaman by remember { mutableStateOf("Beranda") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = halaman == "Beranda",
                                onClick = { halaman = "Beranda" },
                                icon = { Text("⌂") },
                                label = { Text("Beranda") }
                            )

                            NavigationBarItem(
                                selected = halaman == "Tambah",
                                onClick = { halaman = "Tambah" },
                                icon = { Text("+") },
                                label = { Text("Tambah") }
                            )

                            NavigationBarItem(
                                selected = halaman == "Cari",
                                onClick = { halaman = "Cari" },
                                icon = { Text("⌕") },
                                label = { Text("Cari") }
                            )

                            NavigationBarItem(
                                selected = halaman == "Profil",
                                onClick = { halaman = "Profil" },
                                icon = { Text("👤") },
                                label = { Text("Profil") }
                            )
                        }
                    }
                ) { padding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        when (halaman) {
                            "Beranda" -> LayarBeranda(catatanViewModel)
                            "Tambah" -> LayarTambah(catatanViewModel)
                            "Cari" -> LayarCari(catatanViewModel)
                            "Profil" -> LayarProfil()
                        }
                    }
                }
            }
        }
    }
}