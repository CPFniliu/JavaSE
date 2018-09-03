package entity;

import util.validate.RequireUtil;

/**
 * @author CPF 
 * ��һ��6λ����ʾ���ͣ��Ӹ�λ����λ�ֱ��ʾ ���壬���ģ����ģ����������/��������һ/�߶�, ��һ
 * 
 * 
 * 
 */
public interface PointScore {
	public static final int MUST_B_KILL = 1000_0000;// ֱ��ʤ��
	/**
	 * ��4, ˫��4��
	 */
	public static final int KILL_TO_ONE = 1000_000; // ��ɱ��! ��ɹ�����һ��
	/**
	 * ��4��3
	 */
	public static final int KILL_TO_1_2 = 5000_00;  // ��ɱ��! ��ɹ���һ��������
	/**
	 * 2 or 3������
	 */
	public static final int KILL_TO_TWO = 1000_00;  // ��ɱ��! ��ɹ�������
	/**
	 * ����, ����
	 */
	public static final int NEED_TO_DEAL= 1000_0;   // ��ҪӦ��
	
	
	public static final int ONE           = 10; // ��1
	public static final int TWO           = 100; // ��2
	public static final int THREE         = 1000; // ��3 (��ҪӦ��)
	public static final int FOUR          = KILL_TO_ONE; // ��4, ˫��4, ˫4 (��ɱ��! ��ɹ�����һ��)
	public static final int FIVE          = MUST_B_KILL; // ��5 (ֱ��ʤ��)
	
	public static final int BLOCKED_ONE   = 1; // ��1
	public static final int BLOCKED_TWO   = 10; // ��2
	public static final int BLOCKED_THREE = 100; // ��3
	public static final int BLOCKED_FOUR  = 1500; // ��4 (��ҪӦ��)  ��ֵ���ܴ�����������
	
	
	public static final int MULTIPLE_THREE  = KILL_TO_TWO; // ˫3 (��ɱ��! ��ɹ�������)
	public static final int THREE_FOUR    = KILL_TO_1_2; // ��4��3 (��ɱ��! ��ɹ���һ��������)
	

	public static final int H1   = ONE; // ��1
	public static final int H2   = TWO; // ��1
	public static final int H3   = THREE; // ��1
	public static final int H4   = FOUR; // ��1
	public static final int H5   = FIVE; // ��1
	
	public static final int B1   = BLOCKED_ONE; // ��1
	public static final int B2   = BLOCKED_TWO; // ��1
	public static final int B3   = BLOCKED_THREE; // ��1
	public static final int B4   = BLOCKED_FOUR; // ��1
	
	
	
	
	
	public static void test() {
		RequireUtil.requireBooleanTrue(THREE * 3 < THREE_FOUR);
		RequireUtil.requireBooleanTrue(MULTIPLE_THREE < THREE_FOUR);
		// �������Ļ��� (˫����)
		RequireUtil.requireBooleanTrue(THREE_FOUR * 2 >= FOUR);
		RequireUtil.requireBooleanTrue(BLOCKED_FOUR * 2 >= FOUR);
		RequireUtil.requireBooleanTrue(THREE * 2 >= BLOCKED_FOUR);
	}
	
	
}
