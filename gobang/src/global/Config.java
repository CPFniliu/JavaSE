package global;

import entity.MouseCliceState;
import entity.Pt;
import entity.Role;

public class Config {
	/**
	 * 棋子宽度
	 */
	public static int PIECEWIDTH = 34;
	/**
	 * 行列数量
	 */
	public static int BOARDLENGTH = 15; 
	/**
	 * 棋盘边缘宽度
	 */
	public static int BORDERWIDTH = 0;
	/**
	 * 行数
	 */
	public static int ROWTOTAL = 15;
	/**
	 * 列数
	 */
	public static int COLUMTOTAL = 15;
	/**
	 * 搜索深度
	 */
	public static int SEARCHDEEP = 6;
	/**
	 * 按搜索深度递减分数，为了让短路径的结果比深路劲的分数高
	 */
	public static int DEEPDECREASE = 8;
	/**
	 * gen函数返回的节点数量上限，超过之后将会按照分数进行截断
	 */
	public static int countLimit = 8;
	/**
	 * 算杀深度
	 */
	public static int checkmateDeep = 5;
	/**
	 * 是否使用效率不高的置换表
	 */
	public static boolean cache = false;
	
	/**
	 * 先手方 (初始化黑棋先手)
	 */
	public static Pt firstPart = Pt.BLACK;
	/**
	 * 黑方运算
	 */
	public static Role blackPartCompute = Role.MAN;
	/**
	 * 白方运算
	 */
	public static Role whitePartCompute = Role.COM;
	
	/**
	 * 搜索深度
	 */
	public static int deep = 2;
	
	public static MouseCliceState mouseClickState = MouseCliceState.PLAY;

	/**
	 * 获取当前下棋角色 COM, MAN
	 * @return
	 */
	public static Role getRole(Pt part){
		if (Pt.BLACK.equals(part)){
			return blackPartCompute;
		} else {
			return whitePartCompute;
		}
	}
	
	
	
}
