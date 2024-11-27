| Problem | Description | Cause | Solution |
|----|---|---|---|
| Deadlock | Threads wait indefinitely for resources held by each other | Circular resource dependency | Consistent resource ordering, try-lock, timeout mechanisms |
| Starvation | A thread waits indefinitely due to resource contention or priority scheduling | Priority inversion, unfair scheduling | Fair locks, fairness policies, avoiding excessive prioritization |
| Livelock | Threads remain active but unable to make progress due to excessive interaction | Threads reacting to each other without resolution | Back-off strategies, random delays, or central coordination |
