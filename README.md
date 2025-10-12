# Sistema de Gestión Hospitalaria (SGH)

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Java](https://img.shields.io/badge/Java-17-blue)
![Maven](https://img.shields.io/badge/Maven-4.0-red)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

El **Sistema de Gestión Hospitalaria (SGH)** es una aplicación backend robusta que provee una API RESTful para administrar de manera centralizada las operaciones principales de un centro médico.

---
## 📋 Módulos Implementados

Este proyecto implementa los siguientes módulos, cubriendo el flujo completo de atención al paciente:

-   ✅ **Gestión de Pacientes**: Registro, actualización y consulta de datos de pacientes y su historia clínica.
-   ✅ **Médicos y Especialidades**: Administración del personal médico y sus áreas de especialización.
-   ✅ **Citas Médicas**: Programación, reprogramación y cancelación de citas.
-   ✅ **Consultas y Diagnósticos**: Registro detallado del acto médico, diagnósticos y recetas.
-   ✅ **Hospitalización**: Gestión de habitaciones y control de ingresos y altas de pacientes.
-   ✅ **Facturación**: Generación de facturas por los servicios prestados y seguimiento de pagos.
-   ✅ **Administración y Seguridad**: Creación de usuarios con roles y una bitácora para auditoría de acciones.

---
## 🛠️ Stack Tecnológico

-   **Framework**: Spring Boot 3
-   **Lenguaje**: Java 21
-   **Acceso a Datos**: Spring Data JPA / Hibernate
-   **Base de Datos**: MySQL
-   **Gestor de Dependencias**: Maven
-   **Librerías Adicionales**: Lombok

---
## 🚀 Puesta en Marcha

Para ejecutar este proyecto en tu entorno local, sigue estos pasos:

### **Prerrequisitos**

-   JDK 17 o superior.
-   Apache Maven.
-   Un IDE de tu preferencia (IntelliJ IDEA, Eclipse, VSCode).
-   XAMPP (o cualquier otro gestor de MySQL).
-   Postman (o un cliente API similar para pruebas).

### **Configuración**

1.  **Clona el repositorio**:
    ```bash
    git clone https://github.com/sesema98/GestionHospitalaria.git
    ```

2.  **Abre el proyecto** en tu IDE. Maven se encargará de descargar todas las dependencias necesarias.

3.  **Inicia la Base de Datos**:
    -   Abre el panel de control de XAMPP.
    -   Inicia los servicios de **Apache** y **MySQL**.

4.  **Configura la conexión**: El archivo `src/main/resources/application.properties` ya está configurado para conectarse a una base de datos local.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/hospitaldb?createDatabaseIfNotExist=true
    spring.datasource.username=root
    spring.datasource.password=
    spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
    spring.jpa.hibernate.ddl-auto=update
    ```
    La base de datos `hospitaldb` y todas las tablas se crearán automáticamente la primera vez que ejecutes la aplicación.

5.  **Ejecuta la aplicación**:
    -   Navega a la clase `GestionHospitalariaApplication.java`.
    -   Haz clic derecho y selecciona "Run". El servidor se iniciará en `http://localhost:8080`.

---
## 📖 Documentación de la API

A continuación se detallan los endpoints más importantes de cada módulo.

### **Módulo de Pacientes**
| Método HTTP | Endpoint                  | Descripción                      |
| :---------- | :------------------------ | :------------------------------- |
| `POST`      | `/api/pacientes`          | Registra un nuevo paciente.      |
| `GET`       | `/api/pacientes`          | Lista todos los pacientes.       |
| `GET`       | `/api/pacientes/{id}`     | Obtiene un paciente por su ID.   |
| `PUT`       | `/api/pacientes/{id}`     | Actualiza los datos de un paciente.|
| `DELETE`    | `/api/pacientes/{id}`     | Desactiva el estado de un paciente.|

### **Módulo de Médicos y Citas**
| Método HTTP | Endpoint                                | Descripción                      |
| :---------- | :-------------------------------------- | :------------------------------- |
| `POST`      | `/api/especialidades`                   | Crea una nueva especialidad médica. |
| `POST`      | `/api/medicos`                          | Registra un nuevo médico.          |
| `POST`      | `/api/medicos/{mId}/especialidad/{eId}` | Asigna una especialidad a un médico.|
| `POST`      | `/api/citas`                            | Agenda una nueva cita médica.      |
| `PUT`       | `/api/citas/{id}/cancelar`              | Cambia el estado de una cita a "Cancelada". |

### **Módulo de Consultas**
| Método HTTP | Endpoint          | Descripción                                 |
| :---------- | :---------------- | :------------------------------------------ |
| `POST`      | `/api/consultas`  | Registra una consulta completa (diagnóstico y receta). |
| `GET`       | `/api/consultas/{id}` | Obtiene los detalles de una consulta.       |

### **Módulo de Hospitalización**
| Método HTTP | Endpoint                           | Descripción                                     |
| :---------- | :--------------------------------- | :---------------------------------------------- |
| `POST`      | `/api/habitaciones`                | Crea una nueva habitación.                      |
| `GET`       | `/api/habitaciones/disponibles`    | Lista las habitaciones con estado "disponible". |
| `POST`      | `/api/hospitalizaciones/ingresar`  | Registra el ingreso de un paciente.             |
| `PUT`       | `/api/hospitalizaciones/{id}/alta` | Registra el alta de un paciente.                |

### **Módulo de Facturación**
| Método HTTP | Endpoint                  | Descripción                                  |
| :---------- | :------------------------ | :------------------------------------------- |
| `POST`      | `/api/facturas`           | Crea una nueva factura para un paciente.     |
| `PUT`       | `/api/facturas/{id}/pagar`| Cambia el estado de una factura a "pagado".  |

### **Módulo de Seguridad**
| Método HTTP | Endpoint                  | Descripción                                  |
| :---------- | :------------------------ | :------------------------------------------- |
| `POST`      | `/api/usuarios/registrar` | Registra un nuevo usuario en el sistema.     |

---
## 🧪 Cómo Probar

Se recomienda utilizar **Postman** para probar la API. Puedes seguir el flujo lógico descrito en la documentación de endpoints para simular un caso de uso completo:
1.  Crear usuarios, médicos y habitaciones.
2.  Registrar un nuevo paciente.
3.  Agendar una cita para el paciente.
4.  Registrar la consulta derivada de la cita.
5.  (Opcional) Hospitalizar y dar de alta al paciente.
6.  Generar una factura por los servicios.
7.  Pagar la factura.

---
Desarrollado con la asistencia de Gemini. 🤖
