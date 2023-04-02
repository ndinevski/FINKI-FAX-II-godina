from constraint import *

if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())

    # 1, 2, 3, 4, 5, 6, 7 corresponding with weekdays monday to sunday
    week_days = dict()
    week_days[1] = "Monday"
    week_days[2] = "Tuesday"
    week_days[3] = "Wednesday"
    week_days[4] = "Thursday"
    week_days[5] = "Friday"
    week_days[6] = "Saturday"
    week_days[7] = "Sunday"

    # Activities Homework, Test, Laboratory, Theory, Practical - corresponding to letters in activities list
    activities = ["D", "S", "L", "T", "P"]

    # Variables are the activites and Domain is (1, 2,..., 7)
    problem.addVariables(activities, list(week_days.keys()))

    # All activities made in different days
    problem.addConstraint(AllDifferentConstraint(), activities)

    # D and L made in first 4 days
    problem.addConstraint(lambda day: day <= 4, "D")
    problem.addConstraint(lambda day: day <= 4, "L")

    # T before P, and T not next to P
    problem.addConstraint(lambda t, p: t < p, ["T", "P"])
    problem.addConstraint(lambda t, p: abs(t-p) not in (1, 6), ["T", "P"])

    # D, S, L must be before T
    problem.addConstraint(lambda t, d: t > d, ["T", "D"])
    problem.addConstraint(lambda t, s: t > s, ["T", "S"])
    problem.addConstraint(lambda t, l: t > l, ["T", "L"])

    # L must be on tuesday, thursday or saturday
    problem.addConstraint(lambda l: l in (2, 4, 6), "L")

    # Get one solution
    solution = problem.getSolution()

    #Printing
    for key, value in solution.items():
        print("{} : {}".format(week_days[value], key))
