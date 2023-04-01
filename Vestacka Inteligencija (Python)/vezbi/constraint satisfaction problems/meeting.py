from constraint import *


def possible_times(simona, petar, marija, sostanok):
    marija_times = [14,15,18]
    petar_times = [12,13,16,17,18,19]

    if petar==1 and marija==1 and sostanok in marija_times and sostanok in petar_times:
        return True
    if petar==1 and marija==0 and sostanok in petar_times:
        return True
    if marija==1 and petar==0 and sostanok in marija_times:
        return True
    return False


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())

    # ---Dadeni se promenlivite, dodadete gi domenite-----
    problem.addVariable("Marija_prisustvo", [0, 1])
    problem.addVariable("Simona_prisustvo", [1])
    problem.addVariable("Petar_prisustvo", [0, 1])
    problem.addVariable("vreme_sostanok", [13, 14, 16, 19])
    # ----------------------------------------------------
    # ---Tuka dodadete gi ogranichuvanjata----------------
    problem.addConstraint(possible_times, ["Simona_prisustvo", "Petar_prisustvo", "Marija_prisustvo", "vreme_sostanok"])

    # ----------------------------------------------------

    [print(solution) for solution in problem.getSolutions()]