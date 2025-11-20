🛒 Supermercado API — Prueba Técnica

API REST desarrollada con Spring Boot para la gestión de productos, ventas y estadísticas comerciales en un supermercado.
Incluye endpoints para registrar ventas, listar sucursales, gestionar productos y obtener estadísticas como el producto más vendido.

🚀 Tecnologías Utilizadas

Java 17

Spring Boot 3

Spring Web

Spring Data JPA

Hibernate

MySQL (o H2 si usaste en memoria)

Maven

Lombok

📌 Funcionalidades Principales
✔ Gestión de Productos

Listado de productos.

Consulta de detalle.

Stock, precio y categoría.

✔ Gestión de Ventas

Registro de ventas.

Cada venta contiene múltiples detalles (items vendidos).

Cálculo automático del total.

✔ Gestión de Sucursales

Alta y consulta de sucursales.

✔ Estadísticas

Producto más vendido, calculado dinámicamente según todas las ventas.

Suma de cantidades vendidas por producto usando Collectors.groupingBy.

📊 Endpoint de Estadísticas
GET /api/estadisticas/producto-mas-vendido
📥 Ejemplo Respuesta:
{
  "productoId": 1,
  "nombreProducto": "Naranjas",
  "cantidadVendida": 5
}

📚 Estructura del Proyecto
src/main/java/com/todocodeacademy/pruebatecsupermercado
│
├── controllers
│   └── EstadisticasController.java
│
├── services
│   └── EstadisticasService.java
│
├── dtos
│   ├── ProductoDTO.java
│   ├── VentaDTO.java
│   ├── DetalleVentaDTO.java
│   └── ProductoMasVendidoDTO.java
│
├── models
│   ├── Producto.java
│   ├── Venta.java
│   ├── DetalleVenta.java
│   └── Sucursal.java
│
├── mapper
│   └── Mapper.java
│
└── repositories
    ├── ProductoRepository.java
    ├── VentaRepository.java
    └── SucursalRepository.java
