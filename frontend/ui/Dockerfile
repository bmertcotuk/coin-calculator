#
# Build stage
#
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install bootstrap@4.6.0 jquery popper.js vue-router@4 \
    && npm install --save axios
COPY . .
RUN npm run build

#
# Package stage
#
FROM nginx:stable-alpine as production-stage
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=build-stage /app/dist .
ENTRYPOINT ["nginx", "-g", "daemon off;"]

#FROM nginx:stable-alpine as production-stage
#COPY --from=build-stage /app/dist /usr/share/nginx/html
#EXPOSE 8081
#CMD ["nginx", "-g", "daemon off;"]
