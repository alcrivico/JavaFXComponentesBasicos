## Para Empezar

Para compilar y ejecutar proyectos Java en Visual Studio Code necesitarás de la extención "Extension Pack for Java".

## Estructura del Proyecto

El espacio de trabajo contiene dos carpetas por defecto:

- `src`: Contiene el código fuente
- `lib`: Contiene las dependencias y bibliotecas del proyecto

Mientras que, la compilación de los archivos bytecode se generan en la carpeta `bin`.

> Para crear las configuraciones del proyecto selecciona en la barra de navegación de Visual Studio Code Run > Add Configuration, dentro de la carpeta .vscode tendrá un archivo llamado launch.json, En el launch.json antes que todo coloca una coma ',' al final de la linea del "projectName", y en la siguiente linea de configuración justo en la linea debajo del "projectName" coloca:

> "vmArgs": "--module-path \"<Escribe la ruta del SDK de JavaFX 8 o superior>\" --add-modules javafx.controls,javafx.fxml"

(NOTA: Si copias la ruta desde Windows procura cambiar todas las "\" ❌ por "/" ✅ unicamente deja las diagonales que ya estaban, procura que no estén los simbolos de señalamiento "<" y ">").

Ej Windows. "vmArgs": "--module-path \"C:/Program Files/Java/javafx-sdk-17.0.8/lib\" --add-modules javafx.controls,javafx.fxml"

Ej Unix. "vmArgs": "--module-path /path/to/javafx-sdk-17.0.8/lib --add-modules javafx.controls,javafx.fxml"

(NOTA: Si tu ruta de linux o macOSX tiene espacios en los nombres de los directorios también deberás colocar \"</path/to/javafx-sdk-17.0.8/lib>\" sin los "<" ">").

> Para cambiar las librerías del SDK de javaFX a otra version deberás eliminar las librerias predeterminadas en la carpeta lib del proyecto a la version que quieras cambiar para eso debes buscar la ruta del SDK de JavaFX de tu preferencia y seleccionar todos los .jar localizados en la carpeta lib copiarlos y pegarlos en la carpeta lib de este proyecto.

> Ejemplo de ruta donde se puede localizar:

> "C:\Program Files\Java\javafx-sdk-17.0.8\lib".

## Administración de Dependencias

El apartado visual `JAVA PROJECTS` permite administrar las dependencias. Para más detalle [aquí](https://github.com/microsoft/vscode-java-dependency#manage-dependencies). Otra forma de agregar dependencias es mediante la carpeta lib, allí puedes agregar la biblioteca de javaFX y otras dependencias.

## Descrición

Actividad formativa de JavaFX para la Experiencia Educativa Principios de Construcción de Software de la carrera de Ingeniería de Software de la Universidad Veracruzana.
