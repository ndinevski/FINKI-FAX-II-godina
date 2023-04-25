from constraint import *


def check_variables(v1, v2):
    parts = v1.split("_")
    parts2 = v2.split("_")
    hour1 = int(parts[1])
    day1=parts[0]
    hour2 = int(parts2[1])
    day2 = parts2[0]

    if day1==day2:
        if abs(hour1-hour2)==1:
            return False
    return True

def machine_problem(cas, vezbi):
    hour1 = int(cas.split("_")[1])
    hour2 = int(vezbi.split("_")[1])
    if hour1 == hour2:
        return False
    return True



if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    casovi_AI = int(input())
    casovi_ML = int(input())
    casovi_R = int(input())
    casovi_BI = int(input())
    variables = []
    ml_casovi = []

    AI_predavanja_domain = ["Mon_11", "Mon_12", "Wed_11", "Wed_12", "Fri_11", "Fri_12"]
    ML_predavanja_domain = ["Mon_12", "Mon_13", "Mon_15", "Wed_12", "Wed_13", "Wed_15", "Fri_11", "Fri_12", "Fri_15"]
    R_predavanja_domain = ["Mon_10", "Mon_11", "Mon_12", "Mon_13", "Mon_14", "Mon_15", "Wed_10", "Wed_11", "Wed_12",
                           "Wed_13", "Wed_14", "Wed_15", "Fri_10", "Fri_11", "Fri_12", "Fri_13", "Fri_14", "Fri_15"]
    BI_predavanja_domain = ["Mon_10", "Mon_11", "Wed_10", "Wed_11", "Fri_10", "Fri_11"]

    AI_vezbi_domain = ["Tue_10", "Tue_11", "Tue_12", "Tue_13", "Thu_10", "Thu_11", "Thu_12", "Thu_13"]
    ML_vezbi_domain = ["Tue_11", "Tue_13", "Tue_14", "Thu_11", "Thu_13", "Thu_14"]
    BI_vezbi_domain = ["Tue_10", "Tue_11", "Thu_10", "Thu_11"]

    for i in range(casovi_AI):
        problem.addVariable("AI_cas_{}".format(i+1), AI_predavanja_domain)
        variables.append("AI_cas_{}".format(i+1))
    for i in range(casovi_ML):
        problem.addVariable("ML_cas_{}".format(i+1), ML_predavanja_domain)
        variables.append("ML_cas_{}".format(i + 1))
        ml_casovi.append("ML_cas_{}".format(i + 1))
    for i in range(casovi_R):
        problem.addVariable("R_cas_{}".format(i+1), R_predavanja_domain)
        variables.append("R_cas_{}".format(i + 1))
    for i in range(casovi_BI):
        problem.addVariable("BI_cas_{}".format(i+1), BI_predavanja_domain)
        variables.append("BI_cas_{}".format(i + 1))

    problem.addVariable("AI_vezbi", AI_vezbi_domain)
    problem.addVariable("ML_vezbi", ML_vezbi_domain)
    problem.addVariable("BI_vezbi", BI_vezbi_domain)

    variables.append("AI_vezbi")
    variables.append("ML_vezbi")
    variables.append("BI_vezbi")



    # ---Tuka dodadete gi ogranichuvanjata----------------

    problem.addConstraint(AllDifferentConstraint(), variables)

    for v1 in variables:
        for v2 in variables:
            if v1 < v2:
                problem.addConstraint(check_variables, (v1,v2))

    for cas in ml_casovi:
        problem.addConstraint(machine_problem, (cas, "ML_vezbi"))
    # ----------------------------------------------------

    solution = problem.getSolution()

    print(solution)


