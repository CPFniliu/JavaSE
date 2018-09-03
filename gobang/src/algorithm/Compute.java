package algorithm;

import entity.Place;
import global.Global;

public class Compute {

	/**
	 * 获取COM运算后得到的位置
	 * @param curPart 
	 * @return
	 */
	public static Place getEvaluatedPlace(){
		return new AlphaBeta().getEvaluatedPlace(Global.getSituation().getCurPart());
	}

	
	
	
	
	
}
