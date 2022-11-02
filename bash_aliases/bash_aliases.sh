#ALIAS INITIALIZED BY GIT BASH
#CENTRALIZED BY .bash_aliases FILE


#ROOT ALIASES
alias src='source';
# alias go='cd';

go(){
    cd "$1";
    ls;
}

#LOADING ALIASES
src bash_aliases/git_aliases.sh;
src bash_aliases/path_aliases.sh;

echo " -@ ALIASES HEVE BEEN LOADED";
