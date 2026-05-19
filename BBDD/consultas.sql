use reto3spotify;

/*perfil y configuración del usuario*/
SELECT c.IdCliente, c.Nombre, c.Apellido, c.Usuario, c.Tipo, i.Descripcion AS Idioma
FROM Cliente c
JOIN Idioma i ON c.IdIdioma = i.IdIdioma
WHERE c.Usuario = 'mrKristo67';

/*explorar música por álbum*/
SELECT a.IdAudio, a.Nombre AS Cancion, a.Duracion, a.Archivo, al.Titulo AS Album, art.NombreArtistico
FROM Audio a
JOIN Cancion c ON a.IdAudio = c.IdCancion
JOIN Album al ON c.IdAlbum = al.IdAlbum
JOIN Artista art ON al.IdMusico = art.IdArtista
WHERE al.IdAlbum = 'ALB01'
ORDER BY a.Nombre;

/*canciones favoritas de un usuario*/
SELECT a.IdAudio, a.Nombre AS Titulo, a.Tipo, a.Duracion
FROM Gustos g
JOIN Audio a ON g.IdAudio = a.IdAudio
WHERE g.IdCliente = 'CL001';

/*información de una playlist*/
SELECT a.IdAudio, a.Nombre AS Cancion, a.Duracion, al.Titulo AS Album, art.NombreArtistico
FROM Playlist_Canciones pc
JOIN Cancion c ON pc.IdCancion = c.IdCancion
JOIN Audio a ON c.IdCancion = a.IdAudio
JOIN Album al ON c.IdAlbum = al.IdAlbum
JOIN Artista art ON al.IdMusico = art.IdArtista
WHERE pc.IdList = 1
ORDER BY pc.FechaPlayList_cancion DESC;

/*para gestionar playlist*/
SELECT p.IdList, p.Titulo, p.FechaCreacion, COUNT(pc.IdCancion) AS TotalCanciones
FROM Playlist p
LEFT JOIN Playlist_Canciones pc ON p.IdList = pc.IdList
WHERE p.IdCliente = 'CL001'
GROUP BY p.IdList, p.Titulo, p.FechaCreacion;

/*información básica de un álbum por su título*/
SELECT a.IdAlbum, a.Anno, a.Imagen, 
       (SELECT COUNT(*) FROM Cancion WHERE IdAlbum = a.IdAlbum) as TotalCanciones 
FROM Album a 
WHERE a.Titulo = 'Wings';

/*lista de canciones de un álbum*/
SELECT au.Nombre, au.Duracion 
FROM Audio au 
JOIN Cancion c ON au.IdAudio = c.IdCancion 
WHERE c.IdAlbum = 'ALB01';

/*saber a que álbum pertenece una canción*/
SELECT A.Titulo 
FROM Album A 
JOIN Cancion C ON A.IdAlbum = C.IdAlbum 
JOIN Audio AU ON C.IdCancion = AU.IdAudio 
WHERE AU.Nombre = 'Stan';

/*canciones dentro de una playlist por artistas*/
SELECT 
    A.Nombre, 
    AR.NombreArtistico, 
    A.NReproducciones, 
    A.Duracion 
FROM Playlist P   
JOIN Playlist_Canciones PC ON P.IdList = PC.IdList 
JOIN Audio A ON PC.IdCancion = A.IdAudio 
JOIN Cancion C ON A.IdAudio = C.IdCancion 
JOIN Album AL ON C.IdAlbum = AL.IdAlbum 
JOIN Musico M ON AL.IdMusico = M.IdMusico   
JOIN Artista AR ON M.IdMusico = AR.IdArtista  
WHERE P.IdList = 1;

/*información completa de una canción*/
SELECT A.Nombre, AR.NombreArtistico, A.Duracion, A.NReproducciones, AL.Titulo 
FROM Audio A 
JOIN Cancion C ON A.IdAudio = C.IdCancion 
JOIN Album AL ON C.IdAlbum = AL.IdAlbum 
JOIN Artista AR ON AL.IdMusico = AR.IdArtista 
WHERE A.Nombre = 'Fiebre';

/*todos los albumes de un músico*/
SELECT a.Titulo, YEAR(a.Anno) as Anno, 
       (SELECT COUNT(*) FROM Cancion c WHERE c.IdAlbum = a.IdAlbum) as NumCanciones 
FROM Album a 
WHERE a.IdMusico = 'A0001';





