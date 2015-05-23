#!/usr/bin/env bash

docker run -p 8082:8080 -p 9992:9990 \
-e JNDI_NAME=java:jboss/jdbc/pokerserviceDS \
-e NAME=PokerserviceDS \
-e CONNECTION_URL=jdbc:postgresql://pokerservice-db:5432/pokerservice \
-e DB_USER_NAME=postgres \
-e DB_PASSWORD=postgres \
-e DB_DRIVER_PATH=https://jdbc.postgresql.org/download \
-e DB_DRIVER_NAME=postgresql-9.4-1201.jdbc41.jar \
-e TEST_MODE=true \
-e DEPLOYMENT_ARTIFACT=http://optimist.engineer/pokerservice.war \
-d --name pokerservice --link pokerservice-db:pokerservice-db wildfly