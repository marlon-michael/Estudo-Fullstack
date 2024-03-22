#ALIAS INITIALIZED BY GIT BASH
#CENTRALIZED BY .bash_aliases FILE


#ALIASES
alias src='source';

go(){
    cd "$1";
    ls;
}

#LOAD FILE ALIASES
path="./etc/profile.d"

src "$path"/bash_aliases/git_aliases.sh;
src "$path"/bash_aliases/path_aliases.sh;
src "$path"/bash_aliases/curl.sh
echo ">>> ALIASES LOADING HAS FINISHED <<<";
echo '--------------------------------';
