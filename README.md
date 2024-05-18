# *RemindMeInfo*
## ¿Qué es *RemindMeInfo*?

RemindMeInfo es una aplicación desarrollada para un Trabajo de Fin de Grado en la universidad Rey Juan Carlos de Madrid, España. Se ha desarrollado pensada para dispositivos Android, programada en Kotlin y con almacenamiento en la nube de Google, usando los servicios de Firebase. Además el código fuente se distribuye bajo una licencia GNU General Public License.

El objetivo de este Trabajo de Fin de Grado es intentar cerrar la brecha digital generacional e introducir las nuevas tecnologías a un sector de población en concreto, la tercera edad. Para que no se sientan excluidos y sean independientes, haciendo que mejore la calidad de vida de estas personas y su autonomía en la era digital.

## ¿Cómo instalar RemindMeInfo en mi ordenador?

Lo primero que se debe hacer para poder usar *RemindMeInfo* en un ordenador es tener instalado Android Studio.

A continuación, se inicia Android Studio. Si hay un proyecto abierto, se puede ir a `File > New > Project from Version Control`.
Si no hay proyecto abierto, desde la pantalla de bienvenida, se selecciona `Get from VCS`.

Después en la ventana que aparece, hay que asegurarse de que la opción seleccionada en el desplegable es Git. Esto indica que se va a clonar un repositorio que utiliza Git para el control de versiones. 

Se copia la URL del repositorio de GitHub que se desea clonar. Se puede encontrar esta URL en la página del repositorio en GitHub. Se pega esta URL en el campo URL en Android Studio.

Antes de trabajar con el código fuente se elige la ruta de la carpeta local donde se desea guardar el proyecto clonado. A continuación se hace clic en el botón `Clone`. Android Studio comenzará a clonar el repositorio desde GitHub a la computadora local. Durante este proceso, puede solicitar credenciales de GitHub si el repositorio es privado o si requiere autenticación.

Una vez que el repositorio se haya clonado correctamente, Android Studio debería abrir automáticamente el proyecto. Si no es así, se puede abrir manualmente seleccionando `Open an existing project` y navegando hasta la ubicación donde se ha clonado el repositorio.

RemindMeInfo tiene dependencias especificadas en el archivo `build.gradle`, Android Studio preguntará si se desea descargarlas. Se debe aceptar para asegurarse de que todas las librerías y herramientas necesarias estén instaladas y configuradas.

## ¿Cómo usar el emulador de Android Studio con RemindMeInfo?

El emulador en Android Studio permite ejecutar y probar aplicaciones Android en una computadora sin necesidad de un dispositivo físico. Estos pasos conforman una guía clara para configurar un emulador en Android Studio:

- **Paso 1: Acceder al AVD Manager (Administrador de Dispositivos Virtuales)**
  
  En la barra de herramientas superior, se encuentra el  icono del AVD Manager. Es un  icono que se parece a un dispositivo mívil con un pequeño monitor.
  Al hacer clic en él se abre el `Administrador de Dispositivos Virtuales` (AVD Manager).
  
- **Paso 2: Crear un Nuevo Emulador en el AVD Manager**

  Para crear un nuevo emualdor en el AVD Manager, se hace clic en `Create Virtual Device`. Después, se selecciona el tipo de dispositivo que se desea emular (por ejemplo, Pixel 4) y se hace clic en `Next`.
  Se elige una imagen de sistema (una versión de Android) y se hace clic en `Next`. Entonces se configuran las opciones del hardware del emulador, como la cantidad de RAM, y se hace clic en `Next`. Para finalizar, se revisa la configuración y se hace clic en `Finish` para crear el emulador.
  
- **Paso 3: Iniciar el Emulador**

  En el AVD Manager, se ve el emulador que se acaba de crear. Se hace clic en el botón `Play` (triángulo verde) para iniciar el emulador. Se debe esperar a que el emulador se inicie. Puede llevar un tiempo la primera vez.
  
- **Paso 4: Instalar y Ejecutar la Aplicación**

  Una vez que el emulador está en funcionamiento, se puede instalar y ejecutar la aplicación desde Android Studio. Se selecciona el emulador como el destino de implementación cuando se ejecuta la aplicación.

## ¿Cómo conectar un dispositivo móvil Android a Android Studio?

Conectar un dispositivo Android a Android Studio es un paso esencial para probar y depurar aplicaciones directamente en un dispositivo f ́ısico. Aqu ́ı esta ́n los pasos b ́asicos para conectar un dispositivo Android a Android Studio:

- **Paso 1: Se debe preparar el Dispositivo Android**

  Se debe habilitar la Depuración USB. En el dispositivo Android, en la pantalla de ajustes, se accede a `Configuración > Acerca del teléfono > Información de software`. Se debe tocar repetidamente el número de compilación hasta que se active el modo de desarrollador. Después, se vuelve a la configuración principal y se selecciona `Opciones de desarrollador` y se habilita la opción `Depuración USB`. A continuación, se conecta el dispositivo al ordenador. Se debe usar un cable USB.

- **Paso 2: Configuración de Android Studio**
  
  Se abre Android Studio en el ordenador. Se verifica la `Instalación del Controlador`, pero es opcional. En algunos casos, es posible que se necesite instalar controladores USB específicos para el dispositivo. Se consulta el sitio web del fabricante del dispositivo para obtener información sobre los controladores.

- **Paso 3: Configuracio ́n de Android Studio para Depuración USB**

  Primero se debe habilitar la `Depuración USB` en Android Studio. Se busca `Run` en la barra de menú de Android Studio. Android Studio debería detectar automáticamente el dispositivo conectado y desplegar la aplicación en él. A continuación, se verifica la conexión, asegurándose de que Android Studio ha identificado correctamente el dispositivo en la parte superior de la ventana.

- **Paso 4: Aceptar la Confirmación de Depuración USB en el Dispositivo**

  Después de habilitar la Depuración USB, el dispositivo podría mostrar una ventana emergente solicitando permiso para la Depuración USB. Se tiene que aceptar esta confirmación.

