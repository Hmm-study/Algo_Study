def solution(n, t, m, timetable):
    answer = 0

    # 시간을 분으로
    timetable = [int(time[:2]) * 60 + int(time[3:]) for time in timetable]
    timetable.sort()

    print(timetable)

    busTime = [9 * 60 + t * i for i in range(n)]

    print(busTime)

    i = 0
    for bt in busTime:
        cnt = 0 # 탑승 정원 카운트
        while cnt < m and i < len(timetable) and timetable[i] <= bt:
            i += 1
            cnt += 1
        if cnt < m: # 버스 자리 남았으면 버스 타임에 내가 탑승
            answer = bt
        else: # 버스 자리 없으면 맨 마지막 크루보다 내가 1분 먼저 탑승
            answer = timetable[i - 1] - 1

    return str(answer // 60).zfill(2) + ":" + str(answer % 60).zfill(2)