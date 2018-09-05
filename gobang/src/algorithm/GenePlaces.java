package algorithm;

import java.util.ArrayList;
import java.util.List;

import domain.PlacePool;
import domain.SortedPlaces;
import entity.Part;
import entity.Place;
import entity.Score;
import global.Config;

public class GenePlaces {

	

	/**
	 * @return ��ȡ��ǰ�����Ͽ����µĵ��List����
	 */
	public static List<Place> getHasNeighborPlaces(Part[][] board) {
		List<Place> places = new ArrayList<>();
		for (int i = Config.BOARDLENGTH - 1 ; i >= 0; i--){
			for (int j = Config.BOARDLENGTH - 1; j >= 0; j--){
				//TODO Place������̫���
				if (board[i][j] == null){
					Place place = PlacePool.getPlace(i, j);
					// �ж������ϵĵ���Χ�Ƿ����ھ�
					if (Base.hasNeighbor(board, place)){
						// TODO ���������ж�
						places.add(place);
					}
				}
			}
		}
		return places;
	}
	
	
	/**
	 * ���²�������������
	 * @param board
	 * @param steps
	 */
	public static void updateSteps(Part[][] board, int[] steps) {
		for (int i = Config.BOARDLENGTH - 1 ; i >= 0; i--){
			for (int j = Config.BOARDLENGTH - 1; j >= 0; j--){
				if (Part.WHITE.equals(board[i][j])) {
					steps[1] ++;
				} else if (Part.BLACK.equals(board[i][j])){
					steps[2] ++;
				}
			}
		}
		steps[0] = steps[1] + steps[2];
	}
	
	/**
	 * ����ʽ��������
	 * 
	 * �����д�������λ�ý��д�֣����շ����ĸߵ�������ע���������㷨�Ƕ�ĳһ����λ���д�֣�
	 * �Ͷ��������̽��д�ֵ� evaluate �����ǲ�һ���ġ�������ֵĻ���ԭ������ͬ�ġ�
	 * ������Ǹ������λ���Ƿ��ܳ��壬���ģ������������д��
	 * 
	 * @param board ����
	 * @param thispt ��ǰ����
	 * @param hasCommonPlace �Ƿ������ͨ���ӣ�true������ͨ���ӣ� falseֻ������ɱ�壨��ɱ��
	 * @return
	 */
	public static List<Place> getHeuristicPlaces(final Part[][] board, final Part thispt, boolean hasCommonPlace) {
		Part otherPt = Part.getOpposide(thispt);
		List<Place> curKill = null;
		List<Place> otrKill = null;
		List<Place> curWinTo1pls = null;
		List<Place> otrWinTo1pls = null;
		List<Place> curWinTo1_5pls = null;
		List<Place> otrWinTo1_5pls = null;
		List<Place> curWinTo2pls = null;
		List<Place> otrWinTo2pls = null;
		SortedPlaces sortedPlaces = null;
		if (hasCommonPlace){
			sortedPlaces = new SortedPlaces();
		}
		for (int i = Config.BOARDLENGTH - 1 ; i >= 0; i--){
			for (int j = Config.BOARDLENGTH - 1; j >= 0; j--){
				if (board[i][j] == null){
					Place place = PlacePool.getPlace(i, j);
					// �ж������ϵĵ���Χ�Ƿ����ھ�
					if (Base.hasNeighbor(board, place)){
						// TODO ���������ж�
						int curScore = PointEvaluate.pointEvaluate(board, place, thispt);
						int otrScore = PointEvaluate.pointEvaluate(board, place, otherPt);
						if (curScore >= Score.MUST_B_KILL) {
							curKill = addOrInit(curKill, place);
						} else if (curScore >= Score.KILL_TO_ONE){
							curWinTo1pls = addOrInit(curWinTo1pls, place);
						} else if (curScore >= Score.KILL_TO_1_2 ){
							curWinTo1_5pls = addOrInit(curWinTo1_5pls, place);
						} else if (curScore >= Score.KILL_TO_TWO){
							curWinTo2pls = addOrInit(curWinTo2pls, place);
						}else if (otrScore >= Score.MUST_B_KILL) {
							otrKill = addOrInit(otrKill, place);
						} else if (otrScore >= Score.KILL_TO_ONE){
							otrWinTo1pls = addOrInit(otrWinTo1pls, place);
						} else if (otrScore >= Score.KILL_TO_1_2 ){
							otrWinTo1_5pls = addOrInit(otrWinTo1_5pls, place);
						} else if (otrScore >= Score.KILL_TO_TWO){
							otrWinTo2pls = addOrInit(otrWinTo2pls, place);
						} else if (hasCommonPlace){
							sortedPlaces.addOrInit(place, curScore + otrScore / 2);
//							System.out.println(place + " " + thispt + " " + curScore + " " +  otrScore + " " + (curScore + otrScore));
						}
					}
				}
			}
		}
		// ��ɱ��, ������, �Է���!!!
		if (curKill != null){
			return curKill;
		} else if (otrKill != null){
			return otrKill;
		} else if (curWinTo1pls != null) {
			return curWinTo1pls;
		} else if (otrWinTo1pls != null) {
			return otrWinTo1pls;
		} else if (curWinTo1_5pls != null) {
			return curWinTo1_5pls;
		} else if (otrWinTo1_5pls != null) {
			return otrWinTo1_5pls;
		} else if (curWinTo2pls != null) {
			return curWinTo2pls;
		} else if (otrWinTo2pls != null) {
			return otrWinTo2pls;
		} else if (hasCommonPlace && sortedPlaces.hasData()) {
			return sortedPlaces.getSortedPlaces();
		} else {
			return null;
		}
	}
	
	
	
	
	public static <T> List<T> addOrInit(List<T> list, T t){
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(t);
		return list;
	}
	
}
