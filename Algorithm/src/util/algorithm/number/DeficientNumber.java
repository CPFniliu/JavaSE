package util.algorithm.number;

import util.algorithm.api.IRegularNumber;

/**
 * DEFICIENT NUMBER
 * 
 * A perfect number is a number for which the sum of its proper divisors is
 * exactly equal to the number. For example, the sum of the proper divisors of
 * 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
 * 
 * A number n is called deficient if the sum of its proper divisors is great than n
 * 
 * @author CPF
 * 2018Äê7ÔÂ4ÈÕ
 */
public class DeficientNumber implements IRegularNumber {

	@Override
	public boolean isRegular(int number) {
		int sum = CpfUtilInt.getSumOfDivisors(number);
		return sum < number;
	}

}
