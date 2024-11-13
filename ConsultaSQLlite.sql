CREATE TABLE IF NOT EXISTS player_settings (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    player_id INTEGER UNIQUE NOT NULL,
    control_settings TEXT,  -- JSON o XML con la configuración de controles
    sound_enabled BOOLEAN DEFAULT 1,  -- 1 para habilitado, 0 para deshabilitado
    resolution TEXT,  -- Resolución de pantalla (ej., "1920x1080")
    language TEXT  -- Idioma (ej., "es", "en")
);

CREATE TABLE IF NOT EXISTS player_progress (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    player_id INTEGER UNIQUE NOT NULL,
    nick_name TEXT,
    experience INTEGER DEFAULT 0,
    life_level INTEGER DEFAULT 100,
    coins INTEGER DEFAULT 0,
    session_count INTEGER DEFAULT 0,
    last_login TEXT  -- Fecha de la última conexión (ej., formato ISO 8601 "YYYY-MM-DD HH:MM:SS")
);
