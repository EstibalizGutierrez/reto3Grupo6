CREATE DATABASE reto3spotify COLLATE utf8mb4_spanish_ci;
USE reto3spotify;

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
    Duracion time NOT NULL,
    Archivo VARCHAR(300),
    Tipo ENUM('Podcast', 'Cancion') NOT NULL,
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
        ('FR','frances'),
        ('EU','euskera'),
        ('DE','aleman'),
        ('AR','arabe'),
        ('CA','catalan');
		

insert into Artista (IdArtista, NombreArtistico, Genero, Imagen, Descripcion)
values ('A0001', 'Eminem', 'Rap/Hip-Hop', 'img/artistas/eminem.jpg', 'Eminem, conocido también como Slim Shady, es uno de los raperos más influyentes y exitosos en la historia del hip hop. Destaca por su técnica excepcional, rimas complejas y una capacidad única para narrar historias crudas y personales con un estilo provocador. A lo largo de su carrera ha ganado múltiples premios Grammy y un Oscar, consolidándose como un ícono cultural global. Su impacto no solo transformó el género, sino que rompió barreras raciales en la industria musical.'),
	   ('A0002', 'BTS', 'k-pop', 'img/artistas/bts.jpg', 'Septeto surcoreano formado en Seúl en 2010. Es el grupo de K-pop más influyente a nivel mundial, compuesto por Jin, Suga, J-Hope, RM, Jimin, V y Jungkook. Han encabezado las listas de Billboard y son reconocidos por su impacto cultural global y récords en ventas.'),
       ('A0003', 'Hombres G', 'Rock', 'img/artistas/hombresg.jpg', 'Banda española de pop-rock y new wave fundada en Madrid en 1982. Liderada por David Summers, se convirtió en uno de los grupos más exitosos de los años 80 con himnos como Devuélveme a mi chica y Venezia, marcando a toda una generación en España y Latinoamérica'),
       ('A0004', 'Romeo Santos', 'Bachata', 'img/artistas/romeosantos.jpg', 'Cantante y compositor estadounidense de origen dominicano, conocido mundialmente como El Rey de la Bachata. Fue el líder y vocalista de la agrupación Aventura antes de consolidarse como solista, donde ha colaborado con grandes estrellas internacionales como Drake, Usher y Rosalía, logrando posicionar la bachata en las listas de éxitos más importantes del mundo.'),
       ('A0005', 'Bad Gyal' , 'Genero Urbano', 'img/artistas/badgyal.jpg', 'Cantante, compositora y DJ española, referente absoluta del género urbano y la música de club. Conocida como la Reina del Dancehall en España, ha logrado fusionar el reggaetón, el dembow y el trap con una estética única. Ha colaborado con grandes figuras internacionales y es responsable de éxitos globales como Fiebre, Alocao y Chulo, destacando por su estilo innovador y su gran impacto en la escena musical actual.'),
       ('A0006', 'The wildProject', "Talk show", 'img/artistas/thewildproject.jpeg','The Wild Project es el podcast número 1 en español, dirigido por Jordi Wild. Se caracteriza por conversaciones extensas y sin censura sobre actualidad, deportes (MMA, boxeo), ciencia, misterio y filosofía. Con invitados variados, busca el entretenimiento y el debate, ganando premios ESALAND al mejor talk show.'),
       ('A0007', 'Dinosaur Vlogs', "True Crime", 'img/artistas/dinosaurvlogs.jpg', 'Dinosaur Vlogs, liderado por Vicky Armida, es un exitoso canal de YouTube enfocado en casos misteriosos, True Crime, misterios sin resolver y vlogs de la vida cotidiana. Con más de 2 millones de suscriptores, destaca por una narrativa de investigación detallada de casos oscuros y una personalidad cercana, incluyendo también contenido personal.'),
       ('A0008', 'Black Mango Podcast', "True Crime", 'img/artistas/blackmango.jpg','Black Mango es un popular podcast español de crímenes, historia y misterio, creado por un trío de jóvenes divulgadores, que cuenta con más de dos millones de seguidores en plataformas como Spotify, YouTube y Apple Podcasts. Sus episodios abordan temas como atracos famosos, misterios sin resolver y crímenes históricos.');
       
