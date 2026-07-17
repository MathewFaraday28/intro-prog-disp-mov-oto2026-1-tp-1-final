package com.tpbiblioteca.registrolibros.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.tpbiblioteca.registrolibros.model.Libro
import com.tpbiblioteca.registrolibros.ui.theme.RegistroLibrosTheme
import org.junit.Rule
import org.junit.Test

/**
 * Tests instrumentados de Compose para ItemLibro.
 * Corren en un emulador o dispositivo (src/androidTest).
 */
class ItemLibroTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val libroDePrueba = Libro(
        id = "1",
        titulo = "El Principito",
        autor = "Antoine de Saint-Exupéry",
        anioPublicacion = "1943",
        genero = "Fábula",
        isbn = "978-0156012195"
    )

    @Test
    fun muestraTodosLosDatosDelLibro() {
        composeTestRule.setContent {
            RegistroLibrosTheme {
                ItemLibro(libro = libroDePrueba, onDelete = {})
            }
        }

        composeTestRule.onNodeWithText("El Principito").assertExists()
        composeTestRule.onNodeWithText("Antoine de Saint-Exupéry").assertExists()
        composeTestRule.onNodeWithText("1943").assertExists()
        composeTestRule.onNodeWithText("Fábula").assertExists()
        composeTestRule.onNodeWithText("978-0156012195").assertExists()
    }

    @Test
    fun alTocarElBotonEliminar_seInvocaElCallbackConElLibroCorrecto() {
        var libroEliminado: Libro? = null

        composeTestRule.setContent {
            RegistroLibrosTheme {
                ItemLibro(
                    libro = libroDePrueba,
                    onDelete = { libroEliminado = it }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Eliminar libro")
            .performClick()

        assert(libroEliminado == libroDePrueba) {
            "Se esperaba que el callback recibiera $libroDePrueba, pero recibió $libroEliminado"
        }
    }
}
