//countYZ 
//Given a string, count the number of words ending in 'y' or 'z' -- so the 'y' in "heavy" and the 'z' in "fez" count, 
//but not the 'y' in "yellow" (not case sensitive). We'll say that a y or z is at the end of a word if there is not 
//an alphabetic letter immediately following it. (Note: Character.isLetter(char) tests if a char is an alphabetic letter.)

public int countYZ(String str) {
  int counter = 0;
  str = str.toUpperCase();
  for (int i = 1; i < str.length(); i++){
    if (!Character.isLetter(str.charAt(i))){
      if (str.charAt(i-1) == 'Y' || str.charAt(i-1) == 'Z') counter++;
    }
    if (i == str.length() - 1 && str.charAt(i) == 'Y') counter++;
    if (i == str.length() - 1 && str.charAt(i) == 'Z') counter++;
  }
  return counter;
}



//withoutString 
//Given two strings, base and remove, return a version of the base string where all instances of the remove string have 
//been removed (not case sensitive). You may assume that the remove string is length 1 or more. Remove only non-overlapping 
//instances, so with "xxx" removing "xx" leaves "x".

public String withoutString(String base, String remove) {
  int i = 0;
  while (i <= base.length() - remove.length()){
    if (base.toLowerCase().substring(i, i + remove.length()).equals(remove.toLowerCase())){
      base = base.substring(0, i) + base.substring(i + remove.length());
      i = 0;
    }else{
      i++;
    }
  }
  return base;
}



//equalIsNot 
//Given a string, return true if the number of appearances of "is" anywhere in the string is equal to the number of 
//appearances of "not" anywhere in the string (case sensitive).

public boolean equalIsNot(String str) {
  int isCounter = 0;
  int IsCounter = 0;
  int notCounter = 0;
  int NotCounter = 0;
  for (int i = 0; i <= str.length() - 2; i++){
    if (i <= str.length() - 3){
      if (str.substring(i, i + 3).equals("not")) notCounter++;
      if (str.substring(i, i + 3).equals("Not")) NotCounter++;
    }
    if (i <= str.length() - 2){
      if (str.substring(i, i + 2).equals("is")) isCounter++;
      if (str.substring(i, i + 2).equals("Is")) IsCounter++;
    }
  }
  return (isCounter == notCounter) && (IsCounter == NotCounter);
}



//gHappy 
//We'll say that a lowercase 'g' in a string is "happy" if there is another 'g' immediately to its left or right. 
//Return true if all the g's in the given string are happy.

public boolean gHappy(String str) {
  str = " " + str + " ";
  for (int i = 1; i < str.length() - 1; i++){
    if (str.charAt(i) == 'g'){
      if (str.charAt(i - 1) == 'g') continue; 
      if (str.charAt(i + 1) == 'g') continue;
      if (str.charAt(i - 1) != 'g' && str.charAt(i + 1) != 'g') return false;
    }
  }
  return true;
}



//countTriple 
//We'll say that a "triple" in a string is a char appearing three times in a row. Return the number of triples in 
//the given string. The triples may overlap.

public int countTriple(String str) {
  str = str + "  ";
  int counter = 0;
  for (int i = 0; i < str.length() - 4; i++){
    if (str.charAt(i) == str.charAt(i+1) && str.charAt(i) == str.charAt(i+2)) counter++;
  }
  return counter;
}



//sumDigits 
//Given a string, return the sum of the digits 0-9 that appear in the string, ignoring all other characters. 
//Return 0 if there are no digits in the string. (Note: Character.isDigit(char) tests if a char is one of the 
//chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.)

public int sumDigits(String str) {
  int counter = 0;
  for (int i = 0; i < str.length(); i++){
    if (Character.isDigit(str.charAt(i))) counter += Integer.parseInt(str.substring(i, i+1));
  }
  return counter;
}



//sameEnds 
//Given a string, return the longest substring that appears at both the beginning and end of the string without 
//overlapping. For example, sameEnds("abXab") is "ab".

public String sameEnds(String string) {
  int half = string.length() / 2;
  String solution = "";
  if (string.length() < 2) return solution;
  for (int i = 0; i <= half; i++){
    if (string.substring(0, half - i).equals(string.substring(string.length() - half + i))){
         solution = string.substring(0, half - i);
         break;
       }
  }
  return solution;
}



//mirrorEnds 
//Given a string, look for a mirror image (backwards) string at both the beginning and end of the given string. 
//In other words, zero or more characters at the very begining of the given string, and at the very end of the 
//string in reverse order (possibly overlapping). For example, the string "abXYZba" has the mirror end "ab".

public String mirrorEnds(String string) {
  String solution = "";
  for (int i = 0; i < string.length(); i++){
    if (string.charAt(i) == string.charAt(string.length() - 1 - i)){
      solution += string.substring(i, i + 1);
    }else break;
  }
  return solution;
}



//maxBlock 
//Given a string, return the length of the largest "block" in the string. A block is a run of adjacent chars that 
//are the same.

public int maxBlock(String str) {
  int solution = 1;
  int buffer = 1;
  if (str.length() == 0) return 0;
  if (str.length() == 1) return 1;
  for (int i = 0; i < str.length() -1; i++){
    if (str.charAt(i) == str.charAt(i+1)){
      buffer++;
      if (buffer > solution) solution = buffer;
    }else buffer = 1;
  }
  return solution;
}



//sumNumbers 
//Given a string, return the sum of the numbers appearing in the string, ignoring all other characters. A number is 
//a series of 1 or more digit chars in a row. (Note: Character.isDigit(char) tests if a char is one of the 
//chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.)

public int sumNumbers(String str) {
  str += " ";
  int sum = 0;
  String numBuilder = "";
  for (int i = 0; i < str.length(); i++){
    if (Character.isDigit(str.charAt(i))){
      numBuilder += str.substring(i, i+1);
      if (Character.isDigit(str.charAt(i+1))) continue;
    }else{
      if (numBuilder.length() > 0){
      sum += Integer.parseInt(numBuilder);
      numBuilder = "";
      }
    }
  }
  return sum;
}



//notReplace 
//Given a string, return a string where every appearance of the lowercase word "is" has been replaced with "is not". 
//The word "is" should not be immediately preceeded or followed by a letter -- so for example the "is" in "this" 
//does not count. (Note: Character.isLetter(char) tests if a char is a letter.)

public String notReplace(String str) {
  if (str.length() < 2) return str;
  str = str + " ";
  for (int i = 0; i <= str.length() - 2; i++){
    if (str.substring(i, i+2).equals("is")){
      if (i == 0 || !Character.isLetter(str.charAt(i-1))){
        if (Character.isLetter(str.charAt(i+2))){
          break;
        }else{
          str = str.substring(0, i) + "is not" + str.substring(i+2);
        }
      }
    }
  }
  return str.trim();
}
