alias sts='git status';
alias goclass='cd ~ && cd Documents/git/entra21-java-2022';
alias gotcc='cd ~ && cd Documents/git/TCC-Entra21';


function add(){
  if [ "$1" == "" ]
  then
    git add . ;
  elif [ "$1" == "-r" ]
  then
    if [ "$2" == "" ]
    then
      git restore --staged . ;
    else
      git restore --staged "$2" ;
    fi
  else
    git add "$1" ;
  fi
  git status;
}

function com() {
  if [ "$1" = "" ]
  then
    echo default commit;
    git commit -m "Entra21-2022";
  else
    echo commit: $1;
    git commit -m "$1";
  fi
  git push;
}
