# Drawing program by Martin Kuzdowicz

## Overview

Application for drawing in terminal
code challenge created for fun

* Program is written in **Java 8** and uses **gradle** as a build tool
* You do not need to install gradle as this project uses **gradle wrapper**
* external libraries used: only test libs for assertions and spies -
    **junit, mockito**

### Requierments
* [Java 1.8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)


### How To build a jar
on mac or linux

    ./gradlew
on windows

    gradlew
    
### How to run and rerun all tests from command line
on mac or linux

    ./gradlew --rerun-tasks test
    
on windows

    gradlew --rerun-tasks test
### How To run application from root directory `it needs to be build first`

    java -jar build/libs/drawing-app-1.0.0.jar
    
