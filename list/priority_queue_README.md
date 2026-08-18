# Priority Queue in C++

`priority_queue` C++ STL ka ek data structure hai jo humein highest/lowest priority element efficiently deta hai.

---

## 1. Default Priority Queue = Max Heap

```cpp
priority_queue<int> pq;
```

Ismein sabse bada element `top()` par hota hai.

Example:

```cpp
priority_queue<int> pq;

pq.push(5);
pq.push(2);
pq.push(8);
pq.push(1);

cout << pq.top();
```

Output:

```text
8
```

---

## 2. Min Heap

Min heap banane ke liye:

```cpp
priority_queue<int, vector<int>, greater<int>> mini;
```

Ismein sabse chhota element `top()` par hota hai.

Example:

```cpp
priority_queue<int, vector<int>, greater<int>> mini;

mini.push(5);
mini.push(2);
mini.push(8);
mini.push(1);

cout << mini.top();
```

Output:

```text
1
```

---

## 3. Elements ko nikalna

```cpp
while(!mini.empty()) {
    cout << mini.top() << " ";
    mini.pop();
}
```

Output:

```text
1 2 5 8
```

---

## 4. Pair ka Min Heap

Dijkstra jaise algorithms mein pair store karte hain.

```cpp
priority_queue<
    pair<int,int>,
    vector<pair<int,int>>,
    greater<pair<int,int>>
> mini;
```

Usually pair ka meaning hota hai:

```text
{distance, node}
```

Example:

```cpp
mini.push({10, 2});
mini.push({5, 1});
mini.push({3, 4});
```

Top hoga:

```text
{3, 4}
```

kyunki distance `3` sabse chhoti hai.

---

## 5. Dijkstra mein

```cpp
priority_queue<
    pair<int,int>,
    vector<pair<int,int>>,
    greater<pair<int,int>>
> mini;

mini.push({0, src});

while(!mini.empty()) {

    auto [dist, node] = mini.top();
    mini.pop();

    // processing
}
```

Yahan:

```text
dist = current shortest distance
node = current node
```

Min heap hamesha **smallest distance** wala pair pehle dega.

---

## 6. Important Syntax

### Max Heap

```cpp
priority_queue<int> pq;
```

### Min Heap

```cpp
priority_queue<int, vector<int>, greater<int>> pq;
```

### Pair Min Heap

```cpp
priority_queue<
    pair<int,int>,
    vector<pair<int,int>>,
    greater<pair<int,int>>
> pq;
```

---

## 7. Quick Memory Trick

```text
priority_queue<int>
        ↓
    MAX HEAP
        ↓
   biggest first


priority_queue<int, vector<int>, greater<int>>
        ↓
    MIN HEAP
        ↓
   smallest first
```

`top()` → priority wala element

`push()` → element add

`pop()` → top element remove

`empty()` → check empty

`size()` → number of elements
