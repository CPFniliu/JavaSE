package ouler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import util.algorithm.number.AbundantNumber;
import util.algorithm.number.PrimeNumber;
import util.common.CpfUtilArr;

public class Q3 {

	public static void main(String[] args) {
		question23();
		question24();
		question25();
		question26();
		question27();
		question28();
		question29();
		question30();
	}

	/**
	 * on-abundant sums Problem 23
	 * 
	 * A perfect number is a number for which the sum of its proper divisors is
	 * exactly equal to the number. For example, the sum of the proper divisors
	 * of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect
	 * number.
	 * 
	 * A number n is called deficient if the sum of its proper divisors is less
	 * than n and it is called abundant if this sum exceeds n.
	 * 
	 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the
	 * smallest number that can be written as the sum of two abundant numbers is
	 * 24. By mathematical analysis, it can be shown that all integers greater
	 * than 28123 can be written as the sum of two abundant numbers. However,
	 * this upper limit cannot be reduced any further by analysis even though it
	 * is known that the greatest number that cannot be expressed as the sum of
	 * two abundant numbers is less than this limit.
	 * 
	 * Find the sum of all the positive integers which cannot be written as the
	 * sum of two abundant numbers.
	 */
	private static void question23() {
		AbundantNumber abundantNumber = new AbundantNumber();
		int limit = 28123;
		// 获取所有的小于28123的 abundant number
		List<Integer> abundantNumbers = new ArrayList<>(1000);
		for (int i = 2; i <= limit; i++) {
			if (abundantNumber.isRegular(i)) {
				abundantNumbers.add(i);
			}
		}
		int abundantsize = abundantNumbers.size();
//		System.out.println("list :: ");
//		System.out.println(abundantNumbers);
//		System.out.println("abundantNumbers size : " + abundantsize);
		Integer[] abundantArr = abundantNumbers.toArray(new Integer[abundantsize]);

		boolean onabundant[] = new boolean[limit + 1];
		for (int i = 0; i < abundantsize; i++) {
			for (int j = i; j < abundantsize; j++) {
				int sum = abundantArr[i] + abundantArr[j];
				if (sum <= limit && !onabundant[sum]) {
					onabundant[sum] = true;
				}
			}
		}

		int sum = 0;
		for (int i = 0, len = onabundant.length; i < len; i++) {
			if (!onabundant[i]) {
				sum += i;
			}
		}
		System.out.println("question23 : " + sum);
	}

	/**
	 * 纸上搞定
	 */
	private static void question24() {
		System.out.println("question24 : " + 2_783_915_460L);
	}

	private static void question25() {
		int index = 3;
		BigInteger a = new BigInteger("1");
		BigInteger b = new BigInteger("1");
		BigInteger num = a.add(b);
		for (; num.toString().length() < 1000; index++) {
			a = b;
			b = num;
			num = a.add(b);
		}
		System.out.println("question25 : " + index);

	}

	private static void question26() {
		int max = 0;
		int result = 0;
		BigInteger b10 = new BigInteger("10");
		for (int i = 1; i < 1000; i++) {
			BigInteger bigInteger = new BigInteger("" + i);
			int reminderlen = computeNum(bigInteger, b10);
			// System.out.println( i + " : " + reminderlen);
			if (reminderlen > max) {
				max = computeNum(bigInteger, b10);
				result = i;
			}
		}
		System.out.println("question26 : " + result);
	}

	private static int computeNum(BigInteger number, BigInteger b10) {
		BigInteger divisor = new BigInteger("1");
		StringBuilder strbdr = new StringBuilder();
		String str = ";" + divisor + ";";
		strbdr.append(str);
		while (true) {
			divisor = divisor.multiply(b10);
			divisor = divisor.remainder(number);
			if ("0".equals(divisor.toString())) {
				return 0;
			}
			str = divisor + ";";
			if (strbdr.indexOf(";" + str) == -1) {
				strbdr.append(str);
			} else {
				break;
			}
		}
		str = strbdr.substring(strbdr.indexOf(str));
		int len = str.length();
		str = str.replaceAll(";", "");
		len = len - str.length();
		return len;
	}

