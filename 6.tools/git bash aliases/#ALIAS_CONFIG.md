
# INSTRUÇÕES

- encontre a pasta de scripts da inicialização do git bash

    Ex: 

    > C:\Users\{seu-usuario}\Programs\Git\etc\profile.d
    
    > /home/{seu-usuario}/.bashrc


- insira os scripts na pasta (profile.d)

```console
# CARREGANDO ALIASES EXTRAS

dir=$(pwd);
cd /c;
source bash_aliases/bash_aliases.sh;
cd "${dir:0:2}";
cd "${dir:0,3}";
ls;
```


