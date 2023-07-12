#ALIAS INITIALIZED BY GIT BASH
#CENTRALIZED BY .bash_aliases FILE


alias status='git status';
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

up() {
  if [ "$1" = "" ]
  then
    echo -e '\n @ RUNNING: git commit -m "nothing to report"\n';
    git commit -m "default commit";
  else
    echo -e '\n @ RUNNING: git commit -m: '"$1"'\n';
    git commit -m "$1";
  fi
  _push;
  echo -----------;
  git status;
}

_push(){
  echo -----------;
  echo "[ENTER] to git push your commit";
  echo "[n] to skip git push";
  read option;
  if [ "$option" = "" ]
  then
    echo -e "\n @ RUNNING: git push\n"
    push;
  fi
}
