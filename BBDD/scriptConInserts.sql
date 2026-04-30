CREATE DATABASE reto3spotify2 COLLATE utf8mb4_spanish_ci;
USE reto3spotify2;

/* --------------TABLA DE IDIOMA-------------- */
/* ta bn*/
DROP TABLE if exists Idioma;
CREATE TABLE Idioma (
	IdIdioma ENUM('ES', 'EU', 'EN', 'FR', 'DE', 'CA', 'GA', 'AR') PRIMARY KEY,
    Descripcion VARCHAR (25) NOT NULL
);

/* --------------TABLA DE ARTISTA-------------- */
/* TA BN */
DROP TABLE if exists Artista;
CREATE TABLE Artista (
	IdArtista CHAR(5) PRIMARY KEY,
	NombreArtistico VARCHAR(50) NOT NULL UNIQUE,
	Genero VARCHAR(25), 
	Imagen VARCHAR(255),
	Descripcion TEXT NOT NULL
);

/* --------------TABLA DE PODCASTER-------------- */
/* TA BN */
DROP TABLE if exists Podcaster;
CREATE TABLE Podcaster (
	IdPodcaster CHAR(5) PRIMARY KEY,
		FOREIGN KEY (IdPodcaster) REFERENCES Artista(IdArtista)
		ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE MÚSICO-------------- */
/* TA BN */
DROP TABLE if exists Musico;
CREATE TABLE Musico (
	IdMusico CHAR(5) PRIMARY KEY,
    Caracteristica ENUM('Solista', 'Grupo') NOT NULL,
		FOREIGN KEY (IdMusico) REFERENCES Artista(IdArtista) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE ALBUM-------------- */
/* TA BN*/
DROP TABLE if exists Album;
CREATE TABLE Album (
	IdAlbum CHAR(5) PRIMARY KEY,
    Titulo VARCHAR(50) NOT NULL,
    Anno DATE NOT NULL,
    Genero VARCHAR(25) NOT NULL,
    Imagen VARCHAR(300),
    IdMusico CHAR(5) NOT NULL,
    CONSTRAINT fk_musico_album FOREIGN KEY (IdMusico) 
        REFERENCES Musico(IdMusico) ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE AUDIO-------------- */
/* TA BN */
DROP TABLE if exists Audio;
CREATE TABLE Audio (
	IdAudio CHAR(5) PRIMARY KEY, 
    Nombre VARCHAR(25) NOT NULL UNIQUE, 
    Duracion INT UNSIGNED NOT NULL,
    Archivo VARCHAR(300),
    Tipo ENUM('Podcast', 'Canción') NOT NULL,
    NReproducciones INT NOT NULL DEFAULT 0 
);

/* --------------TABLA DE PODCAST-------------- */

DROP TABLE if exists Podcast;
CREATE TABLE Podcast (
    IdPodcast CHAR(5) PRIMARY KEY,
    NColaboradores INT UNSIGNED,
    IdPodcaster CHAR(5) NOT NULL, 
    CONSTRAINT fk_audio_podcast FOREIGN KEY (IdPodcast) 
        REFERENCES Audio(IdAudio) ON DELETE CASCADE,
    CONSTRAINT fk_podcaster_podcast FOREIGN KEY (IdPodcaster) 
        REFERENCES Podcaster(IdPodcaster) ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE CANCION-------------- */
DROP TABLE if exists Cancion;
CREATE TABLE Cancion (
	IdCancion CHAR(5) PRIMARY KEY,
    IdAlbum CHAR(5) NOT NULL,
    ArtistasInvitados TEXT,
    CONSTRAINT fk_audio_cancion FOREIGN KEY (IdCancion) 
        REFERENCES Audio(IdAudio) ON update cascade on DELETE CASCADE,
    CONSTRAINT fk_album_cancion FOREIGN KEY (IdAlbum) 
        REFERENCES Album(IdAlbum) ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE CLIENTE-------------- */
DROP TABLE if exists Cliente;
CREATE TABLE Cliente (
	IdCliente CHAR(5) PRIMARY KEY,
    Nombre VARCHAR(25) NOT NULL,
    Apellido VARCHAR(25) NOT NULL,
    Usuario VARCHAR(25) NOT NULL UNIQUE,
    Contrasena VARCHAR(64) NOT NULL,
    FechaNacimiento DATE NOT NULL,
    FechaRegistro DATE NOT NULL,
    Tipo ENUM('Free', 'Premium') NOT NULL,
	IdIdioma ENUM('ES', 'EU', 'EN', 'FR', 'DE', 'CA', 'GA', 'AR') NOT NULL,
	CONSTRAINT fk_idioma_cliente FOREIGN KEY (IdIdioma) 
		REFERENCES Idioma(IdIdioma) ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE PREMIUM-------------- */
DROP TABLE if exists Premium;
CREATE TABLE Premium (
	IdCliente CHAR(5) PRIMARY KEY,
	FechaCaducidad DATE NOT NULL,
	CONSTRAINT fk_cliente_premium FOREIGN KEY (IdCliente) 
		REFERENCES Cliente(IdCliente) ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE PLAYLIST-------------- */
DROP TABLE if exists Playlist;
CREATE TABLE Playlist (
	IdList INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    Titulo VARCHAR(50) NOT NULL,
    FechaCreacion DATE NOT NULL,
    IdCliente CHAR(5) NOT NULL,
    CONSTRAINT fk_cliente_playlist FOREIGN KEY (IdCliente) 
        REFERENCES Cliente(IdCliente) ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE PLAYLIST-CANCIONES-------------- */
DROP TABLE if exists Playlist_Canciones;
CREATE TABLE Playlist_Canciones (
    IdList INT UNSIGNED AUTO_INCREMENT,
    IdCancion CHAR(5),
    FechaPlayList_cancion DATE NOT NULL,
    PRIMARY KEY (IdList, IdCancion),
    CONSTRAINT fk_playlist_playlist_canciones FOREIGN KEY (IdList) 
        REFERENCES Playlist(IdList) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_cancion_vinculo FOREIGN KEY (IdCancion) 
        REFERENCES Cancion(IdCancion) ON DELETE CASCADE ON UPDATE CASCADE
);

/* --------------TABLA DE GUSTOS-------------- */
DROP TABLE if exists Gustos;
CREATE TABLE Gustos (
	IdCliente CHAR(5),
    IdAudio CHAR(5),
    PRIMARY KEY (IdCliente, IdAudio),
    CONSTRAINT fk_gustos_cliente FOREIGN KEY (IdCliente) 
        REFERENCES Cliente(IdCliente) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_gustos_audio FOREIGN KEY (IdAudio) 
        REFERENCES Audio(IdAudio) ON DELETE CASCADE ON UPDATE CASCADE
);



insert into Idioma (IdIdioma, Descripcion)
values ('ES','español'),
		('EU','euskera'),
        ('EN','ingles'),
        ('FR','frances');
		

insert into Artista (IdArtista, NombreArtistico, Genero, Imagen, Descripcion)
values ('A0001', 'Eminem', 'Rap/Hip-Hop', 'https://static.wikia.nocookie.net/rap/images/3/34/Eminem_Fotografia_01.jpg/revision/latest?cb=20220507042018&path-prefix=es', 'Eminem, conocido también como Slim Shady, es uno de los raperos más influyentes y exitosos en la historia del hip hop. Destaca por su técnica excepcional, rimas complejas y una capacidad única para narrar historias crudas y personales con un estilo provocador. A lo largo de su carrera ha ganado múltiples premios Grammy y un Oscar, consolidándose como un ícono cultural global. Su impacto no solo transformó el género, sino que rompió barreras raciales en la industria musical.'),
	   ('A0002', 'BTS', 'k-pop', 'https://static.wikia.nocookie.net/mamarre-estudios-espanol/images/2/24/3OWSV6ZZHZHQFDIC6HXDTQDUKE.jpg/revision/latest?cb=20200420001515&path-prefix=es', 'Septeto surcoreano formado en Seúl en 2010. Es el grupo de K-pop más influyente a nivel mundial, compuesto por Jin, Suga, J-Hope, RM, Jimin, V y Jungkook. Han encabezado las listas de Billboard y son reconocidos por su impacto cultural global y récords en ventas.'),
       ('A0003', 'Hombres G', 'Rock', 'https://lafonoteca.net/wp-content/uploads/2008/03/hombres-g.jpg', 'Banda española de pop-rock y new wave fundada en Madrid en 1982. Liderada por David Summers, se convirtió en uno de los grupos más exitosos de los años 80 con himnos como Devuélveme a mi chica y Venezia, marcando a toda una generación en España y Latinoamérica'),
       ('A0004', 'Romeo Santos', 'Bachata', 'https://cdn-images.dzcdn.net/images/artist/0a20c1c5fb5cdd61052c915a325b3589/1900x1900-000000-80-0-0.jpg', 'Cantante y compositor estadounidense de origen dominicano, conocido mundialmente como El Rey de la Bachata. Fue el líder y vocalista de la agrupación Aventura antes de consolidarse como solista, donde ha colaborado con grandes estrellas internacionales como Drake, Usher y Rosalía, logrando posicionar la bachata en las listas de éxitos más importantes del mundo.'),
       ('A0005', 'Bad Gyal' , 'Genero Urbano', 'https://elgeneracionalpost.com/wp-content/uploads/2024/01/365689375_844099157085279_2412177309070566805_n.jpeg', 'Cantante, compositora y DJ española, referente absoluta del género urbano y la música de club. Conocida como la Reina del Dancehall en España, ha logrado fusionar el reggaetón, el dembow y el trap con una estética única. Ha colaborado con grandes figuras internacionales y es responsable de éxitos globales como Fiebre, Alocao y Chulo, destacando por su estilo innovador y su gran impacto en la escena musical actual.'),
       ('A0006', 'The wildProject', "Talk show", 'https://s2.elespanol.com/2024/09/13/omicrono/software/885671616_249158676_1024x576.jpg','The Wild Project es el podcast número 1 en español, dirigido por Jordi Wild. Se caracteriza por conversaciones extensas y sin censura sobre actualidad, deportes (MMA, boxeo), ciencia, misterio y filosofía. Con invitados variados, busca el entretenimiento y el debate, ganando premios ESALAND al mejor talk show.');  
       
insert into Musico (IdMusico, Caracteristica)
values ('A0001', 'solista'),
	   ('A0002', 'grupo'),
       ('A0003', 'grupo'),
       ('A0004', 'solista'),
       ('A0005', 'solista');


insert into Audio(IdAudio, Nombre, Duracion, Archivo, Tipo, NReproducciones)
values ('AU001', 'Space bound', 4, 'media/cancion/AU001.mp3', 'Canción', default),
	    ('AU002', '¿Donde invertir?', 9, 'media/podcast/AU002.mp3', 'Podcast', default),
        ('AU003', 'Fiebre', 3, 'media/cancion/AU003.mp3', 'Canción', 3400),
        ('AU004', 'Odio', 4, 'media/cancion/AU004.mp3', 'Canción', 7000);
		

INSERT INTO Album (IdAlbum, Titulo, Anno, Genero, Imagen, IdMusico)
values ('ALB01', 'Recovery', '2010-06-18', 'Rap/Hip-Hop', 'https://cdn-images.dzcdn.net/images/cover/be682506145061814eddee648edb7c59/1900x1900-000000-81-0-0.jpg', 'A0001'),
	   ('ALB02', 'Formula, vol.2', '2014-02-25', 'Bachata', 'https://i.discogs.com/Yg_4JABhzbAaYe0_gva4Utt8tDYWq98c4f2Ih3Xobk0/rs:fit/g:sm/q:40/h:300/w:300/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTEzMjI5/MjU5LTE1NTAzNDkw/NTUtMjYwOC5qcGVn.jpegg', 'A0004'),
	   ('ALB03', 'Slow wine Mixtape', '2016-11-09', 'Dancehall', 'https://i.discogs.com/crWjb1vUa54B4vOK32UJWaQ4izEnHb375Di3KuAiK08/rs:fit/g:sm/q:40/h:300/w:300/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTEwMzg1/NDA2LTE0OTY0MjM1/NzgtMzEwNS5qcGVn.jpeg', 'A0005');


INSERT INTO Cancion(IdCancion, IdAlbum, ArtistasInvitados)
values('AU001', 'ALB01', NULL),
	   ('AU003', 'ALB03', 2),
       ('AU004', 'ALB02', 2);

INSERT INTO Podcaster (IdPodcaster)
values ('A0006');


insert into Podcast (IdPodcast, NColaboradores, IdPodcaster)
values ('AU002', 2, 'A0006');


INSERT INto Cliente (IdCliente, Nombre, Apellido,Usuario, Contrasena, FechaNacimiento, FechaRegistro, Tipo, IdIdioma)
values ('CL001', 'Ernesto Manuel', 'Valverde', 'mrKristo67', SHA2('elorrieta00' ,256), '2004-08-04', current_date(), 'Premium', 'ES'),
	   ('CL002', 'Victoria', 'Rosales', 'Vickyy04', SHA2('estrellaLuna' ,256), '1999-04-04', current_date(), 'Free', 'FR');
       

INSERT into Premium (IdCliente, FechaCaducidad)
values ('CL001', current_date() + interval 1 year),
	   ('CL002', current_date() + interval 6 month);


inserT into Playlist (IdList, Titulo, FechaCreacion, IdCliente)
values (1, 'miPlaylistSolitaria', current_date(), 'CL001'),
		(2, 'paraDiscotecasList', current_date(), 'CL002'); 

INSERT INTO Playlist_Canciones (IdList, IdCancion, FechaPlaylist_cancion)
values (1, 'AU001', current_date()),
		(2, 'AU003', current_date()),
        (2, 'AU004', current_date());


INSERT into Gustos(IdCliente, IdAudio)
values ('CL001', 'AU001'),
	   ('CL002', 'AU004');




