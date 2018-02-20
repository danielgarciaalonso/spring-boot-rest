# Ejemplo API Rest Spring Boot

**SPRING BOOT** https://projects.spring.io/spring-boot/

**REFERENCE** https://docs.spring.io/spring-boot/docs/1.5.10.RELEASE/reference/htmlsingle/

**COMMON APPLICATION PROPERTIES** https://docs.spring.io/spring-boot/docs/1.5.10.RELEASE/reference/htmlsingle/#common-application-properties

**INITIALIZR** https://start.spring.io/

## ¿Qué es SpringBoot?
Una versión subjetiva de lo que necesita un proyecto Spring, incluyendo las librerías de terceros más utilizadas, basándose en lo que Spring considera buenas prácticas para tener un proyecto listo para ejecutar y desplegar.

## Ejemplo de buenas prácticas organizando un proyecto
- `paquete.app.config` Clases de Configuración @Configuration
- `paquete.app.constants` Constantes
- `paquete.app.enum` Enumerados
- `paquete.app.domain` Entidades
- `paquete.app.exception` Excepciones y controladores de excepción
- `paquete.app.controller.v1` Controladores por versión
- `paquete.app.service` Servicios
- `paquete.app.repository` Clases de acceso a datos
- `paquete.app.api.v1.mapper` Clases de mapeo Dominio – DTO (si usamos este patrón)
- `paquete.app.api.v1.model` DTOs por versión



## Algunas utilidades para reducir el código repetitivo
- **Lombok** https://projectlombok.org/
- **MapStruct** http://mapstruct.org/

## Test Unitarios / Integración
Los test se organizan siguiendo la misma estructura que hemos seguido para el código fuente

**DOCUMENTACIÓN** https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html

## Swagger
Proporciona una manera rápida de crear documentación sobre un API Rest, en Spring se configura con el `Docket` Bean. Swagger permite personalizar el API utilizando varias anotaciones (ver la documentación oficial).

**DOCUMENTACIÓN** https://swagger.io/specification/