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



## DOCUMENTACIÓN ACTIVIDADES Y PASOS


### este sistema está diseñado pra la recolección de datos en un entorno experimental, porlo tanto, es muy relevante saber qué códigos están asociados a cada una de las actividades que se pueden realizar durante un experimento así como los pasos que se seguirán durante dicha actividad.


### Ponerse una bata sanitaria: 1

Actividad dentro del vestido con baja complejidad que se puede subdividir en pasos. Se debe realizar en una sala de ambiente tranquilo, sin demasiados distractores, con luz natural. 


<table>
  <tr>
   <td><strong>Pasos relacionados</strong>
   </td>
   <td><strong>Id paso</strong>
   </td>
  </tr>
  <tr>
   <td>Coge la bata
   </td>
   <td>1
   </td>
  </tr>
  <tr>
   <td>Empieza a meter la manga dominante
   </td>
   <td>2
   </td>
  </tr>
  <tr>
   <td>Acaba de meter la manga dominante
   </td>
   <td>3
   </td>
  </tr>
  <tr>
   <td>Empieza a meter la manga NO dominante 
   </td>
   <td>4
   </td>
  </tr>
  <tr>
   <td>Acaba de meter la manga NO dominante
   </td>
   <td>5
   </td>
  </tr>
  <tr>
   <td>Acaba de colocarse la bata 
   </td>
   <td>6
   </td>
  </tr>
  <tr>
   <td>Empieza a colocar el cuello  
   </td>
   <td>7
   </td>
  </tr>
  <tr>
   <td>Acaba de colocar el cuello        
   </td>
   <td>8
   </td>
  </tr>
  <tr>
   <td>Empieza a abrocharse los dos botones
   </td>
   <td>9
   </td>
  </tr>
  <tr>
   <td>Acaba de abrocharse los dos botones
   </td>
   <td>10
   </td>
  </tr>
  <tr>
   <td>Empieza a desabrocharse los dos botones
   </td>
   <td>11
   </td>
  </tr>
  <tr>
   <td>Terminó de desabrochar los dos botones
   </td>
   <td>12
   </td>
  </tr>
  <tr>
   <td>Empieza a quitar la manga dominante
   </td>
   <td>13
   </td>
  </tr>
  <tr>
   <td>Acaba de quitar la manga dominante
   </td>
   <td>14
   </td>
  </tr>
  <tr>
   <td>Empieza a quitar la manga NO dominante
   </td>
   <td>15
   </td>
  </tr>
  <tr>
   <td>Deja la bata donde estaba
   </td>
   <td>16
   </td>
  </tr>
</table>



### Ponerse un zapato y atarse los cordones: 2

Actividad dentro del vestido con complejidad media que se puede subdividir en pasos. Se debe realizar en una sala de ambiente tranquilo, sin demasiados distractores, con luz natural.


<table>
  <tr>
   <td><strong>Pasos relacionados</strong>
   </td>
   <td><strong>Id paso</strong>
   </td>
  </tr>
  <tr>
   <td>Estar sentado
   </td>
   <td>17
   </td>
  </tr>
  <tr>
   <td>Se coloca la bolsa en el pie.
   </td>
   <td>18
   </td>
  </tr>
  <tr>
   <td>Coge el zapato o toca con el pie el zapato
   </td>
   <td>19
   </td>
  </tr>
  <tr>
   <td>Empieza a meter el pie ensanchando la zona del elástico
   </td>
   <td>20
   </td>
  </tr>
  <tr>
   <td>Acaba de meter el pie
   </td>
   <td>21
   </td>
  </tr>
  <tr>
   <td>Empieza a quitárselo ensanchando la zona del elástico
   </td>
   <td>22
   </td>
  </tr>
  <tr>
   <td>Acaba de quitarse el zapato
   </td>
   <td>23
   </td>
  </tr>
</table>



### Prueba del algómetro: 3

 Un dispositivo algómetro de presión de dial equipado con una cabeza circular es utilizado para aplicar presión en puntos clave del cuerpo del paciente. Entre dichos puntos encontramos los hombros, los antebrazos y las manos.


