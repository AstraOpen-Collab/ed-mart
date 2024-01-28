## ed-mart

Welcome to ed-mart. A distributed event-driven digital marketplace, where buyers and sellers seamlessly connect in real-time! Leveraging the power of Red Hat OpenShift, Openvino, Kafka, Java, and Spring Boot.

### Description

The ed-mart project is inspired by the 2023 DevPost Hackathon under the codeshift challenge sponsored by [Redhat](https://www.redhat.com/en/technologies/cloud-computing/openshift) and [intel](https://www.intel.com/content/www/us/en/developer/tools/openvino-toolkit/overview.html) for open collaboration challenge and career advancement under [AstraOpenCollab](https://inventicsflow.slack.com/archives/C05UETHQ8QG) community.
This project is under continuous development using Java, Spring Boot, Spark, Openshift, Kafka and other tools to be added as development continues. It follows a microservices architecture where each service is designed to handle a specific functionality. The project utilizes various tools and technologies to enhance scalability, fault tolerance, and communication between services.

### Architectural Design

<p align="center">
    <img src="/ed-mart-platform.png" alt="ed-mart high level system design">
    <br />
    ed-mart - high level system Design
</p>

### Technologies and Patterns Used

- Java
- Spring Boot
- Flink
- Docker
- Eureka Server (Service Discovery)
- Zipkin (Distributed Log Tracing Tool)
- Feign Client (Inter-Service Communication)
- Kafka (Event-Driven Messaging)
- API Gateway Pattern
- Retry and Circuit Breaker Patterns
- Maven (Dependency Management and Build Tool)

### Prerequisites

Before running the inventics-flow application, ensure you have the following:

- Docker installed
- Java Development Kit (JDK) installed
- Maven installed
- A running instance of PostgreSQL with the required configuration
- A running instance of Redis
- SonarQube and Zipkin services available
- Apache kafka service


### Installation

1. Clone the repository:

```shell
git clone https://github.com/Inventics-Flow/inventics-flow.git
cd ed-mart
```

2. Build the project using Maven:

```shell
mvn clean install
```

3. Run the application using Docker Compose:

```shell
docker-compose up -d
```

### Configuration

The ed-mart application requires some configuration before running. Here are the key configuration files and options:

1. **application.yaml**: Contains application-specific configuration such as database connection details, API keys, etc.

### Usage

Once the application is successfully installed and configured, you can access the various microservices and endpoints through the API Gateway or directly.

For detailed API documentation and endpoint details, please refer to the project's API documentation.

### Additional Resources

If you encounter any issues or need additional information, feel free to refer to the following resources:

- [Project Documentation]() (Add link to the official documentation if available)
- [Issue Tracker](https://github.com/Ed-mart/ed-mart/issues) (Link to the project's issue tracker or GitHub repository for bug reports)
- [Community Forum](https://ed-mart.slack.com/archives/C05JEES1P8E) (Link to the community forum or support channels)

### Contributing

If you wish to contribute to the ed-mart project, please follow the guidelines outlined in the [Contribution Guide](community-guildelines.md).

### License

The ed-mart project is licensed under the [Apache License, Version 2.0](https://opensource.org/license/apache-2-0/).
