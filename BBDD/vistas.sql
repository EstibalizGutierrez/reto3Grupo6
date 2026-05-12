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














