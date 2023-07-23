FROM openjdk:17-jdk-slim
VOLUME /tmp

COPY target/projeto-salus-well-0.0.1-SNAPSHOT.jar app.jar

ENV spring.datasource.url=jdbc:sqlserver://salus-bd.database.windows.net;database=salus
ENV spring.datasource.username=admin-salus-bd
ENV spring.datasource.password=#Gfgrupo3
ENV spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
ENV spring.jpa.show-sql=true
ENV spring.jpa.hibernate.ddl-auto=update
ENV jwt.secret=secrethusdushdusudhndkdkskkwowoo12777366ssdsds
ENV jwt.validity=3600000
ENV defer-datasource-initialization=true
ENV spring.jpa.properties.hibernate.format_sql=true
ENV spring.main.allow-circular-references=true

EXPOSE 3001
ENTRYPOINT ["java", "-jar", "app.jar"]