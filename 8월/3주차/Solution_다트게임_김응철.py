def solution(dartResult):
    answer = 0
    cnt = 0
    score = [0] * 3

    for i in range(len(dartResult)):
        if '0' <= dartResult[i] <= '9':
            if dartResult[i + 1] == '0':
                score[cnt] += 10
                i += 1
            else:
                score[cnt] += int(dartResult[i])
            continue

        if dartResult[i] == 'S':
            if i != len(dartResult) - 1:
                if '0' <= dartResult[i + 1] <= '9':
                    cnt += 1
            continue
        elif dartResult[i] == 'D':
            score[cnt] **= 2
            if i != len(dartResult) - 1:
                if '0' <= dartResult[i + 1] <= '9':
                    cnt += 1
            continue
        elif dartResult[i] == 'T':
            score[cnt] **= 3
            if i != len(dartResult) - 1:
                if '0' <= dartResult[i + 1] <= '9':
                    cnt += 1
            continue

        if dartResult[i] == '*':
            if cnt == 0:
                score[cnt] *= 2
            else:
                score[cnt - 1] *= 2
                score[cnt] *= 2
            cnt += 1
        elif dartResult[i] == '#':
            score[cnt] *= -1
            cnt += 1

    answer = score[0] + score[1] + score[2]

    return answer