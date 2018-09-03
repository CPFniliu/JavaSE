package global;

import entity.MouseCliceState;
import entity.Pt;
import entity.Role;

public class Config {
	/**
	 * ���ӿ��
	 */
	public static int PIECEWIDTH = 34;
	/**
	 * ��������
	 */
	public static int BOARDLENGTH = 15; 
	/**
	 * ���̱�Ե���
	 */
	public static int BORDERWIDTH = 0;
	/**
	 * ����
	 */
	public static int ROWTOTAL = 15;
	/**
	 * ����
	 */
	public static int COLUMTOTAL = 15;
	/**
	 * �������
	 */
	public static int SEARCHDEEP = 6;
	/**
	 * ��������ȵݼ�������Ϊ���ö�·���Ľ������·���ķ�����
	 */
	public static int DEEPDECREASE = 8;
	/**
	 * gen�������صĽڵ��������ޣ�����֮�󽫻ᰴ�շ������нض�
	 */
	public static int countLimit = 8;
	/**
	 * ��ɱ���
	 */
	public static int checkmateDeep = 5;
	/**
	 * �Ƿ�ʹ��Ч�ʲ��ߵ��û���
	 */
	public static boolean cache = false;
	
	/**
	 * ���ַ� (��ʼ����������)
	 */
	public static Pt firstPart = Pt.BLACK;
	/**
	 * �ڷ�����
	 */
	public static Role blackPartCompute = Role.MAN;
	/**
	 * �׷�����
	 */
	public static Role whitePartCompute = Role.COM;
	
	/**
	 * �������
	 */
	public static int deep = 2;
	
	public static MouseCliceState mouseClickState = MouseCliceState.PLAY;

	/**
	 * ��ȡ��ǰ�����ɫ COM, MAN
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
