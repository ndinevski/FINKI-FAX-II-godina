import bisect


class Problem:
    def __init__(self, initial, goal=None):
        self.initial = initial
        self.goal = goal

    def successor(self, state):
        """Given a state, return a dictionary of {action : state} pairs reachable
        from this state. If there are many successors, consider an iterator
        that yields the successors one at a time, rather than building them
        all at once.

        :param state: given state
        :return:  dictionary of {action : state} pairs reachable
                  from this state
        :rtype: dict
        """
        raise NotImplementedError

    def actions(self, state):
        """Given a state, return a list of all actions possible
        from that state

        :param state: given state
        :return: list of actions
        :rtype: list
        """
        raise NotImplementedError

    def result(self, state, action):
        """Given a state and action, return the resulting state

        :param state: given state
        :param action: given action
        :return: resulting state
        """
        raise NotImplementedError

    def goal_test(self, state):
        """Return True if the state is a goal. The default method compares
        the state to self.goal, as specified in the constructor. Implement
        this method if checking against a single self.goal is not enough.

        :param state: given state
        :return: is the given state a goal state
        :rtype: bool
        """
        return state == self.goal

    def path_cost(self, c, state1, action, state2):
        """Return the cost of a solution path that arrives at state2 from state1
        via action, assuming cost c to get up to state1. If the problem is such
        that the path doesn't matter, this function will only look at state2.
        If the path does matter, it will consider c and maybe state1 and action.
        The default method costs 1 for every step in the path.

        :param c: cost of the path to get up to state1
        :param state1: given current state
        :param action: action that needs to be done
        :param state2: state to arrive to
        :return: cost of the path after executing the action
        :rtype: float
        """
        return c + 1

    def value(self):
        """For optimization problems, each state has a value.
        Hill-climbing and related algorithms try to maximize this value.

        :return: state value
        :rtype: float
        """
        raise NotImplementedError


"""
Definition of the class for node structure of the search.
The class Node is not inherited
"""


class Node:
    def __init__(self, state, parent=None, action=None, path_cost=0):
        """Create node from the search tree,  obtained from the parent by
        taking the action

        :param state: current state
        :param parent: parent state
        :param action: action
        :param path_cost: path cost
        """
        self.state = state
        self.parent = parent
        self.action = action
        self.path_cost = path_cost
        self.depth = 0  # search depth
        if parent:
            self.depth = parent.depth + 1

    def __repr__(self):
        return "<Node %s>" % (self.state,)

    def __lt__(self, node):
        return self.state < node.state

    def expand(self, problem):
        """List the nodes reachable in one step from this node.

        :param problem: given problem
        :return: list of available nodes in one step
        :rtype: list(Node)
        """
        return [self.child_node(problem, action)
                for action in problem.actions(self.state)]

    def child_node(self, problem, action):
        """Return a child node from this node

        :param problem: given problem
        :param action: given action
        :return: available node  according to the given action
        :rtype: Node
        """
        next_state = problem.result(self.state, action)
        return Node(next_state, self, action,
                    problem.path_cost(self.path_cost, self.state,
                                      action, next_state))

    def solution(self):
        """Return the sequence of actions to go from the root to this node.

        :return: sequence of actions
        :rtype: list
        """
        return [node.action for node in self.path()[1:]]

    def solve(self):
        """Return the sequence of states to go from the root to this node.

        :return: list of states
        :rtype: list
        """
        return [node.state for node in self.path()[0:]]

    def path(self):
        """Return a list of nodes forming the path from the root to this node.

        :return: list of states from the path
        :rtype: list(Node)
        """
        x, result = self, []
        while x:
            result.append(x)
            x = x.parent
        result.reverse()
        return result

    """We want the queue of nodes at breadth_first_search or
    astar_search to not contain states-duplicates, so the nodes that
    contain the same condition we treat as the same. [Problem: this can
    not be desirable in other situations.]"""

    def __eq__(self, other):
        return isinstance(other, Node) and self.state == other.state

    def __hash__(self):
        return hash(self.state)


"""
Definitions of helper structures for storing the list of generated, but not checked nodes
"""


