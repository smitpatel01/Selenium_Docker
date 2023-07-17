FROM bellsoft/liberica-openjdk-alpine:17.0.6
RUN apk add curl jq
WORKDIR /usr/share/smit

ADD target/selenium-docker.jar selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs libs
ADD BookFlight-module.xml BookFlight-module.xml
ADD Search-module.xml Search-module.xml
ADD healthcheck.sh healthcheck.sh
RUN dos2unix healthcheck.sh

# BROWSER
# HUB_HOST
# MODULE

ENTRYPOINT sh healthcheck.sh