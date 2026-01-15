#ALIAS INITIALIZED BY GIT BASH
#CENTRALIZED BY .bash_aliases FILE


alias gs='git status';
alias gp='git push';

# git add
ga() {
	if [ "$1" = "--help" ]
	then
		echo -e "
ga \$1 \$2
		
\$1: 
	* -r \$2		: remove file \$2 ( git restore --staged \"\$2\")
	* \".\" or \"\"	: add all new/updated files
	* \"file\"	: add specified file
		"
		return;
	fi
	
	if [ "$1" = "-r" ]
	then
		echo -e '\n>>> Running:[ git restore --staged '"$2"' ]\n'; 
		if [ "$2" = "." ] || [ "$2" = "" ]
		then
			git restore --staged . ;
		else
			git restore --staged "$2" ; 
		fi
	elif [ "$1" = "." ] || [ "$1" = "" ]
	then
		echo -e '\n>>> Running:[ git add . ]\n';
		git add . ;
	else
		echo -e '\n>>> Running:[ git add '"$1" ]'\n'; 
		git add "$1" ;
	fi
	git status;
}

# git commit
gc() {
	if [ "$1" = "--help" ]
	then
		echo -e "
gc \$1 \$2
		
\$1: 
	* \"_\" or \"\"	: commit as \"nothing to report\"
	* \"message\"	: add specified message
	
\$2: 
	* -bp		: dont exec git push at the end
		"
		return;
	fi
	if [ "$1" = "" ] || [ "$1" = "_" ]
	then
		echo -e '\n>>> Running:[ git commit -m "nothing to report" ]\n';
		git commit -m "default commit";
	else
		echo -e '\n>>> Running:[ git commit -m: '"$1"' ]\n';
		git commit -m "$1";
	fi
	if [ "$2" != "-bp" ]
	then
		echo -e "\n>>> Running:[ git push ]\n";
		git push;
	fi
	git status;
}
