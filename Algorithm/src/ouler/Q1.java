package ouler;

import util.algorithm.number.NumberRelativel;
import util.algorithm.number.PalindromeNumber;
import util.algorithm.number.PrimeNumber;

/**
 * @author CPF
 *
 *         Multiples of 3 and 5 If we list all the natural numbers below 10 that
 *         are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these
 *         multiples is 23. Find the sum of all the multiples of 3 or 5 below
 *         1000
 */
/**
 * @author CPF
 *
 */
public class Q1 {
	public static void main(String[] args) {
		question10();
	}

	
	
	
	private static void question10() {
		long sum = 0;
		for (int n = 0; n < 2000000; n ++){
			if (PrimeNumber.isprime(n)){
				sum += n;
			}
		}
		System.out.println(sum);
	}




	public static void question9() {
		int num = 1000;
		int a;
		int b;
		int c;
		for (a = 0; a < 333; a ++){
			for (b = a; b < (num - a) / 2; b++){
				c = (1000 - a - b);
				if (a * a + b * b == c * c){
					System.out.println(a + " " + b + " " + c);
					System.out.println( a* b*c);
				}
			}
		}
	}




	public static void question8() {
		String the1000digit = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843"
				+ "8586156078911294949545950173795833195285320880551112540698747158523863050715693290963295227443043557"
				+ "6689664895044524452316173185640309871112172238311362229893423380308135336276614282806444486645238749"
				+ "3035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776"
				+ "6572733300105336788122023542180975125454059475224352584907711670556013604839586446706324415722155397"
				+ "5369781797784617406495514929086256932197846862248283972241375657056057490261407972968652414535100474"
				+ "8216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586"
				+ "1786645835912456652947654568284891288314260769004224219022671055626321111109370544217506941658960408"
				+ "0719840385096245544436298123098787992724428490918884580156166097919133875499200524063689912560717606"
				+ "0588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450";
		int length = the1000digit.length();
		int n1 = Integer.parseInt(Character.toString(the1000digit.charAt(0)));
		int n2 = Integer.parseInt(Character.toString(the1000digit.charAt(1)));
		int n3 = Integer.parseInt(Character.toString(the1000digit.charAt(2)));
		int n4 = Integer.parseInt(Character.toString(the1000digit.charAt(3)));
		int n5 = Integer.parseInt(Character.toString(the1000digit.charAt(4)));
		int n6 = Integer.parseInt(Character.toString(the1000digit.charAt(5)));
		int n7 = Integer.parseInt(Character.toString(the1000digit.charAt(6)));
		int n8 = Integer.parseInt(Character.toString(the1000digit.charAt(7)));
		int n9 = Integer.parseInt(Character.toString(the1000digit.charAt(8)));
		int n10 = Integer.parseInt(Character.toString(the1000digit.charAt(9)));
		int n11 = Integer.parseInt(Character.toString(the1000digit.charAt(10)));
		int n12 = Integer.parseInt(Character.toString(the1000digit.charAt(11)));
		int n13 = Integer.parseInt(Character.toString(the1000digit.charAt(12)));
		long positive = 0;
		long temp = 0;
		for (int i = 13; i < length; i++){
			temp = (long)n1 * n2 * n3 * n4 * n5 * n6 * n7 * n8 * n9 * n10 * n11 * n12 * n13;
			if (temp > positive){
				System.out.println( temp + " = " + n1 + " = " + n2 + " = " + n3 + " = " + n4 + " = " + n5);
				positive = temp;
			}
			n1 = n2;
			n2 = n3;
			n3 = n4;
			n4 = n5;
			n5 = n6;
			n6 = n7;
			n7 = n8;
			n8 = n9;
			n9 = n10;
			n10 = n11;
			n11 = n12;
			n12 = n13;
			n13 = Integer.parseInt(Character.toString(the1000digit.charAt(i)));
		}
		System.out.println(positive);
		
	}




	/**
	 * Problem 7 : 10001st prime
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
	 * What is the 10 001st prime number?
	 */
	public static void question7() {
		int n = 2;
		for(int i = 0; i < 10001; n ++){
			if (PrimeNumber.isprime(n)){
				i ++;
			}
		}
		System.out.println(n - 1);
	}




	/**
	 * Problem 6 : Sum square difference
	 * 
	 * The sum of the squares of the first ten natural numbers is : 12 + 22 + ... + 102 = 385,
	 * The square of the sum of the first ten natural numbers is : (1 + 2 + ... + 10)2 = 552 = 3025,
	 * 
	 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
	 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
	 */
	public static void question6() {
		int squareSum = 0;
		int sum = 0;
		for (int n = 1; n <= 100; n ++){
			sum += n;
			squareSum += (int) Math.pow(n, 2);
		}
		System.out.println((int) Math.pow(sum, 2));
		System.out.println(squareSum);
		System.out.println((int) Math.pow(sum, 2) - squareSum);
	}


	
	
	/**
	 * Problem 5 : Smallest multiple
	 * 
	 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
	 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
	 */
	public static void question5() {
		int number = 1;
		for (int n = 2; n <= 20; n ++){
			number = NumberRelativel.getLeastCommonMultiple(number, n);
		}
		System.out.println(number);
	}

	public static void question4() {
		int number = 999_999;
		for (; number > 0 ; number --){
			if (PalindromeNumber.isPalindrome(number) && isMulprime(number)){
				System.out.println(number);
				break;
			}
		}
	}
	
	/**
	 * 判断整数n是否为两个三位数的乘积
	 * @param n
	 * @return 若为素数，return true，反之return false
	 */
	private static boolean isMulprime(int n){
		int sqrt = (int) Math.sqrt(n);
		for(int i=100;i<=sqrt;i++){
			if(n % i == 0){
				int dive = n / i;
				if (dive > 100 && dive < 1000){
					System.out.println(n + " = " + i + " * " + (n / i));
					return true;
				} else {
					continue;
				}
			}
		}
		return false;
	}	

	/**
	 * Largest prime factor
	 * The prime factors of 13195 are 5, 7, 13 and 29.
	 * 
	 * What is the largest prime factor of the number 600851475143 ?
	 */
	public static void question3() {
		long number = 600_851_475_143L;
		long result = number;
		long resultTmp = 0; 
		while (result != -1){
			resultTmp = result;
			result = getMaxPrimeFactor(result);
		}
		System.out.println(resultTmp);
	}
	
	private static long getMaxPrimeFactor(long number) {
		for (long i = 3; i < number; i += 2){
			if (number % i == 0){
				return number / i;
			}
		}
		return -1;
	}

	/**
	 * Even Fibonacci numbers Each new term in the Fibonacci sequence is
	 * generated by adding the previous two terms. By starting with 1 and 2, 
	 * the first 10 terms will be:
	 * 
	 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, … By considering the terms in the
	 * Fibonacci sequence whose values do not exceed four million, find the sum
	 * of the even-valued terms.
	 */
	public static void question2() {
		int i = 1;
		int j = 2;
		int evenValuedSum = 2;
		for (int n = i + j; n < 4_000_000; n = i + j){
			if (n % 2 == 0){
				evenValuedSum += n;
			}
			i = j;
			j = n;
		}
		System.out.println(evenValuedSum);
	}

	public static void question1() {
		int sum = 0;
		for (int i = 0; i < 1000; i++) {
			if (i % 3 == 0 || i % 5 == 0) {
				sum += i;
			}
		}
		System.out.println("Q1 : " + sum);
	}
}
