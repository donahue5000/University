//doubleChar 
//Given a string, return a string where for every char in the original, there are two chars.

public String doubleChar(String str) {
  String solution = "";
  if (str.length() == 0) return str;
  for (int i = 0; i < str.length(); i++){
    solution += str.substring(i,i+1) + str.substring(i,i+1);
  }
  return solution;
}



//countHi 
//Return the number of times that the string "hi" appears anywhere in the given string.

public int countHi(String str) {
  int counter = 0;
  for (int i = 0; i < str.length() - 1; i++){
    if (str.substring(i, i + 2).equals("hi")) counter++;
  }
  return counter;
}



//catDog
//Return true if the string "cat" and "dog" appear the same number of times in the given string.

public boolean catDog(String str) {
  int catCounter = 0;
  int dogCounter = 0;
  if (str.length() < 3) return true;
  for (int i = 0; i < str.length() - 2; i++){
    if (str.substring(i, i + 3).equals("cat")) catCounter++;
    if (str.substring(i, i + 3).equals("dog")) dogCounter++;
  }
  return catCounter == dogCounter;
}



//countCode 
//Return the number of times that the string "code" appears anywhere in the given string, except we'll accept any 
//letter for the 'd', so "cope" and "cooe" count.

public int countCode(String str) {
  int counter = 0;
  if (str.length() < 4) return 0;
  for (int i = 0; i < str.length() - 3; i++){
    if (str.substring(i, i + 2).equals("co") && str.substring(i + 3, i + 4).equals("e")) counter ++;
    
  }
  return counter;
}



//endOther 
//Given two strings, return true if either of the strings appears at the very end of the other string, ignoring 
//upper/lower case differences (in other words, the computation should not be "case sensitive"). 
//Note: str.toLowerCase() returns the lowercase version of a string.

public boolean endOther(String a, String b) {
  a = a.toLowerCase();
  b = b.toLowerCase();
  
  if (a.length() <= b.length()){
    if (b.substring(b.length() - a.length()).equals(a)) return true;
  }
  if (b.length() <= a.length()){
    if (a.substring(a.length() - b.length()).equals(b)) return true;
  }
  return false;
}



//xyzThere 
//Return true if the given string contains an appearance of "xyz" where the xyz is not directly preceeded by a 
//period (.). So "xxyz" counts but "x.xyz" does not.

public boolean xyzThere(String str) {
  if (str.equals("xyz")) return true;
  if (str.length() < 4) return false;
  if (str.substring(0, 3).equals("xyz")) return true;
  for (int i = 0; i <= str.length() - 4; i++){
    if (!str.substring(i, i+1).equals(".") && str.substring(i+1, i+4).equals("xyz")) return true;
  }
  return false;
}



//bobThere 
//Return true if the given string contains a "bob" string, but where the middle 'o' char can be any char.

public boolean bobThere(String str) {
  if (str.length() < 3) return false;
  for (int i = 0; i <= str.length() - 3; i++){
    if (str.charAt(i) == 'b' && str.charAt(i+2) == 'b') return true;
  }
  return false;
}



//xyBalance 
//We'll say that a String is xy-balanced if for all the 'x' chars in the string, there exists a 'y' char somewhere 
//later in the string. So "xxy" is balanced, but "xyx" is not. One 'y' can balance multiple 'x's. Return true if the 
//given string is xy-balanced.

public boolean xyBalance(String str) {
  int solution = 0;
  for (int i = 0; i < str.length(); i++){
    if (str.charAt(i) == 'x') solution = 1;
    if (str.charAt(i) == 'y') solution = 0;
  }
  return solution == 0;
}



//mixString 
//Given two strings, a and b, create a bigger string made of the first char of a, the first char of b, the second 
//char of a, the second char of b, and so on. Any leftover chars go at the end of the result.

public String mixString(String a, String b) {
  String solution = "";
  int longer = Math.max(a.length(), b.length());
  for (int i = 0; i < longer; i++){
    if (i < a.length()) solution += a.substring(i, i+1);
    if (i < b.length()) solution += b.substring(i, i+1);
  }
  return solution;
}



//repeatEnd 
//Given a string and an int n, return a string made of n repetitions of the last n characters of the string. You may 
//assume that n is between 0 and the length of the string, inclusive.

public String repeatEnd(String str, int n) {
  String solution = "";
  for (int i = 1; i <= n; i++){
    solution += str.substring(str.length() - n);
  }
  return solution;
}



//repeatFront 
//Given a string and an int n, return a string made of the first n characters of the string, followed by the first n-1 
//characters of the string, and so on. You may assume that n is between 0 and the length of the string, inclusive 
//(i.e. n >= 0 and n <= str.length()).

public String repeatFront(String str, int n) {
  String solution = "";
  for (int i = n; i > 0; i--){
    solution += str.substring(0, i);
  }
  return solution;
}



//repeatSeparator 
//Given two strings, word and a separator sep, return a big string made of count occurrences of the word, separated 
//by the separator string.

public String repeatSeparator(String word, String sep, int count) {
  if (count == 0) return "";
  String solution = word;
  int i = 1;
  while (i < count){
    solution += sep + word;
    i++;
  }
  return solution;
}



