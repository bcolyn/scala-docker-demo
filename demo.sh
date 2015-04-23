#!/usr/bin/env bash
DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
sbt clean docker
docker run --read-only -v $DIR/conf:/conf  default/scala-docker-demo