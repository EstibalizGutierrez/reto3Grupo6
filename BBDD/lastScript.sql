CREATE DATABASE reto3spotify5 COLLATE utf8mb4_spanish_ci;
USE reto3spotify5;

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
    Nombre VARCHAR(40) NOT NULL UNIQUE, 
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
    IdList INT UNSIGNED,
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
values ('AU001', 'Space bound', 4, 'media/cancion/AU001.mp3', 'Canción', 343),
	    ('AU002', '¿Donde invertir?', 9, 'media/podcast/AU002.mp3', 'Podcast', 23),
        ('AU003', 'Fiebre', 3, 'media/cancion/AU003.mp3', 'Canción', 3400),
        ('AU004', 'Odio', 4, 'media/cancion/AU004.mp3', 'Canción', 7000),
        ('AU005', 'Love the way you lie',4, 'media/cancion/AU005.mp3', 'Canción', 203),
		('AU006', 'No love', 5, 'media/cancion/AU006.mp3', 'Canción', 43493),
		('AU007', 'Going through changes',5, 'media/cancion/AU007.mp3', 'Canción', 4504),
        ('AU008', "Won't back down",4, 'media/cancion/AU005.mp3', 'Canción', 433),
		('AU009', 'Kill you', 4, 'media/cancion/AU009.mp3', 'Canción', 53454),
        ('AU010', 'Stan', 6,'media/cancion/AU010.mp3', 'Canción', 449549),
        ('AU011', 'The real slim shady', 4, 'media/cancion/AU011.mp3', 'Canción', 4544),
        ('AU012', 'Marshall Mathers', 5,'media/cancion/AU012.mp3', 'Canción', 45545),
        ('AU013', 'Drug ballad', 5,'media/cancion/AU013.mp3', 'Canción', 69699),
        ('AU014', "You Gon'learn",4, 'media/cancion/AU014.mp3', 'Canción', 343),
		('AU015', 'Darkness',5, 'media/cancion/AU015.mp3', 'Canción', 43435),
        ('AU016', 'Leaven heaving',4, 'media/cancion/AU016.mp3', 'Canción', 234),
        ('AU017', 'Never love again',3, 'media/cancion/AU017.mp3', 'Canción', 23),
        ('AU018', 'No regrets', 3,'media/cancion/AU018.mp3', 'Canción', 656),
		('AU019', 'DNA', 3,'media/cancion/AU019.mp3', 'Canción', 43535),
        ('AU020', 'Fake love', 4,'media/cancion/AU020.mp3', 'Canción', 656),
        ('AU021', 'Dimple', 3,'media/cancion/AU021.mp3', 'Canción', 4333),
        ('AU022', 'Her', 3,'media/cancion/AU022.mp3', 'Canción', 67676),
        ('AU023', 'Singularity', 3,'media/cancion/AU023.mp3', 'Canción', 123),
        ('AU024', 'Awake', 3,'media/cancion/AU024.mp3', 'Canción', 3344),
        ('AU025', 'Lost', 4,'media/cancion/AU025.mp3', 'Canción', 233),
        ('AU026', 'MAMA', 3,'media/cancion/AU026.mp3', 'Canción', 66),
        ('AU027', 'Lie', 3,'media/cancion/AU027.mp3', 'Canción', 3),
        ('AU028', 'Stigma', 3,'media/cancion/AU028.mp3', 'Canción', 1111),
        ('AU029', 'El ataque de las chicas cocodrilo', 3,'media/cancion/AU029.mp3', 'Canción', 65650),
        ('AU030', 'Te quiero', 3,'media/cancion/AU030.mp3', 'Canción', 454),
        ('AU031', 'En la Playa', 4,'media/cancion/AU031.mp3', 'Canción', 234),
        ('AU032', 'Indiana', 3,'media/cancion/AU032.mp3', 'Canción', 545),
        ('AU033', 'Un par de palabras', 3,'media/cancion/AU033.mp3', 'Canción', 23),
        ('AU034', 'Tengo una chica', 4,'media/cancion/AU034.mp3', 'Canción', 5454),
        ('AU035', 'Viernes', 3,'media/cancion/AU035.mp3', 'Canción', 343),
        ('AU036', 'Será esta noche', 4,'media/cancion/AU036.mp3', 'Canción', 341),
        ('AU037', 'Suéltate el pelo', 3,'media/cancion/AU037.mP3', 'Canción', 6767),
        ('AU038', 'La madre de Ana', 3,'media/cancion/AU038.mp3', 'Canción', 79);
		

