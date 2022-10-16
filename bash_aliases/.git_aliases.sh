#ALIAS INITIALIZED BY GIT BASH
#CENTRALIZED BY .bash_aliases FILE



alias sts='git status';


add() {
  if [ "$1" = "restore" ]
  then
    echo -e '\n @ RUNNING: git restore --staged .\n'; 
    git restore --staged .;
  elif [ "$1" = "." ]
  then
    echo -e '\n @ RUNNING: git add '"$1"'\n';
    git add .;
  else
    echo -e '\n @ RUNNING: git add '"$1"'\n'; 
    git add "$1";
  fi
  git status;
}

com() {
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
