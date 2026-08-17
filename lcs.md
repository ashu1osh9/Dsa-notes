# Longest Common Subsequence (LCS) - Complete Revision Notes

> **Goal:** Jab bhi LCS revise karna ho, sirf ye notes padhkar pura concept yaad aa jana chahiye.

---

# Problem

Do strings di gayi hain.

Hume **Longest Common Subsequence** ki length nikalni hai.

Example:

```text
text1 = "abcde"
text2 = "ace"

Answer = 3

Subsequence = "ace"
```

---

# Sabse Pehle Difference Samjho

## Subsequence

Characters ko skip kar sakte ho.

Example

```text
abcde

ace  ✅
ade  ✅
abe  ✅
```

Characters ka order same rehna chahiye.

---

## Substring

Characters continuous hone chahiye.

```text
abcde

abc ✅
bcd ✅
ace ❌
```

LCS me hum **Subsequence** nikal rahe hain.

---

# Recursive Thinking

Hum ek function banate hain.

```cpp
solve(i,j)
```

Meaning

> "Agar main text1 ke index i aur text2 ke index j se start karu, to mujhe longest common subsequence ki kitni length milegi?"

---

# Base Case

```cpp
if(i==n || j==m)
    return 0;
```

## Matlab

Agar kisi bhi string ka end aa gaya,

to compare karne ke liye kuch bacha hi nahi.

Isliye answer = 0.

Example

```text
abc
   ↑

ace
 ↑
```

ya

```text
abc
 ↑

ace
    ↑
```

Ab kuch compare nahi ho sakta.

Return 0.

---

# Decision

Har recursive call me sirf ek hi question pucho.

> **Kya current characters equal hain?**

Bas.

---

# Case 1 : Match

Suppose

```text
a == a
```

Diagram

```text
text1

a b c d e
↑

text2

a c e
↑
```

Current character dono strings me common hai.

To ye answer ka part banega.

Isliye

```cpp
return 1 + solve(i+1,j+1);
```

---

## Is line ka matlab

```cpp
1 + solve(i+1,j+1)
```

Sentence me

> Current character mil gaya.

> Isliye answer me is character ko include karo.

> Isliye 1 add karo.

> Ab dono pointers ko aage badha do.

---

# Case 2 : Match Nahi Hua

Suppose

```text
b != c
```

Diagram

```text
text1

a b c d e
  ↑

text2

a c e
  ↑
```

Ab confusion hai.

Kaunsa character skip kare?

b ?

ya

c ?

Hume nahi pata.

Isliye hum dono possibilities check karte hain.

---

# Rasta 1

Text1 ka current character skip karo.

```cpp
solve(i+1,j)
```

Diagram

```text
a b c d e
    ↑

a c e
  ↑
```

---

# Rasta 2

Text2 ka current character skip karo.

```cpp
solve(i,j+1)
```

Diagram

```text
a b c d e
  ↑

a c e
    ↑
```

---

# Ab Kya Karen?

Dono raste explore karo.

Aur dekho

kis raste se future me

zyada lambi subsequence mil rahi hai.

```cpp
return max(
    solve(i+1,j),
    solve(i,j+1)
);
```

---

# Ye line ka matlab

```cpp
max(
solve(i+1,j),
solve(i,j+1)
)
```

Sentence me

> Mujhe nahi pata kis character ko skip karna better hoga.

> Isliye dono raste explore karo.

> Dono answer lekar aao.

> Jis raste se bada answer mile usse return kar do.

---

# Mera Favourite Way (Take / Not Take)

## Match

```cpp
return 1 + solve(i+1,j+1);
```

Sentence

> Character mil gaya.

Take karo.

1 add karo.

---

## Mismatch

```cpp
solve(i+1,j)
```

Matlab

Text1 ka character NOT TAKE.

---

```cpp
solve(i,j+1)
```

Matlab

Text2 ka character NOT TAKE.

---

Finally

```cpp
return max(notTake1,notTake2);
```

Sentence

> Dono not take wale raste explore karo.

Jo answer bada ho

usse return kar do.

---

# Kya Hum 0 + solve() Likh Sakte Hain?

Haan.

Mathematically

```cpp
return max(
0 + solve(i+1,j),
0 + solve(i,j+1)
);
```

Bilkul sahi hai.

