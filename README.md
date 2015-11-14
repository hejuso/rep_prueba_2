# Tutorial de instalación de virtual hosts en apache2

Bien, partiendo desde que tenemos vagrant, virtual box y una box con un entorno LAMP instalado , vamos a explicar brevemente
que pasos deberíamos realizar a la hora de crear dos hosts virtuales.

## 1- Copia de los archivos .php

* Entramos en vagrant.
* Nos dirigimos al directorio `/vagrant/shared`, que es donde tenemos los archivos compartidos con vagrant.
* Introducimos el siguiente comando: `cp * /var/www -R`.

## 2- Configuración de los archivos de apache2

Ahora mismo, si así lo quisiéramos, podríamos acceder a través de localhost:8080, pero nosotros lo que queremos es poder introducir la URL en nuestro navegador y que nos dirija a donde deseemos. Para eso, deberemos hacer los siguiente:

* Nos situamos en el directorio `/etc/apache2/sites-available`

Una vez dentro del directorio, creamos dos archivos en los que configuraremos los hosts virtuales.

* Primero crearemos y configuraremos el archivo del blog: `sudo nano blog.despliegue.com`
* Una vez creado le pasaremos los siguientes parámetros:

```
<VirtualHost *:80>
    ServerAdmin webmaster@localhost
    ServerName blog.despliegue.com
    ServerAlias blog.despliegue.com
    DocumentRoot "/var/www/blog.despliegue.com"

        <Directory "/var/www/blog.despliegue.com/">
                Options All
                AllowOverride All
                Allow from all
        </Directory>
    ErrorLog ${APACHE_LOG_DIR}/error.log
    CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>

```

* Ahora configuraremos el otro archivo de la hora: `sudo nano quehoraes.despliegue.com`
* Una vez creado le pasaremos los siguientes parámetros:

```
<VirtualHost *:80>
    ServerAdmin webmaster@localhost
    ServerName quehoraes.despliegue.com
    ServerAlias quehoraes.despliegue.com
    DocumentRoot "/var/www/quehoraes.despliegue.com"

        <Directory "/var/www/quehoraes.despliegue.com/">
                Options All
                AllowOverride All
        </Directory>

    ErrorLog ${APACHE_LOG_DIR}/error.log
    CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>
```

* Ahora, activaremos los archivos que acabamos de crear con el comando:
`sudo a2ensite quehoraes.despliegue.com`
`sudo a2ensite blog.despliegue.com`

* Acto seguido reiniciaremos el servicio apache2: `sudo service apache2 restart`

## Configuración de el archivo hosts.

Finalmente tendremos que configurar el archivo hosts de la máquina local para poder acceder desde el navegador introduciendo la url: 

**Windows**

* El arhivo hosts en windows se situa en:  "C:\WINDOWS\system32\drivers\etc\hosts"
* Para poder editarlo, debemos abrir el Bloc de Notas en modo administrador.
* Al archivo tendremos que añadirle lo siguiente:


```
# Copyright (c) 1993-1999 Microsoft Corp. 
# 
# Éste es un ejemplo de archivo HOSTS usado por Microsoft TCP/IP para Windows. 
# Este archivo contiene las asignaciones de las direcciones IP a los nombres de 
# host. Cada entrada debe permanecer en una línea individual. La dirección IP 
# debe ponerse en la primera columna, seguida del nombre de host correspondiente. 
# La dirección IP y el nombre de host deben separarse con al menos un espacio. 
# 
# También pueden insertarse comentarios (como éste) en líneas individuales 
# o a continuación del nombre de equipo indicándolos con el símbolo "#" 
# 
# Por ejemplo: 
# 
# 102.54.94.97 rhino.acme.com # servidor origen 
# 38.25.63.10 x.acme.com # host cliente x 

127.0.0.1	localhost 
127.0.0.1	blog.despliegue.com
127.0.0.1	quehoraes.despliegue.com

```

* El arhivo hosts en linux se situa en:  "/etc/hosts"
* `sudo nano /etc/hosts`
* Al archivo tendremos que añadirle lo siguiente:

```
127.0.0.1	localhost
127.0.1.1	hejuso-K73TK
127.0.0.1	blog.despliegue.com
127.0.0.1	quehoraes.despliegue.com

# The following lines are desirable for IPv6 capable hosts
::1     ip6-localhost ip6-loopback
fe00::0 ip6-localnet
ff00::0 ip6-mcastprefix
ff02::1 ip6-allnodes
ff02::2 ip6-allrouters

```
* Así deberíamos de poder acceder a las aplicaciones poniendo en la barra de direcciones `blog.despliegue.com:8080` o `quehoraes.despliegue.com:8080`
