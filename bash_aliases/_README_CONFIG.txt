
>>>find git/bash folder. 
Ex: C:\Users\user_name\Programs\Git\etc\profile.d


###########

>>> add source comand with a path to alias_loader:

cd ;  # -go to root
source bash_aliases/.bash_aliases.sh; # -load aliases loader in root folder

>>> or like this:

# LOAD PERSONEL ALIASES
dir=$(pwd);
cd /d;
source bash_aliases/bash_aliases.sh;
cd "${dir:0:2}";
cd "${dir:0,3}";
ls;

