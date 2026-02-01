# Microservicio: ms-venta-db

## Descripción General
**ms-venta-db** es el microservicio de persistencia de datos del sistema de ventas con Webpay. Funciona como la capa de acceso a datos para almacenar y recuperar información sobre transacciones de pago procesadas a través de Webpay Plus.

## Puerto de Ejecución
- **Puerto:** `8083`

## Responsabilidades Principales

### 1. Gestión de Datos de Transacciones
- Almacena toda la información relacionada con transacciones de Webpay
- Persiste el estado de cada transacción a lo largo de su ciclo de vida
- Proporciona acceso a datos históricos de ventas

### 2. Modelo de Datos Almacenado
El microservicio gestiona la entidad `VentaEntity` que contiene:
- **Identificadores:** `id` (auto-incremental), `buy_order` (orden de compra única)
- **Datos de la transacción:** `session_id`, `amount` (monto), `return_url`
- **Información de Webpay:** `token` (token de transacción), `url` (URL de redirección)
- **Estado y confirmación:** `status`, `authorization_code` (código de autorización)

## Comunicación con Otros Microservicios

### Recibe Llamadas Desde:
1. **ms-venta-amb (Ambassador - Puerto 8082)**
   - Consulta transacciones por `buy_order` para actualizar estado
   - Consulta transacciones por `token` para obtener detalles

2. **ms-venta-bs (Business Service - Puerto 8081)**
   - Registro inicial de transacciones después de crear sesión en Webpay
   - Consulta de transacciones por `buy_order`

### Flujo de Comunicación:
```
Frontend → ms-venta-bff → ms-venta-bs → ms-venta-amb → ms-venta-db
                                  ↓
                            ms-venta-db (consulta/actualización)
```

## Endpoints Disponibles

### Operaciones CRUD:
- **POST `/db/add/venta`** - Registra una nueva transacción
- **GET `/db/ventas`** - Lista todas las transacciones
- **GET `/db/venta/{id}`** - Obtiene transacción por ID interno
- **GET `/db/venta/bo/{buy_order}`** - Obtiene transacción por orden de compra
- **GET `/db/venta/token/{token}`** - Obtiene transacción por token de Webpay
- **PUT `/db/update/venta/{buy_order}`** - Actualiza estado de transacción
- **DELETE `/db/delete/venta/{id}`** - Elimina transacción

## Base de Datos

### Tecnología:
- **Motor:** MySQL
- **Puerto:** 3306
- **Base de datos:** `ferremas`

### Esquema de Tabla:
```sql
CREATE TABLE ventas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    buy_order VARCHAR(255),
    session_id VARCHAR(255),
    amount INT,
    return_url VARCHAR(500),
    token VARCHAR(255),
    url VARCHAR(500),
    status VARCHAR(50),
    authorization_code VARCHAR(255)
);
```

## Dependencias Clave

### Dependencias Internas:
- Spring Data JPA para persistencia
- MySQL Connector para conexión a base de datos
- Spring Web para endpoints REST

### Configuración de Conexión (application.properties):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ferremas
spring.datasource.username=root
spring.datasource.password=root
```

## Flujo de Datos Típico

### 1. Creación de Transacción:
1. Recibe objeto `Venta` desde ms-venta-bs
2. Mapea a entidad `VentaEntity`
3. Persiste en base de datos MySQL
4. Devuelve la venta registrada

### 2. Actualización de Estado:
1. ms-venta-amb confirma pago con Webpay
2. Solicita actualización de estado por `buy_order`
3. Actualiza campos `status` y `authorization_code`
4. Persiste cambios en base de datos

Su rol como repositorio central de datos de transacciones lo convierte en un componente crítico para la operación del sistema de pagos completo.
