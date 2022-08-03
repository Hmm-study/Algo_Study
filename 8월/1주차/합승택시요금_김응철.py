import heapq
def solution(n, s, a, b, fares):
    answer = 1e9
    dis = []
    graph = [[] for _ in range(n + 1)]

    def dijkstra(start):
        q = []
        distance = [1e9] * (n + 1)
        heapq.heappush(q,(0,start))
        distance[start] = 0

        while q:
            dist, now = heapq.heappop(q)
            if distance[now] < dist:
                continue
            for g in graph[now]:
                cost = dist + g[1]
                if cost < distance[g[0]]:
                    distance[g[0]] = cost
                    heapq.heappush(q,(cost,g[0]))

        return distance

    for i, j, k in fares:
        graph[i].append([j, k])
        graph[j].append([i, k])

    dis.append(dijkstra(s))
    dis.append(dijkstra(a))
    dis.append(dijkstra(b))

    for i in range(1,n+1):
        if answer > dis[0][i] + dis [1][i] + dis[2][i]:
            answer = dis[0][i] + dis [1][i] + dis[2][i]

    return answer