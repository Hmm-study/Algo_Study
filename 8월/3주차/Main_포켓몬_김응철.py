def solution(nums):
    answer = 0

    if len(set(nums)) > len(nums)//2:
        answer = len(nums)//2
    else:
        answer = len(set(nums))

    return answer