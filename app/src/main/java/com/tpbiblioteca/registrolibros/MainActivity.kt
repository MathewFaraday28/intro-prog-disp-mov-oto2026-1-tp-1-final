package com.tpbiblioteca.registrolibros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.tpbiblioteca.registrolibros.model.Libro
import com.tpbiblioteca.registrolibros.ui.ListaLibros
import com.tpbiblioteca.registrolibros.ui.theme.RegistroLibrosTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroLibrosTheme {
                Scaffold { innerPadding ->
                    PantallaPrincipal(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaPrincipal(modifier: Modifier = Modifier) {
    // Estado central: lista de libros, sobrevive a recomposiciones
    val libros = remember { mutableStateListOf<Libro>() }

    fun agregarLibro(
        titulo: String,
        autor: String,
        anio: String,
        genero: String,
        isbn: String
    ) {
        libros.add(
            Libro(
                id = UUID.randomUUID().toString(),
                titulo = titulo,
                autor = autor,
                anioPublicacion = anio,
                genero = genero,
                isbn = isbn
            )
        )
    }

    fun eliminarLibro(id: String) {
        libros.removeAll { it.id == id }
    }

    Column(modifier = modifier) {
        /*
        FormularioLibro(
            onAgregar = ::agregarLibro
        )

        ListaLibros(
            libros = libros,
            onEliminar = ::eliminarLibro
        )*/

        // TODO: cuando esté FormularioLibro.kt, se llama así:
        // FormularioLibro(onAgregar = ::agregarLibro)

        // TODO: cuando esté ListaLibros.kt, se llama así:
        // ListaLibros(libros = libros, onEliminar = ::eliminarLibro)
    }
}
