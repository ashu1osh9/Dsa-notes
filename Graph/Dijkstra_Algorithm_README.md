# Dijkstra Algorithm

## 1. Dijkstra Algorithm kya hai?

Dijkstra ek **Shortest Path Algorithm** hai.

Iska use kisi ek source node se graph ke **har node tak ka minimum/shortest distance** find karne ke liye hota hai.

### Example

```text
       4
   0 ------ 1
   |        |
  8|        |5
   |        |
   2 ------ 3
       2
```

Agar source:

```text
src = 0
```

toh hume `0` se baaki nodes tak shortest distance nikalna hai.

---

# 2. Dijkstra kab use karte hain?

Dijkstra tab use karte hain jab:

- Graph weighted ho
- Edge weights **non-negative** hon
- Hume shortest distance chahiye
- Ek source se baaki sabhi nodes tak distance chahiye

### Important

Dijkstra **negative edge weight** ke saath kaam nahi karta.

---

# 3. Input `edges` kya hota hai?

GFG wale problem mein:

```cpp
vector<vector<int>> &edges
```

milta hai.

Har edge ka format hota hai:

```text
{u, v, weight}
```

Example:

```cpp
edges = {
    {0, 1, 4},
    {0, 2, 8},
    {1, 2, 2},
    {1, 3, 5}
};
```

Iska matlab:

```text
{0, 1, 4}
```

means:

```text
0 ----4---- 1
```

---

# 4. Adjacency List kya hoti hai?

Graph ko easily traverse karne ke liye hum `edges` ko **adjacency list** mein convert karte hain.

Hum banate hain:

```cpp
vector<vector<pair<int,int>>> adjList(V);
```

Yahan:

```text
pair.first  = next node
pair.second = edge weight
```

---

# 5. Edges ko Adjacency List mein kaise convert karein?

Agar edge hai:

```cpp
{0, 1, 4}
```

toh graph undirected hone par:

```cpp
adjList[0].push_back({1, 4});
adjList[1].push_back({0, 4});
```

Kyun?

Kyuki:

```text
0 → 1
```

aur undirected graph mein:

```text
1 → 0
```

bhi possible hai.

### Saari edges ko convert karna

```cpp
for(auto &e : edges) {

    int u = e[0];
    int v = e[1];
    int w = e[2];

    adjList[u].push_back({v, w});
    adjList[v].push_back({u, w});
}
```

---

# 6. Example of Adjacency List

Agar:

```cpp
edges = {
    {0, 1, 4},
    {0, 2, 8},
    {1, 2, 2},
    {1, 3, 5}
};
```

toh adjacency list:

```text
0 → (1,4), (2,8)

1 → (0,4), (2,2), (3,5)

2 → (0,8), (1,2)

3 → (1,5)
```

Yahan:

```text
(1,4)
```

ka matlab:

```text
next node = 1
weight = 4
```

---

# 7. `adjList[node]` ko kaise access karenge?

Agar:

```cpp
node = 0;
```

toh:

```cpp
adjList[0]
```

mein:

```text
(1,4)
(2,8)
```

hain.

Hum loop lagayenge:

```cpp
for(auto &v : adjList[node])
```

Ab `v` ek pair hai.

Isliye:

```cpp
v.first
```

= next node

aur:

```cpp
v.second
```

= edge weight.

Example:

```cpp
for(auto &v : adjList[0]) {

    cout << v.first << " ";
    cout << v.second << " ";
}
```

Output conceptually:

```text
1 4
2 8
```

---

# 8. `dist` Array

Shortest distance store karne ke liye:

```cpp
vector<int> dist(V, 1e9);
```

Initially sabki distance infinity maan lete hain:

```text
dist = [∞, ∞, ∞, ∞]
```

Source ki distance `0` hoti hai:

```cpp
dist[src] = 0;
```

Agar:

```text
src = 0
```

toh:

```text
dist = [0, ∞, ∞, ∞]
```

---

# 9. Priority Queue kyun use karte hain?

Dijkstra mein hume baar-baar **sabse chhoti distance wala node** chahiye.

Iske liye min-heap / min priority queue use karte hain.

```cpp
priority_queue<
    pair<int,int>,
    vector<pair<int,int>>,
    greater<pair<int,int>>
> mini;
```

Hum pair store karenge:

```text
{distance, node}
```

Example:

```cpp
mini.push({0, src});
```

Agar:

```text
src = 0
```

toh:

```text
{0,0}
```

store hoga.

---

# 10. Priority Queue mein `top()` hota hai

Priority queue mein:

```cpp
mini.top()
```

use hota hai.

`front()` nahi.

Example:

```cpp
auto res = mini.top();

int weight = res.first;
int node = res.second;

mini.pop();
```

Yahan:

```text
weight = current shortest distance
node   = current node
```

---

# 11. Dijkstra ka Main Logic

Suppose current node hai:

```text
node = 0
```

aur source se `0` tak distance:

```text
weight = 0
```

Agar adjacency list mein:

```text
0 → (1,4)
0 → (2,8)
```

toh:

```text
0 → 1 = 0 + 4 = 4

0 → 2 = 0 + 8 = 8
```

Ab `dist`:

```text
[0, 4, 8, ∞]
```

---

# 12. Relaxation

Dijkstra ki sabse important line:

