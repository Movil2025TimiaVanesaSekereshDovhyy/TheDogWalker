# 🧭 NAVIGATION HELPER (Guía de Supervivencia)

Si en algún momento te quedas atascado, puedes ver esta "receta de cocina" paso a paso para implementar la navegación en tu examen usando el patrón clásico de **Interfaces** y **Rutas String**. Similar al de la aplicación de las arañas y los goblins. En este caso está tematizado en una App de recetas de cocina y para aplicaciones de poca complejidad donde nos podemos permitir algunas licencias como un ViewModel compartido.

> **Ejemplo Práctico:** Vamos a usar como referencia una **App de Recetas** que tiene dos pantallas: una **Lista de Platos** y un **Detalle de Receta**.

### 🥘 Ingredientes Previos

1.  **Dependencias:** Revisa que en tu `build.gradle.kts` (Module: app) tienes la librería de navegación (normalmente ya viene en el proyecto base):
    `implementation("androidx.navigation:navigation-compose:2.8.X")`
2.  **Orden:** Crea un paquete llamado `ui/navigation` para no mezclar ficheros.

-----

### 🍳 Paso 1: La Interfaz `NavigationDestination`

Define el "contrato" que deben cumplir todas tus pantallas. Esto nos asegura que todas tengan una Ruta (para navegar) y un Título (para la barra superior).

Crea el archivo `NavigationDestination.kt`:

```kotlin
interface NavigationDestination {
    /**
     * String único que define la ruta (ej: "lista_recetas")
     * Es la dirección interna que usa Android para saber dónde ir.
     */
    val route: String

    /**
     * ID del recurso de texto que se mostrará en la TopAppBar (ej: R.string.app_name). ¡Ojo en el detalle del plato mostramos el nombre del plato que no es un texto estático! Por simplicidad se puede manejar en la lógica del TopAppBar --> Si estoy en home screen uso titleRes (R.string...) else (detalle) leo título del ViewModel
     */
    val titleRes: Int
}
```

-----

### 🍳 Paso 2: Los Objetos de Destino

Crea un objeto (Singleton) para cada pantalla de tu app. En Kotlin podemos tener todos estos objetos en el mismo file .kt, ventajas de Kotlin. Esto evita escribir strings a mano ("strings mágicas") y cometer errores tipográficos.

Crea el archivo `AppDestinations.kt` o añadelo en cada una de los archivos de las screens:

```kotlin
// Destino 1: La pantalla principal
object RecipeListDestination : NavigationDestination {
    override val route = "recipe_list"
    override val titleRes = R.string.app_name
}

// Destino 2: La pantalla secundaria
object RecipeDetailDestination : NavigationDestination {
    override val route = "recipe_detail"
    override val titleRes = R.string.detail_title // Ej. "Detalle del Plato"
}
```

-----

### 🍳 Paso 3: El `NavHost` (El Mapa de Carreteras)

Este es el componente más importante. Es el que decide qué pantalla pintar según la ruta actual.

Crea el archivo `MyRecipesNavHost.kt` o `MyRecipesNavGraph.kt` si quieres llamarlo como en los codelabs.

**Puntos Clave:**

* Recibe el `navController` para poder moverse.
* Recibe el `viewModel` **compartido** para pasar datos de una pantalla a otra sin complicaciones.

```kotlin
@Composable
fun MyRecipesNavHost(
    navController: NavHostController,
    viewModel: RecipeViewModel, // ViewModel compartido (tiene el estado de toda la app)
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = RecipeListDestination.route, // 1. ¿Dónde empieza la app?
        modifier = modifier
    ) {
        // --- PANTALLA 1: LISTA ---
        composable(route = RecipeListDestination.route) {
            RecipeListScreen(
                // Pasamos el ViewModel o el estado necesario
                viewModel = viewModel,
                // Evento de navegación: Al hacer click en una receta...
                onRecipeClick = { recetaSeleccionada ->
                    // 1. Guardamos la selección en el ViewModel (Shared State)
                    viewModel.onRecipeSelected(recetaSeleccionada)

                    // 2. Navegamos al detalle (sin pasar argumentos complejos, el VM ya sabe cuál es)
                    navController.navigate(RecipeDetailDestination.route)
                }
            )
        }

        // --- PANTALLA 2: DETALLE ---
        composable(route = RecipeDetailDestination.route) {
            RecipeDetailScreen(
                // Al usar el MISMO viewModel, esta pantalla ya sabe qué receta se seleccionó
                viewModel = viewModel,
                // Evento para volver atrás
                onBack = { navController.popBackStack() }
            )
        }
    }
}
```

-----

### 🍳 Paso 4: Integración en el `Scaffold` (`MyRecipesApp`)

Finalmente, conecta todo en tu archivo principal de UI (donde está el `Scaffold`).

**Objetivo:** Que la barra superior cambie de título automáticamente y el botón de "Atrás" aparezca cuando toque.

```kotlin
@Composable
fun MyRecipesApp(
    viewModel: RecipeViewModel = viewModel(), // Inyectamos/Creamos el VM aquí
    navController: NavHostController = rememberNavController() // Creamos el Controller
) {
    // Trucazo 🤙: Observamos la ruta actual para cambiar el título de la TopBar
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Lógica para decidir qué título mostrar según la ruta donde estemos
    val currentScreenTitle = when (currentRoute) {
        RecipeListDestination.route -> stringResource(RecipeListDestination.titleRes)
        RecipeDetailDestination.route -> stringResource(RecipeDetailDestination.titleRes)
        else -> stringResource(R.string.app_name)
    }

    // Lógica para saber si podemos volver atrás (para mostrar la flechita)
    val canNavigateBack = navController.previousBackStackEntry != null

    Scaffold(
        topBar = {
            MyRecipesTopAppBar(
                title = currentScreenTitle,
                canNavigateBack = canNavigateBack,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        // Aquí llamamos a nuestro NavHost personalizado
        MyRecipesNavHost(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
    ...
    
    //Y aquí la definición de nuestro TopAppBar personalizado, que será casi siempre la misma

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyRecipesTopAppBar(
        title: String,
        canNavigateBack: Boolean,
        modifier: Modifier = Modifier,
        scrollBehavior: TopAppBarScrollBehavior? = null,
        navigateUp: () -> Unit = {}
    ) {
        CenterAlignedTopAppBar(
            title = { Text(title) },
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            }
        )
    }

}
```

### 💡 Resumen del Flujo Lógico

1.  **Interface:** Define las reglas del juego.
2.  **Objects:** Definen las rutas fijas (para no equivocarnos al escribir).
3.  **NavHost:** Define **qué** pantalla se pinta para cada ruta y **cómo** se pasa de una a otra.
4.  **App:** Es el contenedor. Crea el `NavController`, observa dónde estamos para pintar la TopBar correcta y aloja al `NavHost`.