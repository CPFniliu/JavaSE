package entity;

import util.validate.RequireUtil;

/**
 * @author CPF 
 * 用一个6位数表示棋型，从高位到低位分别表示 连五，活四，眠四，活三，活二/眠三，活一/眠二, 眠一
 * 
 * 
 * 
 */
public interface PointScore {
	public static final int MUST_B_KILL = 1000_0000;// 直接胜利
	/**
	 * 活4, 双阻4等
	 */
	public static final int KILL_TO_ONE = 1000_000; // 必杀棋! 离成功仅差一步
	/**
	 * 阻4活3
	 */
	public static final int KILL_TO_1_2 = 5000_00;  // 必杀棋! 离成功差一步或两步
	/**
	 * 2 or 3个活三
	 */
	public static final int KILL_TO_TWO = 1000_00;  // 必杀棋! 离成功差两步
	/**
	 * 活三, 阻四
	 */
	public static final int NEED_TO_DEAL= 1000_0;   // 需要应对
	
	
	public static final int ONE           = 10; // 活1
	public static final int TWO           = 100; // 活2
	public static final int THREE         = 1000; // 活3 (需要应对)
	public static final int FOUR          = KILL_TO_ONE; // 活4, 双阻4, 双4 (必杀棋! 离成功仅差一步)
	public static final int FIVE          = MUST_B_KILL; // 成5 (直接胜利)
	
	public static final int BLOCKED_ONE   = 1; // 阻1
	public static final int BLOCKED_TWO   = 10; // 阻2
	public static final int BLOCKED_THREE = 100; // 阻3
	public static final int BLOCKED_FOUR  = 1500; // 阻4 (需要应对)  其值不能大于两倍活三
	
	
	public static final int MULTIPLE_THREE  = KILL_TO_TWO; // 双3 (必杀棋! 离成功差两步)
	public static final int THREE_FOUR    = KILL_TO_1_2; // 阻4活3 (必杀棋! 离成功差一步或两步)
	

	public static final int H1   = ONE; // 阻1
	public static final int H2   = TWO; // 阻1
	public static final int H3   = THREE; // 阻1
	public static final int H4   = FOUR; // 阻1
	public static final int H5   = FIVE; // 阻1
	
	public static final int B1   = BLOCKED_ONE; // 阻1
	public static final int B2   = BLOCKED_TWO; // 阻1
	public static final int B3   = BLOCKED_THREE; // 阻1
	public static final int B4   = BLOCKED_FOUR; // 阻1
	
	
	
	
	
	public static void test() {
		RequireUtil.requireBooleanTrue(THREE * 3 < THREE_FOUR);
		RequireUtil.requireBooleanTrue(MULTIPLE_THREE < THREE_FOUR);
		// 两个阻四活三 (双阻四)
		RequireUtil.requireBooleanTrue(THREE_FOUR * 2 >= FOUR);
		RequireUtil.requireBooleanTrue(BLOCKED_FOUR * 2 >= FOUR);
		RequireUtil.requireBooleanTrue(THREE * 2 >= BLOCKED_FOUR);
	}
	
	
}
