# updated image for java 17
FROM bellsoft/liberica-openjdk-alpine:17.0.6


RUN apk add curl jq

# Workspace
WORKDIR /usr/share/ryanair

# ADD .jar under target from host
# into this image
ADD target/selenium-testNG-docker.jar selenium-testNG-docker.jar
ADD target/selenium-testNG-docker-tests.jar selenium-testNG-docker-tests.jar
ADD target/libs libs


# add config/data path from test/resources
# please ADD that as well
ADD src/test/resources/testData/ryanair.json src/test/resources/testData/ryanair.json
ADD src/test/resources/config/config.properties src/test/resources/config/config.properties

# ADD suite files
ADD testng.xml testng.xml

# ADD health check script
ADD healthcheck.sh                      healthcheck.sh

# ENTRYPOINT java -cp "selenium-testNG-docker.jar:selenium-testNG-docker-tests.jar:libs/*" org.testng.TestNG testng.xml
ENTRYPOINT sh healthcheck.sh