req() {
    if [ "$1" = "" ]
    then
        echo "req [REQ TYPE] [URL] $'[JSON BODY]'"
        echo "REQ TYPEs:"
        echo " g - GET"
        echo " p - POST"
        echo " u - UPDATE"
        echo " d - DELETE"
    elif [ "$1" = "g" ]
    then
        curl -i -X GET $2;
    elif [ "$1" = "p" ]
    then
        curl -i -X POST $2 -H 'Content-Type: application/json' -d $3;
    elif [ "$1" = "u" ]
    then
        curl -i -X PATCH $2 -H 'Content-Type: application/json' -d $3;
    elif [ "$1" = "d" ]
    then
        curl -i -X DELETE $2
    fi
}