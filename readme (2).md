# Sliding Window & Kadane's Algorithm Notes

# 1. Constant Sliding Window

## Idea

-   Window size fixed hoti hai.
-   Har baar:
    -   ek element remove
    -   ek element add

Example:

``` text
arr = [2,5,1,8,2,9], k = 3

[2,5,1] -> sum=8
[5,1,8] -> 8-2+8 =14
[1,8,2] ->14-5+2 =11
[8,2,9] ->11-1+9 =19
```

## Template

``` cpp
int sum = 0;

for(int i=0;i<k;i++)
    sum += arr[i];

int ans = sum;

for(int i=k;i<n;i++){
    sum = sum - arr[i-k] + arr[i];
    ans = max(ans,sum);
}

return ans;
```

------------------------------------------------------------------------

# 2. Variable Sliding Window

## Idea

-   Right pointer se window badhao.
-   Condition toot jaaye to Left pointer se window chhoti karo.

Example:

``` text
arr=[2,1,3,2,4]
k=8

[2]
[2,1]
[2,1,3]
[2,1,3,2]
[2,1,3,2,4] X

Left++
[1,3,2,4] X

Left++
[3,2,4] X

Left++
[2,4] ✓
```

## Template

``` cpp
int left = 0;
int sum = 0;

for(int right=0; right<n; right++){

    sum += arr[right];

    while(sum > k){
        sum -= arr[left];
        left++;
    }

    ans = max(ans, right-left+1);
}
```

------------------------------------------------------------------------

# 3. Kadane's Algorithm

## Use

Maximum Sum Subarray

Example:

``` text
[-2,1,-3,4,-1,2,1,-5,4]
Answer = 6
Subarray = [4,-1,2,1]
```

## Logic

-   currSum += nums\[i\]
-   maxSum = max(maxSum, currSum)
-   Agar currSum negative ho jaye to currSum = 0

## Code

``` cpp
int maxSubArray(vector<int>& nums) {

    int currSum = 0;
    int maxSum = INT_MIN;

    for(int i=0;i<nums.size();i++){

        currSum += nums[i];

        maxSum = max(maxSum, currSum);

        if(currSum < 0){
            currSum = 0;
        }
    }

    return maxSum;
}
```

## DP Form

``` cpp
currSum = max(nums[i], currSum + nums[i]);
maxSum = max(maxSum, currSum);
```

------------------------------------------------------------------------

# Quick Revision

## Constant Window

-   Fixed size
-   Remove one
-   Add one

``` cpp
sum = sum - old + new;
```

## Variable Window

-   Right++ =\> Expand
-   Condition fail =\> Left++
-   Answer update

## Kadane

-   Add current element
-   Update maximum
-   Negative sum? Reset to 0
