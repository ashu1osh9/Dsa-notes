# Greedy Algorithm --- Complete Revision Notes

## 1. Greedy Algorithm kya hota hai?

**Greedy Algorithm** ek algorithmic approach hai jisme hum problem solve
karte waqt **har step par current situation ki sabse best choice**
select karte hain.

Simple words:

> **"Har step par jo choice abhi sabse best lag rahi hai, usko choose
> karo aur aage badho."**

Greedy mein generally hum kisi decision ko baad mein change nahi karte.

### Basic idea

``` text
Problem
   ↓
Current situation
   ↓
Best local choice
   ↓
Choice ko accept karo
   ↓
Next situation
   ↓
Best local choice
   ↓
Repeat
   ↓
Final Answer
```

------------------------------------------------------------------------

# 2. Local Optimum vs Global Optimum

Greedy ka main concept samajhne ke liye ye dono terms important hain.

### Local Optimum

Current step par best choice.

### Global Optimum

Puri problem ka overall best answer.

Greedy kaam tab karta hai jab:

> **Local best choices ko repeatedly choose karne se Global best answer
> mil jaye.**

Example:

``` text
Activity Selection

Goal:
Maximum activities select karni hain.

Greedy choice:
Jo activity sabse pehle finish ho rahi hai,
use choose karo.
```

Yahan local choice eventually global optimum deti hai.

------------------------------------------------------------------------

# 3. Greedy Question kaise identify karein?

Ye sabse important part hai.

Kisi bhi question ko dekhte hi directly Greedy mat lagao.

Pehle ye 5 steps follow karo.

------------------------------------------------------------------------

# 4. Greedy Identification --- 5 Step Method

## Step 1: Goal kya hai?

Sabse pehle question ka goal identify karo.

Question kya maang raha hai?

``` text
Maximum?
Minimum?
Maximum number?
Minimum number?
Earliest?
Latest?
Largest?
Smallest?
```

Examples:

``` text
Maximum number of activities
Minimum removals
Maximum profit
Minimum arrows
Maximum children satisfied
```

Agar optimization problem hai, Greedy ki possibility ho sakti hai.

**Lekin sirf Maximum/Minimum hone se Greedy prove nahi hota.**

------------------------------------------------------------------------

## Step 2: Kya repeatedly koi choice karni hai?

Dekho kya problem mein baar-baar decision lena hai.

Examples:

``` text
Kaunsa item choose karein?
Kaunsi activity choose karein?
Kaunsi cookie assign karein?
Kaunsa interval remove karein?
Kaunsa job pehle karein?
Kis position par place karein?
```

Agar repeated choices hain, Greedy candidate ho sakta hai.

------------------------------------------------------------------------

## Step 3: Kya current step par obvious "best choice" hai?

Ye bahut important hai.

Question karo:

> "Agar mujhe abhi ek choice karni ho, toh sabse best choice kya hogi?"

Examples:

### Activity Selection

``` text
Jo activity sabse jaldi finish kare
→ choose karo
```

### Fractional Knapsack

``` text
Highest value / weight
→ pehle lo
```

### Assign Cookies

``` text
Child ko satisfy karne wali
smallest possible cookie
→ use do
```

Agar aisi clear local best choice milti hai, Greedy ki possibility
strong hai.

------------------------------------------------------------------------

## Step 4: Kya choice ke baad hume wapas nahi jaana padega?

Greedy mein generally hum decision lene ke baad us decision ko
reconsider nahi karte.

Pattern:

``` text
Choice 1
   ↓
Choice 2
   ↓
Choice 3
   ↓
Choice 4
   ↓
Answer
```

Agar problem baar-baar bol rahi hai:

``` text
Take
OR
Skip

Try both
```

ya:

``` text
Agar ye choose kiya toh future mein kya hoga?
Agar nahi choose kiya toh kya hoga?
```

toh DP/backtracking ki possibility zyada ho sakti hai.

------------------------------------------------------------------------

## Step 5: Kya local best choice global best answer degi?

**Ye sabse important step hai.**

Greedy tabhi valid hai jab:

``` text
Best local choice
        ↓
Future ko destroy nahi karti
        ↓
Optimal solution tak pahunch sakte hain
        ↓
Global optimum
```

Agar local best choice kabhi-kabhi global answer ko kharab kar deti hai:

``` text
Greedy ❌
```

Isliye Greedy ko blindly apply nahi karna.

------------------------------------------------------------------------

# 5. Complete Greedy Checklist

Question dekhte hi:

``` text
1. Goal kya hai?
       ↓
2. Kya repeated choice hai?
       ↓
3. Kya obvious best local choice hai?
       ↓
4. Kya choice ko baad mein change nahi karna padega?
       ↓
5. Kya local best choice global optimum de sakti hai?
       ↓
     GREEDY
```