	private static void question27() {
		int maxX = 0, maxY = 0, maxlen = 0;
		for (int x = -1000; x < 1000; x++) {
			for (int y = -1000; y < 1000; y++) {
				int prime = 2;
				int n = -1;
				while (PrimeNumber.isprime(prime)) {
					n++;
					prime = n * (n + x) + y;
				}
				if (n > maxlen) {
					maxlen = n;
					maxX = x;
					maxY = y;
				}
			}
		}
		// System.out.println("maxX : " + maxX);
		// System.out.println("maxY : " + maxY);
		// System.out.println("maxN : " + maxlen);
		System.out.println("question27 : " + maxX * maxY);
	}

	/**
	 * [一句话功能简述] [功能详细描述] 经过优化, 发现规律; a1 = 1; a3 = 25; a5 = 101 a(n) = a(n-2) +
	 * 2 * (n^2 + (n-1)(n-2) + 1) {n > 1; 且为奇数 }
	 */
	private static void question28() {
		int a1 = 1;
		int an_2;
		int an = a1;
		int n = 3;
		for (; n <= 1001; n += 2) {
			an_2 = an;
			an = an_2 + ((n * n + (n - 1) * (n - 2) + 1) << 1);
		}
//		System.out.println(n + " : " + an);
		System.out.println("question28 : " + an);
	}

	/**
	 * Problem 29: Distinct powers
	 * 
	 * Consider all integer combinations of ab for 2 ≤ a ≤ 5 and 2 ≤ b ≤ 5:
	 * 
	 * 22=4, 23=8, 24=16, 25=32 32=9, 33=27, 34=81, 35=243 42=16, 43=64, 44=256,
	 * 45=1024 52=25, 53=125, 54=625, 55=3125 If they are then placed in
	 * numerical order, with any repeats removed, we get the following sequence
	 * of 15 distinct terms:
	 * 
	 * 4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125
	 * 
	 * How many distinct terms are in the sequence generated by ab for 2 ≤ a ≤
	 * 100 and 2 ≤ b ≤ 100?
	 * 
	 * 考虑 : 当a 为 : 2,4,8,16,32, 64 , 利用降次法, 其中重复的有 266种 考虑 : 当a 为 : 3,9,27,82 ,
	 * 利用降次法, 其中重复的有156种 考虑 : 当a 为 : 5,6,7,10时, 分别有25,36,49,100, 和它们重复49个 一共有 99
	 * * 99 种情况, 因此 result : 99 * 99 - 266 - 156 - 49 * 4;
	 * 
	 */
	public static void question29() {
		int i = 99 * 99 - 266 - 156 - 49 * 4;
		System.out.println("question29 : " + i);
	}

	public static void question30() {
		int Tsum = 0;
		int[] arr = new int[7];
		for (arr[0] = 0; arr[0] <= 9; arr[0]++) {
			for (arr[1] = arr[0]; arr[1] <= 9; arr[1]++) {
				for (arr[2] = arr[1]; arr[2] <= 9; arr[2]++) {
					for (arr[3] = arr[2]; arr[3] <= 9; arr[3]++) {
						for (arr[4] = arr[3]; arr[4] <= 9; arr[4]++) {
							for (arr[5] = arr[4]; arr[5] <= 9; arr[5]++) {
								for (arr[6] = arr[5]; arr[6] <= 9; arr[6]++) {
									if (CpfUtilArr.getSumInArr(arr) <= 1) {
										break;
									}
									double sum = 0;
									for (int a = 0, len = arr.length; a < len; a++) {
										sum += Math.pow(arr[a], 5);
									}
									Integer in = Double.valueOf(sum).intValue();
									char[] chararr = in.toString().toCharArray();
									Arrays.sort(chararr);
									String sortstr = new String(chararr);
									if (Integer.parseInt(sortstr) == Integer.parseInt(
											StringUtils.strip(Arrays.toString(arr), "[]").replaceAll(", ", ""))) {
//										System.out.println(
//												StringUtils.strip(Arrays.toString(arr), "[]").replaceAll(", ", "") + " "
//														+ in);
										Tsum += in;
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println("question30 : " + Tsum);

	}

}
