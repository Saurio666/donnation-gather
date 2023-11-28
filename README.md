# donnation-gather
Para el front end, en una consola de powrshell ubicarse en la carpeta /donnation-gather-fe
ejecutar el comando:
	ng serve
En un navegador ingresar a http://localhost:4200/donacion


Para el backend, en una consola cmd ubicarse en la carpeta /donnation-gather-be
ejecutar los comandos
	set JAVA_HOME="C:\Java\jdk1.8.0_202"
	mvnw spring-boot:run


*Notas
JAVA_HOME debe ser la ruta donde esta instalado el jdk (version 8 de preferencia)
Asegurarse de tener el puerto 8290 libre

CAmbiar la configuracion del archivo application.properties para ajustar a los valores de su base de datos
	ruta --> /donnationgatherbe/src/main/resources/application.properties

Si al ejecutar cualquier comando de consola se tiene un error de este estilo
    + CategoryInfo          : SecurityError: (:) [], PSSecurityException
    + FullyQualifiedErrorId : UnauthorizedAccess


Ejecutar el comanto 
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
	Cuando pregunte la opcion seleccionar [A] Yes to all
	
	

-------------------------
Para la base de datos se debe crear una base con nombre donnation_gather, se puede usar el siguiente comando
	CREATE DATABASE donnation_gather;
	
Luego ejecutar el script donnation_gather.sql