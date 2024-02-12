<a name="top"></a>

<h1 align="center">
  <strong><span>Bootcamp Desarrollo de Apps Móviles </span></strong>
</h1>

---

<p align="center">
  <strong><span style="font-size:20px;">Módulo: Android Superpoderes 🤖</span></strong>
</p>

---

<p align="center">
  <strong>Autor:</strong> Salva Moreno Sánchez
</p>

<p align="center">
  <a href="https://www.linkedin.com/in/salvador-moreno-sanchez/">
    <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn">
  </a>
</p>

## Índice
 
* [Herramientas](#herramientas)
* [Proyecto: Marvel App 🦹](#proyecto)
	* [Instalación](#instalacion)
	* [Descripción](#descripcion)
		* [Pantallas principales](#pantallas)
		* [Tecnologías destacadas](#tecnologiasDestacadas)
			* [Jetpack Compose](#compose)
			* [Kotlin Flow y corrutinas](#flow)
			* [Room](#room)
			* [Retrofit](#retrofit)
			* [Inyección de dependencias con Hilt](#hilt)
			* [CLEAN *Arquitecture* y Principios SOLID](#clean)
			* [Testing](#testing)
	* [Problemas, decisiones y soluciones](#problemas)
		* [`LazyColumn` y detección de *scroll* hacia abajo](#scroll)
		* [Agregar capa oscura sobre imagen](#capa)
		* [Ordenación de los personajes](#ordenPersonajes)
		* [Efecto *Shimmer* para carga de elementos](#shimmer)
		* [Gestión de los datos locales al abandonar la aplicación con `BackHandler`](#backHandler)
	* [Tareas a mejorar](#mejoras)
		* [Modularización y reutilización de componentes](#modularizacion)
		* [Uso extendido de Previews](#previews)
		* [`SharedPreferences` para guardar el `offset` de la llamada a la *API*](#preferences)
		* [Investigación sobre Theming en Jetpack Compose](#theming)
		* [Testing de Flow y *Unit Testing*](#testingFlow)
	* [Licencia](#licencia)

<a name="herramientas"></a>
## Herramientas

<p align="center">

<a href="https://www.apple.com/es/ios/ios-17/">
   <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android">
 </a>
  
 <a href="https://www.swift.org/documentation/">
   <img src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin">
 </a>
  
 <a href="https://developer.apple.com/xcode/">
   <img src="https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white" alt="Android Studio">
 </a>
  
</p>

* **Android SDK:** API 24 ("Nougat"; Android 7.0)
* **Kotlin:** 1.9
* **Android Studio:** Hedgehog - 2023.1.1

<a name="proyecto"></a>
## Proyecto: Marvel App 🦹

![Demo app Android gif](images/demoAppAndroid.gif)

<a name="instalacion"></a>
### Instalación

1. Clona el repositorio.
2. Abre el proyecto en Android Studio.
3. Introduce la *API_KEY* y el *HASH* personales de la *API* de [Marvel](https://developer.marvel.com/documentation/authorization) en el archivo `NetworkModule` del directorio `di` del proyecto.
3. Conecta un dispositivo Android o utiliza un emulador.
4. Ejecuta la aplicación.

<a name="descripcion"></a>
### Descripción

Se trata de una aplicación Android que utiliza Jetpack Compose como tecnología principal para la creación de la interfaz de usuario. Se ha desarrollado como práctica final al módulo *Android Superpoderes* del *Bootcamp* de Desarrollo de Apps Móviles de [KeepCoding](https://keepcoding.io), donde se han explorado componentes de Jetpack Compose y programación reactiva usando corrutinas (Kotlin Flow).

<a name="pantallas"></a>
#### Pantallas principales

La aplicación consta de un listado de superhéroes y una pantalla de detalle para cada uno de ellos. En el listado, se muestra información básica sobre cada superhéroe, permitiendo al usuario identificarlos fácilmente. Se ha propuesto un listado horizontal que, de forma reactiva, refleja aquellos personajes que son marcados o desmarcados como favoritos en el listado vertical. Por otro lado, al hacer clic en un elemento de cualquier listado, navegamos a la pantalla de detalle, donde se muestra información más completa sobre el superhéroe seleccionado, así como dos listados horizontales dedicados a los comics y a las series donde aparece.

Cabe señalar que se ha añadido un SplashScreen que actúa de forma dinámica si empleamos un dispositivo o emulador a partir de la API 31 y de forma estática con el resto, cuya documentación para su implementación la encontramos [aquí](https://developer.android.com/develop/ui/views/launch/splash-screen?hl=es-419).

<a name="tecnologias"></a>
#### Tecnologías destacadas

<a name="compose"></a>
##### Jetpack Compose

Jetpack Compose es la tecnología central utilizada para desarrollar la interfaz de usuario de la aplicación. Ofrece una forma declarativa y moderna de construir interfaces de usuario en Android, permitiendo una mayor flexibilidad y facilitando la creación de interfaces dinámicas y reactivas.

<a name="flow"></a>
##### Kotlin Flow y corrutinas

Se hace uso de Kotlin Flow junto con corrutinas para implementar la programación reactiva en la aplicación. Esto permite gestionar de manera eficiente la obtención y actualización de datos, así como mantener una interfaz de usuario fluida y receptiva.

<a name="room"></a>
##### Room

Room se utiliza para cachear el listado de personajes en la base de datos local del dispositivo. Esto mejora la eficiencia y la velocidad de la aplicación al acceder a estos datos, además de permitir su consulta de forma reactiva mediante Kotlin Flow.

<a name="retrofit"></a>
##### Retrofit

Todas las llamadas a la red se realizan utilizando Retrofit, asegurando una comunicación eficiente y segura con el *backend*.

<a name="hilt"></a>
##### Inyección de dependencias con Hilt

Se implementa la inyección de dependencias utilizando Hilt para simplificar la gestión de componentes y promover la modularidad del código.

<a name="clean"></a>
##### CLEAN *Arquitecture* y Principios SOLID

La aplicación sigue el patrón de CLEAN *Architecture* y se adhiera a los principios SOLID para garantizar la escalabilidad, mantenibilidad y flexibilidad del código. La separación por capas se mantiene rigurosamente.

<a name="testing"></a>
##### Testing

El *testing* abarca desde pruebas unitarias reales hasta *mocks* y *fakes*, así como un test de UI.

<a name="problemas"></a>
### Problemas, decisiones y soluciones

<a name="scroll"></a>
#### `LazyColumn` y detección de *scroll* hacia abajo

Problema en el que el *callback* `onLoadMore()` se llamaba repetidamente al hacer *scroll* hacia abajo en el `LazyColumn` de `CharactersScreen`. La solución consistió en implementar un `LaunchedEffect` que detecta cuando se llega al último elemento de la lista y llama a `onLoadMore()` solo una vez.

<a name="capa"></a>
#### Agregar capa oscura sobre imagen

En un inicio no encontraba la manera más adecuada de agregar una capa más oscura pero transparente encima de la foto de mi `CharacterItem`. Investigando, encontré varias soluciones como el simple empleo de un `Box` con un fondo oscuro y opacidad ajustada o el uso de `Modifier.drawWithContent` para dibujar la capa directamente sobre la imagen, opción por la que me decanté por su eficiencia en el proceso de recomposición de la vista.

<a name="ordenPersonajes"></a>
#### Ordenación de los personajes

Se implementó la ordenación alfabética de los personajes desde el `Repository` cuando se realizan cargas adicionales ya que estas cargas desordenaban el listado que procedía de BBDD local, por lo que se decidió elegir un orden personalizado de los elementos.

<a name="shimmer"></a>
#### Efecto *Shimmer* para carga de elementos

Quería añadir un efecto que sea atractivo al usuario y que, al mismo tiempo, esté habituado para cuando elementos estén en carga desde Remoto, desde base de datos local o que simplemente estén cargando sus recursos multimedia. Es por ello que me puse a investigar y elegí implementar el llamado efecto Shimmer gracias al vídeo [How to Create a Shimmer Loading Effect in Jetpack Compose (WITHOUT Library!)](https://www.youtube.com/watch?v=NyO99OJPPec) del desarrollador y youtuber Philipp Lackner.

<a name="backHandler"></a>
#### Gestión de los datos locales al abandonar la aplicación con `BackHandler`

Una de mis preocupaciones fue la de ir acumulando memoria cada vez que se cargaban más y más personajes en local, persistiendo demasiados datos que, llegado el momento, pudieran afectar al rendimiento de nuestra aplicación y, por ende, la experiencia de usuario. De esta forma, pensé en implementar una limpieza de datos de local cada vez que el usuario abandonara la aplicación realizando el tradicional `onBackPressed`, el cual se gestiona de manera más fácil en Jetpack Compose con el BackHandler. Aquí dejo un [vídeo](https://www.youtube.com/watch?v=FPQ50Dg_OvA) donde poder ver su uso.

Así, pude gestionar que al salir de esta forma, se mostrase un `Dialog` y que al confirmar la salida, se eliminasen todos los personajes de BBDD excepto los 20 primeros.

Es evidente que también se eliminarán los favoritos que estuvieran guardados entre esos personajes eliminados, cosa que se solucionaría fácilmente almacenándolos de forma separada al resto, pero para mí el objetivo era aprender a gestionar el `onBackPressed` y a atender a la eficiencia de la memoria al largo plazo del uso de la aplicación.

<a name="mejoras"></a>
### Tareas a mejorar

<a name="modularizacion"></a>
#### Modularización y reutilización de componentes

Se propone dividir en más componentes las vistas para facilitar la modularización y reutilización del código, lo que mejoraría aún más la mantenibilidad y la escalabilidad del proyecto.

<a name="previews"></a>
#### Uso extendido de Previews

Se sugiere sacar más provecho de las Previews. Sería buena idea seguir investigando buenas prácticas acerca de la implementación de *ViewModels*, *callbacks*, interacciones, etc., y así mejorar la eficiencia del desarrollo y la calidad del código.

<a name="preferences"></a>
#### `SharedPreferences` para guardar el `offset` de la llamada a la *API*

Se propone utilizar `SharedPreferences` para guardar el *offset* de la llamada a la *API* de Marvel, asegurando que la aplicación recuerde, aún habiéndola cerrado, por dónde debe comenzar la carga de más superhéroes al hacer *scroll* hasta abajo de la lista.

<a name="theming"></a>
#### Investigación sobre Theming en Jetpack Compose

Investigar sobre Theming en Jetpack Compose para personalizar aún más la apariencia de la aplicación y mejorar la experiencia del usuario.

<a name="testingFlow"></a>
#### Testing de Flow y *Unit Testing*

Se propone investigar y aplicar técnicas de *testing* para *Flows* y *ViewModels*, utilizando herramientas como [Turbine](https://github.com/cashapp/turbine) para *testing* de *Flows* y realizando más pruebas unitarias en pos de mejorar la cobertura.

<a name="licencia"></a>
### Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](https://github.com/salvaMsanchez/MarvelApp-Android/blob/main/LICENSE.md) para más detalles.

---

[Subir ⬆️](#top)

---


