# Complete BST Traversal & Operations Notes

## BST Property

    Left Subtree < Root < Right Subtree

Every node follows this rule.

------------------------------------------------------------------------

# Tree Traversal

                Tree
               /    \
            DFS      BFS

## DFS (Depth First Search)

DFS goes as deep as possible before coming back.

Types: - Inorder : Left -\> Root -\> Right - Preorder: Root -\> Left -\>
Right - Postorder: Left -\> Right -\> Root

### DFS Methods

1.  Recursion
2.  Stack

### Inorder using Stack

Rule:

    Push all left nodes
    Left ends
    Pop
    Visit
    Go Right
    Repeat

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

Important:

``` cpp
while(curr != NULL || !st.empty())
```

If curr becomes NULL but stack still contains nodes, pop from stack and
continue.

------------------------------------------------------------------------

# BFS (Level Order)

Uses Queue (FIFO).

Algorithm

1.  Push root.
2.  Pop front.
3.  Visit.
4.  Push left child.
5.  Push right child.
6.  Repeat until queue becomes empty.

``` cpp
vector<int> levelOrder(TreeNode* root) {
    vector<int> ans;
    if(root==NULL) return ans;

    queue<TreeNode*> q;
    q.push(root);

    while(!q.empty()){
        TreeNode* node=q.front();
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
  ------------------------------ -------------
  Stack / Recursion              Queue
  Goes Deep                      Level Order
  Inorder, Preorder, Postorder   Level Order

------------------------------------------------------------------------

# BST Search (Iterative)

``` cpp
TreeNode* searchBST(TreeNode* root,int val){

    while(root){

        if(root->val==val)
            return root;

        if(val<root->val)
            root=root->left;
        else
            root=root->right;
    }

    return NULL;
}
```

------------------------------------------------------------------------

# BST Insert (Iterative)

``` cpp
TreeNode* insertIntoBST(TreeNode* root,int val){

    if(root==NULL)
        return new TreeNode(val);

    TreeNode* curr=root;

    while(curr){

        if(val<curr->val){

            if(curr->left==NULL){
                curr->left=new TreeNode(val);
                break;
            }

            curr=curr->left;
        }
        else{

            if(curr->right==NULL){
                curr->right=new TreeNode(val);
                break;
            }

            curr=curr->right;
        }
    }

    return root;
}
```

------------------------------------------------------------------------

# BST Operations

-   Search → Recursion / While
-   Insert → Recursion / While
-   Delete → Recursion / While

Time = O(h)

Balanced BST → O(log n)

Skewed BST → O(n)

------------------------------------------------------------------------

# Traversal Complexity

DFS - Time : O(n) - Space : O(h)

BFS - Time : O(n) - Space : O(n)

------------------------------------------------------------------------

# Interview Tips

-   Search -\> Recursion / While
-   Insert -\> Recursion / While
-   Delete -\> Recursion / While
-   Inorder -\> Recursion / Stack
-   Preorder -\> Recursion / Stack
-   Postorder -\> Recursion / Stack
-   Level Order -\> Queue

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

# Golden Rules

    DFS -> Stack / Recursion

    BFS -> Queue

    BST Search
    Left if smaller
    Right if greater

    BST Insert
    NULL found -> Create new node

    Complexity
    O(h)

    Balanced -> O(log n)
    Skewed -> O(n)
