package util.common;

import java.math.BigInteger;

public class CpfUtilMath {

    /**
     * 求从 from 到 to 的数列的乘积
     * 
     * @param from
     * @param to
     * @return
     */
    public static BigInteger getProductFromTo(int from, int to) {
		if (from > to){
			int tmp = from;
			from = to;
			to = tmp;
		}
		BigInteger product = new BigInteger(Integer.valueOf(from).toString());
		for (int i = from + 1; i <= to; i ++) {
			product = product.multiply(new BigInteger(Integer.valueOf(i).toString()));
		}
		return product;
	}
    
    public static BigInteger factorial(int n){
        if (n==0){
            return new BigInteger("1");
        }
    	return getProductFromTo(1, n);
    }
    
    
    /**
     * 概率计算 : 将 up 个不同事件放在 down 个事件箱中,共有多少种情况 A(up down)
     * 
     * @param up
     * @param down
     * @return
     */
    public static BigInteger probability_A(int up, int down){
    	if (up >= down) {
    		throw new RuntimeException("C开头的上面的数需要小于下面的数");
    	}
    	BigInteger result = getProductFromTo(down - up + 1 , down);
    	return result;
    }
    
    /**
     * 概率计算 : 将 up 个相同事件放在 down 个事件箱中,共有多少种情况 C(up down)
     * 
     * @param up
     * @param down
     * @return
     */
    public static BigInteger probability_C(int up, int down){
    	BigInteger upp = probability_A(up, down);
    	BigInteger dop = factorial(up);
    	BigInteger[] result = upp.divideAndRemainder(dop);
    	if (result[1].intValue() != 0){
    		throw new RuntimeException("this situation can never happen!");
    	}
    	return result[0];
    }
    
}
