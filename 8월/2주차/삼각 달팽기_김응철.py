def solution(n):
    answer = []
    t = []

    for i in range(1, n + 1):
        line = []
        for j in range(i):
            line.append(0)
        t.append(line)

    num = 1
    cnt = 0
    while num <= n*(n+1)/2:
        for i in range(n-3*cnt):
            t[i+2*cnt][0+cnt] = num
            num += 1
        for i in range(1, n-3*cnt):
            t[n-1-cnt][i+cnt] = num
            num += 1
        for i in range(n-(2+cnt),2*cnt,-1):
            t[i][-(1+cnt)] = num
            num += 1
        cnt += 1

    for i in range(n):
        for j in range(i + 1):
            answer.append(t[i][j])

    return answer