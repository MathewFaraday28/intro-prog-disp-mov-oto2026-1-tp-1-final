package com.tpbiblioteca.registrolibros.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.tpbiblioteca.registrolibros.model.Libro
import com.tpbiblioteca.registrolibros.ui.theme.RegistroLibrosTheme
import org.junit.Rule
import org.junit.Test

/**
 * Tests instrumentados de Compose para ListaLibros.
 * Corren en un emulador o dispositivo (src/androidTest).
 */
class ListaLibrosTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val librosDePrueba = listOf(
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

    @Test
    fun muestraTodosLosLibrosDeLaLista() {
        composeTestRule.setContent {
            RegistroLibrosTheme {
                ListaLibros(libros = librosDePrueba, onDelete = {})
            }
        }

        composeTestRule.onNodeWithText("El Principito").assertExists()
        composeTestRule.onNodeWithText("Cien años de soledad").assertExists()
        composeTestRule.onNodeWithText("Don Quijote").assertExists()
    }

    @Test
    fun laListaVaciaNoMuestraNingunItem() {
        composeTestRule.setContent {
            RegistroLibrosTheme {
                ListaLibros(libros = emptyList(), onDelete = {})
            }
        }

        composeTestRule
            .onAllNodesWithContentDescription("Eliminar libro")
            .assertCountEquals(0)
    }

    @Test
    fun alEliminarUnLibro_seInvocaElCallbackConEseLibroUnicamente() {
        var libroEliminado: Libro? = null

        composeTestRule.setContent {
            RegistroLibrosTheme {
                ListaLibros(
                    libros = librosDePrueba,
                    onDelete = { libroEliminado = it }
                )
            }
        }

        // Tocamos el botón "Eliminar libro" del segundo ítem (Cien años de soledad)
        composeTestRule
            .onAllNodesWithContentDescription("Eliminar libro")[1]
            .performClick()

        assert(libroEliminado == librosDePrueba[1]) {
            "Se esperaba que se eliminara '${librosDePrueba[1].titulo}', pero se recibió $libroEliminado"
        }
    }
}
