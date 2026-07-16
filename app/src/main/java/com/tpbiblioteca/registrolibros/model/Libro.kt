package com.tpbiblioteca.registrolibros.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tpbiblioteca.registrolibros.ui.ListaLibros
import com.tpbiblioteca.registrolibros.ui.theme.RegistroLibrosTheme

data class Libro(
    val id: String,
    val titulo: String,
    val autor: String,
    val anioPublicacion: String,
    val genero: String,
    val isbn: String
)
