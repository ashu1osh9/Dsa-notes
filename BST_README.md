# BST Handbook

## BST Property

-   Left Subtree \< Root \< Right Subtree
-   Inorder traversal gives sorted order.

## Templates

### 1. DFS (Recursive)

Used for Search, Insert, Delete, Validate BST, Height.

``` cpp
void dfs(TreeNode* root){
    if(root==NULL) return;
    dfs(root->left);
    // Process
    dfs(root->right);
}
```

Time: **O(n)**\
Space: **O(h)**

### 2. DFS using Stack

``` cpp
stack<TreeNode*> st;
TreeNode* curr=root;

while(curr!=NULL || !st.empty()){
    while(curr){
        st.push(curr);
        curr=curr->left;
    }
    curr=st.top();
    st.pop();
    // Process
    curr=curr->right;
}
```

Time: **O(n)**\
Space: **O(h)**

### 3. BFS (Queue)

``` cpp
queue<TreeNode*> q;
q.push(root);

while(!q.empty()){
    TreeNode* node=q.front();
    q.pop();

    // Process

    if(node->left) q.push(node->left);
    if(node->right) q.push(node->right);
}
```

Time: **O(n)**\
Space: **O(n)**

### 4. Search (Iterative)

``` cpp
while(root){
    if(root->val==val) return root;
    if(val<root->val) root=root->left;
    else root=root->right;
}
return NULL;
```

Time: Avg **O(log n)**, Worst **O(n)**\
Space: **O(1)**

### 5. Insert (Iterative)

``` cpp
if(root==NULL) return new TreeNode(val);

TreeNode* curr=root;

while(curr){
    if(val<curr->val){
        if(curr->left==NULL){
            curr->left=new TreeNode(val);
            break;
        }
        curr=curr->left;
    }else{
        if(curr->right==NULL){
            curr->right=new TreeNode(val);
            break;
        }
        curr=curr->right;
    }
}
return root;
```

Time: Avg **O(log n)**, Worst **O(n)**\
Space: **O(1)**

### 6. Inorder + Vector

Used for Kth Smallest / Sorted Output.

### 7. Reverse Inorder

Used for Kth Largest.

### 8. Morris Traversal

Time: **O(n)**\
Space: **O(1)**

## Golden Rules

-   Sorted Order → Inorder
-   Kth Smallest → Inorder
-   Kth Largest → Reverse Inorder
-   Level Order → BFS (Queue)
-   Search / Insert → While
-   General Tree Problems → DFS
-   O(1) Space Traversal → Morris
