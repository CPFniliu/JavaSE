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
	 * @return 获取当前棋盘上可以下的点的List集合
	 */
	public static List<Place> getHasNeighborPlaces(Pt[][] board) {
		List<Place> places = new ArrayList<>();
		for (int i = Config.BOARDLENGTH - 1 ; i >= 0; i--){
			for (int j = Config.BOARDLENGTH - 1; j >= 0; j--){
				//TODO Place被复用太多次
				if (board[i][j] == null){
					Place place = PlacePool.getPlace(i, j);
					// 判断棋盘上的点周围是否有邻居
					if (Base.hasNeighbor(board, place)){
						// TODO 加入其它判断
						places.add(place);
					}
				}
			}
		}
		return places;
	}
	
	/**
	 * @return 获取当前棋盘上可以下的点的List集合
	 */
	public static List<Place> getAllBlankPlaces(Pt[][] board) {
		List<Place> placeSet = new ArrayList<>();
		for (int i = Config.BOARDLENGTH - 1 ; i >= 0; i--){
			for (int j = Config.BOARDLENGTH - 1; j >= 0; j--){
				//TODO Place被复用太多次
				if (board[i][j] == null){
					Place place = PlacePool.getPlace(i, j);
					placeSet.add(place);
				}
			}
		}
		return placeSet;
	}
	
	/**
	 * 启发式搜索函数
	 * 
	 * 给所有待搜索的位置进行打分，按照分数的高低来排序。注意这个打分算法是对某一个空位进行打分，
	 * 和对整个棋盘进行打分的 evaluate 函数是不一样的。不过打分的基本原理是相同的。
	 * 具体就是根据这个位置是否能成五，活四，活三等来进行打分
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
				//TODO Place被复用太多次
				if (board[i][j] == null){
					Place place = PlacePool.getPlace(i, j);
					// 判断棋盘上的点周围是否有邻居
					if (Base.hasNeighbor(board, place)){
						// TODO 加入其它判断
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
		// 必杀棋, 本方下, 对方堵!!!
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
