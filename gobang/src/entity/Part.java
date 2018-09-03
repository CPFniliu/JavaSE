package entity;

public enum Part {
	/**
	 * �׷�����
	 */
	WHITE, 
	
	/**
	 * �ڷ�����
	 */
	BLACK;
	
	/**
	 * �����෴������
	 * @param pt
	 * @return
	 */
	public static Part getOpposide(Part pt){
		if (pt == WHITE){
			return BLACK;
		} else {
			return WHITE;
		}
	}
}
