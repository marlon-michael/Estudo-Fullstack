#ALIAS INITIALIZED BY GIT BASH
#CENTRALIZED BY .bash_aliases FILE



alias gt='git status';
alias push='git push';

add() {
  if [ "$1" = "-r" ]
  then
    echo -e '\n @ RUNNING: git restore --staged '"$2"'\n'; 
    if [ "$2" = "." ] || [ "$2" = "" ]
    then
      git restore --staged . ;
    else
      git restore --staged "$2" ; 
    fi
  elif [ "$1" = "." ] || [ "$1" = "" ]
  then
    echo -e '\n @ RUNNING: git add .\n';
    git add . ;
  else
    echo -e '\n @ RUNNING: git add '"$1"'\n'; 
    git add "$1" ;
  fi
  git status;
}

pushm() {
  if [ "$1" = "" ]
  then
    echo -e '\n @ RUNNING: git commit -m "Entra21 Java - 2022"\n';
    git commit -m "Entra21 Java - 2022";
  else
    echo -e '\n @ RUNNING: git commit -m: '"$1"'\n';
    git commit -m "$1";
  fi
  git push;
  echo ---;
  git status;
}