class Queue:
    """Queue is an abstract class/interface. There are three types:
        Stack(): Last In First Out Queue (stack).
        FIFOQueue(): First In First Out Queue.
        PriorityQueue(order, f): Queue in sorted order (default min-first).
    """

    def __init__(self):
        raise NotImplementedError

    def append(self, item):
        """Adds the item into the queue

        :param item: given element
        :return: None
        """
        raise NotImplementedError

    def extend(self, items):
        """Adds the items into the queue

        :param items: given elements
        :return: None
        """
        raise NotImplementedError

    def pop(self):
        """Returns the first element of the queue

        :return: first element
        """
        raise NotImplementedError

    def __len__(self):
        """Returns the number of elements in the queue

        :return: number of elements in the queue
        :rtype: int
        """
        raise NotImplementedError

    def __contains__(self, item):
        """Check if the queue contains the element item

        :param item: given element
        :return: whether the queue contains the item
        :rtype: bool
        """
        raise NotImplementedError


class Stack(Queue):
    """Last-In-First-Out Queue."""

    def __init__(self):
        self.data = []

    def append(self, item):
        self.data.append(item)

    def extend(self, items):
        self.data.extend(items)

    def pop(self):
        return self.data.pop()

    def __len__(self):
        return len(self.data)

    def __contains__(self, item):
        return item in self.data


class FIFOQueue(Queue):
    """First-In-First-Out Queue."""

    def __init__(self):
        self.data = []

    def append(self, item):
        self.data.append(item)

    def extend(self, items):
        self.data.extend(items)

    def pop(self):
        return self.data.pop(0)

    def __len__(self):
        return len(self.data)

    def __contains__(self, item):
        return item in self.data


class PriorityQueue(Queue):
    """A queue in which the minimum (or maximum) element is returned first
     (as determined by f and order). This structure is used in
     informed search"""

    def __init__(self, order=min, f=lambda x: x):
        """
        :param order: sorting function, if order is min, returns the element
                      with minimal f (x); if the order is max, then returns the
                      element with maximum f (x).
        :param f: function f(x)
        """
        assert order in [min, max]
        self.data = []
        self.order = order
        self.f = f

    def append(self, item):
        bisect.insort_right(self.data, (self.f(item), item))

    def extend(self, items):
        for item in items:
            bisect.insort_right(self.data, (self.f(item), item))

    def pop(self):
        if self.order == min:
            return self.data.pop(0)[1]
        return self.data.pop()[1]

    def __len__(self):
        return len(self.data)

    def __contains__(self, item):
        return any(item == pair[1] for pair in self.data)

    def __getitem__(self, key):
        for _, item in self.data:
            if item == key:
                return item

    def __delitem__(self, key):
        for i, (value, item) in enumerate(self.data):
            if item == key:
                self.data.pop(i)


"""
Uninformed graph search
The main difference is that here we do not allow loops,
i.e. repetition of states
"""


def graph_search(problem, fringe):
    """Search through the successors of a problem to find a goal.
     If two paths reach a state, only use the best one.

    :param problem: given problem
    :param fringe: empty queue
    :return: Node
    """
    closed = {}
    fringe.append(Node(problem.initial))
    while fringe:
        node = fringe.pop()
        if problem.goal_test(node.state):
            return node
        if node.state not in closed:
            closed[node.state] = True
            fringe.extend(node.expand(problem))
    return None


def breadth_first_graph_search(problem):
    """Search the shallowest nodes in the search tree first.

    :param problem: given problem
    :return: Node
    """
    return graph_search(problem, FIFOQueue())


