use reto3spotify;
create user DBA_Administrador_IT identified by '1234';
grant all privileges on *.* to DBA_Administrador_IT
with grant option;

-- primero este
create user Empleados identified by '4321';
-- luego este
alter user 'Empleados'@'%' default role 'RolEmpleados';

-- si no funciona ejecutar este
GRANT 'RolEmpleados' TO 'Empleados'@'%';

create role RolEmpleados;
drop role RolEmpleados;
grant select on reto3spotify.cliente to 'RolEmpleados';
grant select on reto3spotify.premium to 'RolEmpleados';
grant select on reto3spotify.gustos to 'RolEmpleados';
grant select on reto3spotify.idioma to 'RolEmpleados';
show grants for 'RolEmpleados';

grant insert, update, delete on reto3spotify.podcast to 'RolEmpleados';
grant insert, update, delete on reto3spotify.audio to 'RolEmpleados';
grant insert, update, delete on reto3spotify.cancion to 'RolEmpleados';
grant insert, update, delete on reto3spotify.album to 'RolEmpleados';
grant insert, update, delete on reto3spotify.artista to 'RolEmpleados';
grant insert, update, delete on reto3spotify.musico to 'RolEmpleados';
grant insert, update, delete on reto3spotify.podcaster to 'RolEmpleados';

create role RolClientesFree;
create user ClienteFree identified by '0000' default role RolClientesFree;

grant select on reto3spotify.audio to 'RolClientesFree';
grant select on reto3spotify.cancion to 'RolClientesFree';
grant select on reto3spotify.podcast to 'RolClientesFree';
grant select on reto3spotify.album to 'RolClientesFree';
grant select on reto3spotify.artista to 'RolClientesFree';
grant select on reto3spotify.musico to 'RolClientesFree';
grant select on reto3spotify.podcaster to 'RolClientesFree';
grant select on reto3spotify.idioma to 'RolClientesFree';

grant insert, update, delete on reto3spotify.playlist to 'RolClientesFree';
grant insert, update, delete on reto3spotify.playlist_canciones to 'RolClientesFree';
grant insert, update, delete on reto3spotify.gustos to 'RolClientesFree';
/*depende de la tabla cliente, no se sabe si darle la opcion de update*/
grant insert, update, delete on reto3spotify.cliente to 'RolClientesFree';

create user ClientePremium identified by '1111' default role RolClientesPremium;



