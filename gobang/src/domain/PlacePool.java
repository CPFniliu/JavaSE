package domain;

import java.awt.Point;

import entity.Place;
import global.Config;

/**
 * Place池 ： 防止代表同一个位置的Place被复用太多次
 * 
 * @author CPF
 */
public class PlacePool {

	/**
	 * 用来存储Place的数组
	 */
	private static Place[][] places = null;

	private PlacePool() {
	}

	/**
	 * 初始化Place池
	 */
	public static void init() {
		places = new Place[Config.BOARDLENGTH][Config.BOARDLENGTH];
		for (int i = 0; i < Config.BOARDLENGTH; i++) {
			for (int j = 0; j < Config.BOARDLENGTH; j++) {
				places[i][j] = new Place(i, j);
			}
		}
	}

	/**
	 * 通过x, y坐标获取Place池种相应对象
	 */
	public static Place getPlace(int x, int y) {
		return places[x][y];
	}

	/**
	 * 通过Point对象获取Place池种相应对象
	 */
	public static Place getPlace(Point point) {
		int x = (point.x - Config.BORDERWIDTH) / Config.PIECEWIDTH;
		int y = (point.y - Config.BORDERWIDTH) / Config.PIECEWIDTH;
		return places[x][y];
	}

}
