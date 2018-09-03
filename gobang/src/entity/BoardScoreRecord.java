package entity;

/**
 * 记录一次棋盘上相应分数情况
 * 
 * @author CPF
 *
 */
public class BoardScoreRecord {
	/**
	 * 活三数量
	 */
	public int three;
	/**
	 * 阻四数量
	 */
	public int b4;
	/**
	 * 是否有活四
	 */
	public boolean four;
	/**
	 * 是否有连5
	 */
	public boolean five;
	/**
	 * 其余总分数
	 */
	public int total;
}
