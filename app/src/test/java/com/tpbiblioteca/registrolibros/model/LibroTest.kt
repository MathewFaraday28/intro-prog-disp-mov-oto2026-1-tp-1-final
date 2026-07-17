package com.tpbiblioteca.registrolibros.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Tests unitarios para la data class Libro.
 * No requieren emulador ni dispositivo: corren directo en la JVM (src/test).
 */
class LibroTest {

    private fun libroDeEjemplo(id: String = "1") = Libro(
        id = id,
        titulo = "El Principito",
        autor = "Antoine de Saint-Exupéry",
        anioPublicacion = "1943",
        genero = "Fábula",
        isbn = "978-0156012195"
    )

    @Test
    fun `dos libros con los mismos datos son iguales`() {
        val libro1 = libroDeEjemplo()
        val libro2 = libroDeEjemplo()

        assertEquals(libro1, libro2)
    }

    @Test
    fun `dos libros con distinto id no son iguales`() {
        val libro1 = libroDeEjemplo(id = "1")
        val libro2 = libroDeEjemplo(id = "2")

        assertNotEquals(libro1, libro2)
    }

    @Test
    fun `copy permite modificar un campo sin alterar el resto`() {
        val original = libroDeEjemplo()
        val modificado = original.copy(titulo = "El Principito (edición especial)")

        assertEquals("El Principito (edición especial)", modificado.titulo)
        // El resto de los campos no debería cambiar
        assertEquals(original.autor, modificado.autor)
        assertEquals(original.anioPublicacion, modificado.anioPublicacion)
        assertEquals(original.genero, modificado.genero)
        assertEquals(original.isbn, modificado.isbn)
        assertEquals(original.id, modificado.id)
    }

    @Test
    fun `los campos de texto no deberian estar vacios en un libro valido`() {
        val libro = libroDeEjemplo()

        assertFalse(libro.titulo.isBlank())
        assertFalse(libro.autor.isBlank())
        assertFalse(libro.anioPublicacion.isBlank())
        assertFalse(libro.genero.isBlank())
        assertFalse(libro.isbn.isBlank())
    }
}
