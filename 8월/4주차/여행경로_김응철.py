import collections

answer = []
graph = collections.defaultdict(list)

def dfs(start):
    while graph[start]:
        dfs(graph[start].pop(0))

    if not graph[start]:
        answer.append(start)
        return

def solution(tickets):
    for a, b in tickets:
        graph[a].append(b)
    for a, b in graph.items():
        graph[a].sort()

    dfs("ICN")

    return answer[::-1]

print(solution([["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]))
print(solution([["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL", "SFO"]]))
