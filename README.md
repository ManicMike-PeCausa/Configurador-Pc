LEEME.txt - Proyecto Final "ConfiguradorPc"

Este proyecto forma parte del módulo de Entornos de Desarrollo. A continuación, explico qué contiene cada carpeta, cómo se ejecuta el proyecto y qué hace cada módulo.

📁 0-InformeGeneral.pdf  
Contiene el informe donde explico la justificación del proyecto, herramientas utilizadas y estructura general.

📁 1-Cronograma-Kanban  
Incluye el cronograma en formato Gantt y una captura del tablero Kanban que usé en Trello para organizar el desarrollo.

📁 2-Especificacion-Requisitos  
Documento PDF con los requisitos funcionales y no funcionales del proyecto. Explico lo que se pretende conseguir, qué funcionalidades tiene prioridad y qué objetivos se buscan.

📁 3-Diagramas  
Contiene los diagramas UML. Incluí una versión anterior y una actualizada donde reflejo los cambios y mejoras en la arquitectura.

📁 4-CodigoFuente  
Aquí se encuentra todo el código Java del proyecto. Está organizado por paquetes: modelo, vista y controlador. También hay una carpeta opcional con las clases compiladas.

📁 5-DocumentacionTecnica  
Contiene ejemplos y capturas de los comentarios en el código. También está la carpeta con la documentación generada con Javadoc desde Eclipse.

📁 6-Git  
En esta carpeta puse capturas de pantalla donde muestro los commits hechos, cómo subí el proyecto a GitHub y el uso básico de git.

📁 7-XML-Factura  
Contiene un ejemplo de factura generada en formato XML cuando el usuario realiza una compra desde la aplicación.

📁 8-Web  
Página web relacionada con el proyecto. Incluye el archivo `index.html`, estilos CSS, JavaScript y recursos gráficos.

📁 9-ODOO  
Aquí muestro lo trabajado en ODOO: documentación con capturas donde doy de alta una empresa, módulos instalados, etc.

📄 Cómo ejecutar el proyecto:
Desde Eclipse, importar el proyecto como Java Project. Asegurarse de tener el conector MySQL (`mysql-connector-java`) en la carpeta `/lib/` y bien referenciado en el build path. Luego ejecutar `main.java`.

📄 Qué hace cada módulo:
- **modelo/**: contiene las clases principales como Usuario, Componente, Carrito y la conexión a base de datos.
- **vista/**: todas las ventanas hechas en Java Swing (LoginFrame, MainFrame, AdminFrame).
- **controlador/**: lógica de control que conecta la vista con el modelo (LoginController, MainController, etc).

Este proyecto permite simular la configuración y compra de un PC por componentes, con comprobación de compatibilidad y generación de factura XML.

Realizado por: [EL AKHDAR ABDELGHAFFAR]
