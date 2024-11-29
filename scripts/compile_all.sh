cd \cartService
mvn clean package -DskipTests
cd ..

sleep 3

cd \catalogService
mvn clean package -DskipTests
cd ..

sleep 3

cd \encomendaService
mvn clean package -DskipTests
cd ..

sleep 3

cd \favoritosService
mvn clean package -DskipTests
cd ..

sleep 3

cd \notificacoesService
mvn clean package -DskipTests
cd ..

sleep 3

cd \recomendacoesservice
mvn clean package -DskipTests
cd ..

sleep 3

cd \utilizadoresService
mvn clean package -DskipTests
cd ..










 
