from constraint import *

if __name__ == '__main__':
    input = input()
    problem = Problem(BacktrackingSolver())
    if input == "BacktrackingSolver":
        problem = Problem(BacktrackingSolver())
    elif input == "RecursiveBacktrackingSolver":
        problem = Problem(RecursiveBacktrackingSolver())
    elif input == "MinConflictsSolver":
        problem = Problem(MinConflictsSolver())

    variables = [x for x in range(81)]
    domain = [x+1 for x in range(9)]

    problem.addVariables(variables, domain)

    # ---Tuka dodadete gi ogranichuvanjata----------------
    for row in range(9):
        problem.addConstraint(AllDifferentConstraint(), [9*row+i for i in range(9)])
    for col in range(9):
        problem.addConstraint(AllDifferentConstraint(), [9*i+col for i in range(9)])
    for num in range(0,7,3):
        problem.addConstraint(AllDifferentConstraint(), [(9*i)+j+num for i in range(3) for j in range(3)])
    for num in range(0, 7, 3):
        problem.addConstraint(AllDifferentConstraint(), [(9 * i) + j + num + 27 for i in range(3) for j in range(3)])
    for num in range(0, 7, 3):
        problem.addConstraint(AllDifferentConstraint(), [(9 * i) + j + num + 54 for i in range(3) for j in range(3)])
    # ----------------------------------------------------

    print(problem.getSolution())