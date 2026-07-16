package com.tpbiblioteca.registrolibros.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpbiblioteca.registrolibros.model.Libro
import com.tpbiblioteca.registrolibros.ui.theme.RegistroLibrosTheme

@Composable
fun ListaLibros(
    libros: List<Libro>, //Lista de libros que se van a mostrar
    onDelete: (Libro) -> Unit, //Callback para eliminar un libro
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        //Recorrer la lista de libros y dibuja cada uno con ItemLibro
        items(libros) { libro ->
            ItemLibro(
                libro = libro, //Datos del libro actual
                onDelete = onDelete //Acción para borrar
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ListaLibrosPreview() {
    RegistroLibrosTheme {

        val librosEjemplo = listOf(
            Libro(
                id = "1",
                titulo = "El Principito",
                autor = "Antoine de Saint-Exupéry",
                anioPublicacion = "1943",
                genero = "Fábula",
                isbn = "978-0156012195"
            ),
            Libro(
                id = "2",
                titulo = "Cien años de soledad",
                autor = "Gabriel García Márquez",
                anioPublicacion = "1967",
                genero = "Realismo mágico",
                isbn = "978-0307474728"
            ),
            Libro(
                id = "3",
                titulo = "Don Quijote",
                autor = "Miguel de Cervantes",
                anioPublicacion = "1605",
                genero = "Novela",
                isbn = "978-8420412146"
            )
        )

        Box(
            modifier = Modifier.height(600.dp)
        ) {
            ListaLibros(
                libros = librosEjemplo,
                onDelete = {}
            )
        }
    }
}