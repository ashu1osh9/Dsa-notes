# BST Revision Notes (Today's Session)

## 1. Two Sum IV (BST)

### Core Concept

-   BST ka **inorder traversal hamesha sorted** hota hai.
-   Isliye sorted array ki tarah **two pointers** ya **two iterators**
    use kar sakte hain.

### Approaches

1.  Inorder + Vector + Two Pointer
    -   Time: O(n)
    -   Space: O(n)
2.  HashMap
    -   Inorder karo.
    -   Har value ke liye `k-value` search karo.
    -   Time: O(n)
    -   Space: O(n)
3.  BST Iterators (Best)
    -   Time: O(n)
    -   Extra Space: O(h)

------------------------------------------------------------------------

## 2. BST Iterator

### Left Iterator

-   Root se start karo.
-   Sirf **left path** stack me push karo.

Example:

``` text
      5
     / \
    3   7
   / \
  2   4

Stack(top): 2 3 5
```

`next()` 1. top pop karo. 2. Agar right child hai to uske **left path**
ko push karo.

### Right Iterator

-   Root se start karo.
-   Sirf **right path** push karo.

Example:

``` text
      5
     / \
    3   7
       / \
      6   8

Stack(top): 8 7 5
```

`next()` 1. top pop karo. 2. Agar left child hai to uske **right path**
push karo.

### Important

Har node **sirf ek hi baar push** aur **ek hi baar pop** hota hai.

Isliye amortized complexity = **O(1)** per next().

------------------------------------------------------------------------

## 3. Why root doesn't change?

``` cpp
void push(TreeNode* root)
```

Ye **pass by value** hai.

Function ke andar:

``` cpp
root = root->left;
```

Sirf local pointer move hota hai.

Original root same rehta hai.

------------------------------------------------------------------------

## 4. Recover BST

### Main Concept

BST ka inorder sorted hota hai.

Agar:

``` text
prev > current
```

to violation.

### Adjacent

``` text
1 2 4 3 5
```

Ek violation.

### Non-adjacent

``` text
1 5 3 4 2 6
```

Do violations.

### Variables

``` cpp
TreeNode* prev = NULL;
TreeNode* first = NULL;
TreeNode* second = NULL;
```

### Logic

``` cpp
if(prev && prev->val > root->val){

    if(first == NULL)
        first = prev;

    second = root;
}

prev = root;
```

### Why?

Pehli violation:

``` text
5 > 3
```

    first = 5
    second = 3

Dusri violation:

``` text
4 > 2
```

    second = 2

End me

``` cpp
swap(first->val, second->val);
```

### Never swap immediately

Traversal complete hone do.

------------------------------------------------------------------------

## 5. swap()

``` cpp
swap(a,b);
```

Values:

``` cpp
swap(first->val, second->val);
```

Pointers:

``` cpp
swap(first, second);
```

Different concepts.

------------------------------------------------------------------------

## 6. Interview Mistakes

-   Forgetting `dfs(root->right);`
-   `prev` initialize na karna.
-   Violation milte hi swap kar dena.
-   `first` ko baar-baar update karna.
-   `second` ko sirf pehli violation me set karna.

------------------------------------------------------------------------

## 7. Complexity

### Two Sum IV

  Method           Time   Space
  -------------- ------ -------
  Vector           O(n)    O(n)
  HashMap          O(n)    O(n)
  BST Iterator     O(n)    O(h)

### Recover BST

  Method        Time   Space
  ----------- ------ -------
  Recursive     O(n)    O(h)
  Morris        O(n)    O(1)

------------------------------------------------------------------------

## 8. Revision Checklist

-   [ ] Inorder of BST is sorted.
-   [ ] Left iterator pushes left path only.
-   [ ] Right iterator pushes right path only.
-   [ ] `next()` pops one node then pushes opposite-side path.
-   [ ] Every node pushed once and popped once.
-   [ ] `root` inside function is local copy.
-   [ ] Recover BST uses `prev`, `first`, `second`.
-   [ ] Swap only after traversal.

Happy Coding 🚀
