package entity;

public enum Pt {
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
	public static Pt getOpposide(Pt pt){
		if (pt == WHITE){
			return BLACK;
		} else {
			return WHITE;
		}
	}
}
