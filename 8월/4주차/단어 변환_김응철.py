def solution(begin, target, words):
    answer = 0
    visited = [False] * len(words)
    queue = [[begin,0]]

    while queue:
        now = queue.pop(0)
        nowWord = now[0]
        nowCnt = now[1]

        if nowWord == target:
            answer = nowCnt
            break

        for idx,word in enumerate(words):
            if visited[idx]:
                continue
            difCnt = 0
            for i in range(len(word)):
                if nowWord[i] != word[i]:
                    difCnt += 1
            if difCnt == 1:
                queue.append([word,nowCnt+1])
                visited[idx] = True
    return answer