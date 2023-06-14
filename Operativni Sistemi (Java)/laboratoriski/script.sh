#!/bin/bash

if [ $# -ne 1 ]
then
    echo "Nema dovolno argumenti"
fi


month=$1

average_kw=$(cat smth.txt | awk -F '|' 'BEGIN{cnt=0; sum=0}{sum+=$5; cnt++}END{print sum/(cnt-2)}')
people_with_more_kw=$(cat smth.txt | awk -v m="$month" -v avg="$average_kw" -F '|' 'NR>1{if($5>avg && $3==m){printf "%s %s\n\n", $1, $5}}')

echo $people_with_more_kw


