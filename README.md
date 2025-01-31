# Proyecto Teco Challenge

Este proyecto es una aplicación Java basada en Spring Boot que utiliza varias tecnologías de Spring para ofrecer diferentes funcionalidades. El fin fue desarrollar una aplicación Java que exponga un API HTTP para gestionar un sistema de caché en memoria de puntos de venta y los costos entre ellos. La aplicación debe permitir realizar las siguientes operaciones:

1.Gestión de Puntos de Venta:
  - Recuperar todos los puntos de venta.
  - Añadir, actualizar y eliminar puntos de venta en el caché.
  - Gestión de Costos entre Puntos de Venta:

2.Añadir y eliminar costos entre puntos de venta.
  - Consultar los puntos de venta directamente conectados a un punto específico y los costos asociados.
  - Consultar el camino con el costo mínimo entre dos puntos de venta.

3.Acreditaciones:
  - Recibir información de acreditaciones, enriquecerla con la fecha y el nombre del punto de venta, y persistirla en una base de datos.
  - Consultar las acreditaciones persistidas.


## Estructura del Proyecto
~~~
src
└── main
│   ├── java
│   │   └── Teco
│   │       └── Challenge
│   │           └── Source
│   │               ├── ChallengeJavaApplication.java
│   │               ├── Config
│   │               │   └── RedisConfig.java
│   │               ├── Controller
│   │               │   ├── VentaController.java
│   │               │   ├── CostoController.java
│   │               │   └── AcreditacionController.java
│   |               ├── Dijkstra
│   │               │ ├── Dijkstra.java
│   │               │ ├── Graph.java
│   │               │ └── Node.java
│   │               ├── Entity
│   │               │   ├── PuntoVenta.java
│   │               │   ├── Costo.java
│   │               │   └── Acreditacion.java
│   │               ├── Service
│   │               │   ├── PuntoVentaService.java
│   │               │   ├── CostoService.java
│   │               │   └── AcreditacionService.java
│   │               └── Repository
│   │                   ├── PuntoVentaRepository.java
│   │                   ├── CostoRepository.java
│   │                   └── AcreditacionRepository.java
│   └── resources
│       ├── application.yml
│       └── data.sql
└──pom.xml
~~~

## Tecnologías Utilizadas

El proyecto utiliza las siguientes tecnologías:

- **Spring Web**: Para el desarrollo de aplicaciones web.
- **Spring Data Redis**: Para la integración con Redis.
- **Spring Boot Starter Security**: Para la configuración de seguridad.
- **Spring Cloud Starter Netflix Eureka Client**: Para el cliente de Eureka.
- **Spring Cloud Starter Netflix Eureka Server**: Para el servidor de Eureka.
- **Spring Cloud Starter Sleuth**: Para el rastreo distribuido.
- **Spring Cloud Sleuth Zipkin**: Para la integración con Zipkin para el rastreo distribuido.

## Requerimientos del Sistema

Para ejecutar este proyecto, necesitarás tener instalados los siguientes componentes:

- **Java Development Kit (JDK) 11** o superior.
- **Maven 3.6.0** o superior.
- **Redis Server** configurado y en ejecución.
- **Eureka Server** configurado y en ejecución, se usa un servidor Eureka externo.
- **Zipkin Server** configurado y en ejecución, se usa un servidor Zipkin externo.

