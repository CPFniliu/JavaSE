package domain;

import java.awt.Point;

import entity.Place;
import global.Config;

/**
 * Place�� �� ��ֹ����ͬһ��λ�õ�Place������̫���
 * 
 * @author CPF
 */
public class PlacePool {

	/**
	 * �����洢Place������
	 */
	private static Place[][] places = null;

	private PlacePool() {
	}

	/**
	 * ��ʼ��Place��
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
	 * ͨ��x, y�����ȡPlace������Ӧ����
	 */
	public static Place getPlace(int x, int y) {
		return places[x][y];
	}

	/**
	 * ͨ��Point�����ȡPlace������Ӧ����
	 */
	public static Place getPlace(Point point) {
		int x = (point.x - Config.BORDERWIDTH) / Config.PIECEWIDTH;
		int y = (point.y - Config.BORDERWIDTH) / Config.PIECEWIDTH;
		return places[x][y];
	}

}
