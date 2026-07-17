package com.tpbiblioteca.registrolibros.ui

import androidx.compose.ui.test.assertDoesNotExist
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.tpbiblioteca.registrolibros.ui.theme.RegistroLibrosTheme
import org.junit.Rule
import org.junit.Test

/**
 * Tests instrumentados de Compose para FormularioLibro.
 * Corren en un emulador o dispositivo (src/androidTest).
 */
class FormularioLibroTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun elBotonAgregarEstaDeshabilitadoConCamposVacios() {
        composeTestRule.setContent {
            RegistroLibrosTheme {
                FormularioLibro(onAgregar = { _, _, _, _, _ -> })
            }
        }

        composeTestRule.onNodeWithText("Agregar").assertIsNotEnabled()
    }

    @Test
    fun elBotonAgregarEstaDeshabilitadoSiFaltaElAutor() {
        composeTestRule.setContent {
            RegistroLibrosTheme {
                FormularioLibro(onAgregar = { _, _, _, _, _ -> })
            }
        }

        composeTestRule.onNodeWithText("Título").performTextInput("El Principito")
        // Autor queda vacío a propósito

        composeTestRule.onNodeWithText("Agregar").assertIsNotEnabled()
    }

    @Test
    fun elBotonAgregarEstaDeshabilitadoSiElAnioNoEsNumerico() {
        composeTestRule.setContent {
            RegistroLibrosTheme {
                FormularioLibro(onAgregar = { _, _, _, _, _ -> })
            }
        }

        composeTestRule.onNodeWithText("Título").performTextInput("El Principito")
        composeTestRule.onNodeWithText("Autor").performTextInput("Antoine de Saint-Exupéry")
        composeTestRule.onNodeWithText("Año de publicación").performTextInput("mil novecientos")

        composeTestRule.onNodeWithText("Agregar").assertIsNotEnabled()
        composeTestRule.onNodeWithText("Ingresá solo números, ej: 1998").assertExists()
    }

    @Test
    fun elBotonAgregarSeHabilitaConTituloYAutorCompletos() {
        composeTestRule.setContent {
            RegistroLibrosTheme {
                FormularioLibro(onAgregar = { _, _, _, _, _ -> })
            }
        }

        composeTestRule.onNodeWithText("Título").performTextInput("El Principito")
        composeTestRule.onNodeWithText("Autor").performTextInput("Antoine de Saint-Exupéry")
        // Año, género e ISBN son opcionales

        composeTestRule.onNodeWithText("Agregar").assertIsEnabled()
    }

    @Test
    fun alAgregar_seInvocaElCallbackConLosDatosCorrectos() {
        var tituloRecibido = ""
        var autorRecibido = ""
        var anioRecibido = ""
        var generoRecibido = ""
        var isbnRecibido = ""

        composeTestRule.setContent {
            RegistroLibrosTheme {
                FormularioLibro(
                    onAgregar = { titulo, autor, anio, genero, isbn ->
                        tituloRecibido = titulo
                        autorRecibido = autor
                        anioRecibido = anio
                        generoRecibido = genero
                        isbnRecibido = isbn
                    }
                )
            }
        }

        composeTestRule.onNodeWithText("Título").performTextInput("El Principito")
        composeTestRule.onNodeWithText("Autor").performTextInput("Antoine de Saint-Exupéry")
        composeTestRule.onNodeWithText("Año de publicación").performTextInput("1943")
        composeTestRule.onNodeWithText("Género").performTextInput("Fábula")
        composeTestRule.onNodeWithText("ISBN").performTextInput("978-0156012195")

        composeTestRule.onNodeWithText("Agregar").performClick()

        assert(tituloRecibido == "El Principito")
        assert(autorRecibido == "Antoine de Saint-Exupéry")
        assert(anioRecibido == "1943")
        assert(generoRecibido == "Fábula")
        assert(isbnRecibido == "978-0156012195")
    }

    @Test
    fun elFormularioSeLimpiaDespuesDeAgregar() {
        composeTestRule.setContent {
            RegistroLibrosTheme {
                FormularioLibro(onAgregar = { _, _, _, _, _ -> })
            }
        }

        composeTestRule.onNodeWithText("Título").performTextInput("El Principito")
        composeTestRule.onNodeWithText("Autor").performTextInput("Antoine de Saint-Exupéry")
        composeTestRule.onNodeWithText("Año de publicación").performTextInput("1943")
        composeTestRule.onNodeWithText("Género").performTextInput("Fábula")
        composeTestRule.onNodeWithText("ISBN").performTextInput("978-0156012195")

        composeTestRule.onNodeWithText("Agregar").performClick()

        // Si los campos se limpiaron, el texto cargado ya no debería existir
        // y las etiquetas (labels) vuelven a mostrarse solas, vacías.
        composeTestRule.onNodeWithText("El Principito").assertDoesNotExist()
        composeTestRule.onNodeWithText("Antoine de Saint-Exupéry").assertDoesNotExist()
        composeTestRule.onNodeWithText("1943").assertDoesNotExist()
        composeTestRule.onNodeWithText("Fábula").assertDoesNotExist()
        composeTestRule.onNodeWithText("978-0156012195").assertDoesNotExist()

        // Y el botón vuelve a estar deshabilitado, como al inicio
        composeTestRule.onNodeWithText("Agregar").assertIsNotEnabled()
    }
}