## Diagrama de Clases
~~~
package Teco.Challenge.Source {
  class PuntoVenta {
    - Long id
    - String nombre
    + Long getId()
    + void setId(Long id)
    + String getNombre()
    + void setNombre(String nombre)
  }

  class Costo {
    - Long id
    - PuntoVenta puntoVentaA
    - PuntoVenta puntoVentaB
    - int costo
    + Long getId()
    + void setId(Long id)
    + PuntoVenta getPuntoVentaA()
    + void setPuntoVentaA(PuntoVenta puntoVentaA)
    + PuntoVenta getPuntoVentaB()
    + void setPuntoVentaB(PuntoVenta puntoVentaB)
    + int getCosto()
    + void setCosto(int costo)
  }

  class Acreditacion {
    - Long id
    - double importe
    - Long puntoVentaId
    - Date fechaRecepcion
    - String nombrePuntoVenta
    + Long getId()
    + void setId(Long id)
    + double getImporte()
    + void setImporte(double importe)
    + Long getPuntoVentaId()
    + void setPuntoVentaId(Long puntoVentaId)
    + Date getFechaRecepcion()
    + void setFechaRecepcion(Date fechaRecepcion)
    + String getNombrePuntoVenta()
    + void setNombrePuntoVenta(String nombrePuntoVenta)
  }

  class PuntoVentaRepository {
    + List<PuntoVenta> findAll()
    + PuntoVenta save(PuntoVenta puntoVenta)
    + void deleteById(Long id)
    + Optional<PuntoVenta> findById(Long id)
  }

  class CostoRepository {
    + List<Costo> findAll()
    + Costo save(Costo costo)
    + void deleteById(Long id)
    + Optional<Costo> findById(Long id)
  }

  class AcreditacionRepository {
    + List<Acreditacion> findAll()
    + Acreditacion save(Acreditacion acreditacion)
    + void deleteById(Long id)
    + Optional<Acreditacion> findById(Long id)
  }

  class PuntoVentaService {
    - PuntoVentaRepository repository
    + List<PuntoVenta> getAll()
    + PuntoVenta save(PuntoVenta puntoVenta)
    + void delete(Long id)
    + PuntoVenta update(Long id, PuntoVenta puntoVenta)
  }

  class CostoService {
    - CostoRepository repository
    + List<Costo> getAll()
    + Costo save(Costo costo)
    + void delete(Long id)
    + Costo update(Long id, Costo costo)
    + List<Costo> findByPuntoVenta(Long puntoVentaId)
    + Costo findMinCost(Long idA, Long idB)
  }

  class AcreditacionService {
    - AcreditacionRepository repository
    - PuntoVentaService puntoVentaService
    + Acreditacion save(Acreditacion acreditacion)
    + List<Acreditacion> getAll()
    + void delete(Long id)
  }

  class PuntoVentaController {
    - PuntoVentaService service
    + List<PuntoVenta> getAll()
    + PuntoVenta create(PuntoVenta puntoVenta)
    + PuntoVenta update(Long id, PuntoVenta puntoVenta)
    + void delete(Long id)
  }

  class CostoController {
    - CostoService service
    + List<Costo> getAll()
    + Costo create(Costo costo)
    + Costo update(Long id, Costo costo)
    + void delete(Long id)
    + List<Costo> findByPuntoVenta(Long puntoVentaId)
    + Costo findMinCost(Long idA, Long idB)
  }

  class AcreditacionController {
    - AcreditacionService service
    + Acreditacion create(Acreditacion acreditacion)
    + List<Acreditacion> getAll()
    + void delete(Long id)
  }

  class Dijkstra {
    - Graph graph
    + Dijkstra(Graph graph)
    + Map<String, Integer> shortestPath(String start)
    + List<String> getPath(String start, String end, Map<String, String> previous)
  }

  class Graph {
    + Map<String, List<Node>> nodes
    + void addEdge(String source, String destination, int weight)
    + List<Node> getNeighbors(String node)
  }

  class Node {
    + String name
    + int weight
    + Node(String name, int weight)
  }

  PuntoVentaRepository --> PuntoVenta
  CostoRepository --> Costo
  AcreditacionRepository --> Acreditacion

  PuntoVentaService --> PuntoVentaRepository
  CostoService --> CostoRepository
  AcreditacionService --> AcreditacionRepository
  AcreditacionService --> PuntoVentaService

  PuntoVentaController --> PuntoVentaService
  CostoController --> CostoService
  AcreditacionController --> AcreditacionService
  Dijkstra --> Graph
  Graph --> Node
}
~~~

### Explicación del algoritmo de Dijkstra
- Dijkstra: La clase principal que implementa el algoritmo de Dijkstra para encontrar el camino más corto. Contiene un método shortestPath que calcula las distancias más cortas desde un nodo de inicio y un método getPath que devuelve el camino desde el nodo de inicio hasta el nodo final.

- Graph: Representa el grafo que contiene los nodos y las aristas. Tiene un método addEdge para añadir una arista entre dos nodos con un peso y un método getNeighbors para obtener los nodos vecinos de un nodo dado.

- Node: Representa un nodo en el grafo con un nombre y un peso.
