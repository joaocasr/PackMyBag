#!/bin/bash
set -e

# Função para exibir o uso correto do script
usage() {
    echo "Uso: $0 <nome_do_serviço>"
    echo "Exemplo: $0 catalogService, $0 api-gateway, $0 frontend"
    exit 1
}

# Verifica se o nome do serviço foi fornecido
if [ -z "$1" ]; then
    echo "Erro: Nome do serviço não fornecido."
    usage
fi

SERVICE_DIR=$1

# Mapeia o diretório do serviço para o nome do serviço no Docker Compose
get_compose_service_name() {
    case "$1" in
        "catalogService") echo "catalogoservice";;
        "encomendaService") echo "encomendasservice";;
        "favoritosService") echo "favoritosservice";;
        "cartService") echo "cartservice";;
        "recomendacoesservice") echo "recomendacoesservice";;
        "notificacoesService") echo "notificacoesservice";;
        "utilizadoresService") echo "utilizadoresservice";;
        "frontend") echo "frontend";;
        "api-gateway") echo "apigatewayservice";;
        *)
            echo "Erro: Serviço desconhecido '$1'."
            exit 1
            ;;
    esac
}

COMPOSE_SERVICE=$(get_compose_service_name "$SERVICE_DIR")

# Nome do contêiner no Docker Compose
get_container_name() {
    case "$1" in
        "catalogoservice") echo "catalogo-container";;
        "encomendasservice") echo "encomendas-container";;
        "favoritosservice") echo "favoritos-container";;
        "cartservice") echo "cart-container";;
        "recomendacoesservice") echo "recomendacoes-container";;
        "notificacoesservice") echo "notificacoes-container";;
        "utilizadoresservice") echo "utilizadores-container";;
        "frontend") echo "frontend-container";;
        "apigatewayservice") echo "apigateway-container";;
        *)
            echo "Erro: Nome do contêiner desconhecido para o serviço '$1'."
            exit 1
            ;;
    esac
}

CONTAINER_NAME=$(get_container_name "$COMPOSE_SERVICE")

# # Verifica se o diretório do serviço existe
# if [ ! -d "$SERVICE_DIR" ]; then
#     echo "Erro: Diretório '$SERVICE_DIR' não encontrado!"
#     exit 1
# fi

# Função para iniciar o contêiner PostgreSQL temporário
start_temp_postgres() {
    echo "Criando e iniciando um novo contêiner PostgreSQL temporário..."
    docker run -d --name $TEMP_CONTAINER \
        -e POSTGRES_USER=postgres \
        -e POSTGRES_PASSWORD=postgres \
        -e POSTGRES_DB=postgres \
        -p 5433:5432 \
        postgres:latest

    echo "Aguardando o PostgreSQL iniciar..."
    until docker exec $TEMP_CONTAINER pg_isready -U postgres > /dev/null 2>&1; do
        echo "PostgreSQL não está pronto ainda..."
        sleep 1
    done
    echo "PostgreSQL está pronto!"

    # Cria os bancos de dados necessários
    echo "Criando bancos de dados temporários..."
    docker exec -i $TEMP_CONTAINER psql -U postgres <<EOSQL
CREATE DATABASE catalogo;
CREATE DATABASE favoritos;
CREATE DATABASE encomenda;
CREATE DATABASE cart;
CREATE DATABASE notificacoes;
CREATE DATABASE recomendacoes;
CREATE DATABASE utilizadores;
EOSQL
}

# Função para compilar serviços Java
compile_java_service() {
    local service=$1
    echo "Compilando o microserviço '$service' com Maven..."
    (cd "$service" && mvn clean install)
}

# Função para compilar API-Gateway
compile_api_gateway() {
    echo "Compilando o API-GATEWAY..."
    if [ -d "API-GATEWAY" ]; then
        (cd API-GATEWAY && npm install)
    else
        echo "Diretório API-GATEWAY não encontrado!"
        exit 1
    fi
}

# Função para compilar o frontend
compile_frontend() {
    echo "Compilando o frontend..."
    if [ -d "PackMyBag-frontend" ]; then
        (cd PackMyBag-frontend && npm install && npm run build)
    else
        echo "Diretório PackMyBag-frontend não encontrado!"
        exit 1
    fi
}

# Remove o contêiner existente, se estiver rodando
remove_container() {
    local container=$1
    if [ "$(docker ps -q -f name=^/${container}$)" ]; then
        echo "Parando o contêiner '$container'..."
        docker stop "$container"
    fi

    if [ "$(docker ps -aq -f name=^/${container}$)" ]; then
        echo "Removendo o contêiner '$container'..."
        docker rm -f "$container"
    fi
}

# Compilar o serviço especificado
if [ "$SERVICE_DIR" == "api-gateway" ] || [ "$SERVICE_DIR" == "frontend" ]; then
    # Não precisa de banco de dados para 'api-gateway' e 'frontend'

    if [ "$SERVICE_DIR" == "api-gateway" ]; then
        compile_api_gateway
    fi

    if [ "$SERVICE_DIR" == "frontend" ]; then
        compile_frontend
    fi
else
    # Para serviços que necessitam de PostgreSQL

    # Cria e inicia um novo contêiner PostgreSQL temporário
    TEMP_CONTAINER="postgres-temp"
    start_temp_postgres

    # Compilar o microserviço especificado
    compile_java_service "$SERVICE_DIR"

    # Remover o contêiner PostgreSQL temporário
    echo "Removendo o contêiner PostgreSQL temporário..."
    docker rm -f $TEMP_CONTAINER

    # Verificar se o contêiner foi removido antes de continuar
    while [ "$(docker ps -aq -f name=^/${TEMP_CONTAINER}$)" ]; do
        echo "Aguardando a remoção completa do contêiner '$TEMP_CONTAINER'..."
        sleep 1
    done
    echo "Contêiner '$TEMP_CONTAINER' removido."
fi

# Remover o contêiner existente do serviço, se estiver rodando
remove_container "$CONTAINER_NAME"

# Reconstruir e reiniciar apenas o serviço especificado com remoção de órfãos
echo "Reconstruindo e iniciando o serviço '$COMPOSE_SERVICE'..."
docker-compose up -d --no-deps --build "$COMPOSE_SERVICE" 

echo "Serviço '$COMPOSE_SERVICE' foi compilado e está rodando."