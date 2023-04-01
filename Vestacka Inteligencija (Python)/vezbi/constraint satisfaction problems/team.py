from constraint import *

if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    variables = ["Participant 1", "Participant 2", "Participant 3", "Participant 4", "Participant 5"]
    domain = [] #values
    people = [] #people


    n = int(input())

    for i in range(n):
        str = input().split()
        value = float(str[0])
        name = str[1]
        people.append(name)
        domain.append(value)

    problem.addVariables(variables, domain)

    n = int(input())
    domain_leaders = []

    for i in range(n):
        str = input().split()
        value = float(str[0])
        name = str[1]
        people.append(name)
        domain.append(value)
        domain_leaders.append(value)

    problem.addVariable("Team leader", domain_leaders)

    variables.append("Team leader")

    #site razlicni
    problem.addConstraint(AllDifferentConstraint(), variables)

    #suma pomala od 100
    problem.addConstraint(MaxSumConstraint(100), variables)

    results = problem.getSolutions()
    max_sum = 0
    final_dictionary = dict()
    for dictionary in results:
        sum = 0
        for value in dictionary.values():
            sum+=value
        if(sum>max_sum):
            max_sum = sum
            final_dictionary = dictionary

    print("Total score: {:.1f}".format(max_sum))
    for key,value in final_dictionary.items():
        print("{}: {}".format(key ,people[domain.index(value)]))

