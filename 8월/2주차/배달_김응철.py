import heapq

def dijkstra(start,N,town):

    q = []
    distance = [1e9] * (N+1)
    heapq.heappush(q,(0,start))
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)
        if distance[now] < dist:
            continue
        for t in town[now]:
            cost = dist + t[1]
            if cost < distance[t[0]]:
                distance[t[0]] = cost
                heapq.heappush(q,(cost,t[0]))

    return distance

def solution(N, road, K):
    answer = 0

    town = [[] for _ in range(N+1)]

    for r in road:
        town[r[0]].append([r[1],r[2]])
        town[r[1]].append([r[0],r[2]])

    dis = dijkstra(1,N,town)

    for d in dis:
        if d <= K:
            answer += 1

    return answer