//prefixAgain 
//Given a string, consider the prefix string made of the first N chars of the string. Does that prefix string appear 
//somewhere else in the string? Assume that the string is not empty and that N is in the range 1..str.length().

public boolean prefixAgain(String str, int n) {
  for (int i = 1; i <= str.length() - n; i++){
    if (str.substring(0, n).equals(str.substring(i, i+n))) return true;
  }
  return false;
}



//xyzMiddle 
//Given a string, does "xyz" appear in the middle of the string? To define middle, we'll say that the number of 
//chars to the left and right of the "xyz" must differ by at most one. This problem is harder than it looks.

public boolean xyzMiddle(String str) {
  if (str.length() < 3) return false;
  if (str.equals("xyz")) return true;
  if (str.length() % 2 == 0){
    if (str.substring(str.length()/2-2,str.length()/2+1).equals("xyz")) return true;
    if (str.substring(str.length()/2-1,str.length()/2+2).equals("xyz")) return true;
  }else{
      if (str.substring(str.length()/2-1,str.length()/2+2).equals("xyz")) return true;
  }
  return false;
}



//getSandwich 
//A sandwich is two pieces of bread with something in between. Return the string that is between the first and last 
//appearance of "bread" in the given string, or return the empty string "" if there are not two pieces of bread.

public String getSandwich(String str) {
  String meat = "";
  String bread = "bread";
  if (str.indexOf(bread) == -1 || 
    str.indexOf(bread) == str.lastIndexOf(bread)) return meat;
  meat = str.substring(str.indexOf(bread)+5, str.lastIndexOf(bread));
  return meat;
}



//sameStarChar 
//Returns true if for every '*' (star) in the string, if there are chars both immediately before and after 
//the star, they are the same.

public boolean sameStarChar(String str) {
  for (int i = 1; i < str.length() - 1; i++){
    if (str.charAt(i) == '*' && str.charAt(i-1) != str.charAt(i+1)) return false;
  }
  return true;
}



//oneTwo 
//Given a string, compute a new string by moving the first char to come after the next two chars, so "abc" 
//yields "bca". Repeat this process for each subsequent group of 3 chars, so "abcdef" yields "bcaefd". 
//Ignore any group of fewer than 3 chars at the end.

public String oneTwo(String str) {
  String sol = "";
  for (int i = 0; i <= str.length() - 3; i += 3){
    if (str.substring(i).length() >= 3){
      sol += str.substring(i+1, i+3) + str.substring(i, i +1);
    }
  }
  return sol;
}



//zipZap 
//Look for patterns like "zip" and "zap" in the string -- length-3, starting with 'z' and ending with 'p'. 
//Return a string where for all such words, the middle letter is gone, so "zipXzap" yields "zpXzp".

public String zipZap(String str) {
  String sol = "";
  for (int i = 0; i < str.length(); i++){
    if (str.substring(i).length() < 3){
      sol += str.substring(i);
      break;
    }
    if (str.charAt(i) == 'z' && str.charAt(i+2) == 'p'){
      sol += "zp";
      i += 2;
    }else{
      sol += str.substring(i, i+1);
    }
  }
  return sol;
}



//starOut 
//Return a version of the given string, where for every star (*) in the string the star and the chars 
//immediately to its left and right are gone. So "ab*cd" yields "ad" and "ab**cd" also yields "ad".

public String starOut(String str) {
  String sol = "";
  for (int i = 0; i < str.length(); i++){
    if (str.substring(i).length() > 1 && str.charAt(i+1) == '*'){
      continue;
    }
    if (str.charAt(i) == '*'){
      i++;
      continue;
    }
    sol += str.substring(i, i+1);
  }
  return sol;
}



//plusOut 
//Given a string and a non-empty word string, return a version of the original String where all chars have been 
//replaced by pluses ("+"), except for appearances of the word string which are preserved unchanged.

public String plusOut(String str, String word) {
  String sol = "";
  for (int i = 0; i < str.length(); i++){
    if (str.substring(i).length() >= word.length()){
      if (str.substring(i, i + word.length()).equals(word)){
        sol += word;
        i += word.length() - 1;
        continue;
      }
    }
    sol += "+";
  }
  return sol;
}



//wordEnds 
//Given a string and a non-empty word string, return a string made of each char just before and just after every 
//appearance of the word in the string. Ignore cases where there is no char before or after the word, and a char 
//may be included twice if it is between two words.

public String wordEnds(String str, String word) {
  String sol = "";
  if (str.length() <= word.length()) return sol;
  for (int i = 0; i <= str.length() - word.length(); i++){
    if (i == 0 && str.substring(i, i + word.length()).equals(word)){
      sol += str.substring(word.length(), word.length() + 1);
      continue;
    }
    if (i == str.length() - word.length()){
      if (str.substring(i).equals(word)){
        sol += str.substring(i-1, i);
      }
      break;
    }
    if (str.substring(i, i + word.length()).equals(word)){
      sol += str.substring(i-1, i);
      sol += str.substring(i + word.length(), i + word.length() + 1);
    }
  }
  return sol;
}
