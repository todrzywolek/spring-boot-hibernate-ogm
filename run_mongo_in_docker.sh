#!/bin/bash

cd ~

# create require directories
if [ ! -d "mongo_files" ]; then
    mkdir mongo_files
    cd mongo_files
    mkdir mongo_scripts
    mkdir mongo_data
fi

# run mongo docker container
cd ~
docker run -d -p 27017:27017 \
--name bazy-danych-mongo \
-v "/$(pwd)/mongo_files/mongo_data:/data/db" \
-v "/$(pwd)/mongo_files/mongo_scripts:/var/opt" \
-e MONGO_INITDB_DATABASE=Northwind mongo