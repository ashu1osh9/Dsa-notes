# BST Handbook (Revision)

> Personal revision notes based on today's discussion.

# 1. BST Property

-   Left \< Root \< Right
-   Inorder traversal of BST is always sorted.

# 2. Traversal Templates

``` cpp
void inorder(TreeNode* root){
    if(root==NULL) return;
    inorder(root->left);
    // process
    inorder(root->right);
}
```

Time: O(n)

# 3. Two Sum IV

## Approach 1

Inorder + vector + two pointers.

## Approach 2

HashMap.

## Approach 3 (Best): BST Iterators

### Idea

Maintain two iterators: - Left iterator -\> smallest element. - Right
iterator -\> largest element.

Like two pointers on a sorted array.

### Left Iterator

Push only left path.

          5
         / \
        3   7
       /
      2

    Stack(top)
    2
    3
    5

`next()` 1. Pop top. 2. Return its value. 3. Go to popped node's right
child. 4. Push its complete left path.

### Right Iterator

Push only right path.

          5
         / \
        3   7
             \
              8

    Stack(top)
    8
    7
    5

`next()` 1. Pop top. 2. Return value. 3. Go to left child. 4. Push
complete right path.

## Why only left/right path?

Remaining nodes will be reached later after pop. Every node is pushed
exactly once and popped exactly once.

Amortized next() = O(1).

## Iterator Code

``` cpp
class BSTIterator{
public:
    stack<TreeNode*> st;
    bool reverse;

    BSTIterator(TreeNode* root,bool rev){
        reverse=rev;
        pushAll(root);
    }

    void pushAll(TreeNode* root){
        while(root){
            st.push(root);
            if(reverse) root=root->right;
            else root=root->left;
        }
    }

    int next(){
        TreeNode* node=st.top();
        st.pop();

        if(reverse) pushAll(node->left);
        else pushAll(node->right);

        return node->val;
    }
};
```

## Two Sum IV Code

``` cpp
class Solution {
public:
    bool findTarget(TreeNode* root, int k) {
        BSTIterator l(root,false);
        BSTIterator r(root,true);

        int i=l.next();
        int j=r.next();

        while(i<j){
            if(i+j==k) return true;
            if(i+j<k) i=l.next();
            else j=r.next();
        }
        return false;
    }
};
```

# Pass by Value

`TreeNode* root` is passed by value.

Changing

``` cpp
root=root->left;
```

does NOT change original tree pointer.

# Recover BST

## Concept

Correct BST inorder:

    1 2 3 4 5

Wrong:

    1 5 3 4 2 6

Violation whenever

    prev->val > root->val

## Variables

``` cpp
TreeNode* prev=NULL;
TreeNode* first=NULL;
TreeNode* second=NULL;
```

## Logic

``` cpp
if(prev && prev->val>root->val){
    if(first==NULL)
        first=prev;

    second=root;
}

prev=root;
```

### Why?

First violation:

    5 > 3

    first=5
    second=3

Second violation:

    4 > 2

    second=2

Swap only after complete traversal.

``` cpp
swap(first->val,second->val);
```

## Recover BST Code

``` cpp
class Solution{
public:
TreeNode* prev=NULL;
TreeNode* first=NULL;
TreeNode* second=NULL;

void dfs(TreeNode* root){
    if(root==NULL) return;

    dfs(root->left);

    if(prev && prev->val>root->val){
        if(first==NULL)
            first=prev;
        second=root;
    }

    prev=root;

    dfs(root->right);
}

void recoverTree(TreeNode* root){
    dfs(root);
    swap(first->val,second->val);
}
};
```

# Common Mistakes

-   Forget dfs(root-\>right)
-   Swap immediately after first violation
-   Forget prev NULL check
-   Swap pointers instead of values

# Complexity

  Problem                 Time   Space
  --------------------- ------ -------
  Two Sum IV Iterator     O(n)    O(h)
  Recover BST             O(n)    O(h)

# Revision Checklist

-   Inorder sorted.
-   Left iterator = smallest.
-   Right iterator = largest.
-   next() pops then pushes opposite subtree path.
-   Every node push/pop once.
-   Recover BST: prev, first, second.
-   Swap only at end.
