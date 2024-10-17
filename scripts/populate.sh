docker cp data/catalogo.sql postgres:/tmp/catalogo.sql

docker exec -it postgres psql -U postgres -d catalogo -f /tmp/catalogo.sql
