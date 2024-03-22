# Docker Compose

docker compose é um orquestrador de containers que auxilia na utilização de projetos com multiplas aplicações docker, por exemplo: um container meu_app, um container com o nginx e um outro container mysql, com multiplas instancias, váriaveis de ambiente e mapeamento de portas. Tendo sua declaração por meio de um arquivo <b>docker-compose.yml</b> que deve se encontrar no mesmo nivel dos dockerfile utilizados


- contruir containers
```console
docker compose build
```

- inicia docker-compose.yaml
```console
docker compose up
```

- reinicia docker-compose.yaml
```console
docker compose restart
```

- finaliza docker-compose.yaml
```console
docker compose down
```

- como escrever um docker-compose.yml

```docker
# versão do docker compose
version: 3.7

# aplicações
services:
  # nome do container
  node_app:
    container_name: "node_app"
    # procura dockerfile no diretorio atual para contruir container
    build: .
      #dockerfile: ./frontend/src/app # define caminho em do dockerfile
      #context: . # procura o dockerfile apartir do diretorio atual
    # define diretorio principal da aplicação o qual vai executar os comandos descritos
    working_dir: "user/src/app/"
    # informa dependencia do container
    depends_on:
      - "database"
    # reinicia a apicação sempre que parar de funcionar
    restart: always
    # expoe porta 3000
    expose:
      - 3000
    # define rede que container vai utilizar
    networks:
      - production-network
    # definição de volumes da maquina host (/output_container) para o container docker (/output)
    volumes:
      - /output_container:/output
    # execução dos cantainers
    command: "node index.js" # comandos para inicializar container
    # tty:true # comando para inicializar container em execução em segundo plano

  # nome do container
  database:
    # imagem e versão utilizados
    image: postgres:9.5 
    # variaveis de ambiente
    environment:
      - POSTFRES_DB=my_db
      - POSTGRES_USER=my_user
      - POSTGRES_PASSWORD=my_password
    # mapear portas
    ports:
      - "5432:5432"
    # define rede que container vai utilizar
    networks:
      - production-network

  networks:
    production-network:
      # define modo de rede para modo ponte
      driver: bridge

```