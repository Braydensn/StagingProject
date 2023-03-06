FROM amazoncorretto:17

COPY staging-backend/target/staging-ecommerce*.jar staging-ecommerce.jar

ENTRYPOINT ["java", "-jar", "/staging-ecommerce.jar"]

EXPOSE 7777