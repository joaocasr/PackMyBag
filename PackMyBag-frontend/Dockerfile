FROM node:lts-alpine as build-stage

ARG MODE=production
ENV NODE_ENV=$MODE

WORKDIR /app
COPY package*.json ./

# para rodar na cloud, temos de adaptar as dependencias a arm64 , ou seja estas duas abaixo tem de ficar descomentadas
# RUN npm install @rollup/rollup-linux-x64-musl 
# RUN npm install esbuild@0.21.5
RUN npm install

COPY . .

#RUN npm run build -- --mode $MODE


RUN if [ "$MODE" = "development" ]; then \
      echo "Construindo no modo Production"; \
      npm run build -- --mode $MODE; \
    else \
      echo "Construindo no modo Development"; \
      npm run build; \
    fi


# production stage
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY docker_entrypoint.sh generate_env-config.sh ./
RUN chmod +x docker_entrypoint.sh generate_env-config.sh
EXPOSE 80
#CMD ["nginx", "-g", "daemon off;"]

CMD ["/bin/sh", "docker_entrypoint.sh"]