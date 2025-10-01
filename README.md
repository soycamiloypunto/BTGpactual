# Prueba Técnica - Ingeniero de Desarrollo Fullstack (BTG Pactual)

Este repositorio contiene la solución completa a la prueba técnica para el rol de Ingeniero de Desarrollo Fullstack en BTG Pactual. El proyecto fue desarrollado por **Cristian Camilo Tabares**.

## Descripción del Proyecto

La solución implementa un sistema web que permite a los clientes de BTG Pactual gestionar sus suscripciones a Fondos Voluntarios de Pensión (FPV) y Fondos de Inversión Colectiva (FIC's). La plataforma permite realizar las siguientes acciones:

* **Suscribirse a nuevos fondos** (aperturas).
* **Cancelar suscripciones** a fondos actuales (individualmente o todas a la vez).
* **Visualizar el historial** de últimas transacciones.
* **Enviar una notificación** por Email o SMS al momento de la suscripción.

## Tecnologías Utilizadas

Para garantizar una solución robusta, escalable y moderna, se seleccionó un stack tecnológico basado en Java para el backend y Angular para el frontend. A continuación, se justifica la elección de cada tecnología.

### Backend

* **Java 21:** Se eligió la última versión LTS (Long-Term Support) de Java para aprovechar las mejoras de rendimiento, las nuevas características del lenguaje (como Virtual Threads) y garantizar la seguridad y el soporte a largo plazo del proyecto.
* **Spring Boot 3:** Es el framework por excelencia en el ecosistema Java para crear microservicios y APIs REST de forma rápida y eficiente. Su sistema de autoconfiguración, inyección de dependencias y ecosistema robusto (Spring Data, Spring Security) reduce drásticamente el código repetitivo y permite enfocarse en la lógica de negocio.
* **Spring Data JPA & Hibernate:** Para la capa de persistencia, esta combinación es el estándar de facto. Abstrae la complejidad de las operaciones con la base de datos, permitiendo un desarrollo ágil y seguro contra ataques de inyección SQL.
* **Gradle:** Se optó por Gradle como gestor de dependencias y automatización de compilación por su flexibilidad, rendimiento superior en builds incrementales y su sintaxis concisa basada en Groovy/Kotlin.
* **Lombok:** Utilizado para reducir el código "boilerplate" (getters, setters, constructores), resultando en clases de modelo más limpias y legibles.

### Frontend

* **Angular 17:** Se eligió la última versión de Angular por su arquitectura robusta basada en componentes, su enfoque en el tipado fuerte con TypeScript (lo que reduce errores en tiempo de ejecución) y su ecosistema completo que incluye un potente CLI, router y cliente HTTP. Los nuevos componentes "standalone" simplifican la estructura del proyecto.
* **TypeScript:** Es fundamental para construir aplicaciones a gran escala. Aporta seguridad de tipos, lo que facilita el mantenimiento, la refactorización y la colaboración en equipo, además de mejorar la autocompletación en el IDE.
* **Angular Material:** Para la interfaz de usuario, se optó por esta librería de componentes. Proporciona elementos de UI de alta calidad, accesibles y con un diseño consistente y profesional, lo que acelera enormemente el desarrollo del frontend y garantiza una excelente experiencia de usuario.
* **RxJS:** Integrado nativamente en Angular, RxJS es clave para manejar la asincronía de forma reactiva. Se utiliza para gestionar las respuestas de la API y para crear flujos de datos que actualizan la interfaz de usuario de forma eficiente cuando el estado de la aplicación cambia.

### Justificación General

La combinación de **Spring Boot y Angular** es una de las arquitecturas más probadas y demandadas en el desarrollo de aplicaciones web empresariales. Permite una clara separación de responsabilidades entre el backend (lógica de negocio y datos) y el frontend (presentación e interacción con el usuario), lo que facilita el desarrollo paralelo, el mantenimiento y la escalabilidad futura del sistema.

## Anexo: Diseño del Modelo de Datos NoSQL

Esta sección responde al requerimiento 1.b de la prueba técnica: "Diseñe un modelo de datos NoSQL que permita la solución al problema".

Para una base de datos NoSQL orientada a documentos como MongoDB, el enfoque cambia de la normalización (múltiples tablas relacionadas) a la **desnormalización**, donde los datos que se leen juntos se almacenan juntos en un mismo documento. Esto optimiza drásticamente el rendimiento de las lecturas al eliminar la necesidad de operaciones `JOIN`.

El diseño propuesto consistiría en dos colecciones principales: `fondos` y `clientes`.

### Colección: `fondos`

Esta colección almacenaría la información de cada fondo de inversión. Sería una colección de solo lectura para la aplicación del cliente.

**Ejemplo de Documento:**
```json
{
  "_id": 1,
  "nombre": "FPV_BTG_PACTUAL_RECAUDADORA",
  "montoMinimo": 75000,
  "categoria": "FPV"
}
```


### Colección: `fondos`

Esta es la colección principal. Cada documento contendría toda la información relevante de un cliente, incluyendo sus suscripciones activas y su historial de transacciones, incrustados como arreglos de sub-documentos. Almacenar los datos de esta manera permite que, con una sola consulta, la aplicación recupere toda la información necesaria para renderizar la pantalla del usuario.

**Ejemplo de Documento:**
```json
{
  "_id": "UNICO-CLIENTE",
  "email": "cliente.ejemplo@email.com",
  "saldo": 350000,
  "fondosSuscritos": [
    {
      "fondoId": 1,
      "nombreFondo": "FPV_BTG_PACTUAL_RECAUDADORA",
      "montoInvertido": 75000,
      "fechaSuscripcion": "2025-09-30T18:30:00Z"
    }
  ],
  "historialTransacciones": [
    {
      "transaccionId": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
      "tipo": "APERTURA",
      "fondoId": 2,
      "nombreFondo": "FPV_BTG_PACTUAL_ECOPETROL",
      "monto": 125000,
      "fecha": "2025-09-30T23:10:00Z"
    },
    {
      "transaccionId": "f0e9d8c7-b6a5-4321-fedc-ba0987654321",
      "tipo": "APERTURA",
      "fondoId": 1,
      "nombreFondo": "FPV_BTG_PACTUAL_RECAUDADORA",
      "monto": 75000,
      "fecha": "2025-09-30T23:05:00Z"
    }
  ]
}
```

### la Migración
La migración en el código sería sencilla gracias a Spring Data:
- Se reemplazaría spring-boot-starter-data-jpa por spring-boot-starter-data-mongodb
- Las clases Java usarían la anotación @Document en lugar de @Entity. La clase Cliente contendría List<FondoSuscrito> y List<Transaccion> como atributos
- Se extendería de MongoRepository en lugar de JpaRepository. La mayoría de los métodos (save, findById) tienen la misma firma, minimizando los cambios en la capa de servicio.

