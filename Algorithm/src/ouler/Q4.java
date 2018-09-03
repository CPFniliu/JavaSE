package ouler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import util.algorithm.number.NumberRelativel;
import util.algorithm.number.PalindromeNumber;
import util.algorithm.number.PrimeNumber;
import util.common.CpfUtilMath;

public class Q4
{

    public static void main(String[] args) {
        question40();
    }

    public Q4() {
        Q4.question31();
        Q4.question32();
        Q4.question33();
        Q4.question34();
        Q4.question35();
        Q4.question36();
        Q4.question37();
        Q4.question38();
        Q4.question39();
        Q4.question40();
    }

    /**
     * 动态规划方程 FIXME : 需要好好理解下 t : total f(t) = sum{f(t-i) + 1};
     */
    private static void question31() {
        final int total = 200;
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200 };

        int[] matrix = new int[total + 1];
        matrix[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < matrix.length; j++) {
                matrix[j] += matrix[j - coins[i]];
            }
        }
        System.out.println(matrix[200]);
    }

    /**
     * We shall say that an n-digit number is pandigital if it makes use of all
     * the digits 1 to n exactly once; for example, the 5-digit number, 15234,
     * is 1 through 5 pandigital.
     * 
     * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing
     * multiplicand, multiplier, and product is 1 through 9 pandigital.
     * 
     * Find the sum of all products whose multiplicand/multiplier/product
     * identity can be written as a 1 through 9 pandigital.
     * 
     * HINT: Some products can be obtained in more than one way so be sure to
     * only include it once in your sum.
     * 
     * 假如积为5位数,那么两个乘数至少5位数 > 9不合题意 假如积为4位数,那么两个乘数至多5位数,最少4位数 所以product必然为4位数
     * 
     */
    private static void question32() {
        int m2, sum = 0;
        for (int product = 1000; product < 10000; product++) {
            // 首先其除数必有 1
            Double sqrt = Math.sqrt(product);
            int sqrtInt = sqrt.intValue();
            for (int m1 = 2; m1 <= sqrtInt; m1++) {
                if (product % m1 == 0) {
                    m2 = product / m1;
                    String str = "" + m1 + m2 + product;
                    char[] chars = str.toCharArray();
                    Arrays.sort(chars);
                    str = new String(chars);
                    if ("123456789".equals(str)){
                        System.out.println(m1 + " * " + m2 + " = " + product);
                        sum += product;
                        break;
                    }
                }
            }
        }
        System.out.println(sum);
    }
    
    
    /**
     * 分数49/98是一个奇怪的部分，因为一个没有经验的数学家试图简化它可能错误地认为49/98 = 4/8，这是正确的，是通过取消9s获得的。
     * 我们将考虑像30/50 = 3/5这样的分数是微不足道的例子。 这种类型的分数恰好有四个非平凡的例子，小于一个值，并且在分子和分母中包含两个数字。
     * 如果这四个分数的乘积以其最低公共项给出，请找到分母的值。
     */
    private static void question33() {
        int x[] = new int[2];
        int y[] = new int[2];
        int molecularProduct = 1; // 分子乘积
        int denominatorProduct = 1; // 分子乘积
        for (int molecular = 10; molecular < 98; molecular ++ ){
            x[0] = molecular / 10;
            x[1] = molecular % 10;
            if (x[0] == x[1] || x[1] == 0){
                continue;
            }
            for (int denominator = molecular + 1; denominator < 99; denominator++){
                y[0] = denominator / 10;
                y[1] = denominator % 10;
                if (y[0] == y[1] || y[1] == 0){
                    continue;
                }
                if ( (x[0] == y[1] && x[1] * denominator == molecular * y[0]) 
                        || (x[1] == y[0] && x[0] * denominator == molecular * y[1]) ){
//                    System.out.println(molecular + " / " + denominator );
                    molecularProduct *= molecular;
                    denominatorProduct *= denominator;
                }
            }
        }
//        System.out.println(molecularProduct + " / " + denominatorProduct );
        int greatestCommonDivisor = NumberRelativel.getGreatestCommonDivisor(molecularProduct, denominatorProduct);
//        System.out.println("greatestCommonDivisor : " + greatestCommonDivisor);
        int result = denominatorProduct / greatestCommonDivisor;
        System.out.println(result);
    }
    
    /**
     * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
     * 
     * Find the sum of all numbers which are equal to the sum of the factorial
     * of their digits.
     * 
     * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
     * 
     * 假如这个数是n位数,一个数最大是9的阶乘,n个数最大为 n * 9!, 当n为7, 则7 * 9 ! = 2540160,为7位数 
     * 若n为8 则 8 * 9!明显达不到一个8位数, 所以,这个数最大不超过 2540160, 
     * 再进一步, 如果是7位数, 前两个数字阶乘最大为 2 + 5 ! ,后面5个达不到200000, 因此,满足条件的数字< 200 000
     * 结论 : 满足条件的数不操过200万, 每个数字最多存在7次
     */
    private static void question34() {
        int[] factorial = new int[10];
        for (int i = 0, len = factorial.length; i < len; i ++) {
            factorial[i] = CpfUtilMath.factorial(i).intValue();
        }
        long result = 0;
        for (int n = 3; n < 200_000; n ++) {
            int sum = 0;
            for (char ch : ("" + n).toCharArray()){
                sum += factorial[ch - 48];
            }
            if (sum == n){
                System.out.println(sum);
                result += sum;
            }
        }
        System.out.println("question34 : " +result);
    }
    

    /**
     *  满足条件的数除了2以外, 其他的均不含偶数数字和5,(只有1,3,7,9), 可转换为4进制
     */
    private static void question35() {
        int jinzhi = 5;
        int[] by = {0, 1,3,7,9}; 
        int limit = Integer.parseInt("1000000", jinzhi);
        Set<Integer> primes = new HashSet<>();
        primes.add(2);
        primes.add(5);
        for (int n = 0; n < limit; n++) {
            String s = Integer.toString(n, jinzhi);
            int num = 0;
            for (char ch : s.toCharArray()) {
                num = num * 10 + by[ch - 48];
            }
            if (PrimeNumber.isprime(num)){
                primes.add(num);
            }
        }
        
        Set<Integer> results = new TreeSet<>();
        
        for (Integer inte : primes) {
            if (inte < 10){
                results.add(inte);
                continue;
            }
            int in = inte;
            do {
                in = Integer.parseInt("" + in%10 + in/10);
                if (in == inte.intValue()){
                    results.add(inte);
                    break;
                }
            } while(PrimeNumber.isprime(in));
        }
        
        System.out.println(results.size());
    }

    
    /**
     * The decimal number, 585 = 10010010012 (binary), is palindromic in both
     * bases.
     * 
     * Find the sum of all numbers, less than one million, which are palindromic
     * in base 10 and base 2.
     * 
     * (Please note that the palindromic number, in either base, may not include
     * leading zeros.)
     */
    public static void question36() {
        int sum = 0;
        for (int number = 1; number < 1000_000; number++) {
            if (PalindromeNumber.isPalindrome(number) &&
                    PalindromeNumber.isPalindrome(Integer.toBinaryString(number))){
                sum += number;
                System.out.println(number);
            }
        }
        System.out.println(sum);
    }
    

    public static void question37() {
        int sum = 0;
        int count = 11;
        for (int number = 22; count > 0; number++) {
            if (PrimeNumber.isprime(number)){
                int len = Integer.toString(number).length();
                int l10 = (int) Math.pow(10, len - 1);
                int pre = number;
                int bef = number;
                boolean flag = true;
                while (l10 > 1) {
                    pre = pre/10;
                    bef = bef % l10;
                    l10 = l10/10;
                    if (!PrimeNumber.isprime(pre) || !PrimeNumber.isprime(bef)){
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    System.out.println(number);
                    count --;
                    sum += number;
                }
            }
        }
        System.out.println(sum);
    }
    
    public static void question38() {
        int maxnum = 0;
        for (int i = 0; i < 10000; i ++) {
            int r = check38(i);
            if (r > maxnum){
                maxnum = r;
            }
        }
        System.out.println(maxnum);
    }
    
    private static int check38(int num){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; sb.length() < 9; i ++){
            sb.append("" + (num * i));
        }
        if (sb.length() == 9){
            char[] chs = sb.toString().toCharArray();
            Arrays.sort(chs);
            if ("123456789".equals(new String(chs))){
                return Integer.parseInt(sb.toString());
            }
        }
        return 0;
    }
    
    /**
     * If p is the perimeter of a right angle triangle with integral length
     * sides, {a,b,c}, there are exactly three solutions for p = 120.
     * 
     * {20,48,52}, {24,45,51}, {30,40,50}
     * 
     * For which value of p ≤ 1000, is the number of solutions maximised?
     */
    public static void question39(){
        int ps[] = new int[1001];
        int a, b, c, p;
        int maxA = (int) (1000/ (1 + Math.sqrt(2)));
        for (int s = 1; s < maxA; s++){
            for (int t = 1; t < s && (p = 2 * s * (s + t)) < 1000; t ++){
                if (NumberRelativel.getGreatestCommonDivisor(s, t) == 1){
                    a = s * s - t * t;
                    b = s * t * 2;
                    c = s * s + t * t;
                    System.out.println(s + "\t" + t + "\t | " + a + "\t" + b + "\t" + c + " : \t" + p);
                    ps[p] ++;
                }
            }
        }
        
        int max = 0,index = 0;
        for (int i =0; i <= 1000; i ++){
            if (ps[i] > max){
                max = ps[i];
                index = i;
            }
        }
        System.out.println(max);
        System.out.println("question39 : " + index);
    }
    
    
    public static void question40() {
        int le = 1000_000;
        int r[] = new int[7];
        for (int i = 1,len = r.length; i < len; i ++){
            r[i] = 9 * i * (int)Math.pow(10, i - 1);
        }
        
        Map<Integer, Integer> d = new HashMap<>();
        d.put(1, 1);
        d.put(10, 1);
        for (int i = 100; i <= le; i *= 10) {
            int sum = i;
            int idx = 0;
            for (; idx < 7; idx ++){
                if (r[idx] > sum){
                    break;
                }
                sum -= r[idx];
            }
            int remainder = sum % idx;
            int quotient = sum / idx + i / 10;
            char ch;
            if (remainder > 0){
                quotient ++;
                ch = Integer.toString(quotient).charAt(remainder - 1);
            } else {
                ch = Integer.toString(quotient).charAt(idx - 1);
            }
            d.put(i, ch - 48);
        }
        System.out.println(Arrays.toString(r));
        System.out.println(d);
        int product = 1;
        for (int p : d.values()){
            product *= p;
        }
        System.out.println(product);
    }
    
    
}