### Short version

> **"Har step par best choice + future ko damage nahi kare + eventually
> global optimum."**

------------------------------------------------------------------------

# 6. Example --- Assign Cookies

Suppose:

``` text
g = [1, 2, 3]     // children ki greed
s = [1, 2, 3]     // cookie sizes
```

Goal:

> Maximum children ko satisfy karna hai.

------------------------------------------------------------------------

## Step 1: Goal

``` text
Maximum children
```

Optimization problem.

------------------------------------------------------------------------

## Step 2: Repeated choice?

Haan.

Har child ko ek cookie assign karni hai.

``` text
Child → Kaunsi cookie?
```

------------------------------------------------------------------------

## Step 3: Best local choice?

Agar child ki greed `2` hai:

``` text
cookies = [1, 2, 3]
```

Hum `3` nahi denge, kyunki `2` wali cookie enough hai.

Best choice:

``` text
smallest cookie >= child's greed
```

------------------------------------------------------------------------

## Step 4: Backtrack?

Nahi.

Agar child ko smallest possible cookie de di, toh decision ko change
karne ki zarurat nahi.

------------------------------------------------------------------------

## Step 5: Global optimum?

Haan.

Smallest possible cookie use karne se badi cookies future children ke
liye bachti hain.

Therefore:

``` text
Greedy ✅
```

### Code

``` cpp
class Solution {
public:
    int findContentChildren(vector<int>& g, vector<int>& s) {
        sort(g.begin(), g.end());
        sort(s.begin(), s.end());

        int i = 0;
        int j = 0;
        int ans = 0;

        while (i < g.size() && j < s.size()) {

            if (s[j] >= g[i]) {
                ans++;
                i++;
                j++;
            }
            else {
                j++;
            }
        }

        return ans;
    }
};
```

### Logic

``` text
Sort both
   ↓
Smallest child
   ↓
Smallest possible cookie
   ↓
Satisfied → move both
Not satisfied → cookie too small → move cookie
```

------------------------------------------------------------------------

# 7. Example --- Activity Selection

Problem:

> Maximum number of non-overlapping activities select karni hain.

Suppose:

``` text
Activity    Start    End

A             1       2
B             3       4
C             0       6
D             5       7
```

Goal:

``` text
Maximum activities
```

### Greedy choice

> **Jo activity sabse pehle finish hoti hai, use choose karo.**

Why?

Agar koi activity jaldi finish ho gayi, toh remaining time mein aur
activities select karne ke chances zyada hain.

Therefore:

``` text
Earliest finishing activity
        ↓
More remaining time
        ↓
More activities possible
```

### General code idea

``` cpp
sort(activities.begin(), activities.end(),
     [](auto &a, auto &b) {
         return a.second < b.second;
     });

int ans = 0;
int lastEnd = -1;

for (auto &activity : activities) {

    int start = activity.first;
    int end = activity.second;

    if (start >= lastEnd) {
        ans++;
        lastEnd = end;
    }
}
```

------------------------------------------------------------------------

# 8. Example --- Fractional Knapsack

Goal:

> Maximum value obtain karni hai.

Har item ke paas:

``` text
value
weight
```

Greedy idea:

``` text
value / weight
```

calculate karo.

Jiska ratio maximum hai, use pehle lo.

Example:

``` text
Item A:
value = 10
weight = 2

ratio = 10/2 = 5
```

``` text
Item B:
value = 20
weight = 10

ratio = 20/10 = 2
```

A ka ratio zyada hai:

``` text
A → choose first
```

### Why Greedy works?

Fractional Knapsack mein item ko tod sakte hain.

Isliye highest value/weight item ka maximum possible portion lena
beneficial hai.

**Important:** Fractional Knapsack Greedy hai, lekin **0/1 Knapsack
Greedy nahi hai**.

------------------------------------------------------------------------

# 9. Example --- Coin Change: Greedy Trap

Coins:

``` text
[1, 3, 4]
```

Amount:

``` text
6
```

Agar hum greedy lagayein:

``` text
6
↓
4
↓
2
↓
1
↓
1
```

Answer:

``` text
4 + 1 + 1
= 3 coins
```

Lekin optimal answer:

``` text
3 + 3
= 2 coins
```

So:

``` text
Greedy ❌
```

### Lesson

> **"Largest coin first" hamesha correct nahi hota.**

Greedy ke liye correctness check karna zaroori hai.

------------------------------------------------------------------------

# 10. Example --- House Robber

Array:

``` text
[2, 7, 9, 3, 1]
```

Goal:

``` text
Maximum money
```

Adjacent houses nahi le sakte.

