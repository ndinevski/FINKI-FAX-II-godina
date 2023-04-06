from constraint import *

if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    AI_chasovi = []
    ML_chasovi = []
    R_chasovi = []
    BI_chasovi = []

    n = int(input())
    for i in range(1, n+1):
        AI_chasovi.append("AI_cas_{}".format(i))
    n = int(input())
    for i in range(1, n + 1):
        ML_chasovi.append("ML_cas_{}".format(i))
    n = int(input())
    for i in range(1, n + 1):
        R_chasovi.append("R_cas_{}".format(i))
    n = int(input())
    for i in range(1, n + 1):
        BI_chasovi.append("BI_cas_{}".format(i))

    AI_predavanja_domain = ["Mon_11", "Mon_12", "Wed_11", "Wed_12", "Fri_11", "Fri_12"]
    ML_predavanja_domain = ["Mon_12", "Mon_13", "Mon_15", "Wed_12", "Wed_13", "Wed_15", "Fri_11", "Fri_12", "Fri_15"]
    R_predavanja_domain = ["Mon_10", "Mon_11", "Mon_12", "Mon_13", "Mon_14", "Mon_15", "Wed_10", "Wed_11", "Wed_12",
                           "Wed_13", "Wed_14", "Wed_15", "Fri_10", "Fri_11", "Fri_12", "Fri_13", "Fri_14", "Fri_15"]
    BI_predavanja_domain = ["Mon_10", "Mon_11", "Wed_10", "Wed_11", "Fri_10", "Fri_11"]

    AI_vezbi_domain = ["Tue_10", "Tue_11", "Tue_12", "Tue_13", "Thu_10", "Thu_11", "Thu_12", "Thu_13"]
    ML_vezbi_domain = ["Tue_11", "Tue_13", "Tue_14", "Thu_11", "Thu_13", "Thu_14"]
    BI_vezbi_domain = ["Tue_10", "Tue_11", "Thu_10", "Thu_11"]

    # ---Tuka dodadete gi promenlivite--------------------
    problem.addVariables(AI_chasovi, AI_predavanja_domain)
    problem.addVariables(ML_chasovi, ML_predavanja_domain)
    problem.addVariables(R_chasovi, R_predavanja_domain)
    problem.addVariables(BI_chasovi, BI_predavanja_domain)
    problem.addVariable("AI_vezbi", AI_vezbi_domain)
    problem.addVariable("ML_vezbi", ML_vezbi_domain)
    problem.addVariable("BI_vezbi", BI_vezbi_domain)

    # ---Tuka dodadete gi ogranichuvanjata----------------


    # ----------------------------------------------------

    solution = problem.getSolution()

    print(solution)