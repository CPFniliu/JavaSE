package domain;

import java.awt.Point;

import entity.Place;
import global.Config;

public class PlacePool {
	
	private static Place[][] places = null;
	
	private PlacePool(){
	}
	
	public static void init(){
		places = new Place[Config.BOARDLENGTH][Config.BOARDLENGTH];
		for (int i = 0; i < Config.BOARDLENGTH; i++){
			for (int j = 0; j < Config.BOARDLENGTH; j++){
				places[i][j] = new Place(i, j);
			}
		}
	}
	
	public static Place getPlace(int x, int y){
		return places[x][y];
	}
	
	public static Place getPlace(Point point) {
		int x = (point.x - Config.BORDERWIDTH) / Config.PIECEWIDTH;
		int y = (point.y - Config.BORDERWIDTH) / Config.PIECEWIDTH;
		return places[x][y];
	}
	
}