class Football(Problem):
    def __init__(self, initial, oponents, goals, goal=None):
        super().__init__(initial, goal)
        self.goals = goals
        self.oponents = oponents

    def actions(self, state):
        return self.successor(state).keys()

    def result(self, state, action):
        return self.successor(state)[action]

    def goal_test(self, state):
        return state[1] in goals

    def check_valid(self, man_pos, ball_pos, opponents):
        return man_pos[0] >= 0 and man_pos[0] < 8 and \
            man_pos[1] >= 0 and man_pos[1] < 6 and \
            ball_pos[0] >= 0 and ball_pos[0] < 8 and \
            ball_pos[1] >= 0 and ball_pos[1] < 6\
            and ball_pos not in opponents \
            and man_pos not in (opponents[0],opponents[9]) \
            and man_pos!=ball_pos


    def successor(self, state):
        successors = dict()

        man_pos_x = state[0][0]
        man_pos_y = state[0][1]
        ball_pos_x = state[1][0]
        ball_pos_y = state[1][1]

        man_pos = state[0]
        ball_pos = state[1]

        new_man_pos = man_pos_x, man_pos_y+1
        if new_man_pos == ball_pos:
            new_ball_pos = ball_pos_x, ball_pos_y + 1
        else:
            new_ball_pos = ball_pos
        if self.check_valid(new_man_pos, new_ball_pos, self.oponents):
            if new_ball_pos == ball_pos:
                successors["Pomesti coveche gore"] = (new_man_pos, new_ball_pos)
            else:
                successors["Turni topka gore"] = (new_man_pos, new_ball_pos)

        new_man_pos = man_pos_x, man_pos_y - 1
        if new_man_pos == ball_pos:
            new_ball_pos = ball_pos_x, ball_pos_y - 1
        else:
            new_ball_pos = ball_pos
        if self.check_valid(new_man_pos, new_ball_pos, self.oponents):
            if new_ball_pos == ball_pos:
                successors["Pomesti coveche dolu"] = (new_man_pos, new_ball_pos)
            else:
                successors["Turni topka dolu"] = (new_man_pos, new_ball_pos)

        new_man_pos = man_pos_x + 1, man_pos_y
        if new_man_pos == ball_pos:
            new_ball_pos = ball_pos_x + 1, ball_pos_y
        else:
            new_ball_pos = ball_pos
        if self.check_valid(new_man_pos, new_ball_pos, self.oponents):
            if new_ball_pos == ball_pos:
                successors["Pomesti coveche desno"] = (new_man_pos, new_ball_pos)
            else:
                successors["Turni topka desno"] = (new_man_pos, new_ball_pos)

        new_man_pos = man_pos_x + 1, man_pos_y - 1
        if new_man_pos == ball_pos:
            new_ball_pos = ball_pos_x + 1, ball_pos_y - 1
        else:
            new_ball_pos = ball_pos
        if self.check_valid(new_man_pos, new_ball_pos, self.oponents):
            if new_ball_pos == ball_pos:
                successors["Pomesti coveche dolu-desno"] = (new_man_pos, new_ball_pos)
            else:
                successors["Turni topka dolu-desno"] = (new_man_pos, new_ball_pos)

        new_man_pos = man_pos_x + 1, man_pos_y + 1
        if new_man_pos == ball_pos:
            new_ball_pos = ball_pos_x + 1, ball_pos_y + 1
        else:
            new_ball_pos = ball_pos
        if self.check_valid(new_man_pos, new_ball_pos, self.oponents):
            if new_ball_pos == ball_pos:
                successors["Pomesti coveche gore-desno"] = (new_man_pos, new_ball_pos)
            else:
                successors["Turni topka gore-desno"] = (new_man_pos, new_ball_pos)

        return successors

def check_valid_game(man_pos, ball_pos, oponents):
    if ball_pos[1] in (0,5) or man_pos[0]>=ball_pos[0] or man_pos in (oponents[0],oponents[9]) or ball_pos in oponents:
        return False
    return True

def generate_oponents(opponents):
    oponents = []
    for oponent in opponents:
        oponents.append((oponent[0], oponent[1]))
        oponents.append((oponent[0] + 1, oponent[1]))
        oponents.append((oponent[0] + 1, oponent[1] + 1))
        oponents.append((oponent[0] + 1, oponent[1] - 1))
        oponents.append((oponent[0], oponent[1] - 1))
        oponents.append((oponent[0], oponent[1] + 1))
        oponents.append((oponent[0] - 1, oponent[1] - 1))
        oponents.append((oponent[0] - 1, oponent[1]))
        oponents.append((oponent[0] - 1, oponent[1] + 1))
    return oponents

if __name__ == '__main__':
    man_pos = tuple(map(int, input().split(',')))
    ball_pos = tuple(map(int, input().split(',')))

    opponents = [(3, 3), (5, 4)]
    goals = [(7,2),(7,3)]
    oponents = generate_oponents(opponents)

    if check_valid_game(man_pos,ball_pos,oponents):
        football = Football((man_pos, ball_pos), oponents, goals)
        print(breadth_first_graph_search(football).solution())