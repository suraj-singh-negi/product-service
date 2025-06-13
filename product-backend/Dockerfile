FROM eclipse-temurin:17-jdk-alpine

RUN addgroup -S productgroup adduser -S productuser -G productgroup

WORKDIR /

COPY target/product-backend.jar product-backend.jar

RUN chown -R productuser:productgroup /

User productuser

EXPOSE 8080 8000

ENTRYPOINT ["java", "-jar", "-Dserver.port=8080", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,address=8000,suspended=n", "product-backend.jar"]