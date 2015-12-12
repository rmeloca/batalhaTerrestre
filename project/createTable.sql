CREATE DATABASE IF NOT EXISTS batalhaTerrestre;

use batalhaTerrestre;

CREATE TABLE IF NOT EXISTS historico(
    id INTEGER auto_increment NOT NULL,
    jogador1 VARCHAR(40),
    jogador2 VARCHAR(40),
    vencedor VARCHAR(40),
    primary key(id)
);
