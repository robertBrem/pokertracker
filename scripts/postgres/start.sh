#!/usr/bin/env bash

docker run -d \
-v /home/robert/Schreibtisch/static-volumes/pokerservice-db:/var/lib/postgresql/data \
--name pokerservice-datastore postgres-datastore

docker run -d \
-e POSTGRES_PASSWORD=postgres \
--volumes-from pokerservice-datastore \
--name pokerservice-db postgres