# 🌳 Binary Tree Traversal Notes

> These are the **4 fundamental traversals** of a Binary Tree.
>
> **Remember:**
> - DFS → Uses **Stack / Recursion**
> - BFS → Uses **Queue**

---

# Tree Used in Examples

```
        1
       / \
      2   3
     / \   \
    4   5   6
```

---

# Types of Traversals

```
                    Traversals
                  /             \
          Depth First        Breadth First
          (DFS)                  (BFS)
       /      |      \
 Preorder  Inorder  Postorder
```

---

# 1. Preorder Traversal

## Order

```
Root
Left
Right
```

or simply

```
Root → Left → Right
```

### Example

Tree

```
        1
       / \
      2   3
     / \   \
    4   5   6
```

Traversal

```
1 2 4 5 3 6
```

---

## Visualization

```
Visit 1

      1
     / \
    2   3
```

↓

```
Visit 2

      2
     / \
    4   5
```

↓

```
Visit 4
```

↓

```
Back

Visit 5
```

↓

```
Back

Visit 3
```

↓

```
Visit 6
```

Final Answer

```
1 2 4 5 3 6
```

---

## Recursive Code

```cpp
void preorder(TreeNode* root){

    if(root==NULL)
        return;

    cout<<root->val<<" ";

    preorder(root->left);
    preorder(root->right);
}
```

---

## Iterative Code

```cpp
vector<int> preorderTraversal(TreeNode* root) {

    vector<int> ans;

    if(root==NULL)
        return ans;

    stack<TreeNode*> st;
    st.push(root);

    while(!st.empty()){

        TreeNode* curr = st.top();
        st.pop();

        ans.push_back(curr->val);

        if(curr->right)
            st.push(curr->right);

        if(curr->left)
            st.push(curr->left);
    }

    return ans;
}
```

---

## Trick to Remember

```
Preorder

Visit node immediately.

Root
↓
Left
↓
Right
```

---

# 2. Inorder Traversal

## Order

```
Left
Root
Right
```

or

```
Left → Root → Right
```

---

### Example

Traversal

```
4 2 5 1 3 6
```

---

## Visualization

```
Go Left

        1
       /
      2
     /
    4
```

Visit

```
4
```

Back

Visit

```
2
```

Go Right

Visit

```
5
```

Back

Visit

```
1
```

Go Right

Visit

```
3
```

Visit

```
6
```

Final Answer

```
4 2 5 1 3 6
```

---

## Recursive Code

```cpp
void inorder(TreeNode* root){

    if(root==NULL)
        return;

    inorder(root->left);

    cout<<root->val<<" ";

    inorder(root->right);
}
```

---

## Iterative Code

```cpp
vector<int> inorderTraversal(TreeNode* root) {

    vector<int> ans;

    stack<TreeNode*> st;
    TreeNode* curr = root;

    while(curr!=NULL || !st.empty()){

        while(curr){

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

## Trick to Remember

```
First Left

Then Root

Then Right
```

---

# 3. Postorder Traversal

## Order

```
Left
Right
Root
```

---

### Example

Traversal

```
4 5 2 6 3 1
```

---

## Visualization

```
Go Left

Go Left

Visit 4

Back

Visit 5

Now Visit 2

Go Right

Visit 6

Visit 3

Finally Visit 1
```

Final

```
4 5 2 6 3 1
```

---

## Recursive Code

```cpp
void postorder(TreeNode* root){

    if(root==NULL)
        return;

    postorder(root->left);

    postorder(root->right);

    cout<<root->val<<" ";
}
```

---

## Iterative (2 Stack)

```cpp
vector<int> postorderTraversal(TreeNode* root) {

    vector<int> ans;

    if(root==NULL)
        return ans;

    stack<TreeNode*> st1,st2;

    st1.push(root);

    while(!st1.empty()){

        TreeNode* curr = st1.top();
        st1.pop();

        st2.push(curr);

        if(curr->left)
            st1.push(curr->left);

        if(curr->right)
            st1.push(curr->right);
    }

    while(!st2.empty()){

        ans.push_back(st2.top()->val);
        st2.pop();
    }

    return ans;
}
```

---

## Iterative (1 Stack)

```cpp
vector<int> postorderTraversal(TreeNode* root) {

    vector<int> ans;

    stack<TreeNode*> st;

    TreeNode* curr = root;
    TreeNode* lastVisited = NULL;

    while(curr!=NULL || !st.empty()){

        while(curr){

            st.push(curr);
            curr = curr->left;
        }

        TreeNode* node = st.top();

        if(node->right && lastVisited!=node->right){

            curr = node->right;
        }
        else{

            ans.push_back(node->val);

            lastVisited=node;
            st.pop();
        }
    }

    return ans;
}
```

---

## Trick

```
Visit node at the END.

