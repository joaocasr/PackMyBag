#!/bin/bash

echo "packmybag2425" | docker login -u packmybag --password-stdin

# Criar um builder:
#docker buildx create --use
# Verificar se o builder foi criado e se suporta a plataform
#docker buildx inspect --bootstrap

# Declarar plataformas suportadas
PLATFORMS="linux/amd64"
#PLATFORMS="linux/amd64,linux/arm64"

# Construir e enviar imagens multiplataforma com os caminhos corretos
docker buildx build --no-cache --platform $PLATFORMS --build-arg MODE=production -t packmybag/webplataform:frontend ./PackMyBag-frontend --push
docker buildx build --platform $PLATFORMS -t packmybag/webplataform:apigateway ./API-GATEWAY --push
docker buildx build --platform $PLATFORMS -t packmybag/webplataform:recomendacoes ./recomendacoesservice --push
docker buildx build --platform $PLATFORMS -t packmybag/webplataform:notificacoes ./notificacoesService --push
docker buildx build --platform $PLATFORMS -t packmybag/webplataform:cart ./cartService --push
docker buildx build --platform $PLATFORMS -t packmybag/webplataform:catalogo ./catalogService --push
docker buildx build --platform $PLATFORMS -t packmybag/webplataform:utilizadores ./utilizadoresService --push
docker buildx build --platform $PLATFORMS -t packmybag/webplataform:favoritos ./favoritosService --push
docker buildx build --platform $PLATFORMS -t packmybag/webplataform:encomendas ./encomendaService --push


# docker tag packmybag-frontend:latest packmybag/webplataform:frontend
# docker tag packmybag-apigatewayservice:latest packmybag/webplataform:apigateway
# docker tag packmybag-recomendacoesservice:latest packmybag/webplataform:recomendacoes
# docker tag packmybag-notificacoesservice:latest packmybag/webplataform:notificacoes
# docker tag packmybag-cartservice:latest packmybag/webplataform:cart
# docker tag packmybag-catalogoservice:latest packmybag/webplataform:catalogo
# docker tag packmybag-utilizadoresservice:latest packmybag/webplataform:utilizadores
# docker tag packmybag-favoritosservice:latest packmybag/webplataform:favoritos
# docker tag packmybag-encomendasservice:latest packmybag/webplataform:encomendas

# docker push packmybag/webplataform:frontend
# docker push packmybag/webplataform:apigateway
# docker push packmybag/webplataform:recomendacoes
# docker push packmybag/webplataform:notificacoes
# docker push packmybag/webplataform:cart
# docker push packmybag/webplataform:catalogo
# docker push packmybag/webplataform:utilizadores
# docker push packmybag/webplataform:favoritos
# docker push packmybag/webplataform:encomendas