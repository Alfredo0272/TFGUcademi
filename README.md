# TFG Ucademi - Plataforma Cervecera API

Backend REST desarrollado con **Spring Boot** para gestionar empresas cerveceras, fábricas y cervezas.

## Tecnologías

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- Spring Security + JWT
- MySQL
- Maven Wrapper

## Estructura del proyecto

```text
src/main/java/tfg/cervecera
├── aplication/      # Lógica de negocio (servicios)
├── config/          # Seguridad, JWT y utilidades de auth
├── controllers/     # Endpoints REST
├── dto/             # Objetos de entrada/salida de la API
├── exceptions/      # Manejo global de errores
└── model/           # Entidades JPA y repositorios
```

## Requisitos

- JDK 17+
- MySQL 8+
- Variables de entorno configuradas

## Variables de entorno

La aplicación usa estas variables para la conexión a BD y JWT:

- `MYSQL_HOST`
- `MYSQL_PORT`
- `MYSQL_DATABASE`
- `MYSQL_USER`
- `MYSQL_PASSWORD`
- `JWT_SECRET` (**mínimo 32 caracteres**)

Ejemplo:

```bash
export MYSQL_HOST=localhost
export MYSQL_PORT=3306
export MYSQL_DATABASE=cervecera
export MYSQL_USER=root
export MYSQL_PASSWORD=tu_password
export JWT_SECRET=una_clave_muy_segura_de_32_caracteres_o_mas
```

## Configuración de base de datos

En `application.properties` la conexión se resuelve por variables de entorno y JPA está en modo:

- `spring.jpa.hibernate.ddl-auto=update`

Esto crea/actualiza tablas automáticamente en el arranque.

## Ejecutar en local

### Linux / macOS

```bash
./mvnw spring-boot:run
```

### Windows

```bat
mvnw.cmd spring-boot:run
```

Servidor por defecto: `http://localhost:8080`

## Seguridad y autenticación

- La API es **stateless** (sin sesión de servidor).
- Se usa token JWT en cabecera:

```http
Authorization: Bearer <token>
```

### Endpoints públicos

- `POST /api/companies/register`
- `POST /api/companies/login`
- `GET /api/beers`
- `GET /api/beers/{id}`

### Endpoints protegidos (requieren JWT)

- Gestión de fábricas (`/api/factories/**`)
- Crear/eliminar cervezas (`POST /api/beers/new`, `DELETE /api/beers/{id}`)

## Endpoints principales

## 1) Empresas

### Registro

`POST /api/companies/register`

Body:

```json
{
  "name": "Cervezas Norte",
  "email": "info@cervezasnorte.com",
  "password": "password123",
  "country": "España",
  "foundedYear": 1998
}
```

Respuesta: `201 Created`

### Login

`POST /api/companies/login`

Body:

```json
{
  "email": "info@cervezasnorte.com",
  "password": "password123"
}
```

Respuesta `200 OK` (ejemplo):

```json
{
  "id": 1,
  "name": "Cervezas Norte",
  "email": "info@cervezasnorte.com",
  "token": "eyJhbGciOi..."
}
```

## 2) Fábricas

### Crear fábrica

`POST /api/factories/register`

Body:

```json
{
  "name": "Fábrica Central",
  "location": "Madrid",
  "capacity": 120000
}
```

### Obtener fábricas

- `GET /api/factories`
- `GET /api/factories/{id}`

### Actualizar y borrar

- `PUT /api/factories/{id}`
- `DELETE /api/factories/{id}`

## 3) Cervezas

### Crear cerveza

`POST /api/beers/new`

Body:

```json
{
  "name": "IPA Sierra",
  "style": "IPA",
  "alcohol": 6.5,
  "pricePerL": 4.25,
  "factoryId": 1
}
```

### Consultar cervezas

- `GET /api/beers`
- `GET /api/beers/{id}`

### Eliminar cerveza

- `DELETE /api/beers/{id}`

## Validaciones y errores

La API devuelve errores de validación por campo cuando falla `@Valid`, por ejemplo:

```json
{
  "email": "Formato de email inválido",
  "password": "La contraseña debe tener al menos 8 caracteres"
}
```

También devuelve errores estructurados para casos comunes (409, 401, 404, 500):

```json
{
  "message": "El email ya está registrado",
  "status": 409,
  "timestamp": "2026-02-24T10:15:30"
}
```

## Tests

Ejecutar tests:

```bash
./mvnw test
```

> Nota: para pruebas de integración que carguen el contexto, asegúrate de tener las variables de entorno y la BD accesibles.

## Próximas mejoras sugeridas

- Añadir documentación OpenAPI/Swagger.
- Añadir perfiles (`dev`, `test`, `prod`) con configuración separada.
- Añadir Testcontainers para tests de integración con MySQL.
- Añadir Docker Compose para levantar API + DB rápidamente.