Question:

> Kya current step par biggest house lena best hai?

Agar `7` lete hain:

``` text
7 + 3 = 10
```

Lekin:

``` text
2 + 9 + 1 = 12
```

better hai.

So current biggest choice global optimum nahi deti.

``` text
Greedy ❌
```

Yahan:

``` text
Take
OR
Skip
```

dono possibilities matter karti hain.

Therefore DP is more appropriate.

------------------------------------------------------------------------

# 11. Greedy vs DP

## Greedy

``` text
Current best choice
        ↓
Take it
        ↓
Never reconsider
        ↓
Continue
```

## DP

``` text
Current state
      ↓
   Take
   /   \
future  future

      OR

   Skip
   /   \
future  future

      ↓
Compare
      ↓
Best answer
```

### Quick Difference

  Greedy                       DP
  ---------------------------- ----------------------------------
  Local best choice            Multiple possibilities
  Usually no reconsideration   States remember previous results
  Usually simpler              Usually more complex
  Can be very fast             Often more time/space
  Needs greedy-choice proof    Uses recurrence/state
  Activity Selection           House Robber
  Fractional Knapsack          0/1 Knapsack

------------------------------------------------------------------------

# 12. Greedy vs Backtracking

### Greedy

``` text
Choose best
→ Continue
```

### Backtracking

``` text
Choose
→ Explore
→ Undo
→ Try another choice
```

Backtracking is used when many possible combinations need to be
explored.

Examples:

``` text
Subsets
Permutations
N-Queens
Sudoku
Combination Sum
```

------------------------------------------------------------------------

# 13. Common Greedy Patterns

## Pattern 1: Sorting + Greedy

Bahut common.

``` cpp
sort(...);

for (...) {
    // greedy decision
}
```

Examples:

``` text
Assign Cookies
Activity Selection
Interval problems
Job Scheduling
```

------------------------------------------------------------------------

## Pattern 2: Two Pointers + Greedy

Example:

``` text
Assign Cookies
```

Sort both arrays:

``` text
g → children
s → cookies
```

Then:

``` text
i → child
j → cookie
```

Smallest possible match find karo.

------------------------------------------------------------------------

## Pattern 3: Sort by End Time

Common in interval scheduling.

``` text
Earliest finishing interval
→ choose
```

Example:

``` text
Activity Selection
Non-overlapping intervals
```

------------------------------------------------------------------------

## Pattern 4: Sort by Ratio

Fractional Knapsack:

``` text
value / weight
```

Highest ratio first.

------------------------------------------------------------------------

## Pattern 5: Maximum Reach

Kuch problems mein current reachable range se maximum future reach
maintain karte hain.

Example:

``` text
Jump Game
```

Idea:

``` text
current reachable area
        ↓
maximum future reach
        ↓
update
```

------------------------------------------------------------------------

## Pattern 6: Remove the Worst Choice

Kabhi-kabhi Greedy ka idea hota hai:

> Agar kuch remove karna hai, toh strategically worst element remove
> karo.

Examples:

``` text
Minimum removals
Interval problems
Monotonic stack based greedy problems
```

------------------------------------------------------------------------

# 14. Greedy Problems mein Sorting kyun hoti hai?

Sorting hume elements ko aise order mein process karne deti hai jahan
greedy choice obvious ho jaati hai.

Example:

``` text
Children:
[3, 1, 2]
```

Sort:

``` text
[1, 2, 3]
```

Ab smallest greed se start kar sakte hain.

Similarly activities:

``` text
Random order
      ↓
Sort by ending time
      ↓
Earliest finish first
```

So:

> **Sorting often reveals the greedy structure.**

But:

> **Har Greedy problem mein sorting zaroori nahi hoti.**

------------------------------------------------------------------------

# 15. Greedy ka Code Template

Greedy ka ek single fixed template nahi hai.

Lekin common structure:

``` cpp
sort(...);

int ans = 0;

for (...) {

    if (greedy_choice_is_possible) {

        // take best choice

        ans++;
    }
}

return ans;
```

Ya:

``` cpp
sort(...);

int i = 0;

while (...) {

    if (best_choice) {
        // take
    }
    else {
        // skip / move
    }
}
```

Important:

> **Template se zyada important greedy choice hai.**

------------------------------------------------------------------------

# 16. Greedy Identify karne ka Real Interview Method

Question read karo aur paper par ye likho:

``` text
GOAL:
What do I maximize/minimize?

CHOICE:
At every step, what can I choose?

GREEDY CHOICE:
What is the best choice right now?

SAFETY:
Why won't this choice hurt the optimal answer?

PROOF:
Can I explain why this local choice can be part
of an optimal solution?
```

Agar in questions ke convincing answers hain:

