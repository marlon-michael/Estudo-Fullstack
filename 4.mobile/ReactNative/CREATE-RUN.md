
# Como iniciar um novo projeto com React Native + Expo

### Indices
- [requerimentos para começar](#requerimentos-e-instalações)
    - Node JS
    - [Expo - testando a aplicação](#instalando-expo)
    - [eas - build da aplicação](#instalando-eas-globalmente)
- [iniciando projeto com expo](#iniciando-projeto-com-expo)
- [iniciando projeto com npm](#iniciando-projeto-com-npm)
- [testando aplicativo](#testar-o-apicativo-com-aplicativo-expo)

## requerimentos e instalações
- #### instalando expo globalmente
    ```console
    npm install -g expo-cli
    ```
- #### instalando eas globalmente
    ```console
    npm install -g eas-cli
    ```
## iniciando projeto com expo
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
    npx expo start
    ```
    ou (deprecated)
    ```console
    expo start
    ```

## iniciando projeto com NPM
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

## testar o apicativo com aplicativo Expo
- instale o aplcativo da Playstore: Expo
- conecte o aparelho na mesma rede da maquina que roda o projeto
- escaneie o código QR

## configuração e build do projeto para android com eas
#### configuração
- em um terminal interativo faça o login por meio do expo ou eas
    ```console
    expo login
    ```
    ou
    ```console
    expo login -u [nome-de-usuario] -p [senha-da-conta]
    ```
- execute os seguintes comandos
    ```console
    eas build:configure -p android
    ```
    - responda se pretende gerar o projeto com configuração automática ou não
#### build
- execute os seguintes comandos
    ```console
    eas build -p android
    ```
    ou, se preferir, apenas
    ```console
    eas build
    > android
    > ios
    > all
    ```
    - selecionando a plataforma de destino
    - escreva o pacote preferido para o projeto
    - informe qual chave android usar
    - aguarde pelo cim do build, e entre na url informada ao fim do processo para acessar o aplicativo no formato .aab (android aplication bundle) que pode ser convertido também para .apk