# BST Traversal Notes (DFS & BFS)

## Tree Traversal

                Tree
               /    \
            DFS      BFS

## DFS

-   Goes deep first.
-   Implement using:
    -   Recursion
    -   Stack

### DFS Types

-   Inorder: Left → Root → Right
-   Preorder: Root → Left → Right
-   Postorder: Left → Right → Root

### Inorder using Stack

Algorithm: 1. Push all left nodes. 2. Pop top node. 3. Visit it. 4. Move
to right child. 5. Repeat until `curr == NULL` and stack is empty.

``` cpp
vector<int> inorderTraversal(TreeNode* root) {
    vector<int> ans;
    stack<TreeNode*> st;
    TreeNode* curr = root;

    while(curr != NULL || !st.empty()){

        while(curr != NULL){
            st.push(curr);
            curr = curr->left;
        }

        curr = st.top();
        st.pop();

        ans.push_back(curr->val);

        curr = curr->right;
    }

    return ans;
}
```

Important condition:

``` cpp
while(curr != NULL || !st.empty())
```

If `curr` becomes NULL but stack still has nodes, continue by popping
from the stack.

------------------------------------------------------------------------

# BFS (Level Order)

Uses **Queue (FIFO)**.

Algorithm: 1. Push root. 2. While queue is not empty: - Pop front. -
Visit node. - Push left child. - Push right child.

``` cpp
vector<int> levelOrder(TreeNode* root) {
    vector<int> ans;
    if(root == NULL) return ans;

    queue<TreeNode*> q;
    q.push(root);

    while(!q.empty()){
        TreeNode* node = q.front();
        q.pop();

        ans.push_back(node->val);

        if(node->left) q.push(node->left);
        if(node->right) q.push(node->right);
    }

    return ans;
}
```

------------------------------------------------------------------------

# DFS vs BFS

  DFS                            BFS
  ------------------------------ ----------------
  Stack / Recursion              Queue
  Goes deep                      Level by level
  Inorder, Preorder, Postorder   Level Order

------------------------------------------------------------------------

# BST Operations

Search: - Recursion - While loop

Insert: - Recursion - While loop

Delete: - Recursion - While loop

Time Complexity: - Balanced BST: **O(log n)** - Skewed BST: **O(n)**

DFS Traversal: - Time: **O(n)** - Space: **O(h)**

BFS Traversal: - Time: **O(n)** - Space: **O(n)**

------------------------------------------------------------------------

# Quick Revision

    Tree
    │
    ├── DFS
    │   ├── Inorder
    │   ├── Preorder
    │   ├── Postorder
    │   ├── Recursion
    │   └── Stack
    │
    └── BFS
        └── Queue

--------------------------------------------------------------------------------------


# BST Traversal Notes (DFS & BFS)

---

# Tree Traversal

Tree ko traverse (visit) karne ke do main tarike hote hain.

```
                Tree
               /    \
            DFS      BFS
```

---

# 1. DFS (Depth First Search)

DFS mein hum ek path ko jitna ho sake utna deep jaate hain, phir wapas aate hain.

DFS ke 3 types hote hain.

```
DFS
│
├── Inorder    (Left → Root → Right)
├── Preorder   (Root → Left → Right)
└── Postorder  (Left → Right → Root)
```

DFS ko do tarike se implement kar sakte hain.

### Method 1 : Recursion

```
Function(root)

Base Condition

Left

Right
```

Recursion automatically stack use karti hai.

---

### Method 2 : Stack

Hum recursion ki jagah apna stack bana lete hain.

```
stack<TreeNode*> st;
```

---

# Inorder Traversal (Stack)

## Rule

```
Jab tak Left hai

Push karo

Left jao

----------------

Left khatam

Top Pop karo

Print karo

Right jao
```

---

## Code

```cpp
vector<int> inorderTraversal(TreeNode* root) {

    vector<int> ans;
    stack<TreeNode*> st;

    TreeNode* curr = root;

    while(curr != NULL || !st.empty()){

        while(curr != NULL){
            st.push(curr);
            curr = curr->left;
        }

        curr = st.top();
        st.pop();

        ans.push_back(curr->val);

        curr = curr->right;
    }

    return ans;
}
```

---

## Dry Run

Tree

