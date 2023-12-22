# Nginx

nginx é um proxy reverso com multiplas utilidades, dentre elas a de redirecionamento de trafego, de servidor web e de balanceamento de carga.

utilizaremos o nginx por meio do docker. Podemos configurálo por meio de arquivos com extensão <b>.conf</b>, nas pastas <b>/etc/nginx/site-enabled/</b> ou então em <b>/etc/nginx/conf.d/</b> a depender da distribuição linux que escolhemos.

## comandos
- iniciar server
```console
service
```

## servidor web
```console
server{
  listen 80;
  listen [::]:80;

  # dominio do site
  server_name examplo.localhost;

  # endereço da pagina inicial caso não seja informado pelo usuario
  root /usr/share/nginx/nosso_app;

  # arquivos para pagina inicial caso não seja informado pelo usuario
  index index.php index.html;

  # parmite envio de arquivos e define limite
  sendfile on;
  client_max_body_size 512m

  # redireciona para os arquivos e pastas na ordem declarada: nome (arquivo) -> nome/ (pasta) -> 404 (not found)
  location / {
    try_fyles $uri $uri/ =404;
  }
}
```

## proxy reverso
```console
server{
  listen 80;
  listen [::]:80;

  # dominio do site
  server_name app1.localhost;

  location / {
    # permite o envio de headers para o servidor de origem
    proxy_set_header Host $host;
    proxy_set_header X-Real-IP $remote_addr;

    # servidor de origem
    proxy_pass http://localhost:8001;
  }
}

server{
  listen 80;
  listen [::]:80;

  # dominio do site
  server_name app2.localhost;

  location / {
    # permite o envio de headers para o servidor de origem
    proxy_set_header Host $host;
    proxy_set_header X-Real-IP $remote_addr;
    
    # servidor de origem
    proxy_pass http://localhost:8002;
  }
}
```

## balanceamento de carga
```console
# endereços alvos para rebalanceamento
upstream backend {
  server localhost:8001;
  server localhost:8002;
}

server{
  listen 80;
  listen [::]:80;

  server_name app.localhost; # endereço interceptado

  location / {
    # redirecionamento para alvos de rebalanceamento
    proxy_pass http://backend;
  }
}
```