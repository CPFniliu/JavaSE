package entity;

public enum Part {
	/**
	 * 白方势力
	 */
	WHITE, 
	
	/**
	 * 黑方势力
	 */
	BLACK;
	
	/**
	 * 返回相反的势力
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
