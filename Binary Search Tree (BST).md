# 🌳 Binary Search Tree (BST) - Complete Revision Notes

---

# What is a BST?

A Binary Search Tree (BST) is a Binary Tree in which every node follows this property:

```
Left Subtree  <  Root  <  Right Subtree
```

Example:

```
            8
          /   \
         3     10
        / \      \
       1   6      14
          / \     /
         4   7   13
```

For every node:

- Left child contains smaller values.
- Right child contains larger values.

---

# Why is BST Special?

In a normal Binary Tree, if we want to search a value, we may have to visit every node.

Example:

```
        5
      /   \
     7     2
    / \
   1   8
```

Search = 8

There is no rule.

So we may visit every node.

Time Complexity

```
O(N)
```

---

In BST

```
            8
          /   \
         3     10
        / \      \
       1   6      14
```

Search = 6

```
8
↓

3
↓

6
```

We ignored half of the tree.

Average Time Complexity

```
O(log N)
```

---

# Golden Rule

Whenever the question contains

```
BST
```

Always ask yourself

```
Can I use BST Property?
```

If YES

Never traverse the whole tree.

---

# Search in BST

Question

Find a node having value = val.

---

## Approach 1 (Normal DFS)

Recursive

```
dfs(left)

visit root

dfs(right)
```

Code

```cpp
TreeNode* ans = NULL;

void dfs(TreeNode* root,int val){

    if(root==NULL)
        return;

    dfs(root->left,val);

    if(root->val==val){
        ans=root;
        return;
    }

    dfs(root->right,val);
}
```

Time Complexity

```
O(N)
```

Space

```
O(H)
```

### Problem

It completely ignores BST property.

---

## Approach 2 (Iterative DFS)

Uses Stack.

```
Left
↓

Left

↓

Left

↓

Visit

↓

Right
```

Code

```cpp
stack<TreeNode*> st;

while(root!=NULL || !st.empty()){

    while(root!=NULL){
        st.push(root);
        root=root->left;
    }

    TreeNode* curr=st.top();
    st.pop();

    if(curr->val==val)
        return curr;

    root=curr->right;
}
```

Time

```
O(N)
```

Space

```
O(H)
```

---

### Why is it called DFS?

Because stack is used.

It goes deep first.

Traversal

```
Left
↓

Left

↓

Left

↓

Backtrack

↓

Right
```

This is

```
Iterative Inorder DFS
```

---

## Approach 3 (BST Property) ⭐⭐⭐⭐⭐

Best Solution

Idea

```
Current Node

↓

Found?

↓

YES

Return

↓

NO

Is value smaller?

↓

YES

Go Left

↓

NO

Go Right
```

Code

```cpp
while(root!=NULL){

    if(root->val==val)
        return root;

    else if(val<root->val)
        root=root->left;

    else
        root=root->right;
}

return NULL;
```

Time

Balanced BST

```
O(log N)
```

Worst

```
O(N)
```

Space

```
O(1)
```

This is the optimal solution.

---

# Why don't we use Inorder for Search?

Suppose

```
          50
        /    \
      30      80
     / \     / \
   20 40   70 90
```

Search = 90

Inorder

```
20

↓

30

↓

40

↓

50

↓

70

↓

80

↓

90
```

Visited

```
7 nodes
```

BST Property

```
50

↓

80

↓

90
```

Visited

```
3 nodes
```

Huge difference.

---

# DFS vs BFS

## DFS

Uses

```
Stack
```

or

```
Recursion
```

Example

```
Preorder

Root

↓

Left

↓

Right
```

```
Inorder

Left

↓

Root

↓

Right
```

```
Postorder

Left

↓

Right

↓

Root
```

---

## BFS

Uses

```
Queue
```

Traversal

```
Level by Level
```

Example

```
        4
      /   \
     2     7
    / \
   1   3
```

Order

```
4

↓

2

↓

7

↓

1

↓

3
```

---

# Can BFS use BST Property?

No.

Reason

Suppose

```
Search = 3
```

BST tells us

```
3 < 4

↓

Only Left
```

But BFS visits

```
4

↓

2

↓

7
```

7 was unnecessary.

Therefore

Queue is not useful for BST Search.

---

# Which Approach Should I Use?

| Approach | Time | Space | Recommendation |
|----------|------|--------|---------------|
| Recursive DFS | O(N) | O(H) | ❌ |
| Iterative DFS | O(N) | O(H) | ❌ |
| BST Property | O(log N) Avg | O(1) | ✅ Best |

---

# Interview Trick

If question contains

```
Binary Tree
```

Think

```
DFS

BFS

Recursion

Stack
```

Everything is possible.

---

If question contains

```
BST
```

Think

```
Can I move only Left?

or

Only Right?
```

If YES

Never traverse the complete tree.

---

# When should we use BST Property?

| Question | Use BST Property? |
|-----------|-------------------|
| Search in BST | ✅ |
| Insert in BST | ✅ |
| Delete Node in BST | ✅ |
| Lowest Common Ancestor in BST | ✅ |
| Closest Value | ✅ |

---

# When should we use Inorder?

Because Inorder of BST is Sorted.

```
Left

↓

Root

↓

Right
```

Questions

```
Kth Smallest

BST Iterator

Recover BST

Validate BST

Convert BST to Sorted List

Minimum Absolute Difference
```

---

# Easy Revision Flow

```
Question

↓

Binary Tree ?

↓

Yes

↓

DFS / BFS

-----------------------

Question

↓

BST ?

↓

Can BST Property help?

↓

Yes

↓

Use Left / Right

↓

O(log N)

-----------------------

Need Sorted Order?

↓

Use Inorder

↓

Left → Root → Right
```

---

# One Line Summary

```
Binary Tree

↓

Think Traversal

-----------------------

BST

↓

Think Property

-----------------------

Need Sorted Order

↓

Think Inorder
```

---

# Final Interview Rule ⭐

```
If you are not using BST Property
while solving a BST problem,

always ask yourself,

"Can this be optimized using
Left < Root < Right ?"
```

Most of the time,

the answer is

```
YES
```

and that is exactly what interviewers expect.