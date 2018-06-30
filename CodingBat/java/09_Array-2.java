//countEvens 
//Return the number of even ints in the given array. Note: the % "mod" operator computes the remainder, e.g. 5 % 2 is 1.

public int countEvens(int[] nums) {
  int evens = 0;
  for (int i: nums){
    if (i % 2 == 0){
      evens++;
    }
  }
  return evens;
}



//bigDiff 
//Given an array length 1 or more of ints, return the difference between the largest and smallest values in the array. 
//Note: the built-in Math.min(v1, v2) and Math.max(v1, v2) methods return the smaller or larger of two values.

public int bigDiff(int[] nums) {
  int smallest = nums[0];
  int biggest = nums[0];
  for (int i: nums){
    smallest = Math.min(i, smallest);
    biggest = Math.max(i, biggest);
  }
  return biggest - smallest;
}



//centeredAverage 
//Return the "centered" average of an array of ints, which we'll say is the mean average of the values, except 
//ignoring the largest and smallest values in the array. If there are multiple copies of the smallest value, 
//ignore just one copy, and likewise for the largest value. Use int division to produce the final average. 
//You may assume that the array is length 3 or more.

public int centeredAverage(int[] nums) {
  Arrays.sort(nums);
  int sum = 0;
  for (int i = 1; i < nums.length -1; i++){
    sum += nums[i];
  }
  return sum / (nums.length - 2);
}



//sum13 
//Return the sum of the numbers in the array, returning 0 for an empty array. Except the number 13 is very 
//unlucky, so it does not count and numbers that come immediately after a 13 also do not count.

public int sum13(int[] nums) {
  int sum = 0;
  for (int i = 0; i < nums.length; i++){
    if (nums[i] == 13){
      i++;
      continue;
    }else sum += nums[i];
  }
  return sum;
}



//sum67 
//Return the sum of the numbers in the array, except ignore sections of numbers starting with a 6 and extending 
//to the next 7 (every 6 will be followed by at least one 7). Return 0 for no numbers.

public int sum67(int[] nums) {
  int sum = 0;
  boolean exclusion = false;
  for (int i = 0; i < nums.length; i++){
    if (nums[i] == 7 && exclusion == true){
      exclusion = false;
      continue;
    }
    if (nums[i] == 6){
      exclusion = true;
      continue;
    }
    if (exclusion == false) sum += nums[i];
  }
  return sum;
}



//has22 
//Given an array of ints, return true if the array contains a 2 next to a 2 somewhere.

public boolean has22(int[] nums) {
  for (int i = 0; i < nums.length; i++){
    if (nums[i] == 2){
      if (i < nums.length - 1 && nums[i+1] == 2) return true;
    }
  }
  return false;
}



//lucky13 
//Given an array of ints, return true if the array contains no 1's and no 3's.

public boolean lucky13(int[] nums) {
  for (int i: nums){
    if (i == 1 || i == 3) return false;
  }
  return true;
}



//sum28 
//Given an array of ints, return true if the sum of all the 2's in the array is exactly 8.

public boolean sum28(int[] nums) {
  int sum = 0;
  for (int i: nums){
    if (i == 2) sum += 2;
  }
  return sum == 8;
}



//more14 
//Given an array of ints, return true if the number of 1's is greater than the number of 4's

public boolean more14(int[] nums) {
  int counter1 = 0;
  int counter4 = 0;
  for (int i: nums){
    if (i == 1) counter1++;
    if (i == 4) counter4++;
  }
  return counter1 > counter4;
}



//fizzArray 
//Given a number n, create and return a new int array of length n, containing the numbers 0, 1, 2, ... n-1. 
//The given n may be 0, in which case just return a length 0 array. You do not need a separate if-statement 
//for the length-0 case; the for-loop should naturally execute 0 times in that case, so it just works. The 
//syntax to make a new int array is: new int[desired_length]

public int[] fizzArray(int n) {
  int[] solution = new int[n];
  for (int i = 0; i < solution.length; i++){
    solution[i] = i;
  }
  return solution;
}



//only14 
//Given an array of ints, return true if every element is a 1 or a 4.

public boolean only14(int[] nums) {
  for (int i: nums){
    if (i != 1 && i != 4) return false;
  }
  return true;
}



