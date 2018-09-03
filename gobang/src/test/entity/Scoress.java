package test.entity;

import util.validate.RequireUtil;

/**
 * @author CPF 用一个6位数表示棋型，从高位到低位分别表示 连五，活四，眠四，活三，活二/眠三，活一/眠二, 眠一
 * 
 * 
 * 
 */
/**
 *  [一句话功能简述]
 *  [功能详细描述]
 * @作者 CPF
 * @version [版本号, 2018年6月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class Scoress
{
    /**
     * ================================================================
     * 一个棋子在棋盘中的形式
     */
    public static final int MUST_B_KILL = 1000_0000;// 直接胜利
    /**
     * 活4, 双阻4等
     */
    public static final int KILL_TO_ONE = 1000_000; // 必杀棋! 离成功仅差一步
    /**
     * 阻4活3
     */
    public static final int KILL_TO_1_2 = 500_000; // 必杀棋! 离成功差一步或两步
    /**
     * 2 or 3个活三
     */
    public static final int KILL_TO_TWO = 100_000; // 必杀棋! 离成功差两步
    /**
     * 阻四
     */
    public static final int NEED_TO_DEAL_1 = 175_000; // 需要应对(否则离死差一步)
    /**
     * 活三
     */
    public static final int NEED_TO_DEAL_2 = 100_000; // 需要应对(否则离死差两步)

    /**
     * ================================================================
     * 单行形势
     */
    public static final int ONE = 10; // 活1
    public static final int TWO = 100; // 活2
    public static final int THREE = 1000; // 活3 (需要应对)
    public static final int FOUR = 3500; // 活4, 双阻4, 双4 (必杀棋! 离成功仅差一步)
    public static final int FIVE = MUST_B_KILL; // 成5 (直接胜利)

    public static final int BLOCKED_ONE = 1; // 阻1
    public static final int BLOCKED_TWO = 10; // 阻2
    public static final int BLOCKED_THREE = 100; // 阻3
    public static final int BLOCKED_FOUR = 1750; // 阻4 (需要应对) 其值不能大于两倍活三
    /**
     * ================================================================
     * 通用字段
     */
    public static final int DOUBLE_THREE = THREE * 2; // 双3 (必杀棋! 离成功差两步)
    public static final int THREE_FOUR = THREE + BLOCKED_FOUR; // 阻4活3 (必杀棋! 离成功差一步或两步)


    public static int pointEvaluate(int total) {
        // 看看能不能形成绝杀
        if (total > BLOCKED_FOUR) {
            if (total < THREE * 2) { // 小于双三
                return BLOCKED_FOUR;
            } else if (total < THREE + BLOCKED_FOUR) { // 小于冲四活三
                return KILL_TO_TWO;
            } else if (total < FOUR) { // 小于双冲四
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
//        // 两个阻四活三 (双阻四) 相当于活四
//        RequireUtil.requireBooleanTrue((THREE + BLOCKED_FOUR) * 2 >= FOUR);
//        RequireUtil.requireBooleanTrue(BLOCKED_FOUR * 2 >= FOUR);
//        // 双活三
//        RequireUtil.requireBooleanTrue(THREE * 2 >= BLOCKED_FOUR);
//    }
//
//    public static void main(String[] args) {
//        test();
//        System.out.println("success");
//    }

}
