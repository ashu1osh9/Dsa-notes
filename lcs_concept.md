# Ek Aur Intuition (0 + Concept)

Bahut students ka ye doubt hota hai:

> Match hua to `1 + solve(...)` likhte hain.
>
> Lekin mismatch me `+0` kyu nahi likhte?

Actually hum likh sakte hain.

```cpp
if(text1[i] == text2[j]){

    // Current character answer me aa raha hai.
    return 1 + solve(i+1,j+1);

}
else{

    int notTake1 = 0 + solve(i+1,j);

    int notTake2 = 0 + solve(i,j+1);

    return max(notTake1,notTake2);
}
```

## Match

```cpp
1 + solve(i+1,j+1)
```

Sentence me

> Current character match ho gaya.

> Is character ko answer me include karenge.

> Isliye iska contribution **1** hai.

> Ab dono pointers aage badha do.

---

## Mismatch

```cpp
0 + solve(i+1,j)
```

Sentence me

> Current character answer ka part nahi ban raha.

> Isliye iska contribution **0** hai.

> First string ka character skip karke aage ka answer lao.

---

```cpp
0 + solve(i,j+1)
```

Sentence me

> Current character answer ka part nahi ban raha.

> Isliye iska contribution **0** hai.

> Second string ka character skip karke aage ka answer lao.

---

## Final

```cpp
return max(notTake1,notTake2);
```

Sentence me

> Dono raste explore karo.

> Dono raste se answer lekar aao.

> Jis raste se future me sabse lambi common subsequence milegi, us answer ko return kar do.

---

## Important Note

Hum generally code me

```cpp
solve(i+1,j)
```

hi likhte hain.

`0 +` isliye nahi likhte kyunki

```cpp
0 + x = x
```

Dono same hi hain.

Lekin concept samajhne ke liye tum hamesha aise soch sakte ho:

```cpp
Match

1 + solve(...)

↓

Current character contribute kar raha hai.

------------------------------------

Mismatch

0 + solve(...)

↓

Current character contribute nahi kar raha.
```

## Golden Trick

**Match**

```
Contribution = 1
```

**Mismatch**

```
Contribution = 0
```

Bas itna yaad rakh lo.

Isi wajah se match me `+1` aata hai aur mismatch me sirf recursive call hoti hai.