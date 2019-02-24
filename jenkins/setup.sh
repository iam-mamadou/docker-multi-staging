#!/bin/sh

mkdir -p ~/jenkins_home

docker build -t jenkins .

# TODO use compose
docker run --name jenkins \
           -p 8080:8080 -p 50000:50000 \
           -v ~/jenkins_home:/var/jenkins_home \
           -v /var/run/docker.sock:/var/run/docker.sock \
           jenkins

