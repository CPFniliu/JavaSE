package global;

import algorithm.Situation;
import domain.PlacePool;

public class Global {

	
	/**
	 * 全局局势
	 */
	private static Situation situation = null;
	
	private static boolean comRunnable;
	

	/**
	 * @return the comRunnable
	 */
	public static boolean isComRunnable() {
		return comRunnable;
	}

	/**
	 * @param comRunnable the comRunnable to set
	 */
	public static void setComRunnable(boolean comRunnable) {
		Global.comRunnable = comRunnable;
	}

	public static void init(){
		situation = new Situation();
		PlacePool.init();
	}
	
	public static Situation getSituation() {
		return situation;
	}
	
}