Kyun?

Kyuki current character answer me contribute nahi kar raha.

Contribution = 0.

Lekin code me generally

```cpp
solve(i+1,j)
```

Hi likhte hain.

---

# Mera Sabse Bada Confusion

Question

> Equal me to 1 add ho raha hai.

> Lekin mismatch me value kaise aati hai?

Answer

Mismatch me

current call kuch add nahi karti.

Wo sirf neeche wali recursive calls se answer leti hai.

Example

```text
solve(i,j)

       |
   mismatch

   /         \

solve()     solve()

   4           2
```

Current call bolegi

```cpp
return max(4,2);
```

Answer = 4.

Usne kuch add nahi kiya.

Sirf best answer upar bhej diya.

---

# Ek Aur Example

```text
text1 = abc

text2 = ac
```

```
solve(0,0)

a==a

return 1 + solve(1,1)
```

Ab

```
solve(1,1)

b!=c
```

Dono raste

```
solve(2,1)=1

solve(1,2)=0
```

Return

```
max(1,0)=1
```

Ab upar

```
1 + 1

=2
```

Final Answer = 2.

---

# Complete Recursive Code

```cpp
class Solution {
public:

    int solve(string &text1,string &text2,int i,int j){

        if(i==text1.size() || j==text2.size()){
            return 0;
        }

        if(text1[i]==text2[j]){
            return 1 + solve(text1,text2,i+1,j+1);
        }

        int notTake1 = solve(text1,text2,i+1,j);

        int notTake2 = solve(text1,text2,i,j+1);

        return max(notTake1,notTake2);
    }

    int longestCommonSubsequence(string text1,string text2){

        return solve(text1,text2,0,0);
    }
};
```

---

# Memoization

Ek state

```
(i,j)
```

baar baar calculate ho rahi hai.

Isliye

```cpp
dp[i][j]
```

me answer store kar dete hain.

---

State Meaning

```cpp
dp[i][j]
```

Matlab

> solve(i,j) ka answer.

---

Transition

```cpp
if(match)

1+dp[i+1][j+1]

else

max(
dp[i+1][j],
dp[i][j+1]
)
```

---

# Tabulation

Recursion me

```cpp
solve(i+1,j)

solve(i,j+1)

solve(i+1,j+1)
```

use ho raha hai.

Isliye

table ko reverse fill karna padega.

Loop

```cpp
for(int i=n-1;i>=0;i--)
{
    for(int j=m-1;j>=0;j--)
    {

    }
}
```

---

# DP State

```cpp
dp[i][j]
```

Meaning

> i se aur j se start karne par LCS ki length.

---

# Base Condition

Recursion

```cpp
if(i==n || j==m)
return 0;
```

DP

```cpp
for(int j=0;j<=m;j++)
dp[n][j]=0;

for(int i=0;i<=n;i++)
dp[i][m]=0;
```

---

Transition

Match

```cpp
dp[i][j]=1+dp[i+1][j+1];
```

Mismatch

```cpp
dp[i][j]=max(dp[i+1][j],dp[i][j+1]);
```

Answer

```cpp
dp[0][0]
```

---

# Time Complexity

Recursion

```
Exponential
```

---

Memoization

```
O(n*m)
```

---

Tabulation

```
O(n*m)
```

---

# Interview Explanation (Best)

> Har recursive call me main sirf current characters compare karta hu.

> Agar characters equal hain to current character answer ka part banega, isliye 1 add karta hu aur dono pointers aage badha deta hu.

> Agar characters equal nahi hain to mujhe nahi pata kis string ka character skip karna sahi rahega.

> Isliye main dono possibilities explore karta hu.

> Ek me first string ka character skip karta hu.

> Dusre me second string ka character skip karta hu.

> Dono raste se jo maximum subsequence length milti hai usse return kar deta hu.

---

# One Line Revision

✅ Match

```
Take

1 + solve(i+1,j+1)
```

✅ Mismatch

```
Don't know which character to skip.

Explore both paths.

Return maximum.
```

---

# Golden Rule (Exam / Interview)

> **Match hua?**
>
> Character answer me include karo.

> **Match nahi hua?**
>
> Dono raste explore karo.

> Future me jis raste se sabse lambi subsequence milegi, usi answer ko return kar do.
