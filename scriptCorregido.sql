create database reto3BD COLLATE utf8mb4_spanish_ci;
USE reto3Corregido;

/* --------------TABLA DE IDIOMA-------------- */
/* ta bn*/
DROP TABLE if exists idioma;
create table idioma (
IdIdioma enum('ES','EU','EN','FR','DE','CA','GA','AR') primary key,
Descripcion varchar (20) not null
);

/* --------------TABLA DE ARTISTA-------------- */
/* TA BN */
DROP TABLE if exists artista;
create table artista (
IdArtista char(5) primary key,
NombreArtistico varchar(30) not null unique,
Genero varchar(30),
Imagen varchar(400),
Descripcion text not null
);

/* --------------TABLA DE AUDIO-------------- */
/* TA BN */
DROP TABLE if exists audio;
create table audio(
IdAudio char(5) primary key,
Nombre varchar(50) not null unique,
Duracion int not null,
Archivo varchar(400) not null,
Tipo enum('podcast','cancion') not null,
NumReproducciones int unsigned not null default 0
);

/* --------------TABLA DE MUSICO-------------- */
/* TA BN */
DROP TABLE if exists musico;
create table musico (
IdMusico char(5) primary key,
Caracteristica enum('solista', 'grupo') not null,
constraint fk_IdMusico_musico foreign key (IdMusico) 
			references artista(IdArtista) on update cascade on delete cascade
);


/* --------------TABLA DE PODCASTER-------------- */
/* TA BN */
DROP TABLE if exists podcaster;
create table podcaster(
IdPodcaster char(5) primary key,
constraint fk_IdPodcaster_podcaster foreign key (IdPodcaster) 
			references artista(IdArtista) on update cascade on delete cascade
);

/* --------------TABLA DE PODCAST-------------- */

DROP TABLE if exists podcast;
create table podcast(
IdPodcast char(5) primary key,
NumColaboradores int unsigned,
IdPodcaster char(5) not null,
constraint fk_IdPodcast_podcast foreign key (IdPodcast)
			references audio(IdAudio) on update cascade on delete cascade,
constraint fk_IdPodcaster_podcast foreign key (IdPodcaster)
			references podcaster (IdPodcaster) on update cascade on delete cascade
);

/* --------------TABLA DE CLIENTE-------------- */
DROP TABLE if exists cliente;
create table cliente (
IdCliente char(5) primary key,
Nombre varchar(30) not null,
Apellido varchar(30) not null,
Usuario varchar(30) not null unique,
Contrasena varchar(40) not null,
FechaNacimiento date not null,
FechaRegistro date not null, 
Tipo enum('free','premium') not null,
IdIdioma enum('ES','EU','EN','FR','DE','CA','GA','AR') not null,
constraint fk_IdIdioma_cliente foreign key (IdIdioma) 
			references idioma(IdIdioma) 
);

/* --------------TABLA DE ALBUM-------------- */
DROP TABLE if exists album;
create table album (
IdAlbum char(5) primary key,
Titulo varchar(30) not null,
Anno date not null,
Genero varchar(30) not null,
Imagen varchar(400),
IdMusico char(5) not null,
constraint fk_IdMusico_album foreign key (IdMusico)
			references musico(IdMusico) on update cascade on delete cascade
);

/* --------------TABLA DE CANCION-------------- */
DROP TABLE if exists cancion;
create table cancion (
IdCancion char(5) primary key,
IdAlbum char(5) not null,
ArtistasInvitados text,
constraint fk_IdCancion_cancion foreign key(IdCancion)
			references audio(IdAudio) on update cascade on delete cascade,
constraint fk_IdAlbum_cancion foreign key(IdAlbum)
			references album(IdAlbum) on update cascade on delete cascade
);

/* --------------TABLA DE PLAYLIST-------------- */
DROP TABLE if exists playlist;
create table playlist (
IdPlaylist int unsigned primary key,
Titulo varchar(50) not null,
FechaCreacion date not null,
IdCliente char(5) not null,
constraint fk_IdCliente_playlist foreign key (IdCliente)
			references cliente(IdCliente) on update cascade on delete cascade
);

/* --------------TABLA DE PLAYLIST-CANCIONES-------------- */
DROP TABLE if exists playlistCanciones;
create table playlistCanciones (
IdPlaylist int unsigned,
IdCancion char(5),
FechaPlaylistCancion date not null,
primary key (IdPlaylist, IdCancion), 
constraint fk_IdPlaylist_playlistCanciones foreign key (IdPlaylist)
			references playlist(IdPlaylist) on update cascade on delete cascade,
constraint fk_IdCancion_playlistCanciones foreign key (IdCancion)
			references cancion(IdCancion) on update cascade on delete cascade
);

/* --------------TABLA DE PREMIUM-------------- */
drop table if exists premium;
create table premium(
IdCliente char(5) primary key,
FechaCaducidad date not null,
constraint fk_IdCliente_premium foreign key (IdCliente)
			references cliente(IdCliente) on update cascade on delete cascade
);


/* --------------TABLA DE GUSTOS-------------- */
DROP TABLE if exists gustos;
create table gustos(
IdCliente char(5),
IdAudio char(5),
primary key (IdCliente, IdAudio),
constraint fk_IdCliente_gustos foreign key (IdCliente)
			references cliente (IdCliente) on update cascade on delete cascade,
constraint fk_IdAudio_gustos foreign key (IdAudio)
			references audio (IdAudio) on update cascade on delete cascade
);