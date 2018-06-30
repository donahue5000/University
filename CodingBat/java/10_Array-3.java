//maxSpan 
//Consider the leftmost and righmost appearances of some value in an array. We'll say that the "span" is the number of 
//elements between the two inclusive. A single value has a span of 1. Returns the largest span found in the given array. 
//(Efficiency is not a priority.)

public int maxSpan(int[] nums) {
  int spanLength = 0;
  int maxSpanLength = 0;
  boolean toggleCount = false;
  for (int i = 0; i < nums.length; i++){
    spanLength = 0;
    toggleCount = false;
    for (int j = nums.length - 1; j >= i; j--){
      if (nums[i] == nums[j]){
        toggleCount = true;
      }
      if (toggleCount) spanLength++;
      if (maxSpanLength < spanLength) maxSpanLength = spanLength;
    }
  }
  return maxSpanLength;
}



//fix34 
//Return an array that contains exactly the same numbers as the given array, but rearranged so that every 3 is immediately 
//followed by a 4. Do not move the 3's, but every other number may move. The array contains the same number of 3's and 4's, 
//every 3 has a number after it that is not a 3, and a 3 appears in the array before any 4.

public int[] fix34(int[] nums) {
  //TODO Clean up this garbage....
  for (int i = 0; i < nums.length; i++){
    if (nums[i] == 3){
      if (nums[i + 1] == 4){
        i++;
        continue;
      }
      int cache = nums[i + 1];
      nums[i + 1] = 4;
      for (int j = 0; j < nums.length; j++){
        if (nums[j] == 4 && nums[j - 1] != 3){
          nums[j] = cache;
          break;
        }
      }
    }
  }
  return nums;
}



//fix45 
//(This is a slightly harder version of the fix34 problem.) Return an array that contains exactly the same numbers as 
//the given array, but rearranged so that every 4 is immediately followed by a 5. Do not move the 4's, but every other 
//number may move. The array contains the same number of 4's and 5's, and every 4 has a number after it that is not a 4. 
//In this version, 5's may appear anywhere in the original array.

public int[] fix45(int[] nums) {
  //TODO Clean up this garbage.... break it up with more methods
  for (int i = 0; i < nums.length; i++){
    if (nums[i] == 4){
      if (nums[i + 1] == 5){
        i++;
        continue;
      }
      int cache = nums[i + 1];
      nums[i + 1] = 5;
      for (int j = 0; j < nums.length; j++){
        if (j > 0 && nums[j] == 5 && nums[j - 1] != 4){
          nums[j] = cache;
          break;
        }
        if (j == 0 && nums[j] == 5){
          nums[j] = cache;
          break;
        }
      }
    }
  }
  return nums;
}



//canBalance 
//Given a non-empty array, return true if there is a place to split the array so that the sum of the numbers on one side 
//is equal to the sum of the numbers on the other side.

public boolean canBalance(int[] nums) {
  for (int i = 1; i <nums.length; i++){
    int[] first = new int[i];
    int[] last = new int[nums.length - i];
    for (int j = 0; j < first.length; j++){
      first[j] = nums[j];
    }
    for (int k = 0; k < last.length; k++){
        last[k] = nums[first.length + k];
    }
      if (arraySum(first) == arraySum(last)) return true;
  }
  return false;
}

public int arraySum(int[] x){
  int sum = 0;
  for (int i = 0; i < x.length; i++){
    sum += x[i];
  }
  return sum;
}



//linearIn 
//Given two arrays of ints sorted in increasing order, outer and inner, return true if all of the numbers in inner appear 
//in outer. The best solution makes only a single "linear" pass of both arrays, taking advantage of the fact that both 
//arrays are already in sorted order.

public boolean linearIn(int[] outer, int[] inner) {
  for (int i = 0; i < inner.length; i++) {
    for (int j = i; j < outer.length; j++) {
      if (inner[i] == outer[j]) break;
      if (j == outer.length - 1) return false;
    }
  }
  return true;
}



//squareUp 
//Given n>=0, create an array length n*n with the following pattern, shown here for n=3 : 
//{0, 0, 1,    0, 2, 1,    3, 2, 1} (spaces added to show the 3 groups).

public int[] squareUp(int n) {
  int[] solution = new int[n * n];
  for (int i = 1; i <= n; i++){
    for (int j = 1; j <= i; j++){
      solution[n * i - j] = j;
    }
  }
  return solution;
}



//seriesUp 
//Given n>=0, create an array with the pattern {1,    1, 2,    1, 2, 3,   ... 1, 2, 3 .. n} (spaces added t
//o show the grouping). Note that the length of the array will be 1 + 2 + 3 ... + n, which is known to sum 
//to exactly n*(n + 1)/2.

public int[] seriesUp(int n) {
  int[] solution = new int[n * (n + 1) / 2];
  int solutionIndex = 0;
  for (int i = 1; i <= n; i++){
    for (int j = 1; j <= i; j++){
      solution[solutionIndex] = j;
      solutionIndex++;
    }
  }
  return solution;
}



//maxMirror 
//We'll say that a "mirror" section in an array is a group of contiguous elements such that somewhere in the 
//array, the same group appears in reverse order. For example, the largest mirror section in {1, 2, 3, 8, 9, 3, 2, 1} 
//is length 3 (the {1, 2, 3} part). Return the size of the largest mirror section found in the given array.

public int maxMirror(int[] nums) {
  int max = 0;
  int tempMax = 0;
  for (int i = 0; i <= nums.length - 1; i++){
    tempMax = 0;
    for (int j = nums.length - 1; j >= 0 && i + tempMax < nums.length; j--){
      if (nums[i + tempMax] == nums[j]){
        tempMax++;
      }else{
        if (tempMax > max) max = tempMax;
        tempMax = 0;
      }
    }
    if (tempMax > max) max = tempMax;
  }
  return max;
}



//countClumps 
//Say that a "clump" in an array is a series of 2 or more adjacent elements of the same value. Return the number 
//of clumps in the given array.

public int countClumps(int[] nums) {
  int clumps = 0;
  int clumpLength = 0;
  for (int i = 0; i < nums.length; i++){
    for (int j = 1; j + i < nums.length; j++){
      if (nums[i] == nums[i + j]){
        clumpLength++;
      }else{
        break;
      }
    }
    if (clumpLength > 0) clumps++;
    i += clumpLength;
    clumpLength = 0;
  }
  return clumps;
}
