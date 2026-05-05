/*vista medio hecha*/
CREATE VIEW vista_ranking_contenido AS
SELECT 
    A.IdAudio,
    A.Nombre AS Titulo,
    A.Tipo,
    A.NReproducciones,
    CASE 
        WHEN A.Tipo = 'Canción' THEN (SELECT Art.NombreArtistico 
                                      FROM Artista Art 
                                      JOIN Musico M ON Art.IdArtista = M.IdMusico
                                      JOIN Album Alb ON M.IdMusico = Alb.IdMusico
                                      JOIN Cancion C ON Alb.IdAlbum = C.IdAlbum
                                      WHERE C.IdCancion = A.IdAudio)
        WHEN A.Tipo = 'Podcast' THEN (SELECT Art.NombreArtistico 
                                      FROM Artista Art 
                                      JOIN Podcaster P ON Art.IdArtista = P.IdPodcaster
                                      JOIN Podcast Pod ON P.IdPodcaster = Pod.IdPodcaster
                                      WHERE Pod.IdPodcast = A.IdAudio)
    END AS Autor
FROM Audio A
ORDER BY A.NReproducciones DESC;
















