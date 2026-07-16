package com.tpbiblioteca.registrolibros.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpbiblioteca.registrolibros.model.Libro
import com.tpbiblioteca.registrolibros.ui.theme.RegistroLibrosTheme

@Composable
fun ItemLibro(
	libro: Libro,
	onDelete: (Libro) -> Unit,
	modifier: Modifier = Modifier
) {
	Card(
		modifier = modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 8.dp),
		elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
		colors = CardDefaults.cardColors(
			containerColor = MaterialTheme.colorScheme.surface
		)
	) {
		Column(
			modifier = Modifier.padding(16.dp).fillMaxWidth()
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = libro.titulo,
					style = MaterialTheme.typography.titleLarge,
					fontWeight = FontWeight.Bold,
					color = MaterialTheme.colorScheme.primary,
					modifier = Modifier.weight(1f)
				)
				
				// Botón de eliminación
				IconButton(onClick = { onDelete(libro) }) {
					Icon(
						imageVector = Icons.Default.Delete,
						contentDescription = "Eliminar libro",
						tint = MaterialTheme.colorScheme.error
					)
				}
			}
			
			Spacer(modifier = Modifier.height(8.dp))
			
			LazyRow(
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				contentPadding = PaddingValues(end = 8.dp)
			) {
				item {
					DatoLibro(label = "Autor", value = libro.autor)
				}
				item {
					DatoLibro(label = "Año", value = libro.anioPublicacion)
				}
				item {
					DatoLibro(label = "Género", value = libro.genero)
				}
				item {
					DatoLibro(label = "ISBN", value = libro.isbn)
				}
			}
		}
	}
}

@Composable
fun DatoLibro(label: String, value: String) {
	Surface(
		color = MaterialTheme.colorScheme.secondaryContainer,
		shape = MaterialTheme.shapes.medium
	) {
		Row(
			modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = "$label: ",
				style = MaterialTheme.typography.labelMedium,
				fontWeight = FontWeight.Bold,
				color = MaterialTheme.colorScheme.onSecondaryContainer
			)
			Text(
				text = value,
				style = MaterialTheme.typography.bodySmall,
				color = MaterialTheme.colorScheme.onSecondaryContainer
			)
		}
	}
}


@Preview(showBackground = true)
@Composable
fun ItemLibroPreview() {
	RegistroLibrosTheme {
		ItemLibro(
			libro = Libro(
				id = "1",
				titulo = "El Principito",
				autor = "Antoine de Saint-Exupéry",
				anioPublicacion = "1943",
				genero = "Fábula",
				isbn = "978-0156012195"
			),
			onDelete = {}
		)
	}
}