insert into Musico (IdMusico, Caracteristica)
values ('A0001', 'Solista'),
	   ('A0002', 'Grupo'),
       ('A0003', 'Grupo'),
       ('A0004', 'Solista'),
       ('A0005', 'Solista');


insert into Audio(IdAudio, Nombre, Duracion, Archivo, Tipo, NReproducciones)
values  ('AU001', 'Space bound', '00:04:38', 'media/cancion/AU001.mp3', 'Cancion', 343),
        ('AU002', '¿Donde invertir?', '00:09:00', 'media/podcast/AU002.mp3', 'Podcast', 23),
        ('AU003', 'Fiebre', '00:04:07', 'media/cancion/AU003.mp3', 'Cancion', 3400),
        ('AU004', 'Odio', '00:03:45', 'media/cancion/AU004.mp3', 'Cancion', 7000),
        ('AU005', 'Love the way you lie', '00:04:23', 'media/cancion/AU005.mp3', 'Cancion', 203),
        ('AU006', 'No love', '00:04:59', 'media/cancion/AU006.mp3', 'Cancion', 43493),
        ('AU007', 'Going through changes', '00:04:59', 'media/cancion/AU007.mp3', 'Cancion', 4504),
        ('AU008', "Won't back down", '00:04:25', 'media/cancion/AU008.mp3', 'Cancion', 433),
        ('AU009', 'Kill you', '00:04:24', 'media/cancion/AU009.mp3', 'Cancion', 53454),
        ('AU010', 'Stan', '00:06:44', 'media/cancion/AU010.mp3', 'Cancion', 449549),
        ('AU011', 'The real slim shady', '00:04:44', 'media/cancion/AU011.mp3', 'Cancion', 4544),
        ('AU012', 'Marshall Mathers', '00:05:20', 'media/cancion/AU012.mp3', 'Cancion', 45545),
        ('AU013', 'Drug ballad', '00:05:01', 'media/cancion/AU013.mp3', 'Cancion', 69699),
        ('AU014', "You Gon'learn", '00:04:41', 'media/cancion/AU014.mp3', 'Cancion', 343),
        ('AU015', 'Darkness', '00:05:38', 'media/cancion/AU015.mp3', 'Cancion', 43435),
        ('AU016', 'Leaving heaven', '00:04:25', 'media/cancion/AU016.mp3', 'Cancion', 234),
        ('AU017', 'Never love again', '00:03:12', 'media/cancion/AU017.mp3', 'Cancion', 23),
        ('AU018', 'No regrets', '00:04:48', 'media/cancion/AU018.mp3', 'Cancion', 656),
        ('AU019', 'DNA', '00:03:05', 'media/cancion/AU019.mp3', 'Cancion', 43535),
        ('AU020', 'Fake love', '00:03:30', 'media/cancion/AU020.mp3', 'Cancion', 656),
        ('AU021', 'Dimple', '00:03:16', 'media/cancion/AU021.mp3', 'Cancion', 4333),
        ('AU022', 'Her', '00:03:49', 'media/cancion/AU022.mp3', 'Cancion', 67676),
        ('AU023', 'Singularity', '00:03:17', 'media/cancion/AU023.mp3', 'Cancion', 123),
        ('AU024', 'Awake', '00:03:11', 'media/cancion/AU024.mp3', 'Cancion', 3344),
        ('AU025', 'Lost', '00:04:01', 'media/cancion/AU025.mp3', 'Cancion', 233),
        ('AU026', 'MAMA', '00:04:32', 'media/cancion/AU026.mp3', 'Cancion', 66),
        ('AU027', 'Lie', '00:03:35', 'media/cancion/AU027.mp3', 'Cancion', 3),
        ('AU028', 'Stigma', '00:03:36', 'media/cancion/AU028.mp3', 'Cancion', 1111),
        ('AU029', 'El ataque de las chicas cocodrilo', '00:03:07', 'media/cancion/AU029.mp3', 'Cancion', 65650),
        ('AU030', 'Te quiero', '00:03:49', 'media/cancion/AU030.mp3', 'Cancion', 454),
        ('AU031', 'En la Playa', '00:04:09', 'media/cancion/AU031.mp3', 'Cancion', 234),
        ('AU032', 'Indiana', '00:02:54', 'media/cancion/AU032.mp3', 'Cancion', 545),
        ('AU033', 'Un par de palabras', '00:03:16', 'media/cancion/AU033.mp3', 'Cancion', 23),
        ('AU034', 'Tengo una chica', '00:03:10', 'media/cancion/AU034.mp3', 'Cancion', 5454),
        ('AU035', 'Viernes', '00:03:15', 'media/cancion/AU035.mp3', 'Cancion', 343),
        ('AU036', 'Será esta noche', '00:04:08', 'media/cancion/AU036.mp3', 'Cancion', 341),
        ('AU037', 'Suéltate el pelo', '00:03:13', 'media/cancion/AU037.mp3', 'Cancion', 6767),
        ('AU038', 'La madre de Ana', '00:03:33', 'media/cancion/AU038.mp3', 'Cancion', 79),
        ('AU039', '¿De CANTANTE FAMOSO A ASESINO?', '01:17:56', 'media/podcast/AU039.mp3', 'Podcast', 5413),
        ('AU040', 'LA MAFIA MEXICANA', '01:04:34', 'media/podcast/AU040.mp3', 'Podcast', 1654),
		('AU041', 'Cancioncitas de amor', '00:03:57', 'media/cancion/AU041.mp3', 'Cancion', 1654),
		('AU042', 'Hilito', '00:03:55', 'media/cancion/AU042.mp3', 'Cancion', 1654),
		('AU043', 'Necio', '00:04:34', 'media/cancion/AU043.mp3', 'Cancion', 1654),
		('AU044', 'Propuesta indecente', '00:04:05', 'media/cancion/AU044.mp3', 'Cancion', 1654);


		

