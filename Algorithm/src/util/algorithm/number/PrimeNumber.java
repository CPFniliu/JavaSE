package util.algorithm.number;

public class PrimeNumber
{

    /**
     * 判断整数n是否为素数
     * 
     * @param n
     * @return 若为素数，return true，反之return false
     */
    public static boolean isprime(int n) {
        if (n <= 1) { // 排除小于2的数
            return false;
        }
        if (n <= 3) { // 2, 3
            return true;
        }
        // 大于3的质数都是满足 (6n +- 1)
        int remainder = n % 6;
        if (remainder != 1 && remainder != 5) {
            return false;
        }
        if (n % 3 == 0) {
            return false;
        }
        // 从5, 7开始, 每次加6
        for (int i = 5, j = 7, sqrt = (int) Math.sqrt(n); i <= sqrt || j <= sqrt; i += 6, j += 6) {
            if (n % i == 0 || n % j == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 判断整数n是否为素数
     * 
     * @param n
     * @return 若为素数，return true，反之return false
     */
    public static boolean isprime2(int n) {
        if (n <= 1) { // 排除小于2的数
            return false;
        }
        if (n <= 3) { // 2, 3
            return true;
        }
        // 大于3的质数都是满足 (6n +- 1)
        int remainder = n % 6;
        if (remainder != 1 && remainder != 5) {
            return false;
        }
        if (n % 3 == 0) {
            return false;
        }
        // 从5, 7开始, 每次加6
        for (int i = 5, j = 7, sqrt = (int) Math.sqrt(n); j <= sqrt || i <= sqrt; i += 6, j += 6) {
            if (n % i == 0 || n % j == 0) {
                return false;
            }
        }
        return true;
    }
    
}
