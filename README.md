# TFG Ucademi · Plataforma Cervecera (Backend)

Backend REST para gestionar compañías cerveceras, autenticación con JWT y operaciones sobre factorías y cervezas.

> Estado actual del dominio: existen modelos/DTOs de **stock** y **ventas**, pero aún no hay controladores REST para esas áreas.

## Stack tecnológico

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- Spring Security + JWT (stateless)
- MySQL Connector/J
- Jakarta Validation
- Maven / Maven Wrapper

## Estructura principal

```text
src/main/java/tfg/cervecera
├── aplication/          # servicios de negocio
├── config/              # seguridad, JWT y utilidades
├── controllers/         # endpoints REST expuestos hoy
├── dto/                 # contratos de entrada/salida
│   ├── beer/
│   ├── company/
│   ├── factory/
│   ├── sale/
│   └── stock/
├── exceptions/          # manejo global de errores
└── model/
    ├── repositorys/
    ├── Beer.java
    ├── Company.java
    ├── Factory.java
    ├── Sale.java
    └── Stock.java
```

## Requisitos

- JDK 17+
- Maven 3.9+ (o `./mvnw`)
- MySQL accesible

## Configuración por entorno

Variables requeridas:

- `MYSQL_HOST`
- `MYSQL_PORT`
- `MYSQL_DATABASE`
- `MYSQL_USER`
- `MYSQL_PASSWORD`
- `JWT_SECRET` (mínimo 32 caracteres)

Ejemplo:

```bash
export MYSQL_HOST=localhost
export MYSQL_PORT=3306
export MYSQL_DATABASE=cervecera
export MYSQL_USER=root
export MYSQL_PASSWORD=secret
export JWT_SECRET=una_clave_super_segura_de_mas_de_32_chars
```

## Ejecutar en local

```bash
./mvnw spring-boot:run
```

Alternativa:

```bash
mvn spring-boot:run
```

Base URL por defecto: `http://localhost:8080`

## Seguridad (JWT)

La API está en `SessionCreationPolicy.STATELESS`.

### Endpoints públicos

- `POST /api/companies/register`
- `POST /api/companies/login`
- `GET /api/beers`
- `GET /api/beers/{id}`

### Endpoints protegidos (`Authorization: Bearer <token>`)

- `POST /api/factories/register`
- `GET /api/factories`
- `GET /api/factories/{id}`
- `PUT /api/factories/{id}`
- `DELETE /api/factories/{id}`
- `POST /api/beers/new`
- `DELETE /api/beers/{id}`

## API REST disponible hoy

### 1) Compañías

**Registro** · `POST /api/companies/register`

```json
{
  "name": "Cervezas Sierra",
  "email": "contacto@sierra.es",
  "password": "password123",
  "country": "España",
  "foundedYear": 2015
}
```

**Login** · `POST /api/companies/login`

```json
{
  "email": "contacto@sierra.es",
  "password": "password123"
}
```

Respuesta ejemplo:

```json
{
  "id": 1,
  "name": "Cervezas Sierra",
  "email": "contacto@sierra.es",
  "token": "<jwt>"
}
```

### 2) Factorías

**Crear** · `POST /api/factories/register`

```json
{
  "name": "Fábrica Norte",
  "location": "Bilbao",
  "capacity": 50000
}
```

**Consultar/editar/borrar**

- `GET /api/factories`
- `GET /api/factories/{id}`
- `PUT /api/factories/{id}`
- `DELETE /api/factories/{id}`

### 3) Cervezas

**Crear** · `POST /api/beers/new`

```json
{
  "name": "IPA Montaña",
  "style": "IPA",
  "alcohol": 6.5,
  "pricePerL": 4.8,
  "factoryId": 1
}
```

**Consultar/borrar**

- `GET /api/beers`
- `GET /api/beers/{id}`
- `DELETE /api/beers/{id}`

## DTOs del proyecto (estado actual)

- **Company**: `CompanyRegisterDTO`, `CompanyLoginDTO`, `CompanyLoginResponseDTO`, `CompanyDTO`
- **Factory**: `FactoryRegisterDTO`, `FactoryDTO`
- **Beer**: `BeerRegisterDTO`, `BeerDTO`
- **Sale**: `SaleRegisterDTO`, `SaleDTO`
- **Stock**: `StockRegisterDTO`, `StockDTO`

> Nota: en esta rama no existe aún un `RevenueDTO`.

## Dominio modelado sin endpoints REST (todavía)

- `Sale` + `SaleRepository`
- `Stock` + `StockRepository`

## Validaciones principales

- Compañía: email válido, contraseña mínima 8, año fundación entre 1800 y 2100.
- Cerveza: nombre/estilo obligatorios, alcohol entre 0 y 100, precio por litro positivo.
- Factoría: nombre y ubicación obligatorios, capacidad positiva.

## Tests

```bash
./mvnw test
```

Si el wrapper no tiene permisos:

```bash
chmod +x mvnw
./mvnw test
```

## Notas de desarrollo

- `spring.jpa.hibernate.ddl-auto=update` habilitado.
- La app no arranca si `JWT_SECRET` tiene menos de 32 caracteres.
- CORS permite `http://localhost:8080`.
