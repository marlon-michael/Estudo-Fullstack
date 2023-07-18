#ALIAS INITIALIZED BY GIT BASH
#CENTRALIZED BY .bash_aliases FILE


#ALIASES
alias src='source';

go(){
    cd "$1";
    ls;
}

#LOAD IN FILE ALIASES
src bash_aliases/git_aliases.sh;
src bash_aliases/path_aliases.sh;
src bash_aliases/curl.sh

echo ">>> ALIASES HEVE BEEN LOADED <<<";
echo '--------------------------------';
