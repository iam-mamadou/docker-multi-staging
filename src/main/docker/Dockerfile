FROM openjdk:11.0.2-jdk-slim-stretch as build

WORKDIR /home/app

VOLUME /tmp

COPY entrypoint.sh entrypoint.sh
COPY *.jar app.jar

RUN chmod 755 entrypoint.sh

RUN mkdir -p unpacked-app-jar && cd unpacked-app-jar; jar -xf ../app.jar

# 2nd stage build
FROM openjdk:11.0.2-jre-slim-stretch

WORKDIR /home/app

VOLUME /tmp

ARG UNPACKED_JAR=/home/app/unpacked-app-jar
#APP_MAIN_CLASS_NAME is injected by gradle docker plugin
ARG APP_MAIN_CLASS_NAME
ENV APP_MAIN_CLASS_NAME=${APP_MAIN_CLASS_NAME}

COPY --from=build /home/app/entrypoint.sh ./
COPY --from=build ${UNPACKED_JAR}/BOOT-INF/lib lib/
COPY --from=build ${UNPACKED_JAR}/META-INF META-INF/
COPY --from=build ${UNPACKED_JAR}/BOOT-INF/classes ./

ENTRYPOINT ["./entrypoint.sh"]
