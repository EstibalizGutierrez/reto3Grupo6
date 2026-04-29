<<<<<<< HEAD
CREATE DATABASE reto3spotify COLLATE utf8mb4_spanish_ci;
USE reto3spotify;

/* --------------TABLA DE IDIOMA-------------- */
/* ta bn*/
DROP TABLE Idioma;
=======
CREATE DATABASE SPOTIFY COLLATE utf8mb4_spanish_ci;
USE SPOTIFY;

/* --------------TABLA DE IDIOMA-------------- */
DROP TABLE IDIOMA;
>>>>>>> bb2c6d99fe4e131c7ec43fb75e282a1046dd84d1
CREATE TABLE Idioma (
	IdIdioma ENUM('ES', 'EU', 'EN', 'FR', 'DE', 'CA', 'GA', 'AR') PRIMARY KEY,
    Descripcion VARCHAR (25) NOT NULL
);

/* --------------TABLA DE ARTISTA-------------- */
<<<<<<< HEAD
/* TA BN */
=======
ALTER TABLE Artista DISCARD TABLESPACE;
>>>>>>> bb2c6d99fe4e131c7ec43fb75e282a1046dd84d1
DROP TABLE Artista;
CREATE TABLE Artista (
	IdArtista CHAR(5) PRIMARY KEY,
	NombreArtistico VARCHAR(50) NOT NULL UNIQUE,
	Genero VARCHAR(25), 
	Imagen VARCHAR(255),
	Descripcion TEXT NOT NULL
);

