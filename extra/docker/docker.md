# Docker - conteinerização de aplicações

docker é uma maneira de facilitar padronizar o ambiente de desenvolvimento entre varios desenvolvedores, se utilizando de containers para isso. 

Containers são aplicações que rodam de forma isolada do sistema, docker utiliza a kernel do sistema para contruir cada aplicação de forma individual com suas proprias configurações de rede, portas, variaveis de ambiente e diretorios que são criados sempre da mesma forma de acordo com as definições no arquivo <b>dockerfile</b>

#### indice
- [conteiners docker](#container)
- [imagens docker](#imagens-docker)
- [construir imagem docker](#definir-imagem)
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
docker run it imagem-docker
```

- criar container em segundo plano
```console
docker run -d imagem-docker
```

- criar container nomeado
```console
docker run -d --name nome-docker-container imagem-docker
```

- mapear portas do docker para local
```console
# docker run -d -p [porta-local]:[porta-container] imagem-docker
docker run -d -p 80:3000 nginx
```

- iniciar container existente
```console
docker start id-container
```

- finalizar container em segundo plano
```console
docker stop id-ou-nome-container
```

- interagir com o container em segundo plano
```console
docker exec -it nome-container bash
```

- ver logs do container
```console
docker logs nome-ou-id-container
```

- remover container
```console
docker rm id-ou-nome-continer

# forçar exclusão
docker rm id-ou-nome-continer -f 
```

## imagens docker

- listar imagens docker
```console
docker image ls
```

- baixar imagem docker
```console
docker pull nome-da-imagem
```

- remover imagem docker
```console
docker rmi id-ou-nome-imagem
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

## liberar espaço

- limpar containers parados e imagens não utilizadas
```console
docker system prune
```