<table>
  <tr>
   <td><strong>Pasos relacionados</strong>
   </td>
   <td><strong>Id paso</strong>
   </td>
  </tr>
  <tr>
   <td>Empieza trapecio dominante 1
   </td>
   <td>24
   </td>
  </tr>
  <tr>
   <td>Acaba trapecio dominante 1
   </td>
   <td>25
   </td>
  </tr>
  <tr>
   <td>Empieza trapecio dominante 2
   </td>
   <td>26
   </td>
  </tr>
  <tr>
   <td>Acaba trapecio dominante 2
   </td>
   <td>27
   </td>
  </tr>
  <tr>
   <td>Empieza trapecio dominante 3
   </td>
   <td>28
   </td>
  </tr>
  <tr>
   <td>Acaba trapecio dominante 3
   </td>
   <td>29
   </td>
  </tr>
  <tr>
   <td>Empieza trapecio NO dominante 1
   </td>
   <td>30
   </td>
  </tr>
  <tr>
   <td>Acaba trapecio NO dominante 1
   </td>
   <td>31
   </td>
  </tr>
  <tr>
   <td>Empieza trapecio NO dominante 2
   </td>
   <td>32
   </td>
  </tr>
  <tr>
   <td>Acaba trapecio NO dominante 2
   </td>
   <td>33
   </td>
  </tr>
  <tr>
   <td>Empieza trapecio NO dominante 3
   </td>
   <td>34
   </td>
  </tr>
  <tr>
   <td>Acaba trapecio NO dominante 3
   </td>
   <td>35
   </td>
  </tr>
  <tr>
   <td>Empieza antebrazo dominante 1
   </td>
   <td>36
   </td>
  </tr>
  <tr>
   <td>Acaba antebrazo dominante 1
   </td>
   <td>37
   </td>
  </tr>
  <tr>
   <td>Empieza antebrazo dominante 2
   </td>
   <td>38
   </td>
  </tr>
  <tr>
   <td>Acaba antebrazo dominante 2
   </td>
   <td>39
   </td>
  </tr>
  <tr>
   <td>Empieza antebrazo dominante 3
   </td>
   <td>40
   </td>
  </tr>
  <tr>
   <td>Acaba antebrazo dominante 3
   </td>
   <td>41
   </td>
  </tr>
  <tr>
   <td>Empieza antebrazo NO dominante 1
   </td>
   <td>42
   </td>
  </tr>
  <tr>
   <td>Acaba antebrazo NO dominante 1
   </td>
   <td>43
   </td>
  </tr>
  <tr>
   <td>Empieza antebrazo NO dominante 2
   </td>
   <td>44
   </td>
  </tr>
  <tr>
   <td>Acaba antebrazo NO dominante 2
   </td>
   <td>45
   </td>
  </tr>
  <tr>
   <td>Empieza antebrazo NO dominante 3
   </td>
   <td>46
   </td>
  </tr>
  <tr>
   <td>Acaba antebrazo NO dominante 3
   </td>
   <td>47
   </td>
  </tr>
  <tr>
   <td>Empieza mano dominante 1
   </td>
   <td>48
   </td>
  </tr>
  <tr>
   <td>Acaba mano dominante 1
   </td>
   <td>49
   </td>
  </tr>
  <tr>
   <td>Empieza mano dominante 2
   </td>
   <td>50
   </td>
  </tr>
  <tr>
   <td>Acaba mano dominante 2
   </td>
   <td>51
   </td>
  </tr>
  <tr>
   <td>Empieza mano dominante 3
   </td>
   <td>52
   </td>
  </tr>
  <tr>
   <td>Acaba mano dominante 3
   </td>
   <td>53
   </td>
  </tr>
  <tr>
   <td>Empieza mano NO dominante 1
   </td>
   <td>54
   </td>
  </tr>
  <tr>
   <td>Acaba mano NO dominante 1
   </td>
   <td>55
   </td>
  </tr>
  <tr>
   <td>Empieza mano NO dominante 2
   </td>
   <td>56
   </td>
  </tr>
  <tr>
   <td>Acaba mano NO dominante 2
   </td>
   <td>57
   </td>
  </tr>
  <tr>
   <td>Empieza mano NO dominante 3
   </td>
   <td>58
   </td>
  </tr>
  <tr>
   <td>Acaba mano NO dominante 3
   </td>
   <td>59
   </td>
  </tr>
</table>



### Cuestionarios: 4

Se le pasan una serie de cuestionarios al usuario para comprobar cómo reacciona ante ciertas preguntas y medir su estado fisiológico basal, de manera que durante las pruebas de dolor y otras veamos diferencias.


<table>
  <tr>
   <td><strong>Pasos relacionados</strong>
   </td>
   <td><strong>Id paso</strong>
   </td>
  </tr>
  <tr>
   <td>Empieza el cuestionario 1
   </td>
   <td>60
   </td>
  </tr>
  <tr>
   <td>Acaba el cuestionario 1
   </td>
   <td>61
   </td>
  </tr>
  <tr>
   <td>Empieza el cuestionario 2
   </td>
   <td>62
   </td>
  </tr>
  <tr>
   <td>Empieza el cuestionario 2
   </td>
   <td>63
   </td>
  </tr>
  <tr>
   <td>Empieza el cuestionario 3
   </td>
   <td>64
   </td>
  </tr>
  <tr>
   <td>Acaba el cuestionario 3
   </td>
   <td>65
   </td>
  </tr>
</table>
