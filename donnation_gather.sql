create schema if not exists donacion;

create table if not exists donacion.punto_entrega(
	codigo_punto_entrega numeric(6) not null primary key,
	nombre_punto_entrega varchar(200) not null,
	direccion_punto_entrega varchar(500),
	url_direccion_punto_entrega text,
	responsable_punto_entrega varchar(500) not null,
	estado varchar(1) not null default 'A',
	fecha_registro timestamp not null default current_timestamp(6),
	usuario_registro varchar(50) not null default user,
	fecha_actualizacion timestamp not null default current_timestamp(6),
	usuario_actualizacion varchar(50) not null default user
);
insert into donacion.punto_entrega(codigo_punto_entrega, nombre_punto_entrega, direccion_punto_entrega, url_direccion_punto_entrega, responsable_punto_entrega)
	values(1, 'Norte', 'Parque Bicentenario', 'https://g.co/kgs/uvWMAV', 'Responsable Norte');
insert into donacion.punto_entrega(codigo_punto_entrega, nombre_punto_entrega, direccion_punto_entrega, url_direccion_punto_entrega, responsable_punto_entrega)
	values(2, 'Centro', 'Casa de las Bandas', 'https://maps.app.goo.gl/owEQfDcDj6f2T4bu7', 'Responsable Centro');
insert into donacion.punto_entrega(codigo_punto_entrega, nombre_punto_entrega, direccion_punto_entrega, url_direccion_punto_entrega, responsable_punto_entrega)
	values(3, 'Sur', 'Administraci&oacute;n Zonal Eloy Alfaro', 'https://maps.goo.gl/35ympwSESyDZnXzw7', 'Responsable Sur');

create table if not exists donacion.evento(
	codigo_evento numeric(6) not null primary key,
	nombre_evento varchar(200) not null,
	direccion_evento varchar(500),
	url_direccion_evento text,
	fecha_evento date not null,
	cantidad_tickets_evento numeric(10),
	estado varchar(1) not null default 'A',
	fecha_registro timestamp not null default current_timestamp(6),
	usuario_registro varchar(50) not null default user,
	fecha_actualizacion timestamp not null default current_timestamp(6),
	usuario_actualizacion varchar(50) not null default user
);
insert into donacion.evento(codigo_evento, nombre_evento, direccion_evento, fecha_evento, cantidad_tickets_evento) values(1, 'Chicha con Corbat&iacute;n', 'Coliseo Rumi&ntilde;ahui', to_date('04122023','DDMMYYYY'),12000);
insert into donacion.evento(codigo_evento, nombre_evento, direccion_evento, fecha_evento, cantidad_tickets_evento) values(2, 'Festival del Pasacalle', 'Coliseo Rumi&ntilde;ahui', to_date('05122023','DDMMYYYY'), 12000);
insert into donacion.evento(codigo_evento, nombre_evento, direccion_evento, fecha_evento, cantidad_tickets_evento) values(3, 'Festival de las Orquestas', 'Estadio del Aucas', to_date('03122023','DDMMYYYY'), 7000);
--insert into(codigo_evento, nombre_evento, direccion_evento, fecha_evento, cantidad_tickets_evento) values(4, '', to_date('','DDMMYYYY'),);

create sequence donacion.seq_donacion_personal increment 1 start 1;
create table if not exists donacion.donacion_personal(
	codigo_donacion_personal numeric(10) not null primary key default nextval('donacion.seq_donacion_personal'),
	nombre_donador varchar(500) not null,
	cedula_donador varchar(30) not null,
	codigo_evento numeric(6) not null,
	codigo_punto_entrega numeric(6) not null,
	fecha_entrega timestamp not null default current_timestamp(6),
	numero_boleto varchar(500) not null,
	estado varchar(1) not null default 'A',
	fecha_registro timestamp not null default current_timestamp(6),
	usuario_registro varchar(50) not null default user,
	fecha_actualizacion timestamp not null default current_timestamp(6),
	usuario_actualizacion varchar(50) not null default user
);
alter table donacion.donacion_personal add constraint fk_donacion_personal_evento
	foreign key (codigo_evento) references donacion.evento(codigo_evento);
alter table donacion.donacion_personal add constraint fk_donacion_personal_punto_entrega
	foreign key (codigo_punto_entrega) references donacion.punto_entrega(codigo_punto_entrega);

create sequence donacion.seq_item_donacion increment 1 start 1;
create table if not exists donacion.item_donacion(
	codigo_item_donacion numeric(10) not null primary key default nextval('donacion.seq_item_donacion'),
	codigo_donacion_personal numeric(10) not null,
	descripcion_item_donacion text,
	cantidad_item_donacion numeric(10) not null,
	estado varchar(1) not null default 'A',
	fecha_registro timestamp not null default current_timestamp(6),
	usuario_registro varchar(50) not null default user,
	fecha_actualizacion timestamp not null default current_timestamp(6),
	usuario_actualizacion varchar(50) not null default user
);
alter table donacion.item_donacion add constraint fk_item_donacion_personal
	foreign key (codigo_donacion_personal) references donacion.donacion_personal(codigo_donacion_personal);