//fizzArray2 
//Given a number n, create and return a new string array of length n, containing the strings "0", "1" "2" .. 
//through n-1. N may be 0, in which case just return a length 0 array. Note: String.valueOf(xxx) will make the 
//String form of most types. The syntax to make a new string array is: new String[desired_length]

public String[] fizzArray2(int n) {
  String[] solution = new String[n];
  for (int i = 0; i < n; i++){
    solution[i] = String.valueOf(i);
  }
  return solution;
}



//no14 
//Given an array of ints, return true if it contains no 1's or it contains no 4's.

public boolean no14(int[] nums) {
  boolean no1 = true;
  boolean no4 = true;
  for (int i: nums){
    if (i == 1) no1 = false;
    if (i == 4) no4 = false;
  }
  return no1 || no4;
}



//isEverywhere 
//We'll say that a value is "everywhere" in an array if for every pair of adjacent elements in the array, at least 
//one of the pair is that value. Return true if the given value is everywhere in the array.

public boolean isEverywhere(int[] nums, int val) {
  for (int i = 0; i < nums.length - 1; i++){
    if (nums[i] != val){
      if (nums[i+1] != val) return false;
    }
  }
  return true;
}



//either24 
//Given an array of ints, return true if the array contains a 2 next to a 2 or a 4 next to a 4, but not both.

public boolean either24(int[] nums) {
  boolean has2 = false;
  boolean has4 = false;
  for (int i = 0; i < nums.length - 1; i++){
    if (nums[i] == 2 && nums[i+1] == 2) has2 = true;
    if (nums[i] == 4 && nums[i+1] == 4) has4 = true;
  }
  return has2 ^ has4;
}



//matchUp 
//Given arrays nums1 and nums2 of the same length, for every element in nums1, consider the corresponding element 
//in nums2 (at the same index). Return the count of the number of times that the two elements differ by 2 or less, 
//but are not equal.

public int matchUp(int[] nums1, int[] nums2) {
  int counter = 0;
  for (int i = 0; i < nums1.length; i++){
    int difference = Math.abs(nums1[i] - nums2[i]);
    if (difference > 0 && difference <=2) counter++;
  }
  return counter;
}



//has77 
//Given an array of ints, return true if the array contains two 7's next to each other, or there are two 7's separated 
//by one element, such as with {7, 1, 7}.

public boolean has77(int[] nums) {
  for (int i = 0; i < nums.length - 1; i++){
    if (i == nums.length - 2){
      if (nums[i] == 7 && nums[i+1] == 7){
        return true;
      }else break;
    }
      if (nums[i] == 7 && (nums[i+1] == 7 || nums[i+2] == 7)) return true;
  }
  return false;
}



//has12 
//Given an array of ints, return true if there is a 1 in the array with a 2 somewhere later in the array.

public boolean has12(int[] nums) {
  boolean check1 = false;
  boolean check2 = false;
  for (int i = 0; i < nums.length; i++){
    if (nums[i] == 1){
      for (int x = i; x < nums.length; x++){
        if (nums[x] == 2) return true;
      }
    }
  }
  return false;
}



//modThree 
//Given an array of ints, return true if the array contains either 3 even or 3 odd values all next to each other.

public boolean modThree(int[] nums) {
  if (nums.length < 3) return false;
  for (int i = 0; i <= nums.length - 3; i++){
    if (nums[i] % 2 == 0 && nums[i+1] % 2 == 0 && nums[i+2] % 2 == 0) return true;
    if (nums[i] % 2 != 0 && nums[i+1] % 2 != 0 && nums[i+2] % 2 != 0) return true;
  }
  return false;
}



//haveThree 
//Given an array of ints, return true if the value 3 appears in the array exactly 3 times, and no 3's are next to each other.

public boolean haveThree(int[] nums) {
  int counter = 0;
  for (int i = 0; i < nums.length; i++){
    if (i == nums.length - 1){
      if (nums[i] == 3) counter++;
      break;
    }
    if (nums[i] == 3){
      counter++;
      if (nums[i+1] == 3) return false;
    }
  }
  return counter == 3;
}



//twoTwo 
//Given an array of ints, return true if every 2 that appears in the array is next to another 2.

