CREATE DATABASE spotify COLLATE utf8mb4_spanish_ci;
USE spotify;

/* --------------TABLA DE ARTISTA-------------- */
/* relacion hecha artista-podcaster, artista-musico
FALTA CON ALBUM */
DROP TABLE Artista;
CREATE TABLE Artista (
	IdArtista CHAR(5) PRIMARY KEY,
	NombreArtístico CHAR(50) NOT NULL UNIQUE,
	Genero CHAR(25), 
	Imagen LONGBLOB,
	Descripcion TEXT NOT NULL
);

/* --------------TABLA DE PODCASTER-------------- */
/* relacion hecha artista-podcaster 
FALTA PODCAST*/
DROP TABLE Podcaster;
CREATE TABLE Podcaster (
	IdPodcaster CHAR(5) PRIMARY KEY,
    IdArtista CHAR(5) NOT NULL,
    UNIQUE (IdArtista),
    CONSTRAINT fk_artista_podcaster
		FOREIGN KEY (IdArtista) REFERENCES Artista(IdArtista) 
        ON DELETE CASCADE
);

/* --------------TABLA DE MÚSICO-------------- */
/* relacion hecha artista-musico 
FALTA ALBUM*/
DROP TABLE Músico;
CREATE TABLE Músico (
	IdMusico CHAR(5) PRIMARY KEY,
    Característica ENUM('Solista', 'Grupo') ,
    IdArtista CHAR(5) NOT NULL,
    CONSTRAINT fk_artista_musico
		FOREIGN KEY (IdArtista) REFERENCES Artista(IdArtista) 
        ON DELETE CASCADE
);

/* --------------TABLA DE CLIENTE-------------- */
/* 
FALTA PLAYLIST, PREMIUM Y GUSTOS*/
DROP TABLE Cliente;
CREATE TABLE Cliente (
	IdCliente CHAR(5) PRIMARY KEY,
    Nombre VARCHAR(25) NOT NULL,
    Apellido VARCHAR(25) NOT NULL,
    Característica ENUM('ES', 'EU', 'EN', 'FR', 'DE', 'CA', 'GA', 'AR'),
    Usuario VARCHAR(25) NOT NULL UNIQUE,
    Contraseña VARCHAR(30) NOT NULL,
    FechaNacimiento DATE NOT NULL,
    FechaRegistro DATE NOT NULL,
    Tipo ENUM('Free', 'Premium')
);

/* --------------TABLA DE PREMIUM-------------- */
/* 
FALTA CLIENTE*/
DROP TABLE Premium;
CREATE TABLE Premium (
	/* IdCliente CHAR(5) PRIMARY KEY, */
	FechaCaducidad DATE NOT NULL
);

/* --------------TABLA DE IDIOMA-------------- */
/* 
FALTA CLIENTE Y PLAYLIST*/
DROP TABLE Idioma;
CREATE TABLE Idioma (
	IdIdioma ENUM('ES', 'EU', 'EN', 'FR', 'DE', 'CA', 'GA', 'AR') PRIMARY KEY,
    Descripcion TEXT NOT NULL
);

/* --------------TABLA DE AUDIO-------------- */
/* 
FALTA PODCAST, GUSTOS Y CANCION*/
DROP TABLE Audio;
CREATE TABLE Audio (
	IdAudio CHAR(5) PRIMARY KEY, 
    Nombre VARCHAR(25) NOT NULL UNIQUE, 
    Duracion INT NOT NULL,
    Archivo LONGBLOB,
    Tipo ENUM('Podcast', 'Canción'),
    NReproducciones INT NOT NULL DEFAULT 0
);

/* --------------TABLA DE PODCAST-------------- */
/* 
FALTA PODCASTER Y AUDIO*/
DROP TABLE Podcast;
CREATE TABLE Podcast (
	/* IdAudio CHAR(5) PRIMARY KEY, */
    NºColaboradores INT
    /* IdPodcaster CHAR(5) NOT NULL */
);

/* --------------TABLA DE CANCION-------------- */
/* 
FALTA PLAYLIST_CANCIONES, AUDIO Y ALBUM*/
DROP TABLE Canción;
CREATE TABLE Canción (
	IdCancion CHAR(5) PRIMARY KEY,
    /* IdAlbum CHAR(5) NOT NULL */
    ArtistasInvitados TEXT
);

/* --------------TABLA DE ALBUM-------------- */
/* 
FALTA CANCION Y MUSICO*/
DROP TABLE Album;
CREATE TABLE Album (
	IdAlbum CHAR(5) PRIMARY KEY,
    Título VARCHAR(25) NOT NULL,
    Año DATE NOT NULL,
    Genero VARCHAR(25) NOT NULL,
    Imagen LONGBLOB
    /*IdMusico CHAR(5) NOT NULL */
);

/* --------------TABLA DE PLAYLIST-------------- */
/* 
FALTA PLAYLISTCANCIONES Y CLIENTE*/
DROP TABLE Playlist;
CREATE TABLE Playlist (
	IdList INT(5) PRIMARY KEY,
    Título VARCHAR(25) NOT NULL,
    FechaCreacion DATE NOT NULL
    /* IdCliente CHAR(5) NOT NULL */
);

/* --------------TABLA DE PLAYLIST-CANCIONES-------------- */
/* 
FALTA CANCION Y PLAYLIST*/
DROP TABLE Playlist_Canciones;
CREATE TABLE Playlist_Canciones (
    FechaPlayList_Cancion DATE NOT NULL
);

/* --------------TABLA DE GUSTOS-------------- */
/* 
FALTA CLIENTE Y AUDIO*/
/*
DROP TABLE Gustos;
CREATE TABLE Gustos (
	IdCliente CHAR(5) NOT NULL
    IdAudio CHAR(5) NOT NULL 
);
*/

