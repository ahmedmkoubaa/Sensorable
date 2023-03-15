# Sensorable system

Esta app fue generada como parte del TFG: Sistema basado en reglas para la detección de actividades de la vida diaria mediante Fog/Edge Computing de ahmedmkoubaa@correo.ugr.es. Se ha ido adaptando para ser útil en la recogida de datos en los experimentos y de esta manera tener una serie de datos que probar para validar diferentes hipótesis.

Esta aplicación se encuentra formada por 3 subproyectos o paquetes principales. Entre ellos tenemos:

- mobile
- wear
- commons

En el proyecto mobile tenemos la app que se usa en la parte móvil junto con todo lo necesario para su correcta ejecución.
En el proyecto wear tenemos la app que se usa en la parte del wearable con sistema operativo Wear OS.
En el paquete commons tenemos código útil para ambos proyectos que se usa indistamente en wear o mobile.

Estructura del proyecto
La estructura detallada del proyecto con los diferentes paquetes que lo forman es la siguiente:

```
.
├── Sensorable
├── commons
│ ├── build
│ ├── src
│ ├── build.gradle
│ ├── consumer-rules.pro
│ └── proguard-rules.pro
├── gradle
│ └── wrapper
├── mobile
│ ├── build
│ ├── libs
│ ├── src
│ ├── README.md
│ ├── build.gradle
│ └── proguard-rules.pro
├── wear
│ ├── build
│ ├── src
│ ├── build.gradle
│ └── proguard-rules.pro
├── README.md
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
└── settings.gradle
```

Como podemos ver tenemos los diferentes paquetes que forman el proyecto al completo además de una serie de ficheros de configuración que pueden modificarse a nivel local por cada usuario para usar la configuración del entorno que más se le adecúe.

## Documentación del módulo de la aplicación para Android (Mobile)

Este es el módulo que se ejecuta en la parte del dispositivo Android

### Descripción

En esta parte encontramos funciones cruciales del sistema. Disponemos de una base de datos usando Android Rooms.
Tenemos además un cliente MQTT que nos permite hacer peticiones a servicios remotos mediante un broker MQTT.
Existe un servicio de detección de ADLs que en tiempo real detecta que acciones se están llevando a cabo por el usuario del sistema.
El sistema está respaldado por un servicio de guardado de CSVs que nos permita almacenar datos y evitar que se pierdan al exportarlos a servicios remotos.
Tenemos además diferentes servicios de enlazado de dispositivos wearables, principalmente dos: WearTransmissionService y EmpaticaTransmissionService. Estos servicios nos permiten establecer la comunicación entre dispositivos y obtener de los datos de sensores.

### Tecnologías y herramientas utilizadas

- Lenguaje de programación: Java.
- Frameworks: Android SDK.
- Protocolos de comunicación: Android Broadcast, MQTT.
- Herramientas de construcción: Gradle.
- Arquitectura: Edge Computing

### Arquitectura utilizada

Se ha utilizado una arquitectura Edge computing. Se han llevado todas las posibles funcionalidades a la parte del cliente (a este sistema de aplicaciones) y se le ha dotado de todos los mecanismos necesarios para que funcionase correctamente. Tenemos entre otros mecanismos, cliente de comunicación, bases de datos local, servicio de cómputo de detección de ADL.

## Documentación del módulo de la aplicación para Wear OS

Este es el módulo que se ejecuta en la parte del dispositivo Wear OS.

### Descripción

El proyecto Wear OS está completamente orientado a funcionar como un dispositivo recolector de información que irá obteniendo datos y envíandolos al dispositivo Mobile que hará la función de Gateway.
Por cuestiones de fiabilidad y robustez se usa un solo servicio Foreground que aparece siempre en primer plano y lleva a cabo todas todas las tareas indefinidas del sistema. Entre estas tareas tenemos el uso de una base de datos para recolectar información y poder operar sobre ella tanto para el exportado de datos como para el respaldo.
Se incluye además una función de almacenamiento de datos en CSV que permiten guardar una copia de respaldo por si los datos no fuesen correctamente transmitidos al gateway.

### Tecnologías y herramientas utilizadas

- Lenguaje de programación: Java.
- Frameworks: Android SDK, Wear OS SDK.
- Protocolos de comunicación: Android Broadcast.
- Herramientas de construcción: Gradle.
- Arquitectura: IoT fundamentals.

### Integración del paquete commons

Los proyectos Mobile y Wear son principalmente interfaces y están compuestas por una serie de servicios. Algunos de estos servicios y gran parte del código a nivel de clases, funciones y constantes pueden ser reutilizados fácilmente en ambos proyectos. Es justamente esta la función que desempeña el paquete commons, unifica el código utilizado en ambas partes en un solo paquete y simplifica los cambios y reduce las dependencias. También ocurre a nivel de servicios ya que se pueden definir servicios en commons y luego ser desplegados en Mobile y Wear añadiendo las correspondientes instrucciones en el manifest correspondiente a cada uno de los subproyectos (Mobile y Wear).

## Instrucciones para compilar y ejecutar el proyecto

Una vez clonado el repositorio este deberá ser abierto con Android Studio. Este framework permite la instalación de dependencias y configuración automática del proyecto. Si se quiere configurar cualquier aspecto de manera manual, entonces habrá que hacer uso de los ficheros de configuración:

```
├── build.gradle\
├── gradle.properties\
├── gradlew\
├── gradlew.bat\
├── local.properties\
└── settings.gradle
```

Se recomienda siempre usar en producción la rama master que es aquella que integra los últimos cambios de manera estable.
Para tareas de desarrollo se recomienda usar development por ser la rama orientada a añadir nuevos cambios y testearlos, suele presentar inestabilidad hasta que no se pasan los tests de integración.

## Pruebas y depuración

Este proyecto ha sido depurado con diferentes mecanismos y se han asegurado algunas requisitso como son:

- integridad de los datos
- disponibilidad del sistema en primer y segundo plano
- consumo energético de bateria
- optimización de uso de CPU

Para depurar el sistema se recomienda utilizar el debugger que usa de oficio Android Studio.
