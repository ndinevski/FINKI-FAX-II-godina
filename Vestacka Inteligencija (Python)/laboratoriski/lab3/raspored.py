from constraint import *


def max_papers(*args):
    T1_counter = 0
    T2_counter = 0
    T3_counter = 0
    T4_counter = 0

    for paper in args:
        if paper == 'T1':
            T1_counter += 1
        elif paper == 'T2':
            T2_counter += 1
        elif paper == 'T3':
            T3_counter += 1
        else:
            T4_counter += 1

    return T1_counter <= 4 and T2_counter <= 4 and T3_counter <= 4 and T4_counter <= 4


if __name__ == '__main__':
    num = int(input())
    papers_by_topic = dict()
    papers = dict()
    paper_10 = ""

    paper_info = input()
    while paper_info != 'end':
        title, topic = paper_info.split(' ')
        papers[title] = topic
        papers_by_topic.setdefault(topic, list()).append(title + " (" + topic + ")")
        paper_info = input()

    # Tuka definirajte gi promenlivite
    variables = ["{} ({})".format(key, papers[key]) for key in papers.keys()]
    domain = [f'T{i + 1}' for i in range(num)]

    problem = Problem(BacktrackingSolver())

    # Dokolku vi e potrebno moze da go promenite delot za dodavanje na promenlivite
    problem.addVariables(variables, domain)

    # Tuka dodadete gi ogranichuvanjata
    problem.addConstraint(max_papers, variables)

    for topic in papers_by_topic:
        if len(papers_by_topic[topic]) <= 4:
            problem.addConstraint(AllEqualConstraint(), papers_by_topic[topic])

    result = problem.getSolution()

    for key in sorted(result):
        if key[:7] != "Paper10":
            print("{}: {}".format(key, result[key]))
        else:
            paper_10 = "{}: {}".format(key, result[key])
    print(paper_10)

