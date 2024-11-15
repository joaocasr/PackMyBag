docker exec -it postgres psql -U postgres -c "CREATE DATABASE utilizadores;"

docker cp data/utilizadores.sql postgres:/tmp/utilizadores.sql

docker exec -it postgres psql -U postgres -d utilizadores -f /tmp/utilizadores.sql