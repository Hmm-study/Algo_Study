def solution(s):
    answer = -1
    stack1 = [*s]
    stack2 = []

    stack1.reverse()

    while True:
        if not stack1:
            answer = 0 if stack2 else 1
            break

        if not stack2 or stack1[-1] == stack2[-1]:
            if stack2:
                stack1.pop()
                stack2.pop()
            else:
                stack2.append(stack1.pop())
        else:
            stack2.append(stack1.pop())


    return answer