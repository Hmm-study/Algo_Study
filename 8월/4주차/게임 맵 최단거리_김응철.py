def solution(maps):
    answer = -1
    minV = 1e9
    visited = [[0]*len(maps[0]) for i in range(len(maps))]
    di = [-1,1,0,0]
    dj = [0,0,-1,1]
    queue = [[0, 0, 1]]
    visited[0][0] = 1
    while queue:
        now = queue.pop(0)
        nowi = now[0]
        nowj = now[1]
        cnt = now[2]
        if nowi == len(maps)-1 and nowj == len(maps[0])-1:
            if minV > cnt:
                answer = cnt
        for d in range(4):
            nexti = nowi + di[d]
            nextj = nowj + dj[d]
            if nexti < 0 or nexti >= len(maps) or nextj < 0 or nextj >= len(maps[0]) or visited[nexti][nextj] == 1 or maps[nexti][nextj] == 0:
                continue
            queue.append([nexti,nextj,cnt+1])
            visited[nexti][nextj] = 1
    return answer