public boolean twoTwo(int[] nums) {
  //Check single index arrays
  if (nums.length == 1){
    if (nums[0] == 2){
      return false;
    }else return true;
  }
  for (int i = 0; i < nums.length; i++){
    
    // Check last index
    if (i == nums.length - 1){
      if (nums[i] == 2 && nums[i-1] != 2) return false;
      break;
    }
    //Check first indexes
    if (nums[i] == 2){
      if (nums[i+1] != 2){
        return false;
      }else i++;
    }
  }
  return true;
}



//sameEnds 
//Return true if the group of N numbers at the start and end of the array are the same. For example, 
//with {5, 6, 45, 99, 13, 5, 6}, the ends are the same for n=0 and n=2, and false for n=1 and n=3. 
//You may assume that n is in the range 0..nums.length inclusive.

public boolean sameEnds(int[] nums, int len) {
  for (int i = 0; i < len; i++){
    if (nums[i] == nums[nums.length - len + i]){
      continue;
    }else return false;
  }
  return true;
}



//tripleUp 
//Return true if the array contains, somewhere, three increasing adjacent numbers like .... 4, 5, 6, ... or 23, 24, 25.

public boolean tripleUp(int[] nums) {
  for (int i = 0; i <= nums.length - 3; i++){
    if (nums[i] == nums[i+1] - 1 && nums[i] == nums[i+2] - 2) return true;
  }
  return false;
}



//fizzArray3 
//Given start and end numbers, return a new array containing the sequence of integers from start up to but not 
//including end, so start=5 and end=10 yields {5, 6, 7, 8, 9}. The end number will be greater or equal to the start number. 
//Note that a length-0 array is valid.

public int[] fizzArray3(int start, int end) {
  int length = end - start;
  int[] solution = new int[length];
  int j = start;
  for (int i = 0; i < solution.length; i++){
    solution[i] = j;
    j++;
  }
  return solution;
}



//shiftLeft 
//Return an array that is "left shifted" by one -- so {6, 2, 5, 3} returns {2, 5, 3, 6}. You may modify and return the 
//given array, or return a new array.

public int[] shiftLeft(int[] nums) {
  int[] solution = new int[nums.length];
  if (solution.length == 1){
     solution[0] = nums[0];
  }else if (solution.length > 1){
    for (int i = 0; i < solution.length; i++){
      if (i == solution.length - 1){
        solution[i] = nums[0];
        break;
      } 
      solution[i] = nums[i+1];
    }
  }
  return solution;
}



//tenRun 
//For each multiple of 10 in the given array, change all the values following it to be that multiple of 10, until 
//encountering another multiple of 10. So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.

public int[] tenRun(int[] nums) {
  int replacer = 0;
  boolean tripped = false;
  for (int i = 0; i < nums.length; i++){
    if (nums[i] % 10 == 0){
      replacer = nums[i];
      tripped = true;
    }else if (tripped) nums[i] = replacer;
  }
  return nums;
}



//pre4 
//Given a non-empty array of ints, return a new array containing the elements from the original array that come before 
//the first 4 in the original array. The original array will contain at least one 4. Note that it is valid in java to 
//create an array of length 0.

public int[] pre4(int[] nums) {
  int counter = 0;
  while (nums[counter] != 4){
    counter++;
  }
  int[] solution = new int[counter];
  for (int i = 0; i < solution.length; i++){
    solution[i] = nums[i];
  }
  return solution;
}



//post4 
//Given a non-empty array of ints, return a new array containing the elements from the original array that come after 
//the last 4 in the original array. The original array will contain at least one 4. Note that it is valid in java to 
//create an array of length 0.

public int[] post4(int[] nums) {
  int lengthSet = 0;
  while (nums[nums.length - 1 - lengthSet] != 4){
    lengthSet++;
  }
  int[] solution = new int[lengthSet];
  for (int i = 0; i < solution.length; i++){
    solution[i] = nums[nums.length - lengthSet + i];
  }
  return solution;
}



//notAlone 
//We'll say that an element in an array is "alone" if there are values before and after it, and those values are different 
//from it. Return a version of the given array where every instance of the given value which is alone is replaced by whichever 
//value to its left or right is larger.

public int[] notAlone(int[] nums, int val) {
  int[] solution = nums;
  for (int i = 1; i < nums.length - 1; i++){
    if (nums[i] == val){
      if (nums[i] != nums[i-1] && nums[i] != nums[i+1]){
        if (nums[i-1] > nums[i+1]){
          solution[i] = nums[i-1];
        }else solution[i] = nums[i+1];
        i++;
      }
    }
  }
  return nums;
}



