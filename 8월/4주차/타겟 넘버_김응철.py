answer = 0
def dfs(numbers, target, cnt):
    global answer
    if cnt == len(numbers):
        if sum(numbers) == target:
            answer += 1
        return
    dfs(numbers,target,cnt+1)
    numbers[cnt] *= -1
    dfs(numbers,target,cnt+1)

def solution(numbers, target):
    global answer

    dfs(numbers, target, 0)

    return answer