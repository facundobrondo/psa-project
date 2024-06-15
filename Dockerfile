# Usa una imagen base de OpenJDK 11
FROM openjdk:11-jdk-slim

# Configura el directorio de trabajo
WORKDIR /app

# Copia el archivo Gradle Wrapper y el archivo de configuración
COPY gradlew .
COPY gradle gradle

# Copia el resto de los archivos de la aplicación
COPY . .

# Da permisos de ejecución al Gradle Wrapper
RUN chmod +x gradlew

# Construye la aplicación
RUN ./gradlew build

# Encuentra el archivo JAR generado y guárdalo en una variable
RUN JAR_FILE=$(ls build/libs/*.jar) && echo $JAR_FILE

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["sh", "-c", "java -jar build/libs/$(ls build/libs | grep '.*\\.jar' | head -n 1)"]

