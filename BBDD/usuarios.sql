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