INSERT INTO Album (IdAlbum, Titulo, Anno, Genero, Imagen, IdMusico)
values 	('ALB01', 'Recovery', '2010-06-18', 'Rap/Hip-Hop', 'img/albumes/recoveryE.jpg', 'A0001'),
		('ALB02', 'Formula, vol.2', '2014-02-25', 'Bachata', 'img/albumes/formulavol2RS.jpg', 'A0004'),
		('ALB03', 'Slow wine Mixtape', '2016-11-09', 'Dancehall', 'img/albumes/slowwineBG.jpg', 'A0005'),
		('ALB04', 'Marshall Mathers LP', '2020-05-23', 'Rap/hip-hop', 'img/albumes/themarshallMLPE.jpg','A0001'),
		('ALB05', 'Music to be Murdered By', '2020-01-17','Rap/hip-hop','img/albumes/musictobeMBE.png','A0001'),
		('ALB06', 'Love yourself: Answer', '2017-08-24','K-pop','img/albumes/loveyourselfBTS.jpg','A0002'),
		('ALB07', 'Wings', '2016-10-10', 'K-pop','img/albumes/wingsBTS.jpg', 'A0002'),
		('ALB08', 'La cagaste...Burt Lancaster', '1986-04-28', 'Rock','lacagasteHG.jpg', 'A0003'),
        ('ALB09', 'Agitar antes de usar', '1988-05-24', 'Rock', 'img/albumes/agitarantesHG.jpg','A0003');


INSERT INTO Cancion(IdCancion, IdAlbum, ArtistasInvitados)
values ('AU001', 'ALB01', null),
	   ('AU003', 'ALB03', 'x'),
       ('AU004', 'ALB02',  'x'),
       ('AU005', 'ALB01', 'x'),
	   ('AU006', 'ALB01', 'x'),
       ('AU007', 'ALB01',null),
       ('AU008', 'ALB01', 'x'),
       ('AU009', 'ALB04',null),
       ('AU010', 'ALB04', 'x'),
       ('AU011', 'ALB04',null),
       ('AU012', 'ALB04',null),
       ('AU013', 'ALB04',null),
       ('AU014', 'ALB05', 'x'),
       ('AU015', 'ALB05',null),
       ('AU016', 'ALB05', 'x'),
       ('AU017', 'ALB05',null),
       ('AU018', 'ALB05', 'x'),
       ('AU019', 'ALB06',null),
       ('AU020', 'ALB06',null),
       ('AU021', 'ALB06',null),
       ('AU022', 'ALB06',null),
       ('AU023', 'ALB06',null),
       ('AU024', 'ALB07', 'x'),
       ('AU025', 'ALB07', 'x'),
       ('AU026', 'ALB07', 'x'),
       ('AU027', 'ALB07', 'x'),
       ('AU028', 'ALB07', 'x'),
       ('AU029', 'ALB08',null),
       ('AU030', 'ALB08',null),
       ('AU031', 'ALB08',null),
       ('AU032', 'ALB08',null),
       ('AU033', 'ALB08',null),
       ('AU034', 'ALB09',null),
       ('AU035', 'ALB09',null),
       ('AU036', 'ALB09',null),
       ('AU037', 'ALB09',null),
       ('AU038', 'ALB09',null),
       ('AU041', 'ALB02',null),
       ('AU042', 'ALB02',null),
       ('AU043', 'ALB02',null),
       ('AU044', 'ALB02',null);




