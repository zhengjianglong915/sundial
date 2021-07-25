#! /bin/bash

mvn clean install -Dmaven.test.skip=true
docker build -t cn.wegostack/sundial-scheduler .
