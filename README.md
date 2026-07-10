# 📚 Registro de Libros

Trabajo Práctico 1 - Final — Introducción a la Programación para Dispositivos Móviles
Prof. Lic. Diego Bonnin

## 📋 Descripción

La aplicación permite registrar libros con título, autor, año de publicación, género e ISBN, visualizarlos en una lista, y eliminarlos individualmente. 
Incluye tema personalizado con soporte para modo claro y oscuro.

## ✨ Funcionalidades

- **Formulario de ingreso**: carga de nuevos libros (título, autor, año, género, ISBN)
- **Lista de libros**: visualización vertical mediante LazyColumn
- Cada ítem de la lista muestra:
  - Título del libro destacado
  - LazyRow con los datos restantes (autor, año, género, ISBN)
  - Botón de eliminación
- **Eliminación individual** de libros desde la lista
- **Tema claro/oscuro** generado con Material Design Theme Builder

## 🛠️ Tecnologías

| Tecnología | Uso |
|---|---|
| Kotlin | Lenguaje principal |
| Jetpack Compose | Framework de UI declarativa |
| Material Design 3 | Sistema de theming (claro/oscuro) |

## 📂 Estructura del proyecto

```
app/
└── src/
    └── main/
        └── java/com/.../registrolibros/
            ├── MainActivity.kt          # Punto de entrada, estado central de la lista
            ├── model/
            │   └── Libro.kt              # Data class con los datos del libro
            ├── ui/
            │   ├── FormularioLibro.kt     # Formulario de ingreso de datos
            │   ├── ListaLibros.kt         # LazyColumn con la lista de libros
            │   └── ItemLibro.kt           # Ítem individual (LazyRow + botón eliminar)
            └── ui/theme/
                ├── Color.kt               # Paleta de colores (Theme Builder)
                ├── Theme.kt               # Configuración del tema claro/oscuro
                └── Type.kt                # Tipografía
```

## 🚀 Cómo ejecutar la aplicación

### Requisitos previos

- [Android Studio](https://developer.android.com/studio)
- JDK 17 o superior
- Un emulador Android configurado o un dispositivo físico con depuración USB habilitada

### Pasos

1. Cloná el repositorio:
   ```bash
   git clone https://github.com/MathewFaraday28/intro-prog-disp-mov-oto2026-1-tp-1-final.git
   ```
2. Abrí la carpeta del proyecto desde **Android Studio** (`File → Open`)
3. Esperá a que Gradle sincronice las dependencias
4. Seleccioná un emulador o conectá un dispositivo físico
5. Presioná **Run**