INSERT INTO Album (IdAlbum, Titulo, Anno, Genero, Imagen, IdMusico)
values ('ALB01', 'Recovery', '2010-06-18', 'Rap/Hip-Hop', 'https://cdn-images.dzcdn.net/images/cover/be682506145061814eddee648edb7c59/1900x1900-000000-81-0-0.jpg', 'A0001'),
	   ('ALB02', 'Formula, vol.2', '2014-02-25', 'Bachata', 'https://i.discogs.com/Yg_4JABhzbAaYe0_gva4Utt8tDYWq98c4f2Ih3Xobk0/rs:fit/g:sm/q:40/h:300/w:300/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTEzMjI5/MjU5LTE1NTAzNDkw/NTUtMjYwOC5qcGVn.jpegg', 'A0004'),
	   ('ALB03', 'Slow wine Mixtape', '2016-11-09', 'Dancehall', 'https://i.discogs.com/crWjb1vUa54B4vOK32UJWaQ4izEnHb375Di3KuAiK08/rs:fit/g:sm/q:40/h:300/w:300/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTEwMzg1/NDA2LTE0OTY0MjM1/NzgtMzEwNS5qcGVn.jpeg', 'A0005'),
	   ('ALB04', 'Marshall Mathers LP', '2020-05-23', 'Rap/hip-hop', 'https://cdn-3.expansion.mx/dims4/default/52bd153/2147483647/strip/true/crop/1200x630+0+85/resize/1200x630!/format/jpg/quality/80/?url=https%3A%2F%2Fcdn-3.expansion.mx%2Fae%2F67%2F5fb91bce4edd8f43eba266613793%2Feminem.jpg','A0001'),
       ('ALB05', 'Music to be Murdered By', '2020-01-17','Rap/hip-hop','https://upload.wikimedia.org/wikipedia/en/8/80/Eminem_-_Music_to_Be_Murdered_By.png','A0001'),
       ('ALB06', 'Love yourself: Answer', '2017-08-24','K-pop','https://static.wikia.nocookie.net/bangtan/images/5/55/Answer_cover.jpg/revision/latest?cb=20180824221028&path-prefix=es','A0002'),
		('ALB07', 'Wings', '2016-10-10', 'K-pop','https://kstoryespana.es/cdn/shop/products/cokodive-bts-2nd-full-length-album-wings-3716964745296.png?v=1753594143&width=1946', 'A0002'),
		('ALB08', 'La cagaste...Burt Lancaster', '1986-04-28', 'Rock','https://static.wikia.nocookie.net/hombres-g/images/4/48/La_Cagaste_Burt_Lancaster.jpeg/revision/latest/thumbnail/width/360/height/450?cb=20240625075251&path-prefix=es', 'A0003'),
        ('ALB09', 'Agitar antes de usar', '1988-05-24', 'Rock', 'https://static.wikia.nocookie.net/hombres-g/images/a/a0/Agitar_Antes_De_Usar.jpg/revision/latest?cb=20240618195117&path-prefix=es','A0003');


INSERT INTO Cancion(IdCancion, IdAlbum, ArtistasInvitados)
values('AU001', 'ALB01', 0),
	   ('AU003', 'ALB03', 1),
       ('AU004', 'ALB02', 1),
       ('AU005', 'ALB01',1),
	   ('AU006', 'ALB01',1),
       ('AU007', 'ALB01',0),
       ('AU008', 'ALB01',1),
       ('AU009', 'ALB04',0),
       ('AU010', 'ALB04',1),
       ('AU011', 'ALB04',0),
       ('AU012', 'ALB04',0),
       ('AU013', 'ALB04',0),
       ('AU014', 'ALB05',1),
       ('AU015', 'ALB05',0),
       ('AU016', 'ALB05',1),
       ('AU017', 'ALB05',0),
       ('AU018', 'ALB05',1),
       ('AU019', 'ALB06',0),
       ('AU020', 'ALB06',0),
       ('AU021', 'ALB06',0),
       ('AU022', 'ALB06',0),
       ('AU023', 'ALB06',0),
       ('AU024', 'ALB07',1),
       ('AU025', 'ALB07',1),
       ('AU026', 'ALB07',1),
       ('AU027', 'ALB07',1),
       ('AU028', 'ALB07',1),
       ('AU029', 'ALB08',0),
       ('AU030', 'ALB08',0),
       ('AU031', 'ALB08',0),
       ('AU032', 'ALB08',0),
       ('AU033', 'ALB08',0),
       ('AU034', 'ALB09',0),
       ('AU035', 'ALB09',0),
       ('AU036', 'ALB09',0),
       ('AU037', 'ALB09',0),
       ('AU038', 'ALB09',0);



INSERT INTO Podcaster (IdPodcaster)
values ('A0006');


insert into Podcast (IdPodcast, NColaboradores, IdPodcaster)
values ('AU002', 2, 'A0006');


INSERT INto Cliente (IdCliente, Nombre, Apellido,Usuario, Contrasena, FechaNacimiento, FechaRegistro, Tipo, IdIdioma)
values ('CL001', 'Ernesto Manuel', 'Valverde', 'mrKristo67', 'elorrieta00', '2004-08-04', current_date(), 'Premium', 'ES'),
	   ('CL002', 'Victoria', 'Rosales', 'Vickyy04','estrellaLuna', '1999-04-04', current_date(), 'Free', 'FR'),
       ('CL003', 'Jhon', 'Izarra', 'johannbachx25', 'piscobamba', '2004-08-04', current_date(), 'Premium', 'EN'),
       ('CL004', 'Keyla', 'Castillo', 'freeGirl', 'iamfree99', '2000-12-12', current_date(), 'Free', 'ES');
       

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
		('CL001', 'AU003'),
		('CL001', 'AU009'),
		('CL001', 'AU012'),
		('CL001', 'AU018'),
		('CL001', 'AU013'),
	   ('CL002', 'AU004');