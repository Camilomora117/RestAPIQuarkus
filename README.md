# TAREA DE MICROSERVICIOS

## Descripción
Diseñe un API y cree un monolito  Quarkus que permita a los usuarios hacer posts de 140 caracteres 
e ir registrandolos en un stream único de posts (a la Twitter). Piense en tres entidades 
Usuario, hilo(stream), posts

## Software necesarias

- Java
- Docker
- maven - Gestor de dependencias
- Git - Sistema de control de versiones

## Arquitectura

1. Diseñe un API y cree un monolito  Quarkus que permita a los usuarios hacer posts de 140 caracteres e ir registrandolos en un stream único de posts (a la Twitter)
2. Tres entidades Usuario, hilo(stream), posts.
3. aplicación JS para usar el servicio (Desplegada en S3).
4. seguridad usando JWT con el servicio cognito de AWS.
5. Separación del monolito en tres microservicios independientes.
6. Desplieguando el servicio en AWS con EC2 (empleando Docker). 

## Despliegue de la aplicacion

Para clonar la aplicacion utilice el siguiente comando:
```
git clone https://github.com/Camilomora117/RestAPIQuarkus.git
```
Una vez descargado acceda a la carpeta y corra el siguiente comando
```
mvn clean install
```
Despues empaquetamos con el siguiente comando
```
mvn package
```

Finalmente para desplegar la aplicación de forma local use el siguiente comando
```
mvn compile quarkus:dev
```

![1](https://user-images.githubusercontent.com/98135134/233541531-fc4e3847-9589-4e0f-909e-b03516034587.png)

![image](https://user-images.githubusercontent.com/98135134/233541297-2c6eb030-2a1d-4711-bd72-28e3898cc54b.png)


## Despliegue en la nube
En primera intancia creamos la imagen en docker

```
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/code-with-quarkus-jvm .
```

Posteriormente corremos usando el siguiente comando

```
docker run -i --rm -p 8080:8080 quarkus/code-with-quarkus-jvm
```

Le hacemos push al docker hub y ya lo tenemos disponible para usarlo en las intancias de máquina ec2


Una vez se tenga la instancia de una máquina EC2 en la nube, agregamos tres reglas de entrada
en los puertos (34000, 34001 y 34002).

![3](https://user-images.githubusercontent.com/98135134/233541441-80b6a3b9-2f97-469b-b0e7-e31a2b3f2b4c.png)


seguido a esto instale docker en la consola de la máquina con los siguientes comandos:

```
sudo yum update -y
sudo yum install docker
sudo service docker start
```
Una vez instalado docker corremos el siguiente comando con los diferentes puertos

```
docker run -d -p 34000:8080 --name quarkus art2416/quarkus
docker run -d -p 34001:8080 --name quarkus1 art2416/quarkus
docker run -d -p 34002:8080 --name quarkus2 art2416/quarkus
```

Seguido a esto realice el siguiente comando para ver las imagenes:

````
docker ps
````

![4](https://user-images.githubusercontent.com/98135134/233541466-3be49bec-0fcd-48c8-8106-64169f75097e.png)

Finalmente vemos el funcionamiento de la aplicación viendo los logs

````
docker logs quarkus
````

![5](https://user-images.githubusercontent.com/98135134/233541489-a5b074fb-7fa7-408f-bdf5-68b5b5a29d1e.png)

## Funcionamiento

Acceso a las diferentes intancias mediante las siguientes direcciones:

````
http://lab-arep-micrservicios.s3.amazonaws.com/Frontend/index.html

http://ec2-44-212-45-139.compute-1.amazonaws.com:34000

http://ec2-44-212-45-139.compute-1.amazonaws.com:34001

http://ec2-44-212-45-139.compute-1.amazonaws.com:34002
````



# Video

````
https://youtu.be/0TM1PxBsKjA
````

## Autores
* Yesid Camilo Mora

* Luis Felipe Giraldo

* David Arturo Narváez
