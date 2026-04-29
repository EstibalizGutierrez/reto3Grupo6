CREATE DATABASE spotify COLLATE utf8mb4_spanish_ci;
USE spotify;

/* --------------TABLA DE IDIOMA-------------- */
ALTER TABLE Idioma DISCARD TABLESPACE;
CREATE TABLE Idioma (
	IdIdioma ENUM('ES', 'EU', 'EN', 'FR', 'DE', 'CA', 'GA', 'AR') PRIMARY KEY,
    Descripcion TEXT NOT NULL
);

/* --------------TABLA DE ARTISTA-------------- */
DROP TABLE Artista;
CREATE TABLE Artista (
	IdArtista CHAR(5) PRIMARY KEY,
	NombreArtístico CHAR(50) NOT NULL UNIQUE,
	Genero CHAR(25), 
	Imagen LONGBLOB,
	Descripcion TEXT NOT NULL
);

/* --------------TABLA DE PODCASTER-------------- */
DROP TABLE Podcaster;
CREATE TABLE Podcaster (
	IdPodcaster CHAR(5) PRIMARY KEY,
    IdArtista CHAR(5) NOT NULL UNIQUE,
    CONSTRAINT fk_artista_podcaster FOREIGN KEY (IdArtista) 
		REFERENCES Artista(IdArtista) ON DELETE CASCADE
);

/* --------------TABLA DE MÚSICO-------------- */
DROP TABLE Músico;
CREATE TABLE Músico (
	IdMusico CHAR(5) PRIMARY KEY,
    Característica ENUM('Solista', 'Grupo') ,
    IdArtista CHAR(5) NOT NULL UNIQUE,
    CONSTRAINT fk_artista_musico FOREIGN KEY (IdArtista) 
		REFERENCES Artista(IdArtista) ON DELETE CASCADE
);

/* --------------TABLA DE ALBUM-------------- */
DROP TABLE Album;
CREATE TABLE Album (
	IdAlbum CHAR(5) PRIMARY KEY,
    Título VARCHAR(50) NOT NULL,
    Año DATE NOT NULL,
    Genero VARCHAR(25) NOT NULL,
    Imagen LONGBLOB,
    IdMusico CHAR(5) NOT NULL UNIQUE,
    CONSTRAINT fk_musico_album FOREIGN KEY (IdMusico) 
        REFERENCES Músico(IdMusico) ON DELETE CASCADE
);

/* --------------TABLA DE AUDIO-------------- */
DROP TABLE Canción;
CREATE TABLE Audio (
	IdAudio CHAR(5) PRIMARY KEY, 
    Nombre VARCHAR(25) NOT NULL UNIQUE, 
    Duracion INT NOT NULL,
    Archivo LONGBLOB,
    Tipo ENUM('Podcast', 'Canción'),
    NReproducciones INT NOT NULL DEFAULT 0
);

/* --------------TABLA DE PODCAST-------------- */
DROP TABLE Podcast;
CREATE TABLE Podcast (
	IdAudio CHAR(5) PRIMARY KEY,
    NºColaboradores INT,
    IdPodcaster CHAR(5) NOT NULL,
    CONSTRAINT fk_audio_podcast FOREIGN KEY (IdAudio) 
        REFERENCES Audio(IdAudio) ON DELETE CASCADE,
    CONSTRAINT fk_podcaster_podcast FOREIGN KEY (IdPodcaster) 
        REFERENCES Podcaster(IdPodcaster) ON DELETE CASCADE
);

/* --------------TABLA DE CANCION-------------- */
DROP TABLE Canción;
CREATE TABLE Canción (
	IdAudio CHAR(5) PRIMARY KEY,
    IdAlbum CHAR(5) NOT NULL,
    ArtistasInvitados TEXT,
    CONSTRAINT fk_audio_cancion FOREIGN KEY (IdAudio) 
        REFERENCES Audio(IdAudio) ON DELETE CASCADE,
    CONSTRAINT fk_album_cancion FOREIGN KEY (IdAlbum) 
        REFERENCES Album(IdAlbum) ON DELETE CASCADE
);

/* --------------TABLA DE CLIENTE-------------- */
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
    Tipo ENUM('Free', 'Premium'),
	IdIdioma ENUM('ES', 'EU', 'EN', 'FR', 'DE', 'CA', 'GA', 'AR') NOT NULL UNIQUE,
	CONSTRAINT fk_idioma_cliente FOREIGN KEY (IdIdioma) 
		REFERENCES Idioma(IdIdioma) ON DELETE CASCADE
);

/* --------------TABLA DE PREMIUM-------------- */
DROP TABLE Premium;
CREATE TABLE Premium (
	IdCliente CHAR(5) PRIMARY KEY,
	FechaCaducidad DATE NOT NULL,
	CONSTRAINT fk_cliente_premium FOREIGN KEY (IdCliente) 
		REFERENCES Cliente(IdCliente) ON DELETE CASCADE
);

/* --------------TABLA DE PLAYLIST-------------- */
DROP TABLE Playlist;
CREATE TABLE Playlist (
	IdList INT(5) PRIMARY KEY AUTO_INCREMENT,
    Título VARCHAR(50) NOT NULL,
    FechaCreacion DATE NOT NULL,
    IdCliente CHAR(5) NOT NULL,
    CONSTRAINT fk_cliente_playlist FOREIGN KEY (IdCliente) 
        REFERENCES Cliente(IdCliente) ON DELETE CASCADE
);

/* --------------TABLA DE PLAYLIST-CANCIONES-------------- */
DROP TABLE Playlist_Canciones;
CREATE TABLE Playlist_Canciones (
    IdList INT(5),
    IdAudio CHAR(5),
    FechaPayList_cancion DATE NOT NULL,
    PRIMARY KEY (IdList, IdAudio),
    CONSTRAINT fk_playlist_vinculo FOREIGN KEY (IdList) 
        REFERENCES Playlist(IdList) ON DELETE CASCADE,
    CONSTRAINT fk_cancion_vinculo FOREIGN KEY (IdAudio) 
        REFERENCES Canción(IdAudio) ON DELETE CASCADE
);

/* --------------TABLA DE GUSTOS-------------- */
DROP TABLE Gustos;
CREATE TABLE Gustos (
	IdCliente CHAR(5),
    IdAudio CHAR(5),
    PRIMARY KEY (IdCliente, IdAudio),
    CONSTRAINT fk_gustos_cliente FOREIGN KEY (IdCliente) 
        REFERENCES Cliente(IdCliente) ON DELETE CASCADE,
    CONSTRAINT fk_gustos_audio FOREIGN KEY (IdAudio) 
        REFERENCES Audio(IdAudio) ON DELETE CASCADE
);

