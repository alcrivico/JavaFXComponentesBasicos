## Para Empezar

Para compilar y ejecutar proyectos Java en Visual Studio Code necesitarás de la extención "Extension Pack for Java".

## Estructura del Proyecto

El espacio de trabajo contiene dos carpetas por ddefecto:

- `src`: Contiene el código fuente
- `lib`: Contiene las dependencias y bibliotecas del proyecto

Mientras que, la compilación de los archivos fuente generará la carpeta `bin` por defecto con los bytecode.

> Para crear las configuraciones del proyecto selecciona en la barra de navegación de Visual Studio Code Run > Add Configuration, dentro de la carpeta .vscode tendrá un archivo llamado launch.json, agrega en el launch.json la siguiente linea de configuración justo en la linea debajo del "projectName" y coloca una coma ',' al final de la linea del "projectName":

"vmArgs": "--module-path \"<Escribe la ruta del SDK de JavaFX version 17>\" --add-modules javafx.controls,javafx.fxml"

(NOTA: Si copias la ruta desde Windows procura cambiar todas las "\" ❌ por "/" ✅ unicamente deja las diagonales que ya estaban, procura que no estén los simbolos de señalamiento "<" y ">").

> Para cambiar las librerías del SDK de javaFX a otra version en la parte inferior izquierda del editor Visual Studio Code despliega la pestaña JAVA PROJECTS > JavaFXComponentesBasicos > Referenced Libraries > + . Deberás eliminar las librerias de la version que quieras cambiar ybuscar la ruta del SDK de JavaFX de tu preferencia y seleccionar todos los .jar localizados en la carpeta lib y dar clic al botón Select Jar Libraries.

Ejemplo de ruta donde se puede localizar:

"C:\Program Files\Java\javafx-sdk-17.0.8\lib".

## Administración de Dependencias

El apartado visual `JAVA PROJECTS` permite administrar las dependencias. Para más detalle [aquí](https://github.com/microsoft/vscode-java-dependency#manage-dependencies). Otra forma de agregar dependencias es mediante la carpeta lib, allí puedes agregar la biblioteca de javaFX de igual forma.

## Descrición

Actividad formativa de JavaFX para la Experiencia Educativa Principios de Construcción de Software de la carrera de Ingeniería de Software de la Universidad Veracruzana.
