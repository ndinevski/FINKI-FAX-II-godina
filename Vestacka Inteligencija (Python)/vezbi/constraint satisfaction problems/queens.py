from constraint import *


def queen_attack(q1, q2):
    if q1[0] == q2[0]:
        return False
    if q1[1] == q2[1]:
        return False
    if abs(q1[0] - q2[0]) == abs(q1[1] - q2[1]):
        return False
    return True


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    n = int(input())
    domain = [(x,y) for x in range(n) for y in range(n)]
    variables = [x+1 for x in range(n)]

    problem.addVariables(variables, domain)

    # ---Tuka dodadete gi ogranichuvanjata----------------
    for queen1 in variables:
        for queen2 in variables:
            if queen1 < queen2:
                problem.addConstraint(queen_attack, (queen1,queen2))
    # ----------------------------------------------------

    if n<=6:
        print(len(problem.getSolutions()))
    else:
        print(problem.getSolution())

