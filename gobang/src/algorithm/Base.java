package algorithm;

import java.util.Random;

import javax.swing.JOptionPane;

import domain.PlacePool;
import entity.Part;
import entity.Place;
import global.Config;

public class Base {
	/**
	 * 连5胜利
	 */
	private static final int WIN_COUNT = 5;
	/**
	 * 邻居范围半径
	 */
	private static final int distance = 2;

	
	/**
	 * 判断棋子周围一定范围内是否有棋子
	 * @param boardSpace 
	 * @param place 方位
	 * @param distance 距离
	 * @param count 棋子数
	 * @return
	 */
	public static boolean hasNeighbor(final Part[][] boardSpace, final Place place){
		if (boardSpace == null || place == null || distance < 0){
			System.out.println("hasNeighbor : BUG");
			return false;
		}
		int i = Math.max(place.x - distance, 0);
		int minj = Math.max(place.y - distance, 0);
		int maxi = Math.min(Config.BOARDLENGTH - 1, place.x + distance);
		int maxj = Math.min(Config.BOARDLENGTH - 1, place.y + distance);
		for (; i <= maxi; i++){
			for (int j = minj; j <= maxj; j++){
				// 黑白均可
				if (boardSpace[i][j] != null){
					return true;
				}
			}
		}
		return false;
	}

	
//	/**
//	 * 判断两个点是否为邻居
//	 * @param place1
//	 * @param place2
//	 * @return
//	 */
//	public static boolean isNeighbor(Place place1, Place place2){
//		return Math.abs(place1.x - place2.x) <= distance && Math.abs(place1.y - place2.y) <= distance;
//	}
	

	/**
	 * 在棋盘中心随便找一空位
	 * @param board
	 * @return
	 */
	public static Place getRandomCenterPlace(Part[][] board) {
		Random random = new Random();
		int i = random.nextInt(Config.BOARDLENGTH - WIN_COUNT * 2) + WIN_COUNT;
		int j = random.nextInt(Config.BOARDLENGTH - WIN_COUNT * 2) + WIN_COUNT;
		if (board[i][j] != null) {
			JOptionPane.showMessageDialog(null, "出错了！！！！");
			throw new RuntimeException();
		}
		return PlacePool.getPlace(i, j);
	}

	
	/**
	 * 是否胜利(判断当前位置周围是否有5子相连)
	 * @param board
	 * @param place 位置
	 * @param part 
	 * @return
	 */
	public static boolean isWin(Part[][] boardSpace, Place place, Part part){
		int len = Config.BOARDLENGTH;
		int i,j,maxI,maxJ;
		int wincnt = WIN_COUNT - 1;
		
		// -----
		int count = 0;
		i = Math.max(0, place.x - wincnt);
		maxI = Math.min(place.x + wincnt, len - 1);
		for (; i <= maxI; i ++){
			if (part.equals(boardSpace[i][place.y])){
				if (++ count >= 5){
		    		return true;
		    	}
			} else {
				count = 0;
			}
		}
		
		// |
		count = 0;
		j = Math.max(0, place.y - wincnt);
		maxJ = Math.min(place.y + wincnt, len - 1);
		for (; j <= maxJ; j ++){
			if (part.equals(boardSpace[place.x][j])){
				if (++ count >= 5){
		    		return true;
		    	}
			} else {
				count = 0;
			}
		}
		
		// /
		int minlen;
		count = 0;
		minlen = Math.min(4, Math.min(place.x, len - 1 - place.y));
		i = place.x - minlen;
		j = place.y + minlen;
		maxI = place.x + Math.min(4, Math.min(place.y, len - 1 - place.x));
		
		for (; i <= maxI; i++, j--){
		    if (part.equals(boardSpace[i][j])){
		    	if (++ count >= 5){
		    		return true;
		    	}
			} else {
				count = 0;
			}
		}
		

		// \
		count = 0;
		minlen = Math.min(4, Math.min(place.x, place.y));
		i = place.x - minlen;
		j = place.y - minlen;
		maxI = place.x + Math.min(4, len - 1 - Math.max(place.x, place.y));
		
		for (; i <= maxI ; i++, j++){
		    if (part.equals(boardSpace[i][j])){
		    	if (++ count >= 5){
		    		return true;
		    	}
			} else {
				count = 0;
			}
		}
		
		return false;
	}
	
}
