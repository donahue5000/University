//groupSum 
//Given an array of ints, is it possible to choose a group of some of the ints, such that the group sums to the given 
//target? This is a classic backtracking recursion problem. Once you understand the recursive backtracking strategy 
//in this problem, you can use the same pattern for many problems to search a space of choices. Rather than looking 
//at the whole array, our convention is to consider the part of the array starting at index start and continuing to 
//the end of the array. The caller can specify the whole array simply by passing start as 0. No loops are needed -- the 
//recursive calls progress down the array.

public boolean groupSum(int start, int[] nums, int target) {
  if (start >= nums.length){
    if (target == 0){
      return true;
    }else{
      return false;
    }
  }
  if (groupSum(start + 1, nums, target - nums[start])){
    return true;
  }else if (groupSum(start + 1, nums, target)){
    return true;
  }else{
    return false;
  }
}



//groupSum6 
//Given an array of ints, is it possible to choose a group of some of the ints, beginning at the start index, such 
//that the group sums to the given target? However, with the additional constraint that all 6's must be chosen. 
//(No loops needed.)

public boolean groupSum6(int start, int[] nums, int target) {
  if (start >= nums.length) return target == 0;
  if (nums[start] == 6){
    if (groupSum6(start + 1, nums, target - 6)){
      return true;
    }
  }else if (groupSum6(start + 1, nums, target - nums[start])){
    return true;
  }else if (groupSum6(start + 1, nums, target)){
    return true;
  }
  return false;
}



//groupNoAdj 
//Given an array of ints, is it possible to choose a group of some of the ints, such that the group sums to the given target 
//with this additional constraint: If a value in the array is chosen to be in the group, the value immediately following it 
//in the array must not be chosen. (No loops needed.)

public boolean groupNoAdj(int start, int[] nums, int target) {
  if (start >= nums.length) return target == 0;
  if (groupNoAdj(start + 2, nums, target - nums[start])){
    return true;
  } 
  if (groupNoAdj(start + 1, nums, target)){
    return true;
  }
  return false;
}



//groupSum5 
//Given an array of ints, is it possible to choose a group of some of the ints, such that the group sums to the given target 
//with these additional constraints: all multiples of 5 in the array must be included in the group. If the value immediately 
//following a multiple of 5 is 1, it must not be chosen. (No loops needed.)

public boolean groupSum5(int start, int[] nums, int target) {
  if (start >= nums.length) return target == 0;
  if (nums[start] % 5 == 0){
    if (start < nums.length - 1 && nums[start + 1] == 1){
      if (groupSum5(start + 2, nums, target - nums[start])){
        return true;
      }
    }else if (groupSum5(start + 1, nums, target - nums[start])){
      return true;
    }
  }else if (groupSum5(start + 1, nums, target - nums[start])){
    return true;
  }else if (groupSum5(start + 1, nums, target)){
    return true;
  }
  return false;
}



//groupSumClump 
//Given an array of ints, is it possible to choose a group of some of the ints, such that the group sums to the given 
//target, with this additional constraint: if there are numbers in the array that are adjacent and the identical value, they 
//must either all be chosen, or none of them chosen. For example, with the array {1, 2, 2, 2, 5, 2}, either all three 2's in 
//the middle must be chosen or not, all as a group. (one loop can be used to find the extent of the identical values).

public boolean groupSumClump(int start, int[] nums, int target) {
  int counter = 1;
  if (start >= nums.length) return target == 0;
  if (start < nums.length - 1 && nums[start] == nums[start + 1]){
    for (int i = start; i < nums.length; i++){
      if (nums[i] == nums[i + 1]){
        counter++;
      }else{
        break;
      }
    }
    if (groupSumClump(start + counter, nums, target - (nums[start] * counter))){
      return true;
    }
    if (groupSumClump(start + counter, nums, target)){
      return true;
    }
  }else if (groupSumClump(start + 1, nums, target - nums[start])){
    return true;
  }else if (groupSumClump(start + 1, nums, target)){
    return true;
  }
  return false;
}



//splitArray 
//Given an array of ints, is it possible to divide the ints into two groups, so that the sums of the two groups are the 
//same. Every int must be in one group or the other. Write a recursive helper method that takes whatever arguments you 
//like, and make the initial call to your recursive helper from splitArray(). (No loops needed.)

public boolean splitArray(int[] nums) {
  return splitter(0, 0, nums);
}
boolean splitter(int numsIndex, int sum, int[] nums){
  if (numsIndex >= nums.length){ 
    return sum == 0;
  }
  if (splitter(numsIndex + 1, sum + nums[numsIndex], nums)) return true;
  if (splitter(numsIndex + 1, sum - nums[numsIndex], nums)) return true;
  return false;
}



//splitOdd10 
//Given an array of ints, is it possible to divide the ints into two groups, so that the sum of one group is a multiple 
//of 10, and the sum of the other group is odd. Every int must be in one group or the other. Write a recursive helper method 
//that takes whatever arguments you like, and make the initial call to your recursive helper from splitOdd10().
//(No loops needed.)

public boolean splitOdd10(int[] nums) {
  return splitter(0, 0, 0, nums);
}
boolean splitter(int numsIndex, int totalTen, int totalOdd, int[] nums){
  if (numsIndex >= nums.length){
    return totalTen % 10 == 0 && totalOdd % 2 != 0;
  }
  if (splitter(numsIndex + 1, totalTen + nums[numsIndex], totalOdd, nums)){
    return true;
  }
  if (splitter(numsIndex + 1, totalTen, totalOdd + nums[numsIndex], nums)){
    return true;
  }
  return false;
}



//split53 
//Given an array of ints, is it possible to divide the ints into two groups, so that the sum of the two groups is the same, 
//with these constraints: all the values that are multiple of 5 must be in one group, and all the values that are a multiple 
//of 3 (and not a multiple of 5) must be in the other. (No loops needed.)

public boolean split53(int[] nums) {
  return splitter(0, 0, nums);
}
boolean splitter (int numsIndex, int total, int[] nums){
  if (numsIndex >= nums.length){
    return total == 0;
  }
  if (nums[numsIndex] % 3 != 0){
    if (splitter(numsIndex + 1, total + nums[numsIndex], nums)){
      return true;
    }
  }
  if (nums[numsIndex] % 5 != 0){
    if (splitter(numsIndex + 1, total - nums[numsIndex], nums)){
      return true;
    }
  }
  return false;
}
