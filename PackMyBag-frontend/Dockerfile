FROM node:lts-alpine as build-stage

ARG MODE=production
ENV NODE_ENV=$MODE

WORKDIR /app
COPY package*.json ./
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
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]