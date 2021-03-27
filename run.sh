#!/bin/bash
#mvn clean package
ant war
docker build -t shop .
docker run -d -p 8080:8080 --name shop shop