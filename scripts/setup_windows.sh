winpty docker run --name postgres -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=postgres -e PSQL_DATABASE=catalogo -p 5432:5432 -d postgres:latest

sleep 3

winpty docker start postgres

sleep 3

winpty docker exec -it postgres psql -U postgres -c "CREATE DATABASE catalogo;"
winpty docker exec -it postgres psql -U postgres -c "CREATE DATABASE favoritos;"
winpty docker exec -it postgres psql -U postgres -c "CREATE DATABASE encomenda;"
winpty docker exec -it postgres psql -U postgres -c "CREATE DATABASE cart;"
winpty docker exec -it postgres psql -U postgres -c "CREATE DATABASE notificacoes;"
winpty docker exec -it postgres psql -U postgres -c "CREATE DATABASE recomendacoes;"




 
