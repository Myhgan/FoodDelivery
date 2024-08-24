#Dockerfile
FROM openjdk:20-jdk
EXPOSE 6789
COPY ../target/FoodDelivery.jar /FoodDelivery.jar
ENTRYPOINT ["java", "-jar", "/FoodDelivery.jar"]