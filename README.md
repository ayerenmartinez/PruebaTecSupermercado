🛒 Supermercado API — Prueba Técnica

API REST desarrollada con Spring Boot para gestionar productos, ventas, sucursales y estadísticas comerciales de un supermercado.
Incluye endpoints para:

Registrar ventas

Listar y consultar productos

Gestionar sucursales

Obtener estadísticas, como el producto más vendido

🚀 Tecnologías Utilizadas

Java 17

Spring Boot 3

Spring Web

Spring Data JPA

Hibernate

MySQL (o H2 para pruebas)

Maven

Lombok

📌 Funcionalidades Principales
✔ Gestión de Productos

Listar productos

Consultar detalles individuales

Manejar stock, precio y categoría

✔ Gestión de Ventas

Registrar ventas

Manejo de múltiples ítems por venta

Cálculo automático del total de la venta

✔ Gestión de Sucursales

Crear y listar sucursales

✔ Estadísticas

Obtener el producto más vendido

Cálculo dinámico mediante Collectors.groupingBy

📊 Endpoint de Estadísticas
🔹 GET /api/estadisticas/producto-mas-vendido
📥 Ejemplo de Respuesta:
{
  "productoId": 1,
  "nombreProducto": "Naranjas",
  "cantidadVendida": 5
}

📁 Estructura del Proyecto
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

🧠 Lógica del Producto Más Vendido

Se suman las cantidades vendidas agrupando por producto:

ventas.stream()
      .flatMap(venta -> venta.getDetalleVenta().stream())
      .collect(Collectors.groupingBy(
          detalle -> detalle.getProducto(),
          Collectors.summingInt(detalle -> detalle.getCantidad())
      ));


Luego se obtiene el producto con mayor cantidad vendida:

.max(Map.Entry.comparingByValue())

⚙️ Configuración
application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/supermercado
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

▶ Ejecución del Proyecto

Desde la terminal:

mvn spring-boot:run


O desde tu IDE ejecutando la clase principal PruebaTecSupermercadoApplication.

🧪 Datos de Prueba

Ejemplo de ventas usadas para validar el cálculo del producto más vendido:

[
  {
    "id": 1,
    "detalles": [
      { "nombreProducto": "Coca Cola 1.5L", "cantidadProducto": 2 },
      { "nombreProducto": "Naranjas", "cantidadProducto": 3 }
    ]
  },
  {
    "id": 2,
    "detalles": [
      { "nombreProducto": "Inca Cola 1.5L", "cantidadProducto": 2 }
    ]
  }
]

📄 Licencia

Proyecto desarrollado como prueba técnica.
Libre uso para fines educativos o personales.