```cpp
if(weight + edgeWeight < dist[nextNode])
```

Isko **relaxation** kehte hain.

Matlab:

```text
current node tak distance
+
current edge ka weight
=
new distance
```

Agar new distance purani distance se chhoti hai:

```text
new distance < old distance
```

toh update kar do.

```cpp
dist[nextNode] = weight + edgeWeight;
```

Aur priority queue mein push karo:

```cpp
mini.push({dist[nextNode], nextNode});
```

---

# 13. Complete Flow

Dijkstra ka flow:

```text
Source
  ↓
dist[src] = 0
  ↓
Priority Queue mein source push
  ↓
Smallest distance wala node nikalo
  ↓
Uske saare neighbours dekho
  ↓
New distance calculate karo
  ↓
Agar new distance chhota hai
  ↓
dist update karo
  ↓
Priority Queue mein push karo
  ↓
Queue empty hone tak repeat
  ↓
dist = shortest distances
```

---

# 14. Dry Run

Graph:

```text
       4
   0 ------ 1
   |        |
  8|        |2
   |        |
   2 ------ 3
       5
```

Edges:

```cpp
edges = {
    {0,1,4},
    {0,2,8},
    {1,3,2},
    {2,3,5}
};
```

Source:

```text
src = 0
```

### Step 1

Initially:

```text
dist = [0, ∞, ∞, ∞]
PQ = {(0,0)}
```

Node `0` nikala.

Neighbours:

```text
1 → 4
2 → 8
```

Update:

```text
dist = [0,4,8,∞]
```

PQ:

```text
{4,1}
{8,2}
```

---

### Step 2

Smallest:

```text
{4,1}
```

Node `1`.

Neighbour:

```text
3 → weight 2
```

New distance:

```text
4 + 2 = 6
```

So:

```text
dist = [0,4,8,6]
```

---

### Step 3

Next smallest:

```text
{6,3}
```

Node `3`.

Koi better distance nahi milti.

---

### Step 4

Node `2`:

```text
{8,2}
```

`2 → 3` ka distance:

```text
8 + 5 = 13
```

Lekin:

```text
dist[3] = 6
```

Already `6` chhota hai.

Isliye update nahi karenge.

Final:

```text
dist = [0,4,8,6]
```

---

# 15. Complete C++ Code

```cpp
class Solution {
public:
    vector<int> dijkstra(int V, vector<vector<int>> &edges, int src) {

        // 1. Create adjacency list
        vector<vector<pair<int,int>>> adjList(V);

        // 2. Convert edges to adjacency list
        for(auto &e : edges) {

            int u = e[0];
            int v = e[1];
            int w = e[2];

            adjList[u].push_back({v, w});
            adjList[v].push_back({u, w});
        }

        // 3. Min Heap
        priority_queue<
            pair<int,int>,
            vector<pair<int,int>>,
            greater<pair<int,int>>
        > mini;

        // 4. Distance array
        vector<int> dist(V, 1e9);

        // 5. Source distance = 0
        dist[src] = 0;

        // 6. Push source
        mini.push({0, src});

        // 7. Dijkstra
        while(!mini.empty()) {

            auto res = mini.top();
            mini.pop();

            int weight = res.first;
            int node = res.second;

            // 8. Visit neighbours
            for(auto &v : adjList[node]) {

                int nextNode = v.first;
                int edgeWeight = v.second;

                // 9. Relaxation
                if(weight + edgeWeight < dist[nextNode]) {

                    dist[nextNode] = weight + edgeWeight;

                    mini.push({
                        dist[nextNode],
                        nextNode
                    });
                }
            }
        }

        return dist;
    }
};
```

---

# 16. Important Variables

Code dekhte waqt ye mapping yaad rakho:

```text
e[0] → u
e[1] → v
e[2] → weight

v.first  → nextNode
v.second → edgeWeight

res.first  → current distance
res.second → current node
```

---

# 17. Common Mistakes

### ❌ `front()`

```cpp
mini.front()
```

### ✅ Correct

```cpp
mini.top()
```

---

### ❌ `empty` ko galat spelling

```cpp
mini.emptty()
```

### ✅ Correct

```cpp
mini.empty()
```

---

### ❌ Wrong priority queue

```cpp
priority_queue<pair<int,int>, vector<int>, greater<int>>
```

### ✅ Correct

```cpp
priority_queue<
    pair<int,int>,
    vector<pair<int,int>>,
    greater<pair<int,int>>
>
```

---

### ❌ Pair ko directly distance mat samjho

```cpp
v
```

ek pair hai.

Use:

```cpp
v.first
v.second
```

---

# 18. Sabse Important Concept

Dijkstra mein tumhe bas ye 4 cheezein clear honi chahiye:

```text
1. edges
      ↓
2. adjacency list
      ↓
3. priority queue
      ↓
4. relaxation
```

### Short formula

```text
newDistance = currentDistance + edgeWeight
```

Agar:

```text
newDistance < oldDistance
```

toh:

```text
dist[neighbour] = newDistance
```

**Yehi Dijkstra ka core hai.**

---

# 19. Complexity

Adjacency list + priority queue ke saath:

```text
Time Complexity  = O((V + E) log V)
Space Complexity = O(V + E)
```

Where:

```text
V = number of vertices/nodes
E = number of edges
```
