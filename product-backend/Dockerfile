FROM eclipse-temurin:17-jdk-alpine

RUN addgroup -S productgroup adduser -S productuser -G productgroup

WORKDIR /

COPY target/product-backend.jar product-backend.jar

RUN chown -R productuser:productgroup /

User productuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "product-backend.jar"]