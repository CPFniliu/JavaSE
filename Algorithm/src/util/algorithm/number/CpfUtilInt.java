package util.algorithm.number;

import com.sun.org.apache.regexp.internal.REUtil;

public class CpfUtilInt
{

    /**
     * 获取一个数的除数的数量
     * eg : 28的除数  1,2,4,7,14,28 共有6个
     * @param number (> 1)
     * @return
     */
    public static int getDivisorsCount(int number) {
        if (number <= 1) {
            throw new RuntimeException("err");
        }
        // 首先有2个( 1 & 其自身)
        int count = 2;
        Double sqrt = Math.sqrt(number);
        int sqrtInt = sqrt.intValue();
        if (sqrtInt == sqrt) {
            count --;
        }
        for (int i = 2; i <= sqrtInt; i++) {
            if (number % i == 0) {
            	// 能整除 则 必有 i & number/i
                count += 2;
            }
        }
        return count;
    }
    
    /**
     * 获取一个数的除数的和(不包含其自身)
     * eg : 28的除数  {1,2,4,7,14} 其和为 28
     * @param number
     * @return
     */
    public static int getSumOfDivisors(int number) {
        if (number <= 1) {
            throw new RuntimeException("err");
        }
        // 首先其除数必有 1
        int sum = 1;
        Double sqrt = Math.sqrt(number);
        int sqrtInt = sqrt.intValue();
        if (sqrtInt == sqrt) {
        	sum = sum - sqrtInt;
        }
        for (int i = 2; i <= sqrtInt; i++) {
            if (number % i == 0) {
            	sum = sum + i + number / i; 
            }
        }
        return sum;
    }

    
//    /**
//     * 获取一个数的所有为质数的除数的数量(不包含其自身)
//     * eg : 28的质除数  {7} 就 1 个
//     * @param number
//     * @return
//     */
//    public static int getPrimeDivisorCount(int number) {
//        if (number <= 1) {
//            throw new RuntimeException("err");
//        }
//        // 首先其除数必有 1
//        int count = 0;
//        Double sqrt = Math.sqrt(number);
//        int sqrtInt = sqrt.intValue();
//        for (int i = 2,j; i <= sqrtInt; i++) {
//            if (number % i == 0) {
//            	if (PrimeNumber.isprime(i)) {
//            		count ++;
//            	}
//            	j = number / i; 
//            	if (i != j && PrimeNumber.isprime(j)) {
//            		count ++;
//            	}
//            }
//        }
//        return count;
//    }

    
    /**
     * 获取一个数的所有为质数的除数的数量(不包含其自身)
     * eg : 28的质除数  {7} 就 1 个
     * @param number
     * @return
     */
    public static int getPrimeDivisorCount(int number) {
        if (number <= 1) {
            throw new RuntimeException("err");
        }
        // 首先其除数必有 1
        Double sqrt = Math.sqrt(number);
        int sqrtInt = sqrt.intValue();
        int cnt = 0;
        int n = number;
        boolean isprime = true;
        for (int i = 2; i <= sqrtInt; i++) {
            if (n % i == 0) {
            	if (PrimeNumber.isprime(i)) {
            		cnt ++;
            		while (n % i == 0) {
						n = n / i;
//						System.out.print(i + "\t");
					}
                    sqrt = Math.sqrt(n);
                    sqrtInt = sqrt.intValue();
                    continue;
            	} else {
            		return -1;
            	}
            }
        }
        if (n == 1){
//            System.out.println();
            return cnt;
        }
//        System.out.println(n);
        return cnt + 1;
    }

}
