# Aplicación de Gestión de Jugadores y Videojuegos

Esta es una aplicación  diseñada para gestionar jugadores, configuraciones de jugadores, y la información de los videojuegos en una plataforma de juego. La aplicación se conecta a bases de datos relacionales utilizando patrones de diseño como DAO  y Factory para acceder y manipular los datos.

## Descripción

La aplicación permite gestionar la información de los jugadores y videojuegos, incluyendo:
- **Jugadores**: Crear, leer, actualizar y eliminar jugadores.
- **Configuración de Jugadores**: Gestionar las configuraciones de sonido, resolución e idioma.
- **Progreso del Jugador**: Almacenar y actualizar el progreso de los jugadores en sus partidas (exp, nivel, monedas, etc.).
- **Videojuegos**: Gestionar la información de los videojuegos (título, ISBN, cantidad de jugadores, etc.).

## Estructura del Proyecto

La aplicación está dividida en diferentes clases y paquetes que gestionan distintas áreas de la plataforma:

### Modelos
Los modelos representan las entidades en el sistema. Los siguientes son los principales modelos:

- **Jugador**: Representa a un jugador con atributos como `id`, `nick`, `experience`, `lifeLevel` y `coins`.
- **ConfjugadorLite**: Contiene la configuración del jugador, como la habilitación de sonido, resolución y el idioma.
- **Partida**: Contiene información sobre las partidas del jugador, como el `idjuego`, `idjugador`, `monedas`, `exp`, `nivel`, y `ultimaconexion`.
- **PlayerProgress**: Representa el progreso de un jugador, con detalles como `playerId`, `nickName`, `experience`, `lifeLevel`, `coins`, `sessionCount`, y `lastLogin`.
- **Videojuego**: Representa un videojuego, con detalles como `game_id`, `isbn`, `title`, `player_count`, `total_sessions`, y `last_session`.

### DAOs (Data Access Objects)
Cada clase DAO (Data Access Object) es responsable de interactuar con las bases de datos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en las tablas correspondientes. Las clases DAO se encargan de las siguientes funciones:

- **SQLiteConfiguracion**: Gestiona la configuración del jugador utilizando SQLite, permitiendo guardar y actualizar configuraciones.
- **JugadorDAO**: Interactúa con las bases de datos (MySQL, PostgreSQL o SQLite) para gestionar los jugadores.
- **DAOFactory**: Proporciona una fábrica de DAOs para crear instancias de las clases DAO correspondientes según la base de datos elegida.

### Conexiones
La aplicación se conecta a bases de datos utilizando las siguientes clases de conexión:

- **Mysqlconexion**: Permite la conexión a una base de datos MySQL.
- **PostgreConexion**: Permite la conexión a una base de datos PostgreSQL.
- **Sqlliteconexion**: Permite la conexión a una base de datos SQLite.

### Funcionalidades

La aplicación ofrece diversas funcionalidades para interactuar con los jugadores y los videojuegos:

1. **Gestionar Jugadores**: Puedes crear, eliminar, modificar y listar jugadores en la base de datos. Cada jugador tiene atributos como `nick`, `experience`, `lifeLevel` y `coins`.
   
2. **Gestionar Configuración de Jugador**: La clase `SQLiteConfiguracion` permite almacenar y actualizar configuraciones del jugador, como el sonido, la resolución de pantalla y el idioma del juego y el progreso para cuando no tiene conexión.

3. **Gestionar Partidas**: Se pueden gestionar las partidas de los jugadores, actualizando su progreso, monedas, experiencia y nivel en la base de datos.

4. **Gestionar Videojuegos**: La clase `Videojuego` permite almacenar y gestionar videojuegos, incluyendo su título, ISBN, número de jugadores, total de sesiones y fecha de la última sesión.

## Uso

### Configuración de la Base de Datos

La aplicación puede conectarse a bases de datos MySQL, PostgreSQL o SQLite. Para establecer la conexión, debes configurar las credenciales de la base de datos en la clase `ConexionSelector` o la clase correspondiente de la base de datos que elijas.

### Ejecución

1. **Iniciar la aplicación**: Para iniciar la aplicación, ejecuta la clase `MenuBackend`, que cargará el menú principal. Desde ahí, podrás acceder a las diferentes funcionalidades de la aplicación. Para iniciar la aplicación de front del cliente y jugar en local o obtener datos del servidor, ejecuta la clase `MenuClienteJugador2`

2. **Interacción con el menú**: La aplicación proporcionará un menú interactivo para gestionar jugadores, configuraciones, videojuegos y más. Puedes agregar, eliminar o modificar jugadores, consultar configuraciones, y obtener información sobre los videojuegos.