Left

↓

Right

↓

Root
```

---

# 4. Level Order Traversal (BFS)

## Order

```
Level by Level
```

---

Traversal

```
1

2 3

4 5 6
```

---

## Idea

Use Queue.

Queue always stores current level.

Process all nodes of one level.

Then move to next level.

---

## Dry Run

Queue

```
[1]
```

Process

```
1
```

Queue

```
2 3
```

Process

```
2 3
```

Queue

```
4 5 6
```

Process

```
4 5 6
```

Done.

---

## Code

```cpp
vector<vector<int>> levelOrder(TreeNode* root) {

    vector<vector<int>> ans;

    if(root==NULL)
        return ans;

    queue<TreeNode*> q;

    q.push(root);

    while(!q.empty()){

        int size=q.size();

        vector<int> level;

        for(int i=0;i<size;i++){

            TreeNode* curr=q.front();
            q.pop();

            level.push_back(curr->val);

            if(curr->left)
                q.push(curr->left);

            if(curr->right)
                q.push(curr->right);
        }

        ans.push_back(level);
    }

    return ans;
}
```

---

# Complete Comparison

| Traversal | Order | Data Structure |
|------------|-------------------------|----------------|
| Preorder | Root Left Right | Stack |
| Inorder | Left Root Right | Stack |
| Postorder | Left Right Root | Stack |
| Level Order | Level by Level | Queue |

---

# Time Complexity

Every node is visited exactly once.

```
Time = O(n)
```

---

# Space Complexity

### DFS

Uses recursion stack or explicit stack.

```
O(h)
```

where

```
h = height of tree
```

Balanced Tree

```
O(log n)
```

Worst Case

```
O(n)
```

---

### BFS

Queue may contain an entire level.

Worst Case

```
O(n)
```

---

# Interview Tips

### Preorder

✅ Tree Copy

✅ Serialization

✅ Prefix Expression

---

### Inorder

✅ BST gives Sorted Order

✅ Validate BST

✅ Kth Smallest

---

### Postorder

✅ Delete Tree

✅ Calculate Height

✅ DP on Trees

---

### Level Order

✅ Level Wise Problems

✅ Right View

✅ Left View

✅ Zigzag Traversal

✅ Minimum Depth

---

# Easy Memory Trick

```
PRE

Root Left Right

(RLR)
```

```
IN

Left Root Right

(LRR)
```

```
POST

Left Right Root

(LRRo)
```

```
LEVEL

Queue

Level by Level
```

---

# Which Traversal Should I Use?

```
Need root first?
        ↓
    Preorder

Need sorted order in BST?
        ↓
     Inorder

Need root after children?
        ↓
    Postorder

Need level-wise answer?
        ↓
    Level Order
```

---

# Revision Sheet (1 Minute)

```
Preorder
---------
Root Left Right

Stack

Visit first

-----------------------

Inorder
--------
Left Root Right

Stack

BST → Sorted

-----------------------

Postorder
----------
Left Right Root

Stack

Visit last

-----------------------

Level Order
------------
Queue

Level by Level

-----------------------

Time
-----
O(n)

DFS Space
----------
O(h)

BFS Space
----------
O(n)
```

---

# Final Cheat Sheet

```
DFS

Preorder
Root Left Right

Inorder
Left Root Right

Postorder
Left Right Root

---------------------

BFS

Queue

Level Order

---------------------

Time

O(n)

Space

DFS -> O(h)

BFS -> O(n)
```
