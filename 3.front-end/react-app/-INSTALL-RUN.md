# Como criar um app React

### Conteudo e tecnologias abordadas - indices
- construtor de projetos React
    - [Vite](#vite-react--typescript)
        - [criar projeto](#criando-projeto-com-vite)
        - [iniciar e rodar](#iniciando-aplicação-com-vite)
    - [create-react-app (obsoleto)](#create-react-app--javascript)
        - [criando projeto](#gerando-aplicação-com-create-react-app)
        - [iniciar e rodar](#iniciando-aplicação-com-create-react-app)

---
### pré requisitos: 
 - node.js
---
## create-react-app + javascript

### gerando aplicação com create-react-app
- comandos
    ```console
    npx create-react-app name-of-project
    ```
### iniciando aplicação com create-react-app
- instalando dependencias
    ```console
    npm install
    npm install @types/react @types/react-dom
    ```
- rodando aplicação
    ```console
    npm start
    ```
---
## Vite: React + typescript

### criando projeto com vite
- comandos npm
    ```console
    npm create vite@lastest
    ```
- escolhas
    - nome do projeto
    - templates: React, Vue, Svelte, e outros
    - linguagens: typescript, javascript

### iniciando aplicação com vite
- instalando dependencias
    ```console
    npm install
    npm install @types/react @types/react-dom
    ```
- rodando
    ```console
    npm run dev
    ```