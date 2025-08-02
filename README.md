# 🛒 Tienda de Productos - Arquitectura de Microservicios con Spring Cloud

Este proyecto implementa una **tienda de electrodomésticos online** utilizando una arquitectura basada en **microservicios** con tecnologías modernas como Spring Boot, Spring Cloud, Eureka, Feign, API Gateway y Config Server.

---

## 📦 Microservicios incluidos

| Servicio            | Descripción                                                                 |
|---------------------|------------------------------------------------------------------------------|
| `product-service`   | Gestión de productos: alta, baja, modificación, consulta.                   |
| `cart-service`      | Administración del carrito de compras de los usuarios.                      |
| `sale-service`      | Procesamiento de ventas y registro de pedidos realizados.                   |
| `eureka-sv`         | Service Registry con Eureka.                                                 |
| `config-server`     | Centralización de configuración para todos los servicios.                   |
| `api-gateway`       | Punto de entrada unificado al sistema, con ruteo dinámico de solicitudes.   |

---

## 🧱 Arquitectura general

- ✅ Spring Boot (Java 21)
- ✅ Spring Cloud (Eureka, Config Server, OpenFeign, Circuit Breaker con Resilience4J)
- ✅ Arquitectura basada en microservicios
- ✅ Comunicación entre servicios vía REST + Feign
- ✅ Descubrimiento y registro de servicios con Eureka
- ✅ Configuración centralizada en `config-server`
- ✅ API Gateway para entrada única y ruteo dinámico
- ✅ Circuit Breaker para tolerancia a fallos

---

## 🚀 Cómo ejecutar el proyecto

Asegurate de tener instalado:

- Java 21
- Maven 3.9+
- Postman (opcional para probar la API)

### Paso a paso para correr localmente:

1. Clonar el proyecto y navegar al directorio principal:
   ```bash
   git clone <https://github.com/lucasberonvonbrand/tienda-productos.git>
   cd tienda-productos
   ```

2. Asegurate de tener todos los servicios configurados en tu IDE (cada uno es un proyecto Maven independiente).

3. Iniciar los servicios **en este orden**:

   1. `eureka-sv`  
   2. `config-server`  
   3. `product-service`  
   4. `cart-service`  
   5. `sale-service`  
   6. `api-gateway`

> 📍 Todos los servicios están configurados para obtener su configuración desde `config-server` y registrarse automáticamente en Eureka.

---

## 🌐 Endpoints importantes

Una vez todos los servicios estén corriendo:

- **Eureka Dashboard:**  
  [http://localhost:8761](http://localhost:8761)

- **API Gateway:**  
  [http://localhost:443](http://localhost:443)

- **Ejemplos de rutas vía Gateway:**
  - `GET /product-service/products` → redirige a `product-service`
  - `POST /cart-service/cart` → redirige a `cart-service`
  - `POST /sale-service/sales` → redirige a `sale-service`

---

## 🧪 Colección Postman

Para probar fácilmente todos los endpoints, se incluye una colección de Postman con llamadas a los distintos microservicios.

📁 Archivo: `postman/tienda-productos.postman_collection.json`

### Instrucciones:

1. Abrir Postman
2. Ir a *Importar* → Seleccionar archivo → elegir el `.json`
3. Ejecutar las requests una vez que los servicios estén en funcionamiento

---

## 🔧 Configuración centralizada

Toda la configuración externa de los servicios está en la carpeta:

📁 `config-data/`

El `config-server` se encarga de distribuir estos archivos según el nombre del servicio. Cada servicio apunta a ese servidor al iniciar (`application.properties`).

---

## 💾 Bases de datos MySQL

Este proyecto usa tres bases de datos MySQL independientes, una para cada microservicio con persistencia:

| Base de datos     | Microservicio      |
|-------------------|--------------------|
| `cart-service`    | Carrito de compras |
| `product-service` | Productos          |
| `sale-service`    | Ventas             |

Para facilitar la puesta en marcha, se incluyen archivos de exportación (`.sql`) que contienen la estructura y datos iniciales.

### Archivos de exportación

Están ubicados en la carpeta:

db/
├── cart-service-dump.sql
├── product-service-dump.sql
├── sale-service-dump.sql

### Cómo importar las bases de datos

1. Crear las bases de datos (si no existen):

```bash
mysql -u <usuario> -p -e "CREATE DATABASE IF NOT EXISTS cart-service;"
mysql -u <usuario> -p -e "CREATE DATABASE IF NOT EXISTS product-service;"
mysql -u <usuario> -p -e "CREATE DATABASE IF NOT EXISTS sale-service;"
```

2. Importar cada dump en su base correspondiente:

```bash
mysql -u <usuario> -p cart-service < ./db/cart-service-dump.sql
mysql -u <usuario> -p product-service < ./db/product-service-dump.sql
mysql -u <usuario> -p sale-service < ./db/sale-service-dump.sql
```

> **Nota:** En los comandos siguientes, reemplazá `<usuario>` por tu usuario de MySQL (por ejemplo, `root` o el que uses para conectarte a tu base de datos).  
> Al ejecutar, te pedirá la contraseña asociada a ese usuario.

## 📄 Estructura del proyecto

```
tienda-productos-config/
├── api-gateway/
├── cart-service/
├── config-data/
├── config-server/
├── eureka-sv/
├── product-service/
├── sale-service/
└── postman/
```

---

## 👤 Autor

**Lucas Ruben Beron Von Brand**  
Proyecto realizado como parte del curso *Microservicios con Spring Cloud* de TodoCode Academy.
