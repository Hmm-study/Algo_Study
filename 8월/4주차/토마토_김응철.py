from collections import deque
N,M,H = map(int,input().split()) # N = 가로, M = 세로, H = 높이
graph = [[list(map(int, input().split())) for _ in range(M)] for _ in range(H)]
day = 0
# 1 = 익은 토마토, 0 = 익지 않은 토마토, -1 = 토마토 없는 칸
# 익은 토마토 옆의 익지 않은 토마토는 하루지나면 익게 됨 -> 익지 않은 토마토의 상하좌우위아래에 익은 토마토가 없으면 -1 출력
di = [-1,1,0,0,0,0]
dj = [0,0,-1,1,0,0]
dh = [0,0,0,0,-1,1]
deq = deque()
def bfs():
    while deq:
        nowh,nowi,nowj = deq.popleft()
        for d in range(6):
            nexti = nowi + di[d]
            nextj = nowj + dj[d]
            nexth = nowh + dh[d]
            if -1<nexth<H and -1< nexti <M and -1<nextj<N:
                if graph[nexth][nexti][nextj] == 0:
                    graph[nexth][nexti][nextj] = graph[nowh][nowi][nowj] + 1
                    deq.append((nexth,nexti,nextj))

for h in range(H):
    for i in range(M):
        for j in range(N):
            if graph[h][i][j] == 1:
                deq.append((h,i,j))

bfs()

zCnt = 0
day = -2
for h in range(H):
    for i in range(M):
        for j in range(N):
            if graph[h][i][j] == 0:
                zCnt += 1
            day = max(day,graph[h][i][j])
if zCnt > 0:
    print(-1)
elif day == 1:
    print(0)
else:
    print(day-1)