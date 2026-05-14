<<<<<<< HEAD
create view vista_ranking_contenido as
select 
    A.Nombre as Titulo,
    A.Tipo,
    A.NReproducciones,
    case 
        when A.Tipo = 'Canción' then (select Art.NombreArtistico 
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
/*albumes mas escuchados*/
/*artistas*/
/*podcasters*/
=======
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
>>>>>>> 80e118177dbdb15824a6f69003ddf2c493e1fdb7














