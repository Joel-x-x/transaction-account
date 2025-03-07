# Usar imagen ligera y segura de Java 17
FROM gcr.io/distroless/java17

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado al contenedor
COPY target/*.jar app.jar

# Exponer el puerto donde correrá la app
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