//zeroFront 
//Return an array that contains the exact same numbers as the given array, but rearranged so that all the zeros are 
//grouped at the start of the array. The order of the non-zero numbers does not matter. So {1, 0, 0, 1} becomes 
//{0 ,0, 1, 1}. You may modify and return the given array or make a new array.

public int[] zeroFront(int[] nums) {
  int zeroCounter = 0;
  for (int i = 0; i < nums.length; i++){
    if (nums[i] == 0) zeroCounter++;
  }
  int[] solution = new int[nums.length];
  for (int i = 0; i < nums.length; i++){
    if (nums[i] != 0){
      solution[zeroCounter] = nums[i];
      zeroCounter++;
    }
  }
  return solution;
}



//withoutTen 
//Return a version of the given array where all the 10's have been removed. The remaining elements should shift 
//left towards the start of the array as needed, and the empty spaces a the end of the array should be 0. 
//So {1, 10, 10, 2} yields {1, 2, 0, 0}. You may modify and return the given array or make a new array.

public int[] withoutTen(int[] nums) {
  int[] solution = new int[nums.length];
  for (int i = 0, j = 0; i < nums.length; i++){
    if (nums[i] != 10){
      solution[j] = nums[i];
      j++;
    }
  }
  return solution;
}



//zeroMax 
//Return a version of the given array where each zero value in the array is replaced by the largest odd value 
//to the right of the zero in the array. If there is no odd value to the right of the zero, leave the zero as a zero.

public int[] zeroMax(int[] nums) {
  int replacer = 0;
  for (int i = 0; i < nums.length - 1; i++){
    replacer = 0;
    if (nums[i] == 0){
      for (int j = i; j < nums.length; j++){
        if (nums[j] % 2 != 0 && replacer < nums[j]) replacer = nums[j];
      }
      nums[i] = replacer;
    }
  }
  return nums;
}



//evenOdd 
//Return an array that contains the exact same numbers as the given array, but rearranged so that all the even numbers 
//come before all the odd numbers. Other than that, the numbers can be in any order. You may modify and return the 
//given array, or make a new array.

public int[] evenOdd(int[] nums) {
  int[] solution = new int[nums.length];
  int solutionCounter = 0;
  //Build solution with evens
  for (int i = 0; i < nums.length; i++){
    if (nums[i] % 2 == 0){
      solution[solutionCounter] = nums[i];
      solutionCounter++;
    }
  }
  //Build solution with odds
  for (int i = 0; i < nums.length; i++){
    if (nums[i] % 2 != 0){
      solution[solutionCounter] = nums[i];
      solutionCounter++;
    }
  }
  return solution;
}



//fizzBuzz 
//This is slightly more difficult version of the famous FizzBuzz problem which is sometimes given as a first problem 
//for job interviews. (See also: FizzBuzz Code.) Consider the series of numbers beginning at start and running up to 
//but not including end, so for example start=1 and end=5 gives the series 1, 2, 3, 4. Return a new String[] array 
//containing the string form of these numbers, except for multiples of 3, use "Fizz" instead of the number, for 
//multiples of 5 use "Buzz", and for multiples of both 3 and 5 use "FizzBuzz". In Java, String.valueOf(xxx) will 
//make the String form of an int or other type. This version is a little more complicated than the usual version 
//since you have to allocate and index into an array instead of just printing, and we vary the start/end instead 
//of just always doing 1..100.

public String[] fizzBuzz(int start, int end) {
  int[] preSolution = new int[end - start];
  String[] solution = new String[preSolution.length];
  //Populate preSolution array with numbers
  for (int i = 0; i < preSolution.length; i++){
    preSolution[i] = start + i;
  }
  //Replace numbers with Strings, Fizzes and Buzzes
  for (int i = 0; i < solution.length; i++){
    if (preSolution[i] % 3 == 0 && preSolution[i] % 5 == 0){
      solution[i] = "FizzBuzz";
    }else if (preSolution[i] % 3 == 0){
      solution[i] = "Fizz";
    }else if (preSolution[i] % 5 == 0){
      solution[i] = "Buzz";
    }else solution[i] = String.valueOf(preSolution[i]);
  }
  return solution;
}
