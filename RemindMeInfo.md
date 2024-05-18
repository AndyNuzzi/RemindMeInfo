#*RemindMeInfo*
##¿Cómo instalar RemindMeInfo en mi ordenador?

Lo primero que se debe hacer para poder usar *RemindMeInfo* en un ordenador es tener instalado Android Studio.

A continuación, se inicia Android Studio. Si hay un proyecto abierto, se puede ir a `File > New > Project` from Version Control.
Si no hay proyecto abierto, desde la pantalla de bienvenida, se selecciona `Get from VCS`.

Después en la ventana que aparece, hay que asegurarse de que la opción seleccionada en el desplegable es Git. Esto indica que se va a clonar un repositorio que utiliza Git para el control de versiones. 

Se copia la URL del repositorio de GitHub que se desea clonar. Se puede encontrar esta URL en la página del repositorio en GitHub. Se pega esta URL en el campo URL en Android Studio.

Antes de trabajar con el código fuente se elige la ruta de la carpeta local donde se desea guardar el proyecto clonado. A continuación se hace clic en el botón `Clone`. Android Studio comenzará a clonar el repositorio desde GitHub a la computadora local. Durante este proceso, puede solicitar credenciales de GitHub si el repositorio es privado o si requiere autenticación.

Una vez que el repositorio se haya clonado correctamente, Android Studio debería abrir automáticamente el proyecto. Si no es así, se puede abrir manualmente seleccionando `Open an existing project` y navegando hasta la ubicación donde se ha clonado el repositorio.

RemindMeInfo tiene dependencias especificadas en el archivo `build.gradle`, Android Studio preguntará si se desea descargarlas. Se debe aceptar para asegurarse de que todas las librerías y herramientas necesarias estén instaladas y configuradas.

