#!/bin/bash
set -e

# Verifica se o contêiner PostgreSQL existe
if [ ! "$(docker ps -q -f name=postgres)" ]; then
    if [ "$(docker ps -aq -f name=postgres)" ]; then
        # Contêiner existe mas está parado
        echo "Iniciando o contêiner PostgreSQL existente..."
        docker start postgres
    else
        # Executa um novo contêiner PostgreSQL
        echo "Criando e iniciando um novo contêiner PostgreSQL..."
        docker run -d --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres -p 5432:5432 postgres:latest

        # Aguarda o PostgreSQL iniciar
        echo "Aguardando o PostgreSQL iniciar..."
        sleep 5

        # Cria os bancos de dados necessários
        echo "Criando bancos de dados..."
        docker exec -it postgres psql -U postgres -c "CREATE DATABASE catalogo;"
        docker exec -it postgres psql -U postgres -c "CREATE DATABASE favoritos;"
        docker exec -it postgres psql -U postgres -c "CREATE DATABASE encomenda;"
        docker exec -it postgres psql -U postgres -c "CREATE DATABASE cart;"
        docker exec -it postgres psql -U postgres -c "CREATE DATABASE notificacoes;"
        docker exec -it postgres psql -U postgres -c "CREATE DATABASE recomendacoes;"
        docker exec -it postgres psql -U postgres -c "CREATE DATABASE utilizadores;"
    fi
fi

# Aguarda o PostgreSQL estar pronto
echo "Aguardando o PostgreSQL iniciar..."
until docker exec postgres pg_isready -U postgres > /dev/null 2>&1; do
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

# abre a pasta do encomendaService e executa o script de populate-container.sh
echo "Populando o banco de dados do encomendaService..."
(cd encomendaService && sudo sh ./populate.sh)

# abre a pasta scripts e executa o script de populate.sh
echo "Populando o banco de dados do utilizadoresService..."
(cd scripts && sudo sh ./populate.sh)

# insere este utilizador no docker postgres
# docker exec -it postgres psql -U postgres -d utilizadores -c "INSERT INTO cliente (idcliente, email, nome, password, profile_image, username)
# VALUES (1, 'cliente1@example.com', 'Cliente 1', 'cliente1', 'image1.png', 'cliente1');"

# cria a loja INSERT INTO loja (idloja, localizacao, marca, nome)
docker exec -it postgres psql -U postgres -d utilizadores -c "INSERT INTO loja (idloja, localizacao, marca, nome) VALUES (1, 'Braga', 'Independente', 'Os Criadores');"

# # crai a loja no catalogo
# docker exec -it postgres psql -U postgres -d catalogo -c "INSERT INTO loja (idloja, localizacao, marca, nome) VALUES (1, 'Braga', 'Independente', 'Os Criadores');"

# INSERT INTO tecnico (clienteidcliente, lojaidloja) VALUES (1, 2); agora faz este insert
# docker exec -it postgres psql -U postgres -d utilizadores -c "INSERT INTO tecnico (clienteidcliente, lojaidloja) VALUES (1, 1);"

# # corre o encomenda service com mvn spring-boot:run 
# echo "Iniciando o encomendaService..."
# (cd encomendaService && mvn spring-boot:run &)

# # correr o utilizadores service
# echo "Iniciando o utilizadoresService..."
# (cd utilizadoresService && mvn spring-boot:run &)

# # correr o catalog service
# echo "Iniciando o catalogService..."
# (cd catalogService && mvn spring-boot:run &)

# # corre o api-gateway
# echo "Iniciando o API-GATEWAY..."
# (cd API-GATEWAY && npm start &)

# # corre o frontend
# echo "Iniciando o frontend..."
# (cd PackMyBag-frontend && npm run dev &)


