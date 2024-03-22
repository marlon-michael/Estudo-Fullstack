# Docker - conteinerização de aplicações

docker é uma maneira de facilitar padronizar o ambiente de desenvolvimento entre varios desenvolvedores, se utilizando de containers para isso. 

Containers são aplicações que rodam de forma isolada do sistema, docker utiliza a kernel do sistema para contruir cada aplicação de forma individual com suas proprias configurações de rede, portas, variaveis de ambiente e diretorios que são criados sempre da mesma forma de acordo com as definições no arquivo <b>dockerfile</b>

#### indice
- [boas praticas](#boas-praticas)
- [utilidades gerais docker](#padrões)
- [conteiners docker](#container)
- [imagens docker](#imagens-docker)
- [definir imagem docker](#definir-imagem)
- [mapear volumes do host para container](#volumes)
- [redes de containers](#redes-docker)
- [variaveis de ambiente](#variaveis-de-ambiente)
- [limpar espaço e deletar arquivos não utilizados](#liberar-espaço)

## boas praticas

- definir de forma explicita a versão dos programas utilizadas
  - não deixar claro para o docker a versão das aplicações utilizada pode ocasionar em incompatibilidades conforme novas versões de um mesmo programa são lançadas

## padrões

- para executar um comando com todas as imagens docker voce pode executar:
```console
docker COMANDO $(docker images -a -q)
```

## container

- listar todos os containers
```console
docker ps -a
```

- criar container integrado
```console
docker run it ID-NOME_CONTAINER
```

- criar container em segundo plano
```console
docker run -d ID-NOME_CONTAINER
```

- criar container nomeado
```console
docker run -d --name LABEL_CONTAINER ID-NOME_CONTAINER
```

- mapear portas do docker para local
```console
# docker run -d -p [porta-local]:[porta-container] ID-NOME_CONTAINER
docker run -d -p 80:3000 nginx
```

- iniciar container existente
```console
docker start ID-NOME_CONTAINER
```

- interagir com o container em segundo plano / executar aplicações no container
```console
# iniciar terminal bash dentro do container
docker exec -it ID-NOME_CONTAINER bash

# iniciar mysql no usuario root protegido por senha
docker exec -it ID-NOME_CONTAINER  mysql -u root -p
```

- ver logs do container
```console
docker logs ID-NOME_CONTAINER
```

- finalizar container em segundo plano
```console
docker stop ID-NOME_CONTAINER
```

- remover container
```console
docker rm ID-NOME_CONTAINER

# forçar exclusão
docker rm ID-NOME_CONTAINER -f 
```

## imagens docker

- listar imagens docker
```console
docker image ls
```

- baixar imagem docker
```console
docker pull NOME_IMAGEM
```

- remover imagem docker
```console
docker rmi ID-NOME_IMAGEM
```

## volumes

- definir volumes
```console
docker volume create NOME_VOLUME
```

- listar volumes
```console
docker volume ls
```

- inspecionar dados do volume
```console
docker volume inspect NOME_VOLUME
```

- iniciar container utilizando o volume
```console
docker run -v NOME_VOLUME:/diretorio_de_montagem/db NOME_CONTAINER
```

## redes docker

por padrão as redes docker podem se comunicar, mas é possível criar isolar as redes de cada container criando uma rede específica para cada um

- criar rede
```console
docker network create NOME_REDE
```

- listar redes
```console
docker network ls
```

- iniciar container com rede especifica
```console
docker run --network NOME_REDE NOME_CONTAINER
```

- iniciar container com rede especifica com alias para interagir com outros containers
```console
docker run --network NOME_REDE NOME_CONTAINER --network-alias NOME_ALIAS
```

## variaveis de ambiente

- passar variaveis de ambiente por argumento
```console
docker run -e NOME_VARIAVEL=VALOR_VARIAVEL NOME_CONTAINER
```

## liberar espaço

- limpar containers parados e imagens não utilizadas
```console
docker system prune
```

## definir imagem

- criar dockerfile para aplicação em node
```docker
# definir base inicial da imagem (linguagem do projeto)
FROM node

# define diretório do projeto no cantainer
WORKDIR /user/src/app

# copiar lista de dependencias para diretorio no container
COPY package.json /user/src/app/

# instalar dependencias
RUN npm install

# copiar aplicação completa para o container
COPY . .

# expor porta 3000 (porta exposta pelo arquivo index.js)
EXPOSE 3000

# comando para iniciar aplicação, separados por palavra em um array
CMD [ "node", "index.js" ]
```

- criar e nomear imagem apartir do dockerfile na pasta atual
```console
docker build -t nome-imagem .
```
