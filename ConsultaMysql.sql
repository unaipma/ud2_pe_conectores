-- Tabla de Jugadores
use ud2conectores;
CREATE TABLE jugadores (
    player_id INT PRIMARY KEY AUTO_INCREMENT,         -- ID �nico del jugador
	nickname varchar(30),
    experience INT NOT NULL,           -- Nivel de experiencia
    life_level INT NOT NULL,           -- Puntos de vida del jugador
    coins INT NOT NULL,                -- Monedas acumuladas
    session_count INT NOT NULL,        -- N�mero de sesiones jugadas
    last_login DATE                    -- Fecha de la �ltima conexi�n
);

-- Tabla de Videojuegos
CREATE TABLE videojuegos (
    game_id INT PRIMARY KEY,           -- Identificador del juego
    isbn VARCHAR(13) NOT NULL,         -- Identificador ISBN del juego
    title VARCHAR(255) NOT NULL,       -- T�tulo del videojuego
    player_count INT NOT NULL,         -- N�mero de jugadores registrados
    total_sessions INT NOT NULL,       -- Total de sesiones o partidas jugadas
    last_session DATE                  -- Fecha de la �ltima sesi�n
);

-- Tabla de Partidas
CREATE TABLE partidas (
    game_id INT,                       -- Identificador del juego
    player_id INT,                     -- Identificador del jugador
    experience INT,                    -- Incremento del nivel de experiencia
    life_level INT,                    -- Actualizaci�n del nivel de vida (+/-)
    coins INT,                          -- Actualizaci�n de las monedas acumuladas (+/-)
    session_date DATE,                 -- Fecha de la sesi�n o partida
    PRIMARY KEY (game_id, player_id, session_date),  -- Clave primaria compuesta
    FOREIGN KEY (game_id) REFERENCES videojuegos(game_id),  -- Relaci�n con la tabla juegos
    FOREIGN KEY (player_id) REFERENCES jugadores(player_id) -- Relaci�n con la tabla jugadores
);