INSERT INTO Podcaster (IdPodcaster)
values  ('A0006'),
		('A0007'),
        ('A0008');


insert into Podcast (IdPodcast, NColaboradores, IdPodcaster)
values  ('AU002', 2, 'A0006'),
		('AU039', 1, 'A0007'),
        ('AU040', 3, 'A0008');


INSERT INto Cliente (IdCliente, Nombre, Apellido,Usuario, Contrasena, FechaNacimiento, FechaRegistro, Tipo, IdIdioma)
values ('CL001', 'Ernesto Manuel', 'Valverde', 'mrKristo67', 'elorrieta00', '2004-08-04', current_date(), 'Premium', 'ES'),
	   ('CL002', 'Victoria', 'Rosales', 'Vickyy04','estrellaLuna', '1999-04-04', current_date(), 'Free', 'FR'),
       ('CL003', 'Jhon', 'Izarra', 'johannbachx25', 'piscobamba', '2004-08-04', current_date(), 'Premium', 'EN'),
       ('CL004', 'Keyla', 'Castillo', 'freeGirl', 'iamfree99', '2000-12-12', current_date(), 'Free', 'ES');
       

INSERT into Premium (IdCliente, FechaCaducidad)
values ('CL001', current_date() + interval 1 year),
	   ('CL003', current_date() + interval 6 month);


INSERT INTO Playlist (IdList, Titulo, FechaCreacion, IdCliente)
VALUES 
(1, 'Favoritos Rap', '2024-05-10', 'CL001'),
(2, 'Gym Motivation', '2024-05-12', 'CL001'),
(3, 'Clasicos Rock Espanol', '2024-05-15', 'CL001'),
(4, 'Noche de Studio', '2024-05-20', 'CL001'),

(5, 'Party Dancehall', '2024-05-01', 'CL002'),
(6, 'K-Pop Love', '2024-05-05', 'CL002'),

(9, 'Eminem Selection', '2024-04-20', 'CL003'),
(10, 'The Best of BTS', '2024-04-25', 'CL003'),
(11, 'Viernes de Bachata', '2024-05-01', 'CL003'),
(12, 'Hombres G Legend', '2024-05-18', 'CL003'),

(13, 'Girl Power Urban', '2024-05-02', 'CL004'),
(14, 'Relaxing Beats', '2024-05-10', 'CL004');


INSERT INTO Playlist_Canciones (IdList, IdCancion, FechaPlaylist_cancion)
VALUES 
(1, 'AU001', '2024-05-10'), (1, 'AU005', '2024-05-10'), (1, 'AU009', '2024-05-10'), (1, 'AU010', '2024-05-10'), (1, 'AU011', '2024-05-10'),
(2, 'AU006', '2024-05-12'), (2, 'AU008', '2024-05-12'), (2, 'AU014', '2024-05-12'), (2, 'AU018', '2024-05-12'), (2, 'AU019', '2024-05-12'),
(3, 'AU029', '2024-05-15'), (3, 'AU030', '2024-05-15'), (3, 'AU031', '2024-05-15'), (3, 'AU034', '2024-05-15'), (3, 'AU037', '2024-05-15'),
(4, 'AU012', '2024-05-20'), (4, 'AU015', '2024-05-20'), (4, 'AU017', '2024-05-20'), (4, 'AU023', '2024-05-20'), (4, 'AU028', '2024-05-20'),

