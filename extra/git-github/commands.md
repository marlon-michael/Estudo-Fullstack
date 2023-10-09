# comandos git

Git e Github.
------------------------------------

### conceitos universais

- para se referir a varios arquivos com um mesmo padrão é possivel usar:
    - [ *.js ] adiciona todos os arquivos com final .js para commit  
        git add *.js
___
### criação e inicialização

git init: inicia repositorio git
- para sincronização com repositorio online, siga as intruções no site
___
### configurações

git config --global http.proxy https://0.0.0.0:0000
git config --global http.proxy 0.0.0.0:0000
git config --global http.proxy "" : remover conexão proxy
___
### alterações e versionamento com commit: salvar, cerregar, e identificar

git status: lista modificações na branch

git log: mostra commits feitos

git show: mostra mudanças no ultimo commit

git show [codigo-commit]: mostra mudanças feitas no commit informado

git diff: mostra todas mudanças não adicionadas a commit

git diff [arquivo]: mostra mudanças feitas no arquivo

git add [arquivos]: adiciona arquivos para commit (separados por espaços)

git add -r [arquivos]: remove arquivos do commit (separados por espaços)

git add . : adiciona todos os arquivos para commit

git commit -m "[menssagem]" : guarda modificações adicionadas
___
### como desfazer alterações ou retornar a uma versão do código

git revert [commit]: cria um novo commit no mesmo estado do commit informado

git reset [commit]: deleta alterações e commits feitos após o commit informado

git reset HEAD^: defaz ultimo commit

git reset HEAD^3: desfaz ultimos 3 commits

git checkout [codigo-commit]: cria uma nova branch no estado do commit informado 

### como listar e retornar versão com stash

git stash: salva alterações sem fazer um commit

git stash -p: deixa escolher individualmente qual alteração/arquivo salvar
- [y] salvar
- [n] não salvar
- [q] cancelar
- [?] ajuda

git stash branch [nome-branch] [stash]: cria branch com base nos stashes

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

git branch [nome-branch]: cria nova branch

git push origin [nome-antigo-branch] [novo-nome-branch]: renomear branch remota

git branch -m [nome-antigo-branch] [novo-nome-branch]: renomear branch local

git push origin --delete [nome-branch]: deleta branch remota

git branch -d [nome-branch]: deleta branch local

git checkout -b [nome-branch]: cria nova branch e move para ela

git ceckout -b [nova-branch] [branch-base]: cria branch com base em outra branch

git checkout [nome-branch]: move para branch
___
### download e upload ao repositório remoto

git push : envia atualizações para o repositorio

git push [repositorio] [nome-branch]: faz o envio para a branch do repositorio informados

git push [repositorio] --delete [nome-branch]

git pull : baixa atualizações do repositorio

git pull [repositorio] [nome-branch]: baixa branch do repositorio informado

git remote: mostra nome do repositório

git clone [url-repositorio] : clona repositorio localmente

git remote add upstream [url-repositorio-original]: adiciona repositorio remoto

git fetch upstream: baixa repositirio
___
### merge

merge é uma funcionalidade git para juntarmos as atualizações de duas branches em uma só
- merge da main para feature

    git checkout [feature-branch]  
    git merge main
- repositorio local

    git checkout main  
    git merge [nome-branch]
- repositorio remoto

    git push --set-upstream [repositorio] [nome-branch]
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