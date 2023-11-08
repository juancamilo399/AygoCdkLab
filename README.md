# CDK WORKSHOP

## Descripción
El laboratorio consiste en utilizar IaC para diseñar un script cuyo objetivo es aprovisionar una instancia ec2
y que esta se cree con todos los recursos necesarios como el security group
y la vpc, ademas de esto es necesario automatizar la creacion de un servidor web
simple con la creacion de la instancia, para esto se añaden los comandos necesarios
al campo userData.

## Detalle

Para lograr lo anterior se utilizara cdk como herramienta de creacion de
infraestructura como codigo, donde para la creacion del servidor web se utilizara
una imagen de dockerhub previamente desarrollada, siendo esta creada y ejecutada mediante los
scripts en userData.

## Pre-requisitos
* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [Git](https://git-scm.com/) - Sistema de control de versiones
* [Java 8](https://www.java.com/) - Tecnología para el desarrollo de aplicaciones
* [Amazon cdk](https://docs.aws.amazon.com/cdk/v2/guide/getting_started.html) - Amazon cloud development kit


## Instrucciones de construcción y ejecución

1. **Verificar la compilacion de la aplicación**: Desde la raíz del proyecto compilar la aplicación.

``mvn clean install``

2. **Crear el template**: Desde la raíz del proyecto correr el siguiente comando.

``cdk synth AygoCdkStack > template.yaml``

4. **Iniciar la creacion del stack en cloudformation**: Desde la raíz del proyecto correr el siguiente comando.

``aws cloudformation create-stack --stack-name AygoStack --template-body file://template.yaml --capabilities CAPABILITY_IAMl``


## Resultados del despliegue

A continuación se muestran evidencias de la creacion del stack y de la ec2.

### AWS cloudformation
![deploy.png](img%2Fdeploy.png)

### Creacion stack cloudformation
![stack.png](img%2Fstack.png)

### Creacion ec2
![ec2.png](img%2Fec2.png)

### Verificar creacion web server basado en una imagen de docker
![docker.png](img%2Fdocker.png)

### Verificar servidor web
![web.png](img%2Fweb.png)