# Sistema de Gestión Hospitalaria (SGH) 🏥

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=spring)
![React](https://img.shields.io/badge/React-18-blue?logo=react)
![Vite](https://img.shields.io/badge/Vite-5.x-purple?logo=vite)
![Java](https://img.shields.io/badge/Java-17+-orange?logo=java)
![MySQL](https://img.shields.io/badge/MySQL-8.x-blue?logo=mysql)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

El **Sistema de Gestión Hospitalaria (SGH)** es una aplicación full-stack diseñada para administrar de manera centralizada las operaciones de un centro médico. Provee una API RESTful robusta construida con **Spring Boot** y un frontend dinámico e interactivo desarrollado con **React**.

---
## 📋 Módulos Implementados

Este proyecto cubre el flujo completo de atención al paciente a través de los siguientes módulos:

-   ✅ **Gestión de Pacientes**: Registro, actualización y consulta de datos de pacientes y su historia clínica.
-   ✅ **Médicos y Especialidades**: Administración del personal médico y sus áreas de especialización.
-   ✅ **Citas Médicas**: Programación, reprogramación y gestión de estados de citas (Programada, Atendida).
-   ✅ **Consultas y Diagnósticos**: Registro detallado del acto médico, incluyendo diagnósticos.
-   ✅ **Hospitalización**: Gestión de habitaciones y control de ingresos y altas de pacientes.
-   ✅ **Facturación**: Generación de facturas por servicios y seguimiento de pagos.
-   ✅ **Administración y Seguridad**: Creación de usuarios con roles (`recepcionista`, `admin`) y un panel de control para operaciones clave.

---
## 🛠️ Stack Tecnológico

### **Backend**
-   **Framework**: Spring Boot 3
-   **Lenguaje**: Java 17+
-   **Acceso a Datos**: Spring Data JPA / Hibernate
-   **Base de Datos**: MySQL
-   **Gestor de Dependencias**: Maven
-   **Librerías Adicionales**: Lombok

### **Frontend**
-   **Framework/Librería**: React 18
-   **Herramienta de Build**: Vite
-   **Cliente HTTP**: Axios
-   **Enrutamiento**: React Router DOM

---
## 🚀 Puesta en Marcha (Desarrollo Local)

Para ejecutar este proyecto completo en tu entorno local, sigue los pasos para el backend y el frontend.

### **Prerrequisitos**
-   JDK 17 o superior.
-   Node.js y npm.
-   Un IDE para Java (ej. IntelliJ IDEA, Eclipse).
-   Un editor de código para JavaScript (ej. VS Code).
-   XAMPP o un servidor MySQL independiente.

---
### **1. Configuración del Backend (API)**

1.  **Clona el repositorio**:
    ```bash
    git clone [https://github.com/sesema98/GestionHospitalaria.git](https://github.com/sesema98/GestionHospitalaria.git)
    cd GestionHospitalaria # O el nombre de la carpeta raíz del proyecto
    ```

2.  **Inicia la Base de Datos**:
    -   Abre el panel de control de XAMPP e inicia el servicio de **MySQL**.
    -   Abre **phpMyAdmin** y crea una nueva base de datos llamada `hospitaldb`.

3.  **Configura la Conexión**: El archivo `src/main/resources/application.properties` ya está preconfigurado.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/hospitaldb
    spring.datasource.username=root
    spring.datasource.password=
    spring.jpa.hibernate.ddl-auto=update
    ```
    La propiedad `ddl-auto=update` creará las tablas automáticamente la primera vez que se ejecute.

4.  **Ejecuta el Backend**:
    -   Abre la carpeta del backend en tu IDE de Java.
    -   Ejecuta la clase principal `GestionHospitalariaApplication.java`.
    -   El servidor se iniciará en `http://localhost:8080`.

---
### **2. Configuración del Frontend (React)**

1.  **Navega a la carpeta del frontend**: Desde la raíz del proyecto, entra a la carpeta del frontend (ej. `hospital-frontend`).
    ```bash
    cd hospital-frontend
    ```

2.  **Instala las dependencias**:
    ```bash
    npm install
    ```

3.  **Ejecuta el Frontend**:
    ```bash
    npm run dev
    ```
    -   La aplicación React se iniciará en `http://localhost:5173`.
    -   Abre esta URL en tu navegador para ver la interfaz de inicio de sesión.

---
## 📖 Documentación de la API (Endpoints Clave)

### **Autenticación y Usuarios**
| Método | Endpoint                  | Descripción                      |
|:-------|:--------------------------|:---------------------------------|
| `POST` | `/api/usuarios/login`     | Inicia sesión con un usuario.    |
| `POST` | `/api/usuarios/registrar` | Registra un nuevo usuario.       |

### **Gestión de Pacientes**
| Método   | Endpoint                      | Descripción                                      |
|:---------|:------------------------------|:-------------------------------------------------|
| `POST`   | `/api/pacientes`              | Registra un nuevo paciente (con antecedentes).  |
| `GET`    | `/api/pacientes`              | Lista todos los pacientes.                       |
| `GET`    | `/api/pacientes/{id}/citas`   | Obtiene todas las citas de un paciente.          |
| `GET`    | `/api/pacientes/{id}/consultas`| Obtiene todas las consultas de un paciente.      |
| `DELETE` | `/api/pacientes/{id}`         | Desactiva el estado de un paciente.              |

### **Gestión de Médicos**
| Método | Endpoint                      | Descripción                               |
|:-------|:------------------------------|:------------------------------------------|
| `POST` | `/api/medicos`                | Registra un nuevo médico (con especialidades). |
| `GET`  | `/api/medicos`                | Lista todos los médicos.                  |
| `GET`  | `/api/medicos/{id}/consultas` | Obtiene todas las consultas de un médico. |

### **Acciones y Flujos de Trabajo**
| Método | Endpoint                              | Descripción                               |
|:-------|:--------------------------------------|:------------------------------------------|
| `POST` | `/api/citas`                          | Agenda una nueva cita.                    |
| `PUT`  | `/api/citas/{id}/atender`             | Cambia el estado de una cita a "Atendida". |
| `POST` | `/api/consultas`                      | Registra una consulta completa.           |
| `POST` | `/api/hospitalizaciones/ingresar`     | Registra el ingreso de un paciente.       |
| `PUT`  | `/api/hospitalizaciones/{id}/alta`    | Registra el alta de un paciente.          |
| `POST` | `/api/habitaciones`                   | Crea una nueva habitación.                |
| `POST` | `/api/facturas`                       | Genera una nueva factura.                 |
| `PUT`  | `/api/facturas/{id}/pagar`            | Cambia el estado de una factura a "Pagada".|
