/*todavia no ejecutar*/
use reto3spotify;
create user DBA_Administrador_IT identified by '1234';
grant all privileges on *.* to DBA_Administrador_IT
with grant option;

SET DEFAULT ROLE 'RolEmpleados' to 'empleados'@'%';

create user Empleados identified by '4321';
alter user empleados default role 'RolEmpleados';
create role RolEmpleados;
drop role RolEmpleados;
grant select on reto3spotify.cliente to 'RolEmpleados';
grant select on reto3spotify.premium to 'RolEmpleados';
grant select on reto3spotify.gustos to 'RolEmpleados';
grant select on reto3spotify.idioma to 'RolEmpleados';
show grants for 'RolEmpleados';

grant insert, update, delete on reto3spotify.podcast to 'RolEmpleados';
grant insert, update, delete on reto3spotify.audio to 'RolEmpleados';
grant insert, update, delete on reto3spotify.canción to 'RolEmpleados';
grant insert, update, delete on reto3spotify.album to 'RolEmpleados';
grant insert, update, delete on reto3spotify.artista to 'RolEmpleados';
grant insert, update, delete on reto3spotify.músico to 'RolEmpleados';
grant insert, update, delete on reto3spotify.podcaster to 'RolEmpleados';

create user ClienteFree identified by '0000' default role RolClientesFree;
create role RolClientesFree;

grant select on reto3spotify.audio to ‘RolClientes’;
grant select on reto3spotify.canción to ‘RolClientes’;
grant select on reto3spotify.podcast to ‘RolClientes’;
grant select on reto3spotify.album to ‘RolClientes’;
grant select on reto3spotify.artista to ‘RolClientes’;
grant select on reto3spotify.músico to ‘RolClientes’;
grant select on reto3spotify.podcaster to ‘RolClientes’;
grant select on reto3spotify.idioma to ‘RolClientes’;

grant insert, update, delete on reto3spotify.playlist to 'RolClientes';
grant insert, update, delete on reto3spotify.playlist_canciones to 'RolClientes';
grant insert, update, delete on reto3spotify.gustos to 'RolClientes';
/*depende de la tabla cliente, no se sabe si darle la opcion de update*/
grant insert, update, delete on reto3spotify.cliente to 'RolClientes';

create user ClientePremium identified by '1111' default role RolClientes;