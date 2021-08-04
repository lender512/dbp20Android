# Desarrollo Basado en Plataformas Sección 2.01 - Proyecto 1

## Nombre del proyecto: Party On

## Integrantes

| <a target="_blank">**Luis Berrospi**</a> | <a target="_blank">**Mauricio Nieto**</a> | <a target="_blank">**Julio Sarazu**</a> |<a target="_blank">**Adrián Boza**</a> |
| :---: | :---:| :---:| :---:|
| ![Luis](https://avatars2.githubusercontent.com/u/52045791?v=3&s=150) | ![Mauricio](https://avatars.githubusercontent.com/u/63524901?v=4) | ![Julio](https://avatars.githubusercontent.com/u/40171658?s=64&v=4) | ![Adrian](https://avatars.githubusercontent.com/u/40300535?v=4) |
| <a href="https://github.com/lender512" target="_blank">`github.com/lender512`</a> | <a href="https://github.com/Elmau1618" target="_blank">`github.com/Elmau1618`</a> | <a href="https://github.com/kalehtfree123" target="_blank">`github.com/kalehtfree123`</a> |<a href="https://github.com/adrianboza" target="_blank">`github.com/adrianboza`</a> |


## Descripción del proyecto
En la entrega final desarrollamos por completo la aplicación que permite a las personas poder añadir, loguearse y registrarse a travez de esta misma.

## Objetivos principales / Misión / Visión
### Misión:
- Nuestra misión es convertir las invitaciones a fiestas en algo rápido y sencillo, que sea escalable y de fácil uso.

### Visión:
- Construir el megáfono por el que la gente pueda conseguir invitados para sus grandes reuniones y dar las herramientas para facilitar la construcción de las mismas.

### Objetivos principales:
- Tener un login y sign in que permita a los usuarios tener una cuenta personal.
- - Dar al usuario la posibilidad de agregar direcciones a través de la aplicación movil.

## Información acerca de las tecnologías utilizadas en Front-end, Back-end y Base de datos
### Front-end:
XML
#### - activity_main.xml:  
Es el contenedor predeterminado de vista al login.
#### - activity_registro.xml:  
Contenedor de formulario de registro.
#### - activity_home.xml:  
Contenedor de la vista principal, donde se crean posts y direcciones.
### Back-end:
- flask: Framework que permite crear el servidor.
- flask.helpers: Módulo de flask que permite pasar un string message al request y generar una URL usando el método pasado.
- flask_login: Proporciona gestión de sesiones de usuario para Flask.
- passlib.hash: Algoritmo de encriptación de contraseñas.
- os: Permite interactuar con el Sistema Operativo.
- json: Crear listas de diccionarios.
- flask_login.utils: Hacer necesario el login para acceder al main.
- sqlalchemy.orm: Acceder a una base de datos relacional.
- models: Permite importar los modelos creados para la base de datos.
- werkzeug.utils: Permite redirigir a otra página.
- flask_migrate: Permite manejar las migraciones de la base de datos.
- operator: Exportar funciones. operator.add(x, y) = x+y
- volley: Biblioteca Http que facilita la conexión entre la aplicación y el servidor.

#### - models.py:  
Permite crear las tablas persons, apartments, posts y likes.  
#### - app.py:
Permite implementar el back-end usando flask y más herramientas, además de tomar models.py para el uso de los modelos y lograr que la página web funcione correctamente junto a la base de datos.
#### - test.py:
Permite realizar el unit testing
### Base de datos:
Postgresql alojado en Heroku.

## Cómo ejecutar el sistema (Deployment scripts)  
La primera vez entrar a file - setting - system settings - http proxy - seleccionar Manual Proxy configuration - seleccionar HTTP - colocar 10.0.2.2 en Hostname - puerto 8080


