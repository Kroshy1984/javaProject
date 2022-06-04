FROM openjdk:8
ADD Server.java .
ADD netty-all-4.1.0.CR3.jar .
RUN javac -cp ".:netty-all-4.1.0.CR3.jar" Server.java