(5, 'AU003', '2024-05-01'), (5, 'AU004', '2024-05-01'), (5, 'AU013', '2024-05-01'), (5, 'AU019', '2024-05-01'), (5, 'AU006', '2024-05-01'),
(6, 'AU020', '2024-05-05'), (6, 'AU021', '2024-05-05'), (6, 'AU022', '2024-05-05'), (6, 'AU024', '2024-05-05'), (6, 'AU025', '2024-05-05'),

(9, 'AU009', '2024-04-20'), (9, 'AU010', '2024-04-20'), (9, 'AU011', '2024-04-20'), (9, 'AU012', '2024-04-20'), (9, 'AU013', '2024-04-20'),
(10, 'AU024', '2024-04-25'), (10, 'AU025', '2024-04-25'), (10, 'AU026', '2024-04-25'), (10, 'AU027', '2024-04-25'), (10, 'AU028', '2024-04-25'),
(11, 'AU004', '2024-05-01'), (11, 'AU003', '2024-05-01'), (11, 'AU031', '2024-05-01'), (11, 'AU036', '2024-05-01'), (11, 'AU023', '2024-05-01'),
(12, 'AU032', '2024-05-18'), (12, 'AU033', '2024-05-18'), (12, 'AU034', '2024-05-18'), (12, 'AU035', '2024-05-18'), (12, 'AU038', '2024-05-18'),

(13, 'AU003', '2024-05-02'), (13, 'AU005', '2024-05-02'), (13, 'AU019', '2024-05-02'), (13, 'AU020', '2024-05-02'), (13, 'AU022', '2024-05-02'),
(14, 'AU023', '2024-05-10'), (14, 'AU027', '2024-05-10'), (14, 'AU035', '2024-05-10'), (14, 'AU015', '2024-05-10'), (14, 'AU007', '2024-05-10');


INSERT INTO Gustos(IdCliente, IdAudio)
VALUES ('CL001', 'AU001'),
		('CL001', 'AU003'),
		('CL001', 'AU009'),
		('CL001', 'AU012'),
		('CL001', 'AU018'),
		('CL001', 'AU013'),
		('CL002', 'AU004');       
       /*VISTAS*/
create view vista_ranking_contenido as
select 
    A.Nombre as Titulo,
    A.Tipo,
    A.NReproducciones,
    A.Duracion,
    case 
        when A.Tipo = 'Cancion' then (select Art.NombreArtistico 
                                      from Artista Art 
                                      join Musico M on Art.IdArtista = M.IdMusico
                                      join Album Alb on M.IdMusico = Alb.IdMusico
                                      join Cancion C on Alb.IdAlbum = C.IdAlbum
                                      where C.IdCancion = A.IdAudio)
        when A.Tipo = 'Podcast' then (select Art.NombreArtistico 
                                      from Artista Art 
                                      join Podcaster P on Art.IdArtista = P.IdPodcaster
                                      join Podcast Pod on P.IdPodcaster = Pod.IdPodcaster
                                      where Pod.IdPodcast = A.IdAudio)
    end as Autor
from Audio A
order by A.NReproducciones desc;


create view AlbumesMasEscuchados as
select 
    al.Titulo,
    al.Anno,
    al.Genero,
    ar.NombreArtistico,
    sum(a.NReproducciones) as TotalReproducciones
from Album al
join Cancion c on al.IdAlbum= c.IdAlbum
join Audio a on c.IdCancion= a.IdAudio
join Musico m on al.IdMusico= m.IdMusico
join Artista ar on m.IdMusico= ar.IdArtista
group by al.Titulo, al.Anno, al.Genero, ar.NombreArtistico
order by TotalReproducciones desc;

create view ArtistasMasEscuchados as
select 
    ar.NombreArtistico,
    m.Caracteristica,
    sum(a.NReproducciones) as TotalReproducciones
from Artista ar
join Musico m on ar.IdArtista = m.IdMusico
join Album al on m.IdMusico = al.IdMusico
join Cancion c on al.IdAlbum = c.IdAlbum
join Audio a on c.IdCancion = a.IdAudio
group by ar.NombreArtistico, m.Caracteristica
order by TotalReproducciones desc;