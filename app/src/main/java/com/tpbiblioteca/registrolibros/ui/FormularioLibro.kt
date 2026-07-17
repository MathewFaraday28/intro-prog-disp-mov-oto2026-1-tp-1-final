package com.tpbiblioteca.registrolibros.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpbiblioteca.registrolibros.ui.theme.RegistroLibrosTheme

/**
 * Formulario para cargar un nuevo libro.
 *
 * @param onAgregar callback que recibe los 5 campos (titulo, autor, anio, genero, isbn)
 * cuando el usuario confirma la carga. La creación del Libro (con su id) queda
 * a cargo de quien llama, tal como espera MainActivity.agregarLibro().
 */
@Composable
fun FormularioLibro(
    onAgregar: (titulo: String, autor: String, anio: String, genero: String, isbn: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }

    // Validaciones básicas: título y autor obligatorios, año numérico si se cargó
    val tituloValido = titulo.isNotBlank()
    val autorValido = autor.isNotBlank()
    val anioValido = anio.isBlank() || anio.toIntOrNull() != null
    val formularioValido = tituloValido && autorValido && anioValido

    fun limpiarFormulario() {
        titulo = ""
        autor = ""
        anio = ""
        genero = ""
        isbn = ""
    }

    Card(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Agregar libro",
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = autor,
                onValueChange = { autor = it },
                label = { Text("Autor") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = anio,
                onValueChange = { anio = it },
                label = { Text("Año de publicación") },
                isError = !anioValido,
                supportingText = {
                    if (!anioValido) Text("Ingresá solo números, ej: 1998")
                },
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = genero,
                onValueChange = { genero = it },
                label = { Text("Género") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = isbn,
                onValueChange = { isbn = it },
                label = { Text("ISBN") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Button(
                onClick = {
                    onAgregar(titulo.trim(), autor.trim(), anio.trim(), genero.trim(), isbn.trim())
                    limpiarFormulario()
                },
                enabled = formularioValido,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioLibroPreview() {
    RegistroLibrosTheme {
        FormularioLibro(onAgregar = { _, _, _, _, _ -> })
    }
}
