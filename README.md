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
	* [Descripción](#descripcion)
		* [Pantallas principales](#pantallasPrincipales)
			* [Login](#login)
			* [Listado](#listado)
			* [Detalle](#detalle)
			* [Listado de Favoritos](#listadoFavoritos)
		* [Tecnologías destacadas](#tecnologiasDestacadas)
			* [Retrofit](#retrofit)
			* [Corrutinas](#corrutinas)
			* [Room](#room)
			* [CLEAN *Arquitecture* y Principios SOLID](#clean)
			* [Inyección de dependencias con Hilt](#hilt)
			* [Google Maps API](#maps)
			* [Testing](#testing)
	* [Requisitos](#requisitos)
		* [Obligatorios](#obligatorios)
		* [Opcionales](#opcionales)
	* [Instalación](#instalacion)
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

<a name="descripcion"></a>
### Descripción

La aplicación Android se centra en proporcionar una experiencia intuitiva y funcional para los usuarios que deseen explorar información sobre los superhéroes de Dragon Ball. A continuación, se presenta una breve descripción de las pantallas y las tecnologías destacadas empleadas en el proyecto.

<a name="pantallasPrincipales"></a>
#### Pantallas principales

<a name="login"></a>
##### Login

Pantalla de inicio que permite a los usuarios iniciar sesión utilizando las credenciales proporcionadas en el *bootcamp*.

<a name="listado"></a>
##### Listado

Pantalla principal que presenta un listado completo de personajes de Dragon Ball. La información se obtiene de manera eficiente a través de Retrofit, asegurando una conexión rápida y fiable con la red.

<a name="detalle"></a>
##### Detalle

Pantalla detallada de un personaje, que muestra información completa con un `BottomSheetDialog` y las localizaciones en las que ha estado a través de un mapa. Además, proporciona la capacidad de marcar a un héroe como favorito o no. Las actualizaciones de favoritos se sincronizan entre la base de datos local y remota para garantizar coherencia.

<a name="listadoFavoritos"></a>
##### Listado de Favoritos

Una pantalla adicional que muestra solo los personajes marcados como favoritos, proporcionando una experiencia personalizada para los usuarios.

<a name="tecnologiasDestacadas"></a>
#### Tecnologias destacadas

<a name="retrofit"></a>
##### Retrofit

Todas las llamadas a la red se realizan utilizando Retrofit, asegurando una comunicación eficiente y segura con el *backend*.

<a name="corrutinas"></a>
##### Corrutinas

Se implementa la paralelización de corrutinas siempre que sea posible para evitar esperas innecesarias y mejorar la eficiencia de la aplicación.

<a name="room"></a>
##### Room

La base de datos local se construye con Room, permitiendo un almacenamiento eficiente y una gestión fácil de los datos a través de consultas (*queries*) de SQL.

<a name="clean"></a>
##### CLEAN *Arquitecture* y Principios SOLID

La aplicación sigue el patrón de CLEAN *Architecture* y se adhiera a los principios SOLID para garantizar la escalabilidad, mantenibilidad y flexibilidad del código. La separación por capas se mantiene rigurosamente.

<a name="hilt"></a>
##### Inyección de dependencias con Hilt

Se implementa la inyección de dependencias utilizando Hilt para simplificar la gestión de componentes y promover la modularidad del código.

<a name="maps"></a>
##### Google Maps API

Se registra la aplicación en la *API* de *Google Maps* para proporcionar información sobre las localizaciones de los personajes de manera visual en la aplicación.

<a name="testing"></a>
##### Testing

El proyecto incluye pruebas obligatorias, que abarcan desde pruebas unitarias reales hasta *mocks* y *fakes*.

<a name="requisitos"></a>
### Requisitos

<a name="obligatorios"></a>
#### Obligatorios

* Todas las llamadas a red deben realizarse con Retrofit.
* Paralelización de corrutinas para evitar esperas innecesarias.
* Sincronización entre la base de datos local y remota para las actualizaciones de favoritos.
* Implementación de Room para la base de datos local.
* Aplicación de Clean *Architecture* y principios SOLID.
* Inyección de dependencias con Hilt.
* Registro de la aplicación en la *API* de *Google Maps*.
* Testing: pruebas unitarias reales, *mocks* y *fakes*.

<a name="opcionales"></a>
#### Opcionales

* Pantalla adicional de listado de favoritos.
* Diseño visual abierto, permitiendo libertad en la elección de componentes y representación de información. Para el listado de personajes, me he inspirado en el **[concepto creativo y prototipo](https://dribbble.com/shots/2671572-Marvel-App/attachments/537660?mode=media)** del usuario llamado [Luis Herrero](https://dribbble.com/luisherrero) encontrado en la web [Dribbble](https://dribbble.com/shots/22234085-Dragon-Ball-Z-Character-Info).

<a name="instalacion"></a>
### Instalación

1. Clona el repositorio.
2. Abre el proyecto en Android Studio.
3. Introduce la *key* personal de la *API* de *Google Maps* en el archivo `local.properties`.
3. Conecta un dispositivo Android o utiliza un emulador.
4. Ejecuta la aplicación.

<a name="licencia"></a>
### Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](https://github.com/salvaMsanchez/MarvelApp-Android/blob/main/LICENSE.md) para más detalles.

---

* [App inspiration](https://dribbble.com/shots/5322370-Marvel-Heroes-App-UI)
* [App implementation](https://github.com/amberjen/marvel-heroes)

* timestamp = system.currenttimemillis() ??? OPCIONAL

* LaunchScreen con animación
* Animación Lottie de Carga del detalle, ya que no hará falta cargar nada en la de inicio al no haber login ni nada y estar el LaunchScreen, o por lo menos creo que eso hace en el vídeo del LaunchScreen.
* Listado de superhéroes que cargue una página y luego cargue otra al bajar (FLOW) y que haya un CircularProgressBar abajo de la lista. -> [Easy Android ListView Pagination using Jetpack Compose](https://nabil6391.medium.com/easy-android-listview-pagination-using-jetpack-compose-267472f07929) -> Cachear en BBDD los 20 primeros e ir cargando en BBDD cuando vayamos cargando otros 20 más, ya que si vamos al detalle del héroe cogeríamos su nombre o ID y lo buscaríamos en BBDD. Luego, al salir de la aplicación, se debería eliminar de la BBDD todo lo que no sean los 20 primeros para no tener una app que tire de mucha memoria. FIJARNOS EN LA LISTA DE JUGHADORES DE LA APP DEL MISTER.

- Otros recursos para la lazyColumn -> [Endless Scrolling in Android with Jetpack Compose](https://medium.com/@giorgos.patronas1/endless-scrolling-in-android-with-jetpack-compose-af1f55a03d1a) y [Implementing Infinite Scroll in Android Using Jetpack Compose](https://medium.com/@dery.fathurochman/implementing-infinite-scroll-in-android-using-jetpack-compose-373d5b758f1e)

* Por si queremos hacer una lista de favoritos y que podamos eliminarlos con animación -> [LazyColumn Animations in Jetpack Compose (with Examples)](https://semicolonspace.com/jetpack-compose-lazycolumn-animations/)

* Seguridad de las api keys
	* [Using local.properties file to avoid API Keys check-in into Version Control System](https://blog.mindorks.com/using-local-properties-file-to-avoid-api-keys-check-in-into-version-control-system/)
	* [Securing API Keys in Android projects](https://medium.com/@vontonnie/secure-api-keys-in-android-projects-f8eb4839701d)

	
# APUNTES

* Problema con LazyColumn y detección de scroll hacia abajo: Se enfrentaba a un problema donde el onLoadMore() se llamaba repetidamente al hacer scroll hacia abajo en un LazyColumn, pero no funcionaba correctamente al hacer scroll hacia arriba. Se resolvió implementando un LaunchedEffect que detecta cuando se llega al último elemento de la lista y llama a onLoadMore() solo una vez.
* Optimización del rendimiento en CharacterItem: Se proporcionaron sugerencias para optimizar el rendimiento dentro de CharacterItem, como cargar las imágenes de forma asíncrona de manera eficiente, evitar la creación de nuevos objetos en cada recomposición y reducir la anidación excesiva de componibles.
* Ajuste del tamaño de la imagen y recorte: Se solicitó que la imagen en CharacterItem ocupara toda la tarjeta y se recortara según el tamaño de la tarjeta. Se proporcionó una solución utilizando Modifier.fillMaxSize() y ContentScale.Crop en el composable Image.
* Agregar una capa oscura sobre la imagen: Se solicitó agregar una capa más oscura pero transparente encima de la foto en CharacterItem. Se proporcionaron varias soluciones, incluyendo el uso de un Box con un fondo oscuro y ajuste de la opacidad, así como el uso de Modifier.drawWithContent para dibujar la capa directamente sobre la imagen.
* Orden de dibujo de la capa oscura: Se quería que la capa oscura se aplicara sobre la imagen en CharacterItem. Se ajustó el orden de los elementos dentro de Modifier.drawWithContent para que la capa oscura se dibujara encima de la imagen.
* Ordenación de los personajes cuando se realizan cargas de más superhéroes, ya que actualmente se reflejan tal y como se insertan y ordenan en BBDD.

# A MEJORAR

* Comentarios.
* Dividir en más componentes mis vistas.
* Eliminar los personajes cargados durante el uso de la app una vez que el usuario ha querido salir de la aplicación. Es decir, mostrar Diálogo de Salir, el usuario acepta y, a continuación, se eliminan de BBDD todos los personajes cargados, excepto los 20 primeros.
* Investigar sobre Theming en Jetpack Compose -> https://m3.material.io/theme-builder#/custom -> muy interesante

---

[Subir ⬆️](#top)

---


