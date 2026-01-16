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
		
- \$1: 
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
		
	* \"_\" or \"\"	: commit as \"nothing to report\"
	* \"message\"	: add specified message
	* -p		: exec git push at the end
		"
		return;
	fi
	
	doPush(){
		echo -e "\n>>> Running:[ git push ]\n";
		command git push;
	}
	
	doEmptyCommit(){
		echo -e '\n>>> Running:[ git commit -m "nothing to report" ]\n';
		git commit -m "default commit";
	}
	
	doCommit(){
		echo -e '\n>>> Running:[ git commit -m '"$gitAliases_messageToCommit"' ]\n';
		git commit -m "$gitAliases_messageToCommit"
	}
	
	local gitAliases_messageToCommit=""
	local initCommand=()
	local endingCommand=()
	for arg in "$@"; do
        if [[ "$arg" = *"-"* ]]
        then
        	if [[ "$arg" = "-p" ]]
        	then
        		endingCommand+=("doPush");
        	fi
        else
        	if [ "$arg" = "" ] || [ "$arg" = "_" ]
        	then
				initCommand+=("doEmptyCommit");
			else
				gitAliases_messageToCommit="$arg"
				initCommand+=("doCommit");
			fi
        fi
    done
    
	for cmd in "${initCommand[@]}"; do "$cmd"; done
    for cmd in "${endingCommand[@]}"; do "$cmd"; done
	
	git status;
}
