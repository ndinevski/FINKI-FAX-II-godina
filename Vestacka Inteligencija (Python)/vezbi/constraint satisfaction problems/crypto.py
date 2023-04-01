from constraint import *

def function(*variables):
    line1 = variables[0] * 1000 + variables[1] * 100 + variables[2] * 10 + variables[3]
    line2 = variables[4] * 1000 + variables[5] * 100 + variables[6] * 10 + variables[1]
    line3 = variables[4] * 10000 + variables[5] * 1000 + variables[2] * 100 + variables[1] * 10 + variables[-1]
    return line1 + line2 == line3


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    variables = ["S", "E", "N", "D", "M", "O", "R", "Y"]
    for variable in variables:
        problem.addVariable(variable, Domain(set(range(10))))

    # ---Tuka dodadete gi ogranichuvanjata----------------

    # problem.addConstraint(lambda a: a != 0, "M")
    # problem.addConstraint(lambda a: a != 0, "S")
    problem.addConstraint(AllDifferentConstraint(), variables)

    problem.addConstraint(function, variables)
    # ----------------------------------------------------

    print(problem.getSolution())