/*todavia no ejecutar*/
create user DBA_Administrador_IT identified by '1234';
grant all privileges on *.* to DBA_Administrador_IT
with grant option;

create user Empleados identified by '4321' default role RolEmpleados;

create role RolEmpleados;
grant select on spotify.cliente to 'RolEmpleados';
grant select on spotify.premium to 'RolEmpleados';
grant select on spotify.gustos to 'RolEmpleados';
grant select on spotify.idioma to 'RolEmpleados';

grant insert, update, delete on spotify.podcast to 'RolEmpleados';
grant insert, update, delete on spotify.audio to 'RolEmpleados';
grant insert, update, delete on spotify.canción to 'RolEmpleados';
grant insert, update, delete on spotify.album to 'RolEmpleados';
grant insert, update, delete on spotify.artista to 'RolEmpleados';
grant insert, update, delete on spotify.músico to 'RolEmpleados';
grant insert, update, delete on spotify.podcaster to 'RolEmpleados';

create user Cliente identified by '0000' default role RolClientes;
create role RolClientes;

grant select on spotify.audio to ‘RolClientes’;
grant select on spotify.canción to ‘RolClientes’;
grant select on spotify.podcast to ‘RolClientes’;
grant select on spotify.album to ‘RolClientes’;
grant select on spotify.artista to ‘RolClientes’;
grant select on spotify.músico to ‘RolClientes’;
grant select on spotify.podcaster to ‘RolClientes’;
grant select on spotify.idioma to ‘RolClientes’;

grant insert, update, delete on spotify.playlist to 'RolClientes';
grant insert, update, delete on spotify.playlist_canciones to 'RolClientes';
grant insert, update, delete on spotify.gustos to 'RolClientes';
grant insert, update, delete on spotify.cliente to 'RolClientes';
