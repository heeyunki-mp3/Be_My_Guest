package com.BeMyGuest.mini_game.are_you_ok;

/** This class contains array utilities that could be used for modifying arrays
 *  @author Heeyun Kim
 *  */
public class Array
{
    /** These are the dummy variable to satisfy the requirements for having a 
     * class without constructor, but to have instance variable.
     */
    private int dummy;
    private String hi;
    /**
     * Checks if arr contains a certain item.
     * @param arr is the String array to check if it contains a certain String
     * @param target is the String element to check if it is inside arr.
     * @return boolean if arr contains target
     */
    public static boolean contains(String[] arr, String target){
        for (String element : arr){
            if (element.equals(target))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Add a String to String array
     * @param arr is the String array to add an element to
     * @param target is the String item to add to arr
     * @return String array produced after adding target to arr
     */
    public static String[] add(String[] arr, String target)
    {
       String[] newArr = new String[arr.length+1];
       for (int i=0; i<arr.length; i++)
       {
          newArr[i] = arr[i];
       }
       newArr[arr.length] = target;
      
       return newArr;
    }
    /**
     * Add an int to int array
     * @param arr is the int array to add an element to
     * @param target is the int item to add to arr
     * @return int array produced after adding target to arr
     */
    public static int[] add(int[] arr, int target)
    {
       int[] newArr = new int[arr.length+1];
       for (int i=0; i<arr.length; i++)
       {
          newArr[i] = arr[i];
       }
       newArr[arr.length] = target;
      
       return newArr;
    }
    /**
     * Finds the index of the max value of the rate of Symptom items in the array
     * @param arr the array to find the max value of the rate of Symptom
     * @return index of the max rate of item in the array, arr
     */
    public static int maxIndex(Symptom[] arr)
    {
        int resultIndex = 0;
        for (int i=1; i<arr.length; i++)
        {
            if (arr[resultIndex].getRate()<arr[i].getRate())
            {
                resultIndex = i;
            }
        }
        return resultIndex;
    }
    /**
     * Finds the index of the min value of the rate of Symptom items in the array
     * @param arr the array to find the min value of the rate of Symptom
     * @return index of the min rate of item in the array, arr
     */
    public static int minIndex(Symptom[] arr)
    {
        int resultIndex = 0;
        for (int i=1; i<arr.length; i++)
        {
            if (arr[resultIndex].getRate()>arr[i].getRate())
            {
                resultIndex = i;
            }
        }
        return resultIndex;
    }
    /**
     * Finds the index of the max value of items in the array
     * @param arr the array to find the max value
     * @return index of the max item in the array, arr
     */
    public static int maxIndex(int[] arr)
    {
        int resultIndex = 0;
        for (int i=1; i<arr.length; i++)
        {
            if (arr[resultIndex]<arr[i])
            {
                resultIndex = i;
            }
        }
        return resultIndex;
    }
    /**
     * Finds the index of the min value of items in the array
     * @param arr the array to find the min value
     * @return index of the min item in the array, arr
     */
    public static int minIndex(int[] arr)
    {
        int resultIndex = 0;
        for (int i=1; i<arr.length; i++)
        {
            if (arr[resultIndex]>arr[i])
            {
                resultIndex = i;
            }
        }
        return resultIndex;
    }
    /**
     * Add an Symptom to Symptom array
     * @param arr is the Symptom array to add an element to
     * @param target is the Symptom item to add to arr
     * @return Symptom array produced after adding target to arr
     */
    public static Symptom[] add(Symptom[] arr, Symptom target)
    {
        Symptom[] newArr = new Symptom[arr.length+1];
        for (int i=0; i<arr.length; i++)
        {
            newArr[i] = arr[i];
        }
        newArr[arr.length] = target;
        return newArr;
    } 
    /**
     * Add an Patient to Patient array
     * @param arr is the Patient array to add an element to
     * @param target is the Patient item to add to arr
     * @return Patient array produced after adding target to arr
     */ 
    public static Patient[] add(Patient[] arr, Patient target)
    {
        Patient[] newArr = new Patient[arr.length+1];
        for (int i=0; i<arr.length; i++)
        {
            newArr[i] = arr[i];
        }
        newArr[arr.length] = target;
        return newArr;
    } 
    /**
     * Checks if any item in the int array is equal or greater than 8
     * @param arr the int array to check if the it contains an item equal or greater than 8.
     * @return boolean if any item in the int array is equal or greater than 8
     */ 
    public static boolean anyOverEight(int[] arr)
    {
        for (int i=0; i<arr.length; i++)
        {
            if (arr[i]>=8)
                return true;
        }
        return false;
    }
     /**
     * Checks if any item in the int array is less than 8
     * @param arr the int array to check if the it contains an item less than 8.
     * @return boolean if any item in the int array is less than 8
     */ 
    public static boolean allOverEight(int[] arr)
    {
        for (int i=0; i<arr.length; i++)
        {
            if (arr[i]<8)
                return false;;
        }
        return true;
    }
    /**
     * Checks if there is a duplicated Symptom inside the Symptom array arr
     * @param arr the Symptom array to check
     * @return boolean if there is any duplicated item in the array
     */
    public static boolean duplicatedItem(Symptom[] arr)
    {
        for (int i=0; i<arr.length; i++)
        {
            for (int j=i+1; j<arr.length;j++)
            {
                if (arr[i].equals(arr[j]))
                {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Gets the mean value of the int items inside of an int array arr
     * @param arr the int array that you want to get the mean of 
     * @return mean
     */
    public static int mean(int[] arr)
    {
        int mean = 0;
        for (int i=0; i<arr.length-1; i++)
        {
            mean += arr[i] + arr[i+1];
        }
        mean += arr[0] + arr[arr.length-1];
        mean = mean/2;
        mean = mean / arr.length;
        return mean;
    }
    /**
     * Gets the frequency of a number inside an int array num
     * @param num the int array to get the frequency of
     * @param target the value to get the frequency of 
     * @return the number of target showed in num
     */
    public static int frequency(int[] num, int target)
    {
      int returnVal = 0;
      for (int i=0; i<num.length; i++)
      {
         int lastDigit = num[i];
         if (lastDigit == target)
            returnVal++;
      }
      return returnVal;
    }
    /**
     * Get the mode of int array num
     * @param num the int array to get the mode of
     * @return the mode of int array num
     */
    public static int mode(int[] num)
    {
        int holdDigit = 0;
        int holdFreq = 0;
        for (int i=0; i<num.length; i++)
        {
            int lastDigit = num[i];
            if (holdFreq < frequency(num, lastDigit))
            {
                holdFreq = frequency(num, lastDigit);
                holdDigit = lastDigit;
            }
        }
        return holdDigit;
    }
    /**
     * Gets the sum of the int inside of the int array arr
     * @param arr the int array to get the sum of
     * @return the sum of the each items inside the array
     */
    public static int sum(int[] arr)
    {
        int sum = 0;
        for (int i=0; i<arr.length; i++)
        {
            sum += arr[i];
        }
        return sum;
    }
    /**
     * Reverses the order of the array
     * @param arr the array that you want to reverse the order of
     * @return the new array that the order is reversed
     */
    public static int[] reverse(int[] arr)
    {
        int[] result = {};
        for (int i = arr.length-1; i>-1; i--)
        {
            result = add(result, arr[i]);
        }
        return result;
    }
    /**
     * Shifts all the items inside the array right (one index up) and have the first item to be 0
     * @param arr the array that you want to shift the items right
     * @return new array where the items are shifted
     */
    public static int[] shiftRight(int[] arr)
    {
        int[] result = new int[arr.length+1];
        for (int i=0; i<arr.length; i++)
        {
            result[i+1] = arr[i];
        }
        return result;
    }
    /**
     * Shifts all items left (decrease the index by 1) and erases the first item (index 0)
     * @param arr the array that you want to shift the item left
     * @return new array where the items are shifted accordingly
     */
    public static int[] shiftLeft(int[] arr)
    {
        int[] result = new int[arr.length-1];
        for (int i=0; i<arr.length; i++)
        {
            result[i] = arr[i+1];
        }
        return result;
    }
    /**
     * Gets the index of the digit inside int num
     * @param num the integer that contains the digit that you want to get the index of
     * @param index the digit that you want to get the index of
     * @return the index of the digit, gets the index that is first found if there are multiple places that the digit comes up
     */
    public static int intAt(int num, int index)
    {
      num = (int) num / (int)(Math.pow(10,(int)Math.log10(num)-index));
      return num%10;
    }
}
