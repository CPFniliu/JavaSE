package util.common;

public class CpfUtilDate {

	/**
	 * 判断一年是否为闰年 : 能被400整除 或者是不能被100整除但能被4整除的年份是闰年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
	}

}
