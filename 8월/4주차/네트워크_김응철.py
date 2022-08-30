def solution(n, computers):

    def dfs(i):
        visited[i] = True
        for a in range(n):
            if computers[i][a] and not visited[a]:
                dfs(a)

    answer = 0
    visited = [False] * n
    for i in range(n):
        if not visited[i]:
            dfs(i)
            answer+=1

    return answer
