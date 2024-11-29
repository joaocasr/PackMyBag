#!/bin/bash

# Importar imagens para o dockerHub

# Criar um builder:
#docker buildx create --use
# Verificar se o builder foi criado e se suporta a plataform
#docker buildx inspect --bootstrap

# Declarar plataformas suportadas
PLATFORMS="linux/amd64"
#PLATFORMS="linux/amd64,linux/arm64"

# Construir e enviar imagens multiplataforma com os caminhos corretos
docker buildx build --no-cache --platform $PLATFORMS -t packmybag/webplataform:frontend ./PackMyBag-frontend --push
docker buildx build --no-cache --platform $PLATFORMS -t packmybag/webplataform:apigateway ./API-GATEWAY --push
docker buildx build --no-cache --platform $PLATFORMS -t packmybag/webplataform:recomendacoes ./recomendacoesservice --push
docker buildx build --no-cache --platform $PLATFORMS -t packmybag/webplataform:notificacoes ./notificacoesService --push
docker buildx build --no-cache --platform $PLATFORMS -t packmybag/webplataform:cart ./cartService --push
docker buildx build --no-cache --platform $PLATFORMS -t packmybag/webplataform:catalogo ./catalogService --push
docker buildx build --no-cache --platform $PLATFORMS -t packmybag/webplataform:utilizadores ./utilizadoresService --push
docker buildx build --no-cache --platform $PLATFORMS -t packmybag/webplataform:favoritos ./favoritosService --push
docker buildx build --no-cache --platform $PLATFORMS -t packmybag/webplataform:encomendas ./encomendaService --push
