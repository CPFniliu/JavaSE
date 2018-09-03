package algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import domain.PlacePool;
import entity.Place;
import entity.Pt;
import entity.Score;
import entity.SortedPlaces;
import global.Config;

public class GenePlaces {

	

	/**
	 * @return ��ȡ��ǰ�����Ͽ����µĵ��List����
	 */
	public static List<Place> getHasNeighborPlaces(Pt[][] board) {
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
	 * @return ��ȡ��ǰ�����Ͽ����µĵ��List����
	 */
	public static List<Place> getAllBlankPlaces(Pt[][] board) {
		List<Place> placeSet = new ArrayList<>();
		for (int i = Config.BOARDLENGTH - 1 ; i >= 0; i--){
			for (int j = Config.BOARDLENGTH - 1; j >= 0; j--){
				//TODO Place������̫���
				if (board[i][j] == null){
					Place place = PlacePool.getPlace(i, j);
					placeSet.add(place);
				}
			}
		}
		return placeSet;
	}
	
	/**
	 * ����ʽ��������
	 * 
	 * �����д�������λ�ý��д�֣����շ����ĸߵ�������ע���������㷨�Ƕ�ĳһ����λ���д�֣�
	 * �Ͷ��������̽��д�ֵ� evaluate �����ǲ�һ���ġ�������ֵĻ���ԭ������ͬ�ġ�
	 * ������Ǹ������λ���Ƿ��ܳ��壬���ģ������������д��
	 * 
	 * @return
	 */
	public static Collection<Place> getHeuristicPlaces(final Pt[][] board, final Pt thispt) {
		Pt otherPt = Pt.getOpposide(thispt);
		List<Place> curKill = null;
		List<Place> otrKill = null;
		List<Place> curWinTo1pls = null;
		List<Place> otrWinTo1pls = null;
		List<Place> curWinTo1_5pls = null;
		List<Place> otrWinTo1_5pls = null;
		List<Place> curWinTo2pls = null;
		List<Place> otrWinTo2pls = null;
		SortedPlaces sortedPlaces = new SortedPlaces();
		for (int i = Config.BOARDLENGTH - 1 ; i >= 0; i--){
			for (int j = Config.BOARDLENGTH - 1; j >= 0; j--){
				//TODO Place������̫���
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
						} else {
							sortedPlaces.addOrInit(place, curScore - otrScore);
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
		} else if (sortedPlaces.hasData()) {
			return sortedPlaces.getSortedPlaces();
		} else {
			System.out.println("nulllllll");
			return getAllBlankPlaces(board);
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