/* --------------TABLA DE PODCASTER-------------- */
/* TA BN */
DROP TABLE Podcaster;
CREATE TABLE Podcaster (
	IdPodcaster CHAR(5) PRIMARY KEY,
		FOREIGN KEY (IdPodcaster) REFERENCES Artista(IdArtista)
		ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE MÚSICO-------------- */
/* TA BN */
DROP TABLE Musico;
CREATE TABLE Musico (
	IdMusico CHAR(5) PRIMARY KEY,
    Caracteristica ENUM('Solista', 'Grupo') NOT NULL,
		FOREIGN KEY (IdMusico) REFERENCES Artista(IdArtista) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE ALBUM-------------- */
/* TA BN*/
DROP TABLE Album;
CREATE TABLE Album (
	IdAlbum CHAR(5) PRIMARY KEY,
    Titulo VARCHAR(50) NOT NULL,
    Anno DATE NOT NULL,
    Genero VARCHAR(25) NOT NULL,
    Imagen LONGBLOB,
    IdMusico CHAR(5) NOT NULL,
    CONSTRAINT fk_musico_album FOREIGN KEY (IdMusico) 
        REFERENCES Musico(IdMusico) ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE AUDIO-------------- */
/* TA BN */
DROP TABLE Audio;
CREATE TABLE Audio (
	IdAudio CHAR(5) PRIMARY KEY, 
    Nombre VARCHAR(25) NOT NULL UNIQUE, 
    Duracion INT NOT NULL,
    Archivo LONGBLOB,
    Tipo ENUM('Podcast', 'Canción') NOT NULL,
    NReproducciones INT NOT NULL DEFAULT 0 
);

/* --------------TABLA DE PODCAST-------------- */

DROP TABLE Podcast;
CREATE TABLE Podcast (
<<<<<<< HEAD
    IdPodcaster CHAR(5) PRIMARY KEY,
    IdAudio CHAR(5),
    NºColaboradores INT,
=======
	IdAudio CHAR(5),
    NºColaboradores INT,
    IdPodcaster CHAR(5) NOT NULL,
    PRIMARY KEY (IdAudio, IdPodcaster),
>>>>>>> bb2c6d99fe4e131c7ec43fb75e282a1046dd84d1
    CONSTRAINT fk_audio_podcast FOREIGN KEY (IdAudio) 
        REFERENCES Audio(IdAudio) ON DELETE CASCADE,
    CONSTRAINT fk_podcaster_podcast FOREIGN KEY (IdPodcaster) 
        REFERENCES Podcaster(IdPodcaster) ON DELETE CASCADE ON UPDATE CASCADE
);

<<<<<<< HEAD
/* --------------TABLA DE CANCION-------------- */
DROP TABLE Cancion;
CREATE TABLE Cancion (
	IdCancion CHAR(5) PRIMARY KEY,
    IdAlbum CHAR(5) NOT NULL,
    IdAudio CHAR(5) NOT NULL,
    ArtistasInvitados TEXT,
    CONSTRAINT fk_audio_cancion FOREIGN KEY (IdAudio) 
        REFERENCES Audio(IdAudio) ON DELETE CASCADE,
    CONSTRAINT fk_album_cancion FOREIGN KEY (IdAlbum) 
        REFERENCES Album(IdAlbum) ON DELETE CASCADE ON UPDATE CASCADE
);

=======
>>>>>>> bb2c6d99fe4e131c7ec43fb75e282a1046dd84d1
/* --------------TABLA DE CLIENTE-------------- */
DROP TABLE Cliente;
CREATE TABLE Cliente (
	IdCliente CHAR(5) PRIMARY KEY,
    Nombre VARCHAR(25) NOT NULL,
    Apellido VARCHAR(25) NOT NULL,
	Idioma ENUM('ES', 'EU', 'EN', 'FR', 'DE', 'CA', 'GA', 'AR'),
    Usuario VARCHAR(25) NOT NULL UNIQUE,
    Contrasenna VARCHAR(30) NOT NULL,
    FechaNacimiento DATE NOT NULL,
    FechaRegistro DATE NOT NULL,
    Tipo ENUM('Free', 'Premium'),
<<<<<<< HEAD
	IdIdioma ENUM('ES', 'EU', 'EN', 'FR', 'DE', 'CA', 'GA', 'AR'),
=======
	IdIdioma ENUM('ES', 'EU', 'EN', 'FR', 'DE', 'CA', 'GA', 'AR') NOT NULL UNIQUE,
>>>>>>> bb2c6d99fe4e131c7ec43fb75e282a1046dd84d1
	CONSTRAINT fk_idioma_cliente FOREIGN KEY (IdIdioma) 
		REFERENCES Idioma(IdIdioma) ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE PREMIUM-------------- */
DROP TABLE Premium;
CREATE TABLE Premium (
	IdCliente CHAR(5) PRIMARY KEY,
	FechaCaducidad DATE NOT NULL,
	CONSTRAINT fk_cliente_premium FOREIGN KEY (IdCliente) 
		REFERENCES Cliente(IdCliente) ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE PLAYLIST-------------- */
DROP TABLE Playlist;
CREATE TABLE Playlist (
	IdList INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    Titulo VARCHAR(50) NOT NULL,
    FechaCreacion DATE NOT NULL,
    IdCliente CHAR(5) NOT NULL,
    CONSTRAINT fk_cliente_playlist FOREIGN KEY (IdCliente) 
        REFERENCES Cliente(IdCliente) ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE PLAYLIST-CANCIONES-------------- */
DROP TABLE Playlist_Canciones;
CREATE TABLE Playlist_Canciones (
<<<<<<< HEAD
    IdList INT UNSIGNED AUTO_INCREMENT,
    IdCancion CHAR(5),
    FechaPlayList_cancion DATE NOT NULL,
    PRIMARY KEY (IdList, IdCancion),
    CONSTRAINT fk_playlist_vinculo FOREIGN KEY (IdList) 
        REFERENCES Playlist(IdList) ON DELETE CASCADE,
    CONSTRAINT fk_cancion_vinculo FOREIGN KEY (IdCancion) 
        REFERENCES Cancion(IdCancion) ON DELETE CASCADE
=======
    IdList INT(5),
    IdAudio CHAR(5),
    FechaPayList_cancion DATE NOT NULL,
    PRIMARY KEY (IdList, IdAudio),
    CONSTRAINT fk_playlist_vinculo FOREIGN KEY (IdList) 
        REFERENCES Playlist(IdList) ON DELETE CASCADE,
    CONSTRAINT fk_cancion_vinculo FOREIGN KEY (IdAudio) 
        REFERENCES Audio(IdAudio) ON DELETE CASCADE
>>>>>>> bb2c6d99fe4e131c7ec43fb75e282a1046dd84d1
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