``` text
→ Greedy
```

Agar repeatedly different choices compare karni pad rahi hain:

``` text
→ DP / Backtracking / Other approach
```

------------------------------------------------------------------------

# 17. Greedy ko Blindly Identify Mat Karo

Ye mistakes avoid karo:

### Mistake 1

``` text
Maximum answer hai
→ Greedy
```

Wrong.

Maximum problem DP bhi ho sakti hai.

------------------------------------------------------------------------

### Mistake 2

``` text
Sorting hai
→ Greedy
```

Wrong.

Sorting bahut algorithms mein use hoti hai.

------------------------------------------------------------------------

### Mistake 3

``` text
Largest element choose karunga
→ Greedy
```

Wrong.

Largest element hamesha best choice nahi hota.

------------------------------------------------------------------------

### Mistake 4

``` text
Greedy simple lag raha hai
→ Use kar do
```

Wrong.

Correctness justify karo.

------------------------------------------------------------------------

# 18. Greedy ki Correctness ka Basic Proof Idea

Greedy solution ko justify karne ke liye ek common idea hai:

## Exchange Argument

Suppose:

``` text
Greedy choice = G
```

Aur optimal solution mein:

``` text
Choice = X
```

Agar hum X ko G se replace kar sakte hain aur solution ki quality kharab
nahi hoti:

``` text
Optimal solution
      ↓
X ko remove
      ↓
G ko add
      ↓
Solution still optimal
```

Then greedy choice safe hai.

Ye proof technique interviews mein useful hai.

------------------------------------------------------------------------

# 19. Greedy ke Common LeetCode Topics

Practice ke liye ye order useful hai:

### Beginner

1.  Assign Cookies
2.  Lemonade Change
3.  Best Time to Buy and Sell Stock II
4.  Can Place Flowers
5.  Jump Game

### Intermediate

6.  Jump Game II
7.  Gas Station
8.  Non-overlapping Intervals
9.  Minimum Number of Arrows to Burst Balloons
10. Partition Labels

### More Important

11. Activity Selection
12. Fractional Knapsack
13. Job Sequencing
14. Huffman Coding
15. Candy
16. Task Scheduler

------------------------------------------------------------------------

# 20. Revision ke liye One-Page Summary

``` text
                    GREEDY
                       ↓
              Optimization Problem
                       ↓
               Repeated Choices?
                       ↓
              Find Local Best Choice
                       ↓
          Can we safely commit to it?
                       ↓
            No need to reconsider?
                       ↓
       Local Best → Global Best ?
                       ↓
                    YES
                       ↓
                   GREEDY
```

### Golden Rule

> **Greedy = Local optimum choices ko repeatedly select karke global
> optimum tak pahunchna.**

### 5 Questions

``` text
1. Goal kya hai?
2. Choice kya hai?
3. Best local choice kya hai?
4. Kya choice safe hai?
5. Kya local best global best dega?
```

### Remember

``` text
Greedy ≠ Just maximum/minimum

Greedy ≠ Just sorting

Greedy ≠ Just largest/smallest element

Greedy =
Best current choice
+
Safe choice
+
No need to reconsider
+
Global optimum
```

------------------------------------------------------------------------

# 21. Greedy Question Solve karne ka Final Workflow

Jab bhi LeetCode par koi new problem mile:

``` text
Step 1:
Question ko normal language mein samjho.

Step 2:
Goal identify karo.

Step 3:
Choices identify karo.

Step 4:
Socho:
"Abhi sabse best choice kya hai?"

Step 5:
Check karo:
"Kya ye choice future mein problem create karegi?"

Step 6:
Ek counterexample dhoondhne ki try karo.

Step 7:
Agar counterexample nahi mil raha,
greedy choice ko justify/prove karo.

Step 8:
Sorting / two pointer / heap / set etc.
jo required ho use karo.

Step 9:
Code likho.

Step 10:
Time aur space complexity check karo.
```

------------------------------------------------------------------------

# 22. Final Mental Model

Greedy problem ko dekhkar turant code mat likhna.

Pehle apne mind mein ye conversation karo:

``` text
Me:
Mujhe kya optimize karna hai?

Problem:
Maximum / Minimum ...

Me:
Mujhe kya choose karna hai?

Problem:
Item / interval / cookie / job ...

Me:
Abhi sabse best choice kya hai?

Problem:
[Greedy choice]

Me:
Agar main ye choice kar loon,
kya future mein mujhe regret hoga?

Problem:
No, because [reason].

Me:
Kya ye choice optimal solution ka part ho sakti hai?

Problem:
Yes.

Me:
Okay → GREEDY.
```

**Ye thinking develop karna hi Greedy master karne ka main goal hai.**
