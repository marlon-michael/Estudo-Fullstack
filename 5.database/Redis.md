# Redis (cache e banco de dados)

Redis é um banco de dados chave-valor em memoria, o que significa que é executa de forma muito mais veloz em comparação com um banco de dados comum, sendo dessa forma excelente para armazenarmos valores com muitas requisições, assim acelerando nossas aplicações uma vez que não precisam consultar o banco de dados para obter os valores requisitado. 

Sendo um banco em memória isso também significa que ele custa mais caro do que os que armazenam diretamente no disco, além de pode piorar o desempenho do sistema caso seja utiliado na ocasião errada, portanto deve ser implementado apenas quando realmente é necessário o aumento de performance para o funcionamento da aplicação.

Há duas maneiras de garantir a persistencia dos dados com redis, a primeira funciona com um sistema de snapshots, ou sejá, é feito um backup do banco a cada X horas, minutos, etc. A segunda funciona por AOF (append on file), que descreve passo a passo de cada execução do banco de dados, podendo recriar o banco exatamente como estava no ultimo momento antes da parada. Ambos os arquivos podem ser armazenados em servidores separado, dessa forma, podendo recriar uma replica do banco de dados em outro servidor caso o principal esteja fora de funcionamento.

Podemos escalar redis utilizando replicas de apenas-leitura, o que melhoraria a disponibilidade dos dados e a velocidade para a leitura desses dados, no entanto estariamos multiplicando o uso de memoria para replicar exatamente os mesmos dados do servidor principal sem uma real melhora para a escrita de dados. Fragmentar (sharding) é uma pratica que consiste em dividir as inserções e requisições em diferentes servidores, podendo ser repartido por região ou algoritimos hash que dividem os usuários em porções, como por exemplo: usuários em que o primeiro nome começam da letra A até M utilizarão o servidor redis-1, já da letra N até Z no servidor redis-2, diminuindo o consumo de memoria e adicionando escrita a todos os servidores disponiveis.

Resolução de conflitos para um mesmo dado em diferentes servidores pode ser feita através do CRDT (conflict-free replicated data types), o qual vai identificar ação com maior prioridade ou a mais recente, por exemplo, se a chave "data1" for alterada no servidor A para "valor1" e depois no servidor B para "valor2", na sincronização dos servidores se manterá o "valor2", que é a atualização mais recente. já quando o conflito é entre uma atualização contra uma deleção, mesmo que a deleção seja mais recente o que será mantido é a ultima atualização.

## utilização

- após a instalação, para iniciar o banco de dados execute o comando
```console
redis-server
```
- para acessar a interface de comando execute
```console
redis-cli
```

## tipos de dados

- por padrão, qualquer chave adicionada é do tipo string (texto)

## chave comum
- para definir uma nova chave
```console
set nomeDaChave valorDaChave
```

- para ler o valor da chave
```console
get nomeDaChave
```

- para deletar a chave
```console
del nomeDaChave
```

- para verificar se a chave existe
```console
exists nomeDaChave

output:
(integer) 1 # retorna 1 caso existir
(integer) 0 # retorna 0 caso não existir
```

- listar chaves definidas
```console
keys *
```

## listas

- iniciar ou adicionar item a lista
```console
lpush listaDeItens [item1, item2]

lpush listaDeItens item3
```

- listar items da lista do primeiro ao ultimo item
> \> lrange listaDeItens [inicio] [ultimo]
```console
lrange listaDeItens 0 -1
```

- deletar e retornar ultimo item da lista
```console
lpop listaDeItens
```

## sets

- iniciar ou adicionar item ao set
```console
sadd setDeItens item1
```

- listar items do set
```console
smembers setDeItens
```

- verificar se está no set
```console
sismember setDeItens item3
```

- deletar item do set
```console
srem setDeItens item1
```

## sets ordenados

- iniciar ou adicionar item ao set
> \> zadd setOrdenado [valor] [item1]
```console
zadd setOrdenado 1 item1:counter
```

- incrementar contagem em 1
```console
zincrby setOrdenado 1 item1:counter
```

- listar item do set
```console
zscore setOrdenado item1:counter
```

- listar e ordenar set
> \> zrange setOrdenado [inicio] [ultimo]
```console
zrange setOrdenado 0 -1 # ordenado
zrevrange setOrdenado 0 -1 # ordenado na ordem inversa

zrangebyscore setOrdenado 0 +inf WITHSCORES # ordenado pelo valor de cada item mostrando o valor
zrangebyscore setOrdenado 0 +inf WITHSCORES # ordenado pelo valor de cada item na ordem inversa mostrando o valor
```

- quantidade de elementos no set
```console
zcard setOrdenado
```

- deletar item do set
```console

```


## hashes

- iniciar ou adicionar item ao hash
```console
hset nomeDoHash nomeDaChave1 valor1 # define uma chave de cada vez
hmset nomeDoHash nomeDaChave1 valor1 nomeDaChave2 valor2 # define multiplas chaves
```

- listar items do hash
```console
hget nomeDoHash nomeDaChave1
```

- listar todos items do hash
```console
hgetall nomeDoHash

output:
1) nomeDaChave1
2) valor1
3) nomeDaChave2
4) valor2
```

- deletar item do hash
```console
hdel nomeDoHash nomeDaChave1
```

- verificar se a chave do hash existe
```console
hexists nomeDoHash nomeDaChave1
```

## expiração de chaves

- definir tempo para expiração da chave para 60 segundos
```console
expire nomeDaChave 60
```

- definir chave, valor e tempo para expiração da chave para 60 segundos
```console
setex nomeDaChave 60 valorDaChave
```

- verificar segundos restantes para expiração de chave
```console
ttl nomeDaChave
```

- deletar chaves
```console
flushall
```