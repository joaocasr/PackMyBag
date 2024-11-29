# PackMyBag

Startup que oferece um serviço de aluguer de roupas diretamente no destino de viagem, eliminando a necessidade de transportar malas. A solução promove sustentabilidade e conveniência para viajantes.

# Informações

## Branches do Repositório

O repositório do **PackMyBag** possui dois branches principais:

- **main**: Destinado ao desenvolvimento contínuo. Todas as novas funcionalidades e melhorias são integradas neste branch antes de serem testadas e preparadas para produção.
- **production**: Configurado para deploy em ambiente de produção através da cloud. Este branch contém o código estável e pronto para ser utilizado pelos utilizadores finais.

> **Nota:** Recomenda-se utilizar o branch **main** para desenvolvimento e testes locais, e o branch **production** para deploy na nuvem.


Para testar a aplicação **Pack My Bag**, certifique-se de que as seguintes dependências estão instaladas no seu sistema:

- **Docker** (versão 20.10 ou superior)
- **Docker Compose**
- **Maven** (versão 3.6.0 ou superior)
- **NPM** (versão 6.14 ou superior)
- **Java JDK** (versão 11 ou superior)

> **Nota:** O backend é implementado em **Java Spring Boot**, portanto, é necessário ter o Java JDK instalado no seu sistema.

## Preparação

O repositório não inclui os arquivos **.jar** devido ao seu tamanho esceder o permitido pelo github. Portanto, é necessário compilar todos os microserviços antes de testar a aplicação.

Além disso, os microserviços Java Spring Boot dependem de uma base de dados PostgreSQL em execução no Docker. Sem ela, os serviços não conseguirão criar as tabelas necessárias e poderão gerar erros.


### Configurar a Base de Dados PostgreSQL

Execute o script `setup.sh` para iniciar o PostgreSQL no Docker e criar os bancos de dados necessários:

```bash
./scripts/setup.sh
```

### Compilar e Executar Microserviços Individualmente

1. Clonar o Repositório e Navegar para a Raiz:

```bash
git clone <repository-url>
cd packmybag
```

2. Compilar Microserviços Java Spring Boot:

```bash
mvn clean install
mvn spring-boot:run
```

Para cada microserviço (por exemplo, `catalogService`, `encomendaService`, etc.), navegue para a pasta correspondente e execute os comandos acima.

**Portas dos Microserviços:**

- `catalogService`: http://localhost:8081
- `encomendaService`: http://localhost:8082
- `favoritosService`: http://localhost:8083
- `recomendacoesservice`: http://localhost:8084
- `cartService`: http://localhost:8085
- `notificacoesService`: http://localhost:8086
- `utilizadoresService`: http://localhost:8087

### Compilar e Executar o API Gateway:

Navegue para a pasta `API-GATEWAY` e execute:

```bash
cd API-GATEWAY
npm install
npm start
```

Porta: http://localhost:8888

### Compilar e Executar o Frontend:

Navegue para a pasta `PackMyBag-frontend` e execute:

```bash
cd PackMyBag-frontend
npm install
npm run dev
```

Porta: http://localhost:5173


## Executar a Aplicação com Docker Compose

Para iniciar toda a aplicação de forma simplificada utilizando Docker Compose, siga os passos abaixo:

1. Certifique-se de que o Docker está em execução e que tem os **.jar** de todos os microserviços.

2. Executar Docker Compose:

Na raiz do projeto, execute:

```bash
docker-compose up --build
```

Este comando irá construir as imagens Docker e iniciar todos os contêineres necessários para a aplicação.

**Portas dos Microserviços com Docker Compose:**

- Frontend: http://localhost:3000

### Executar a Aplicação com o `build_and_run.sh` 

Uma maneira mais fácil de compilar todos os microserviços e iniciar a aplicação é utilizar o script `build_and_run.sh`. Este script automatiza o processo de compilação dos microserviços e a execução do Docker Compose, simplificando significativamente o fluxo de trabalho.

#### Passos para Utilizar o Script

1. **Certifique-se de que o Docker está em execução e que você possui os arquivos `.jar` de todos os microserviços.**

2. **Tornar o Script Executável (se necessário):**

    Se o script `build_and_run.sh` não tiver permissões de execução, você pode torná-lo executável com o seguinte comando:

    ```bash
    chmod +x build_and_run.sh
    ```

3. **Executar o Script:**

    Na raiz do projeto, execute o seguinte comando para compilar todos os microserviços e iniciar os contêineres Docker:

    ```bash
    ./build_and_run.sh
    ```

    Este comando realizará as seguintes ações:
    
    - **Compilação dos Microserviços:** Navega para cada diretório de microserviço e executa `mvn clean install` para compilar os projetos Java Spring Boot.
    
    - **Início dos Contêineres Docker:** Após a compilação, executa `docker-compose up --build` para construir as imagens Docker e iniciar todos os contêineres necessários para a aplicação.

## Verificação

Após a execução, acesse o frontend via navegador:

- Modo Individual: http://localhost:5173
- Com Docker Compose: http://localhost:3000




# Arquitetura da aplicação e de cada microserviço

## Modelo de Use Cases atual

<img src="/modeling/Use Case Diagram.jpg" width="450px" />

## Modelo de Domínio atual

<img src="/modeling/Domain Model.jpg" width="550px" />

## Diagrama de Arquitetura

<img src="/modeling/Diagrama de Arquitetura.jpg" width="700px" />


## Diagrama de Classes Catalogo

<img src="/modeling/CatalogoService.jpg" width="600px" />

## Diagrama de Classes Encomendas

<img src="/modeling/EncomendaService.jpg" width="600px" />

## Diagrama de Classes Favoritos

<img src="/modeling/FavoritosService.jpg" width="600px" />


## Diagrama de Classes Recomendacoes

<img src="/modeling/RecomendacoesService.jpg" width="600px" />

## Diagrama de Classes Cesto

<img src="/modeling/CestoService.jpg" width="600px" />

## Diagrama de Classes Notificacoes

<img src="/modeling/NotificacoesService.jpg" width="600px" />
