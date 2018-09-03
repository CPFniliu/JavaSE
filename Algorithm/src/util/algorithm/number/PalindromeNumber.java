package util.algorithm.number;

import util.algorithm.api.IRegularNumber;

public class PalindromeNumber implements IRegularNumber
{

    @Override
    public boolean isRegular(int number) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 判断输入的参数是否是回文数
     * 
     * @param number
     * @return
     */
    public static boolean isPalindrome(int number) {
        return isPalindrome(Integer.valueOf(number).toString());
    }

    /**
     * 判断输入的参数是否是回文数
     * 
     * @param number
     * @return
     */
    public static boolean isPalindrome(String number) {
        char[] intchar = number.toCharArray();
        int length = intchar.length;
        for (int i = length / 2 - 1; i >= 0; i-- ){
            if (intchar[i] != intchar[length - 1 - i]){
                return false;
            }
        }
        return true;
    }

}
