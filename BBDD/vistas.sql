/*vista medio hecha*/
create view TopMejoresCanciones as select idcancion from cancion where count(nreproducciones);