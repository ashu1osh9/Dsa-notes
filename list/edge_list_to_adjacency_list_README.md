# Edge List to Adjacency List

## 1. Edge List

Edge List mein graph ki edges ko pairs ke form mein store karte hain.

Example:

```cpp
vector<vector<int>> edges = {
    {0, 1},
    {0, 2},
    {1, 3},
    {2, 3}
};
```

Iska matlab:

```text
0 ---- 1
|      |
2 ---- 3
```

Har pair `{u, v}` ek edge represent karta hai.

---

## 2. Undirected Graph

Undirected graph mein agar `u -> v` edge hai, toh `v -> u` bhi possible hai.

```cpp
vector<vector<int>> adj(V);

for(auto edge : edges) {
    int u = edge[0];
    int v = edge[1];

    adj[u].push_back(v);
    adj[v].push_back(u);
}
```

Adjacency List:

```text
0 -> 1, 2
1 -> 0, 3
2 -> 0, 3
3 -> 1, 2
```

### Rule

```cpp
adj[u].push_back(v);
adj[v].push_back(u);
```

---

## 3. Directed Graph

Directed graph mein direction important hoti hai.

Agar edge hai:

```text
0 -> 1
```

toh `1 -> 0` automatically nahi hoga.

```cpp
vector<vector<int>> adj(V);

for(auto edge : edges) {
    int u = edge[0];
    int v = edge[1];

    adj[u].push_back(v);
}
```

Adjacency List:

```text
0 -> 1, 2
1 -> 3
2 -> 3
3 -> 
```

### Rule

```cpp
adj[u].push_back(v);
```

---

## 4. Weighted Graph

Agar edge ke saath weight bhi diya ho:

```cpp
edges = {
    {0, 1, 5},
    {0, 2, 2},
    {1, 3, 4}
};
```

Toh adjacency list mein `{node, weight}` store kar sakte hain:

```cpp
vector<vector<pair<int,int>>> adj(V);

for(auto edge : edges) {
    int u = edge[0];
    int v = edge[1];
    int wt = edge[2];

    adj[u].push_back({v, wt});
    adj[v].push_back({u, wt}); // undirected
}
```

---

## 5. Quick Revision

### Undirected

```cpp
adj[u].push_back(v);
adj[v].push_back(u);
```

### Directed

```cpp
adj[u].push_back(v);
```

### Weighted

```cpp
adj[u].push_back({v, wt});
```

### Main Idea

**Edge List → Adjacency List**

```text
Edge List
   ↓
Har edge {u, v} lo
   ↓
adj[u] mein v daalo
   ↓
Agar undirected hai
adj[v] mein u bhi daalo
```
