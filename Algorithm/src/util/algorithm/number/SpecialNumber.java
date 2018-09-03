package util.algorithm.number;

import java.util.Arrays;

public class SpecialNumber
{

    /**
     *  an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once.
     *  For example, 2143 is a 4-digit pandigital 
     */
    public static boolean isPandigital(int n){
        char[] arr = Integer.toString(n).toCharArray();
        Arrays.sort(arr);
        return "123456789".startsWith(String.valueOf(arr));
    }
    
}
