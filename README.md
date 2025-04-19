# Authorization Server
Es una aplicación que ayuda a gestionar la autorización que necesitan 
los usuarios para poder realizar operaciones en algún cliente que requiera permisos.
## Script de instalación
~~~
INSERT INTO roles(role) VALUES ('ROLE_ADMIN');
~~~

~~~
INSERT INTO roles(role) VALUES ('ROLE_USER');
~~~



## Variables de entorno

~~~
DB_HOST= jdbc:postgresql://localhost:5432/db_name
~~~
~~~
DB_USER= ******
~~~
~~~
DB_PASSWORD= ******
~~~
~~~
FRONT_URL= http://localhost:4200
~~~
~~~
FRONT_LOGOUT_URL= http://localhost:4200/logout
~~~
~~~
GOOGLE_CLIENT_ID=******* 
~~~
~~~
GOOGLE_SECRET=*******
~~~
~~~
GOOGLE_CLIENT_NAME=*****
~~~
>Nota: Se necesitará crear un client en [Google Cloud Plataform (GCP)](https://cloud.google.com/endpoints/docs/frameworks/java/creating-client-ids?hl=es-419#web-client) para obtener los valores de las variables de entorno [GOOGLE_CLIENT_ID,GOOGLE_SECRET,GOOGLE_CLIENT_NAME].
## Tabla de recursos
| NOMBRE          | RUTA | PETICION | PARAMETROS | CUERPO                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | 
|-----------------|----------|----------|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Guardar cliente | /client/create   | POST     | NINGUNO    | <br>{<br>  "clientId": "angular-cli",<br>  "clientSecret": "secret",<br>  "clientName": "angular-cli",<br>  "authenticationMethods": [ "client_secret_basic" ],<br>  "grantTypes": [ "authorization_code", "refresh_token", "client_credentials" ],<br>  "redirectUris": [ "http://localhost:4200", "http://127.0.0.1:4200" ],<br>  "scopes": [ "openid", "profile" ],<br>  "requireProofKey": true,<br>  "postLogoutRedirectUris": [ "http://localhost:4200/logout", "http://127.0.0.1:4200/logout" ]<br>} |
| Obtener token   | /oauth2/token   | POST     | NINGUNO    | `curl --location 'http://localhost:9000/oauth2/token' --header 'Authorization: ••••••' --form 'code="************"' --form 'grant_type="authorization_code"' --form 'redirect_uri="https://oauthdebugger.com/debug"' --form 'client_id="********"' --form 'code_verifier="*************"'`                                                                                                                                                                                                                  |
| Crear usuario   | /auth/create   | POST     | NINGUNO    | <br>{<br> "username":"admin", <br>"password":"*******", <br>"roles": ["ROLE_ADMIN","ROLE_USER"] <br>}<br>                                                                                                                                                                                                                                                                                                                                                                                                   |
|Inicio de sesion | /login | GET      |NINGUNO| NINGUNO                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |

## Stack
* SPRING BOOT
* SPRING AUTHORIZATION SERVER
* SPRING AUTHORIZATION CLIENT
* SPRING DATA
* POSTGRESQL
* LOMBOK
* THYMELEAF
* GRADLE
* TAILWINDCSS
* DOCKER

## Referencias
* [Spring Authorizacion Server](https://docs.spring.io/spring-authorization-server/reference/getting-started.html)
* [Google Cloud Plataform](https://cloud.google.com/endpoints/docs/frameworks/java/creating-client-ids?hl=es-419#web-client)
* [Tailwindcss](https://tailwindcss.com/)
* [Changelog](https://github.com/fsialer/auth-server/blob/main/CHANGELOG.md)