import time 

iterations = 100000
start_time = time.perf_counter_ns()
for i in range (0,iterations+1):
    pass
end_time = time.perf_counter_ns()
total_time = end_time - start_time
print("Total iterations : ",iterations)
print(f"Total time taken : {total_time} nano seconds ")
    