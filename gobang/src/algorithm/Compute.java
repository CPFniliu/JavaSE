package algorithm;

import entity.Place;
import global.Global;

public class Compute {

	/**
	 * ��ȡCOM�����õ���λ��
	 * @param curPart 
	 * @return
	 */
	public static Place getEvaluatedPlace(){
		return new AlphaBeta().getEvaluatedPlace(Global.getSituation().getCurPart());
	}

	
	
	
	
	
}