```
       4
      / \
     2   6
    / \ / \
   1  3 5  7
```

Initially

```
curr = 4

stack = []
```

Push Left

```
Push 4
Push 2
Push 1

stack

4
2
1

curr = NULL
```

Pop

```
curr = 1

Output

1

curr = curr->right

NULL
```

Now

```
curr == NULL

But

Stack

4
2

Still not empty
```

Outer while

```
while(curr != NULL || !st.empty())
```

Condition

```
false || true

true
```

Again Pop

```
curr = 2

Output

1 2
```

Continue...

Final Output

```
1 2 3 4 5 6 7
```

---

# Important Condition

```
while(curr != NULL || !st.empty())
```

Matlab

```
Agar curr hai

To Left jao

----------------

Agar curr NULL hai

Lekin Stack empty nahi hai

To Stack se Pop karo
```

Ye line bahut important hai.

---

# BFS (Breadth First Search)

DFS depth mein jaata hai.

BFS level by level chalti hai.

Example

```
        1
      /   \
     2     3
    / \   / \
   4  5  6  7
```

Traversal

```
1

2 3

4 5 6 7
```

Output

```
1 2 3 4 5 6 7
```

---

# BFS mein kya use hota hai?

Queue

```
queue<TreeNode*> q;
```

Queue FIFO hoti hai.

First In

↓

First Out

---

# BFS Algorithm

```
Root ko Queue mein dalo

Jab tak Queue empty na ho

    Front nikalo

    Print karo

    Left child ho to Queue mein dalo

    Right child ho to Queue mein dalo
```

---

# BFS Code

```cpp
vector<int> levelOrder(TreeNode* root) {

    vector<int> ans;

    if(root == NULL)
        return ans;

    queue<TreeNode*> q;

    q.push(root);

    while(!q.empty()){

        TreeNode* node = q.front();
        q.pop();

        ans.push_back(node->val);

        if(node->left)
            q.push(node->left);

        if(node->right)
            q.push(node->right);
    }

    return ans;
}
```

---

# BFS Dry Run

Tree

```
        1
      /   \
     2     3
    / \   / \
   4  5  6  7
```

Initially

```
Queue

1
```

Pop

```
1

Output

1

Push

2
3
```

Queue

```
2
3
```

Pop

```
2

Output

1 2

Push

4
5
```

Queue

```
3
4
5
```

Pop

```
3

Output

1 2 3

Push

6
7
```

Queue

```
4
5
6
7
```

Continue...

Final Output

```
1 2 3 4 5 6 7
```

---

# DFS vs BFS

| DFS | BFS |
|------|-----|
| Stack / Recursion | Queue |
| Depth mein jaata hai | Level by Level |
| Left/Right explore karta hai | Complete level explore karta hai |
| Inorder, Preorder, Postorder | Level Order |

---

# BST Operations

## Search

Methods

```
1. Recursion

2. While Loop
```

Time

```
Average

O(log n)

Worst

O(n)
```

---

## Insert

Methods

```
1. Recursion

2. While Loop
```

Time

```
Average

O(log n)

Worst

O(n)
```

---

## Delete

Methods

```
1. Recursion

2. While Loop
```

Time

```
Average

O(log n)

Worst

O(n)
```

---

# Time Complexities

## DFS

```
Time

O(n)

Space

Recursive

O(h)

Worst

O(n)
```

---

## BFS

```
Time

O(n)

Space

O(n)
```

---

# Interview Tips

✅ Search → Recursion / While

✅ Insert → Recursion / While

✅ Delete → Recursion / While

✅ Inorder → Recursion / Stack

✅ Preorder → Recursion / Stack

✅ Postorder → Recursion / Stack

✅ Level Order → Queue (BFS)

---

# Quick Revision

```
Tree

│

├── DFS

│      ├── Inorder

│      ├── Preorder

│      ├── Postorder

│      ├── Recursion

│      └── Stack

│

└── BFS

       └── Queue
```

---

# Golden Rules

DFS

```
Stack / Recursion
```

BFS

```
Queue
```

BST Search

```
Left if smaller

Right if greater
```

BST Insert

```
NULL mila

↓

New Node bana do
```

BST Complexity

```
O(h)

Balanced

↓

O(log n)

Skewed

↓

O(n)
```