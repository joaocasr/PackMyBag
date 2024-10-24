docker run --name postgres -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=postgres -e PSQL_DATABASE=encomenda -p 5432:5432 -d postgres:latest

sleep 3

docker start postgres

sleep 3

docker exec -it postgres psql -U postgres -c "CREATE DATABASE encomenda;"
