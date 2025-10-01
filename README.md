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
