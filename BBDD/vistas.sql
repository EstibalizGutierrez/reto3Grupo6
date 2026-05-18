create view AlbumesMasEscuchados as
select 
    al.IdAlbum,
    al.Titulo,
    ar.NombreArtistico,
    sum(a.NReproducciones) as TotalReproducciones
from Album al
join Cancion c on al.IdAlbum= c.IdAlbum
join Audio a on c.IdCancion= a.IdAudio
join Musico m on al.IdMusico= m.IdMusico
join Artista ar on m.IdMusico= ar.IdArtista
group by al.IdAlbum, al.Titulo, ar.NombreArtistico
order by TotalReproducciones desc;

create view ArtistasMasEscuchados as
select 
    ar.IdArtista,
    ar.NombreArtistico,
    sum(a.NReproducciones) as TotalReproducciones
from Artista ar
join Musico m on ar.IdArtista = m.IdMusico
join Album al on m.IdMusico = al.IdMusico
join Cancion c on al.IdAlbum = c.IdAlbum
join Audio a on c.IdCancion = a.IdAudio
group by ar.IdArtista, ar.NombreArtistico
order by TotalReproducciones desc;

create view PodcastersMasEscuchados as
select 
    ar.IdArtista,
    ar.NombreArtistico,
    sum(a.NReproducciones) as TotalReproducciones
from Artista ar
join Podcaster p on ar.IdArtista = p.IdPodcaster
join Podcast po on p.IdPodcaster = po.IdPodcaster
join Audio a on po.IdPodcast = a.IdAudio
group by ar.IdArtista, ar.NombreArtistico
order by TotalReproducciones desc;














