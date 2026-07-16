package com.tpbiblioteca.registrolibros.model

data class Libro(
    val id: String,
    val titulo: String,
    val autor: String,
    val anioPublicacion: String,
    val genero: String,
    val isbn: String
)
