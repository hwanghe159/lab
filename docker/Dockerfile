# base 이미지 지정. <이미지>:<태그> 형식
FROM openjdk:17

# 컨테이너 안으로 복사
COPY build/libs/docker-0.0.1-SNAPSHOT.jar docker.jar

ENTRYPOINT ["java", "-jar", "/docker.jar"]
