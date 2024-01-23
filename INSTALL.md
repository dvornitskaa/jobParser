## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)

## Running the application locally

* 🔄 [Clone](https://www.git-scm.com/docs/git-clone) the repository using [Git](https://git-scm.com/downloads)
```
git clone https://github.com/Microsoft/containers-rest-cosmos-appservice-java.git
```

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the [InterviewApplication.java](src%2Fmain%2Fjava%2Foksana%2Fdvornitska%2Finterview%2FInterviewApplication.java)`oksana.dvornitska.interview.InterviewApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```