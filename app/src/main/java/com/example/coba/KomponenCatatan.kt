
package com.example.coba

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun KomponenCatatan(
    catatan: Catatan,
    onEdit: () -> Unit,
    onHapus: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF383434)
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = catatan.kategori.uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = Color(0xFFE0C6C8)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = catatan.judul,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = catatan.isi,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onEdit,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2196F3)
                    )
                ) {
                    Text(
                        text = "Edit",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onHapus,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text(
                        text = "Hapus",
                        color = Color.White
                    )
                }
            }
        }
    }
}
