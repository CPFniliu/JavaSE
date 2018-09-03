package test.entity;

import util.validate.RequireUtil;

/**
 * @author CPF ��һ��6λ����ʾ���ͣ��Ӹ�λ����λ�ֱ��ʾ ���壬���ģ����ģ����������/��������һ/�߶�, ��һ
 * 
 * 
 * 
 */
/**
 *  [һ�仰���ܼ���]
 *  [������ϸ����]
 * @���� CPF
 * @version [�汾��, 2018��6��12��]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾] 
 */
public class Scoress
{
    /**
     * ================================================================
     * һ�������������е���ʽ
     */
    public static final int MUST_B_KILL = 1000_0000;// ֱ��ʤ��
    /**
     * ��4, ˫��4��
     */
    public static final int KILL_TO_ONE = 1000_000; // ��ɱ��! ��ɹ�����һ��
    /**
     * ��4��3
     */
    public static final int KILL_TO_1_2 = 500_000; // ��ɱ��! ��ɹ���һ��������
    /**
     * 2 or 3������
     */
    public static final int KILL_TO_TWO = 100_000; // ��ɱ��! ��ɹ�������
    /**
     * ����
     */
    public static final int NEED_TO_DEAL_1 = 175_000; // ��ҪӦ��(����������һ��)
    /**
     * ����
     */
    public static final int NEED_TO_DEAL_2 = 100_000; // ��ҪӦ��(��������������)

    /**
     * ================================================================
     * ��������
     */
    public static final int ONE = 10; // ��1
    public static final int TWO = 100; // ��2
    public static final int THREE = 1000; // ��3 (��ҪӦ��)
    public static final int FOUR = 3500; // ��4, ˫��4, ˫4 (��ɱ��! ��ɹ�����һ��)
    public static final int FIVE = MUST_B_KILL; // ��5 (ֱ��ʤ��)

    public static final int BLOCKED_ONE = 1; // ��1
    public static final int BLOCKED_TWO = 10; // ��2
    public static final int BLOCKED_THREE = 100; // ��3
    public static final int BLOCKED_FOUR = 1750; // ��4 (��ҪӦ��) ��ֵ���ܴ�����������
    /**
     * ================================================================
     * ͨ���ֶ�
     */
    public static final int DOUBLE_THREE = THREE * 2; // ˫3 (��ɱ��! ��ɹ�������)
    public static final int THREE_FOUR = THREE + BLOCKED_FOUR; // ��4��3 (��ɱ��! ��ɹ���һ��������)


    public static int pointEvaluate(int total) {
        // �����ܲ����γɾ�ɱ
        if (total > BLOCKED_FOUR) {
            if (total < THREE * 2) { // С��˫��
                return BLOCKED_FOUR;
            } else if (total < THREE + BLOCKED_FOUR) { // С�ڳ��Ļ���
                return KILL_TO_TWO;
            } else if (total < FOUR) { // С��˫����
                return KILL_TO_1_2;
            } else {
                return KILL_TO_ONE;
            }
        }
        return total;
    }

//    public static int GlobleEvaluate0(){
//        int h3 = 0;
//        int h4 = 0;
//        int b4 = 0;
//        int b4h3 = 0;
//        int total = 0;
//        int pointScore;
//        for (int i=0; i < 100; i ++) {
//            pointScore = i;
//            if (pointScore > Score.THREE){
//                if (pointScore < Score.BLOCKED_FOUR) {
//                    h3 ++;
//                } else if (pointScore < Score.BLOCKED_FOUR + Score.BLOCKED_THREE){
//                    b4 ++;
//                } else if (pointScore < Score.FOUR) {
//                    b4h3 ++;
//                } else if (pointScore < Score.FIVE) {
//                    h4 ++;
//                } else {
//                    return Score.MUST_B_KILL;
//                }
//            } else {
//                total += pointScore;
//            }
//        }
//        if (h4 >= 1 || b4 >= 2 || b4h3 >= 2){
//            return Score.KILL_TO_ONE;
//        } else if (b4h3 >= 1){
//            return Score.KILL_TO_1_2;
//        } else if (h3 >= 2) {
//            return Score.KILL_TO_TWO;
//        } else {
//            return total;
//        }
//    }
//    
//    public static void test() {
//        RequireUtil.requireBooleanTrue(THREE * 3 < THREE + BLOCKED_FOUR);
//        // �������Ļ��� (˫����) �൱�ڻ���
//        RequireUtil.requireBooleanTrue((THREE + BLOCKED_FOUR) * 2 >= FOUR);
//        RequireUtil.requireBooleanTrue(BLOCKED_FOUR * 2 >= FOUR);
//        // ˫����
//        RequireUtil.requireBooleanTrue(THREE * 2 >= BLOCKED_FOUR);
//    }
//
//    public static void main(String[] args) {
//        test();
//        System.out.println("success");
//    }

}
