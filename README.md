# 📚 Microservicio de Gestión de Libros – Parcial Electiva II

Este proyecto implementa un microservicio completo en Java usando Spring Boot, que permite realizar operaciones CRUD sobre libros. El proyecto incluye pruebas unitarias, pruebas de integración, respaldo de base de datos, y contenerización usando Docker y Docker Compose.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- MariaDB
- Maven
- JUnit 5 + Mockito
- JaCoCo
- Docker / Docker Compose
- Bash

---

## 📁 Estructura del proyecto


---

## 📌 Funcionalidades del microservicio

- **Crear libro** → `POST /api/libros`
- **Listar libros** → `GET /api/libros`
- **Consultar por ID** → `GET /api/libros/{id}`
- **Actualizar libro** → `PUT /api/libros/{id}`
- **Eliminar libro** → `DELETE /api/libros/{id}`

---

## 🧪 Pruebas implementadas

- ✅ Unitarias para `LibroServiceImpl` con Mockito
- ✅ Integración de `LibroController` con MockMvc
- ✅ Generación de cobertura con JaCoCo (`target/site/jacoco/index.html`)

# Ejecutar:
./mvnw clean verify

## 🐳 Contenerización
Dockerfile

Multi-stage build para generar una imagen optimizada del microservicio.
docker-compose.yml

# Contiene los servicios:

    libros-app: microservicio en Spring Boot
    mariadb-libros: base de datos persistente

# Ejecutar:
docker-compose up --build

💾 Respaldo de la base de datos

Script backup-db.sh que crea un archivo .sql con fecha en la carpeta backups/.

# Ejecutar:
./backup-db.sh

# Resultado:
backups/respaldo-librosdb-2025-04-12.sql

✅ CRUD funcional
✅ Pruebas unitarias y de integración
✅ Colección Postman entregada
✅ Imagen Docker lista
✅ Base de datos persistente
✅ Automatización por script
✅ Respaldo funcional

🧑‍💻 Autora: Gabriela Galvis
📝 Proyecto entregado para: Electiva II – Parcial Microservicios