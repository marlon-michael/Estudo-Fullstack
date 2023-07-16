#ALIAS INITIALIZED BY GIT BASH
#CENTRALIZED BY .bash_aliases FILE


alias gs='git status';
alias gp='git push';

# git add
ga() {
  if [ "$1" = "-r" ]
  then
    echo -e '\n[ @ RUNNING: git restore --staged '"$2"' ]\n'; 
    if [ "$2" = "." ] || [ "$2" = "" ]
    then
      git restore --staged . ;
    else
      git restore --staged "$2" ; 
    fi
  elif [ "$1" = "." ] || [ "$1" = "" ]
  then
    echo -e '\n[ @ RUNNING: git add . ]\n';
    git add . ;
  else
    echo -e '\n[ @ RUNNING: git add '"$1" ]'\n'; 
    git add "$1" ;
  fi
  git status;
}

# git commit
gc() {
  if [ "$1" = "" ] || [ "$1" = "_" ]
  then
    echo -e '\n[ @ RUNNING: git commit -m "nothing to report" ]\n';
    git commit -m "default commit";
  else
    echo -e '\n[ @ RUNNING: git commit -m: '"$1"' ]\n';
    git commit -m "$1";
  fi
  if [ "$2" != "-bp" ]
  then
    echo -e "\n[ @ RUNNING: git push ]\n";
    git push;
  fi
  echo -----------;
  git status;
}
