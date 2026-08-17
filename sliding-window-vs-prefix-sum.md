# Sliding Window vs Prefix Sum

## 1. Quick Decision Flow

```text
                    SUBARRAY / SUBSTRING?
                            |
                       +----+----+
                       |         |
                      NO        YES
                       |         |
                 Other approach  |
                                 |
                    Can we maintain a
                    window with a condition?
                                 |
                         +-------+-------+
                         |               |
                        YES              NO
                         |               |
                         ↓               ↓
                 SLIDING WINDOW     PREFIX SUM
                         |
                  +------+------+
                  |             |
              Fixed size?   Variable size?
                  |             |
                 YES           YES
                  |             |
                  ↓             ↓
          FIXED WINDOW    VARIABLE WINDOW
```

---

# 2. Fixed Size Sliding Window

Use it when the window size is already given.

### Common clues

- Subarray of size `K`
- Maximum sum of subarray of size `K`
- Minimum sum of subarray of size `K`
- First negative number in every window of size `K`

### Example

```text
arr = [2, 1, 5, 1, 3, 2]
k = 3

[2, 1, 5] = 8
   [1, 5, 1] = 7
      [5, 1, 3] = 9
         [1, 3, 2] = 6
```

### Template

```cpp
int left = 0;
int sum = 0;
int ans = INT_MIN;

for (int right = 0; right < nums.size(); right++) {

    sum += nums[right];

    if (right - left + 1 == k) {

        ans = max(ans, sum);

        sum -= nums[left];
        left++;
    }
}
```

### Important

```cpp
right - left + 1
```

gives the current window size.

---

# 3. Variable Size Sliding Window

Here the window size is **not fixed**.

The window grows and shrinks according to a condition.

### Common clues

- Longest subarray with `sum <= K`
- Smallest subarray with `sum >= K`
- Longest substring with at most `K` distinct characters
- Longest substring without repeating characters

### Basic idea

```text
right → expand window

condition valid?
        |
       YES → continue / update answer
        |
       NO
        ↓
left → shrink window
```

### Example

```text
arr = [2, 1, 3, 2, 1]
K = 5
```

Suppose:

```text
[2, 1, 3] → sum = 6
```

Condition:

```text
sum <= 5
```

is false.

So shrink:

```text
[1, 3] → sum = 4
```

Now it is valid.

### Template

```cpp
int left = 0;
int sum = 0;
int ans = 0;

for (int right = 0; right < nums.size(); right++) {

    sum += nums[right];

    while (sum > k) {
        sum -= nums[left];
        left++;
    }

    ans = max(ans, right - left + 1);
}
```

### Important

For sum-based variable sliding window, this direct approach generally requires **non-negative numbers**.

Why?

Because with non-negative numbers:

```text
expand window  → sum cannot decrease
shrink window  → sum cannot increase
```

This predictable behavior lets us maintain the window.

---

# 4. Prefix Sum

Prefix Sum is useful when we need information about **previous sums** or need to calculate range sums quickly.

### Basic formula

For:

```text
arr = [2, 1, 3, 4]
```

Prefix:

```text
index:   0  1  2  3
arr:     2  1  3  4
prefix:  2  3  6  10
```

Sum from index `l` to `r`:

```text
prefix[r] - prefix[l - 1]
```

---

# 5. Exact Sum = Prefix Sum + HashMap

This is extremely important.

Suppose the question is:

> Count subarrays whose sum is exactly `K`.

We have:

```text
currentPrefix - previousPrefix = K
```

Therefore:

```text
previousPrefix = currentPrefix - K
```

So for every current prefix sum, we check:

```cpp
map[currentPrefix - K]
```

If it exists, those previous prefix sums create subarrays with sum exactly `K`.

### Template

```cpp
int prefix = 0;
int count = 0;

unordered_map<int, int> mp;

mp[0] = 1;

for (int x : nums) {

    prefix += x;

    count += mp[prefix - k];

    mp[prefix]++;
}
```

---

# 6. Your Current Problem

## Number of Subarrays With Sum

Question:

```text
numSubarraysWithSum(nums, goal)
```

The important words are:

```text
HOW MANY
     +
SUM EXACTLY GOAL
```

So:

```text
Prefix Sum + HashMap
```

### Why not just `count++`?

Suppose several valid subarrays end at the same `right`.

A single:

```cpp
count++;
```

counts only one.

But:

```cpp
count += mp[prefix - goal];
```

counts **all previous prefixes** that can form the required sum.

---

# 7. Sliding Window vs Prefix Sum

| Question Pattern | Approach |
|---|---|
| Subarray of size `K` | Fixed Sliding Window |
| Maximum sum of size `K` | Fixed Sliding Window |
| Minimum sum of size `K` | Fixed Sliding Window |
| Longest subarray with sum `<= K` | Variable Sliding Window |
| Shortest subarray with sum `>= K` | Variable Sliding Window* |
| Longest substring with at most `K` distinct | Variable Sliding Window |
| Count subarrays with sum exactly `K` | Prefix Sum + HashMap |
| Count subarrays with sum `0` | Prefix Sum + HashMap |
| Exact sum with negative numbers | Prefix Sum + HashMap |
| Sum of many ranges `[L, R]` | Prefix Sum |

`*` The direct sum-based sliding-window version has important assumptions; with arbitrary negative numbers, use a different technique.

---

# 8. Easy Decision Flow

```text
                SUBARRAY / SUBSTRING?
                         |
                    +----+----+
                    |         |
                   NO        YES
                    |         |
              Other approach  |
                              ↓
                     What is asked?
                              |
             +----------------+----------------+
             |                |                |
         Fixed size       Condition         Exact sum
             |                |                |
             ↓                ↓                ↓
      Fixed Window      Variable Window   Prefix + Map
```

---

# 9. One-Line Rules to Remember

```text
FIXED SIZE K
    ↓
Fixed Sliding Window
```

```text
LONGEST / SHORTEST
+ CONDITION
    ↓
Variable Sliding Window
```

```text
EXACT SUM K
+ COUNT
    ↓
Prefix Sum + HashMap
```

```text
NEGATIVE NUMBERS
+ EXACT SUM
    ↓
Prefix Sum + HashMap
```

---

# 10. Most Important Intuition

### Sliding Window

Think:

```text
"Main ek window maintain karunga."

right → window bada
left  → window chhota
```

### Prefix Sum

Think:

```text
"Current sum pata hai.
Mujhe previous kaunsa sum chahiye?"
```

For exact sum `K`:

```text
currentPrefix - previousPrefix = K

previousPrefix = currentPrefix - K
```

So:

```cpp
mp[currentPrefix - K]
```

check karo.

---

# 11. Final Cheat Sheet

```text
                 SUBARRAY / SUBSTRING
                         |
              +----------+----------+
              |                     |
          Fixed Size             Variable
              |                     |
              ↓                     ↓
       Sliding Window        Is there a condition?
                                    |
                              +-----+-----+
                              |           |
                             YES          NO
                              |           |
                              ↓           ↓
                       Sliding Window   Prefix Sum

Special case:
Exact sum K + counting
        ↓
Prefix Sum + HashMap
```

## Remember

> **Window ko move karke condition maintain kar sakte ho → Sliding Window**

> **Previous prefix sum ki frequency chahiye → Prefix Sum + HashMap**
