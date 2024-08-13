import sys

num = int(input())
road_length = list(map(int, sys.stdin.readline().split()))
gas_cost = list(map(int, sys.stdin.readline().split()))
cost = 0
now_min_cost = gas_cost[0]
min_cost = min(gas_cost[0:-2])

for i in range(len(gas_cost)-1):
    if now_min_cost > gas_cost[i+1]:
        cost += now_min_cost * road_length[i]
        now_min_cost = gas_cost[i+1]

    else:
        if now_min_cost == 1 or now_min_cost == min_cost:
            cost += now_min_cost * sum(road_length[i:])
            break
        elif now_min_cost <= gas_cost[i+1]:
            cost += now_min_cost * road_length[i]

print(cost)