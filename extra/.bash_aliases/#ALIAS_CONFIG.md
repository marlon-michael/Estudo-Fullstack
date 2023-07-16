
# INSTRUÇÕES

- encontre a o arquivo de carregamento do git bash
> Ex: C:\Users\user_name\Programs\Git\etc\profile.d

- insira os comandos para carregamento para pasta dos aliases (profile.d)

```console
# CARREGANDO ALIASES EXTRAS

dir=$(pwd);
cd /d;
source bash_aliases/bash_aliases.sh;
cd "${dir:0:2}";
cd "${dir:0,3}";
ls;
```


