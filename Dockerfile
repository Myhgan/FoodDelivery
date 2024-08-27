#Dockerfile
FROM openjdk:20-jdk
#port dự kiến
#EXPOSE 6789
# file jar chứa src code
COPY ../target/FoodDelivery.jar /FoodDelivery.jar
ENTRYPOINT ["java", "-jar", "/FoodDelivery.jar"]

# Lệnh build
# docker build -t myhgan1/fooddelivery .
# docker run myhgan1/fooddelivery
# docker run --name fooddelivery-container -p 805:805 myhgan1/fooddelivery

# để connect từ docker đến db local: thay localhost hoặc 127.0.0.1 bằng host.docker.internal

# Lệnh push lên docker hub
# docker push myhgan1/fooddelivery
