
# Como iniciar um novo projeto com React Native + Expo
- requerimentos
    - Node JS
    - [Expo](#instalando-expo)
- [iniciando projeto com expo](#iniciando-projeto-com-expo)
- [iniciando projeto com npm](#iniciando-projeto-com-npm)
- [testando aplicativo](#testar-o-apicativo-com-aplicativo-expo)

### instalando expo
- comando para instalar expo globalmente
    ```console
    npm install -g expo-cli
    ```

### iniciando projeto com expo
- comando para criar projeto
    - templates predefinidos possíveis
        - blank (em branco)
        - tabs
        - bare-minimun
    ```console
    expo init [nome-projeto] --template blank
    ```
    - após a criação do projeto entre na pasta criada
- inicie usando o comando
    ```console
    expo start
    ```

### iniciando projeto com NPM
- use o comando
    ```console
    npx create-expo-app [nome-projeto] 
    ```
- após a criação do projeto entre na pasta criada
- inicie usando os comandos
    ```console
    npm start # para iniciar todos ao mesmo tempo
    npm run android # para rodar no android
    npm run ios # para rodar no ios
    npm run web # para rodar no browser
    ```

- ### testar o apicativo com aplicativo Expo
    - instale o aplcativo da Playstore: Expo
    - conecte o aparelho na mesma rede da maquina que roda o projeto
    - escaneie o código QR
