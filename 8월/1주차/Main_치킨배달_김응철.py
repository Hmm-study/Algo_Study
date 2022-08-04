from itertools import combinations

n,m = map(int,input().split())

town = [list(map(int,input().split())) for _ in range(n)]

home = []
chicken = []
chickenSub = []

for i in range(n):
    for j in range(n):
        if town[i][j] == 1:
            home.append([i,j])
        if town[i][j] == 2:
            chicken.append([i,j])

for i in range(len(chicken)+1):
    if i > m:
        break
    chickenSub = chickenSub + list(combinations(chicken,i))

minDis = 1e9

for c in chickenSub:
    chickenDis = 0
    for h in home:
        dis = 1e9
        for i in range(len(c)):
            if dis > abs(h[0] - c[i][0]) + abs(h[1] - c[i][1]):
                dis = abs(h[0] - c[i][0]) + abs(h[1] - c[i][1])
        chickenDis += dis
    if minDis > chickenDis:
        minDis = chickenDis

print(minDis)
