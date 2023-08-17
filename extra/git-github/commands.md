# comandos git

Introdução ao Git e Github.
------------------------------------

### criação
git init: inicia repositorio git
- para sincronização com repositorio online, siga as intruções no site

git clone [link to repository] : clona repositorio localmente
___
### alterações e versionamento: salvar, cerregar, e identificar

git status : lista modificações na branch

git add [files]: adiciona arquivos para commit

git add -r [files]: remove arquivos do commit

git add . : adiciona todos os arquivos para commit

git commit -m "[message]" : guarda modificações adicionadas

git stash: salva alterações sem fazer um commit

git stash -p: deixa escolher individualmente qual alteração/arquivo salvar
- [y] salvar
- [n] não salvar
- [q] cancelar
- [?] ajuda

git stash branch [branch-name] [stash]: cria branch com base nos stashes

git stash list: lista salvamentos feitos

git stash apply: carrega ultimo salvamento

git stash pop: carrega ultimo salvamento removendo-o

git stash show: mostra diferenças entre salvamentos

git stash show -p: mostra modificações individualmente

git stash drop [stash]: deleta stash

git stash clear: deleta todos os stashes
___
### branches

git branch: lista todas as branches do repositorio

git branch [branch-name]: cria nova branch

git checkout -b [branch/name]: cria nova branch e move para ela

git ceckout -b [nova/branch] [branch/base]: cria branch com base em outra branch

git checkout [branch/name]: move para branch
___
### download e upload ao repositório

git push : envia atualizações para o repositorio

git pull : baixa atualizações do repositorio

git pull origin [branch/name]: baixa branch do repositorio
___
### merge

merge é uma funcionalidade git para juntarmos as atualizações de duas branches em uma só
- merge da main para feature

    git checkout [feature-branch]  
    git merge main
- repositorio local

    git checkout main  
    git merge [branch-name]
- repositorio remoto

    git push --set-upstream origin [branch-name]
___
### rebase

rebase é uma funcionalidade git que atualiza a base de uma branch, trazendo suas novas atualizações a branch atual, mas mantendo as modificações já feitas (não recomendado para introduzir features na branch principal em repositórios publicos. pois o rebase impede a utilização de commits anteriores)

- rebase da main para feature

    git checkout [feature-branch]  
    git rebase main
___
### ignorar pastas e arquivos em commits

 - #### o arquivo deve ser nomeado como " .gitignore "
- os arquivos e pastas a serem ignorados devem ser declarados da seguinte forma:
    - pastas
        > .pasta/  
        > .pasta/subpasta/
    - arquivos
        > .arquivo  
        > .pasta/arquivo
- ignorar arquivos após o commit

    git rm --cached [nome-arquivo/pasta]  
    git commit -m "arquivo ignorado"