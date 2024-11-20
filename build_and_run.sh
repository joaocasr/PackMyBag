#!/bin/bash
set -e

# Verifica se o contêiner PostgreSQL existe
if [ ! "$(docker ps -q -f name=postgres-container)" ]; then
    if [ "$(docker ps -aq -f name=postgres-container)" ]; then
        # Contêiner existe mas está parado
        echo "Iniciando o contêiner PostgreSQL existente..."
        docker start postgres-container
    else
        # Executa um novo contêiner PostgreSQL
        echo "Criando e iniciando um novo contêiner PostgreSQL..."
        docker run -d --name postgres-container -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres -p 5432:5432 postgres:latest

        # Aguarda o PostgreSQL iniciar
        echo "Aguardando o PostgreSQL iniciar..."
        sleep 5

        # Cria os bancos de dados necessários
        echo "Criando bancos de dados..."
        docker exec -it postgres-container psql -U postgres -c "CREATE DATABASE catalogo;"
        docker exec -it postgres-container psql -U postgres -c "CREATE DATABASE favoritos;"
        docker exec -it postgres-container psql -U postgres -c "CREATE DATABASE encomenda;"
        docker exec -it postgres-container psql -U postgres -c "CREATE DATABASE cart;"
        docker exec -it postgres-container psql -U postgres -c "CREATE DATABASE notificacoes;"
        docker exec -it postgres-container psql -U postgres -c "CREATE DATABASE recomendacoes;"
        docker exec -it postgres-container psql -U postgres -c "CREATE DATABASE utilizadores;"
    fi
fi

# Aguarda o PostgreSQL estar pronto
echo "Aguardando o PostgreSQL iniciar..."
until docker exec postgres-container pg_isready -U postgres > /dev/null 2>&1; do
    echo "PostgreSQL não está pronto ainda..."
    sleep 1
done
echo "PostgreSQL está pronto!"

# Compila todos os microserviços
echo "Compilando os microserviços..."
services=(
    "catalogService"
    "encomendaService"
    "favoritosService"
    "cartService"
    "notificacoesService"
    "recomendacoesservice"
    "utilizadoresService"
)

for service in "${services[@]}"; do
    echo "Compilando $service..."
    if [ -d "$service" ]; then
        (cd "$service" && mvn clean install)
    else
        echo "Diretório $service não encontrado!"
        exit 1
    fi
done

# Remover o contêiner PostgreSQL temporário
echo "Removendo o contêiner PostgreSQL temporário..."
docker rm -f postgres-container

# Verificar se o contêiner foi removido antes de continuar
while [ "$(docker ps -aq -f name=postgres-container)" ]; do
    echo "Aguardando a remoção completa do contêiner postgres-container..."
    sleep 1
done

# Compila o API-GATEWAY
echo "Compilando o API-GATEWAY..."
if [ -d "API-GATEWAY" ]; then
    (cd API-GATEWAY && npm install)
else
    echo "Diretório API-GATEWAY não encontrado!"
    exit 1
fi

# Compila o frontend
echo "Compilando o frontend..."
if [ -d "PackMyBag-frontend" ]; then
    (cd PackMyBag-frontend && npm install && npm run build)
else
    echo "Diretório PackMyBag-frontend não encontrado!"
    exit 1
fi

# Executa o docker-compose
echo "Iniciando o Docker Compose..."
docker-compose up --build