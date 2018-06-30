//word0 
//Given an array of strings, return a Map<String, Integer> containing a key for every different string in the array, always 
//with the value 0. For example the string "hello" makes the pair "hello":0. We'll do more complicated counting later, but 
//for this problem the value is simply 0.

public Map<String, Integer> word0(String[] strings) {
  Map<String, Integer> map = new HashMap<String, Integer>();
  for (String element : strings){
    map.put(element, 0);
  }
  return map;
}



//wordLen 
//Given an array of strings, return a Map<String, Integer> containing a key for every different string in the array, and 
//the value is that string's length.

public Map<String, Integer> wordLen(String[] strings) {
  Map<String, Integer> map = new HashMap<String, Integer>();
  for (String element : strings){
    map.put(element, element.length());
  }
  return map;
}



//pairs 
//Given an array of non-empty strings, create and return a Map<String, String> as follows: for each string add its first 
//character as a key with its last character as the value.

public Map<String, String> pairs(String[] strings) {
  Map<String, String> map = new HashMap<String, String>();
  for (String element : strings){
    map.put(element.substring(0, 1), element.substring(element.length() - 1));
  }
  return map;
}



//wordCount 
//The classic word-count algorithm: given an array of strings, return a Map<String, Integer> with a key for each different 
//string, with the value the number of times that string appears in the array.

public Map<String, Integer> wordCount(String[] strings) {
  Map<String, Integer> map = new HashMap<String, Integer>();
  for (String element : strings){
    if (!map.containsKey(element)){
      map.put(element, 1);
    }else{
      map.put(element, map.get(element) + 1);
    }
  }
  return map;
}



//firstChar 
//Given an array of non-empty strings, return a Map<String, String> with a key for every different first character seen, with 
//the value of all the strings starting with that character appended together in the order they appear in the array.

public Map<String, String> firstChar(String[] strings) {
  Map<String, String> map = new HashMap<String, String>();
  for (String element : strings){
    String firstChar = element.substring(0, 1);
    String allStrings = "";
    for (String element2 : strings){
      if (element2.substring(0, 1).equals(firstChar)){
        allStrings += element2;
      }
    }
    map.put(firstChar, allStrings);
  }
  return map;
}



//wordAppend 
//Loop over the given array of strings to build a result string like this: when a string appears the 2nd, 4th, 6th, etc. 
//time in the array, append the string to the result. Return the empty string if no string appears a 2nd time.

public String wordAppend(String[] strings) {
  String solution = "";
  Map<String, Integer> map = new HashMap<String, Integer>();
  for (String element : strings){
    if (!map.containsKey(element)){
      map.put(element, 1);
    }else{
      map.put(element, map.get(element) + 1);
    }
    if (map.get(element) > 1 && map.get(element) % 2 == 0){
      solution += element;
    }
  }
  return solution;
}



//wordMultiple 
//Given an array of strings, return a Map<String, Boolean> where each different string is a key and its value is true if 
//that string appears 2 or more times in the array.

public Map<String, Boolean> wordMultiple(String[] strings) {
  Map<String, Boolean> map = new HashMap<String, Boolean>();
  for (String element : strings){
    if (!map.containsKey(element)){
      map.put(element, false);
    }else{
      map.put(element, true);
    }
  }
  return map;
}



//allSwap 
//We'll say that 2 strings "match" if they are non-empty and their first chars are the same. Loop over and then return the 
//given array of non-empty strings as follows: if a string matches an earlier string in the array, swap the 2 strings in 
//the array. When a position in the array has been swapped, it no longer matches anything. Using a map, this can be solved 
//making just one pass over the array. More difficult than it looks.

public String[] allSwap(String[] strings) {
  Map<String, Integer> map = new HashMap();
  String[] solution = new String[strings.length];
  for (int i = 0; i < strings.length; i++){
    solution[i] = strings[i];
  }
  for (int i = 0; i < strings.length; i++){
    String firstChar = strings[i].substring(0, 1);
    int index1;
    if (map.containsKey(firstChar)){
      index1 = map.get(firstChar);
      solution[i] = strings[index1];
      solution[index1] = strings[i];
      map.remove(firstChar);
    }else{
      map.put(firstChar, i);
    }
  }
  return solution;
}



//firstSwap 
//We'll say that 2 strings "match" if they are non-empty and their first chars are the same. Loop over and then return the 
//given array of non-empty strings as follows: if a string matches an earlier string in the array, swap the 2 strings in 
//the array. A particular first char can only cause 1 swap, so once a char has caused a swap, its later swaps are disabled. 
//Using a map, this can be solved making just one pass over the array. More difficult than it looks.

public String[] firstSwap(String[] strings) {
  Map<String, Integer> map = new HashMap();
  for (int i = 0; i < strings.length; i++){
    String first = strings[i].substring(0, 1);
    if (map.containsKey(first) && !map.containsKey(first + "x")){
      String cache = strings[i];
      strings[i] = strings[map.get(first)];
      strings[map.get(first)] = cache;
      map.put(first + "x", 0);
    }else{
      map.put(first, i);
    }
  }
  return strings;
}
