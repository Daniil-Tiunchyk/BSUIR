package com.example.labwork_7_v13;

public class SortUtils {

  public static int[] sortArray(String numbers) {
    String[] numbersArray = numbers.split(",");
    int[] intArray = new int[numbersArray.length];
    for (int i = 0; i < numbersArray.length; i++) {
      intArray[i] = Integer.parseInt(numbersArray[i].trim());
    }
    java.util.Arrays.sort(intArray);
    return intArray;
  }
}
