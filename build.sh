#!/bin/bash
set -e

cd backend
docker build -t coin-calculator .
#docker run --rm -it coin-calculator:latest

cd ../frontend
docker build -t coin-calculator-ui .
docker run --rm -it -p 8080:80 coin-calculator-ui
