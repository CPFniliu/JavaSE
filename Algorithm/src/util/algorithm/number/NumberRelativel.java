package util.algorithm.number;

import util.common.CpfUtilString;

public class NumberRelativel
{

    /**
     * 获取两个数的最小公倍数
     * 
     * @param x
     * @param y
     * @return x 和 y 的最小公倍数
     */
    public static int getLeastCommonMultiple(int x, int y){
    	return x / NumberRelativel.getGreatestCommonDivisor(x, y) * y;
    }

    /**
     * 获取两个数的最大公约数 ( 辗转相除法 )
     * 
     * @param x
     * @param y
     * @return x 和 y 的最大公约数
     */
    public static int getGreatestCommonDivisor(int x, int y){
    	int tmp = 1;
    	if (x < y){
    		tmp = x;
    		x = y;
    		y = tmp;
    	}
    	
    	while ((tmp = x % y) != 0) {
    		x = y;
    		y = tmp;
    	}
    	return y;
    }

    /**
     * 获取两个10进制大数的和
     * @param str1
     * @param str2
     * @return
     */
    public static String getSum(String str1, String str2){
    	if (str1.length() < str2.length()){
    		String tmp = str1;
    		str1 = str2;
    		str2 = tmp;
    	}
    	StringBuffer strbuf = new StringBuffer();
    	String[] arr1 = CpfUtilString.splitBylength(str1, 18);
    	String[] arr2 = CpfUtilString.splitBylength(str2, 18);
    	int len1 = arr1.length;
    	int len2 = arr2.length;
    	long yichu = 0;
    	int i = 0;
    	long sum;
    	for (; i < len1; i ++) {
    		if (i < len2){
    			sum = Long.parseLong(arr1[len1 - i - 1]) + Long.parseLong(arr2[len2 - i - 1]) + yichu;    			
    		} else {
    			sum = Long.parseLong(arr1[len1 - i - 1]) + yichu;    			
    		}
    		yichu = sum / 1_000_000_000_000_000_000L;
    		strbuf.insert(0, String.format("%018d", sum % 1_000_000_000_000_000_000L));
    	}
    	if (yichu > 0){
    		return yichu + strbuf.toString();
    	} else {
    		int idx = 0;
    		while (strbuf.charAt(idx) == '0') {
    			idx ++;
    		}
    		return strbuf.substring(idx);
    	}
    }

}
