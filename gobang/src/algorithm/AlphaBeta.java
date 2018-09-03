package algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import entity.Part;
import entity.Place;
import global.Config;
import global.Global;

/**
 * @author CPF
 * 1.双方都按自己认为的最佳着法行棋.
 * 
 * 对给定的盘面用一个分值来评估，这个评估值永远是从一方（搜索程序）来评价的，红方有利时给一个正数，黑方有利时给一个负数。
 * （通常把它称为Max）看来，分值大的数表示对己方有利，而对于对方Min来说，它会选择分值小的着法。
 * 
 * 用Negamax风格来描述的AlphaBeta中的评估函数，对轮到谁走棋是敏感的。
 * 在Minimax风格的AlphaBeta算法中，轮红方走棋时，评估值为100，轮黑方走棋评估值仍是100。
 * 但在Negamax风格的AlphaBeta算法中，轮红方走棋时，评估值为100，轮黑方走棋时评估值要为-100。
 */
public class AlphaBeta {
	
	private static final int MAX = Integer.MAX_VALUE;
	private static final int MIN = - MAX;
//	private static final int MIN = - Integer.MIN_VALUE; // note : 最小值的负值仍然是最小值
	private static int total = 0;//总节点数
	private static int PVcut = 0;
//	private int steps = 0;//总步数
//	private int count = 0;//每次思考的节点数
//	private int ABcut = 0;//AB剪枝次数
//	private int cacheCount = 0;	//zobrist缓存节点数数
//	private int cacheGet = 0;	//zobrist缓存命中数量
//	private int _checkmateDeep = Config.checkmateDeep;	//算杀深度
	
	/**
	 * 
	 * 奇数层是电脑(max层)thisSide, 偶数层是human(min层)otherSide
	 * 
	 * @param deep 搜索深度(一定要是偶数, 因为每下一步都要考虑一下对手的防守)
	 * @param checkmateDeep
	 * @return
	 */
	public Place evaluatedPlace(Part curPart){
		total = 0;
		PVcut = 0;
		Part oppopt = Part.getOpposide(curPart);
		//搜索深度
		int deep = Config.deep;
		// 1. 初始化各个变量
		int best = MIN;
//		count = 0;
//		ABcut = 0;
//		PVcut = 0;
		// 复制棋盘(保险)
		Situation situation = Global.getSituation();
		// 2. 获取可以下子的空位列表
		// 生成待选的列表，就是可以下子的空位
		List<Place> places = situation.getHeuristicPlaces(curPart);
		if (places.isEmpty()){ 
			// 为空则证明棋盘上已没有空位
			return null;
		}
		HashSet<Place> bestPlace = new HashSet<>();
		// 3. 获取当前局势最好分数
		int score;
		for (Place place : places){  // 如果跟之前的一个好，则把当前位子加入待选位子
			situation.virtualLocatePiece(place, curPart);  // 尝试下一个子
			if (situation.canWin(place, curPart)){ // 如果当前落子能够胜利,则置分数为最大,否则通过最小最大函数计算score
				score = situation.evaluate(curPart);;
			} else {
				score = maxmin(situation, oppopt, deep - 1, -best); 
			}
			situation.virtualRemovePiece(place); // 移除刚才下的子
			if (score == best){
				bestPlace.add(place);
			}
			if (score > best){ // 找到一个更好的分，就把以前存的位子全部清除
				best = score;
				bestPlace.clear();
				bestPlace.add(place);
			}
		}
		System.out.println(total + " pvcut : " + PVcut);
		int count = bestPlace.size();
		int ran = new Random().nextInt(count);
		return (Place) bestPlace.toArray()[ran];
	}
	
	
	public int maxmin(Situation situation, Part pt, int deep, int alphabeta) {
		total ++;
		int best = MIN;
		// 获取空位
		List<Place> places = situation.getHeuristicPlaces(pt);
		Part oppopt = Part.getOpposide(pt);
		
		for (Place place : places){  // 如果跟之前的一个好，则把当前位子加入待选位子
			situation.virtualLocatePiece(place, pt);
			int score;
			// 判断搜索深度 和是否 胜利 , 评估的值大于一定分数
			if (deep <= 1 || situation.canWin(place, pt)){
				score = situation.evaluate(pt);
			} else {
 				score = maxmin(situation, oppopt, deep - 1, -best); 
			}
			situation.virtualRemovePiece(place); // 移除刚才下的子
			if (score > best){ // 找到一个更好的分，就把以前存的位子全部清除
				best = score;
			}
			if (score > alphabeta){ // alpha剪枝
				PVcut ++;
				break;
			}
		}
		return - best;
	}
//
//	public int min(Situation situation, Place lastplace, int deep, int alpha, int beta) {
//		int best = MIN;
//		// 获取空位
//		List<Place> places = situation.getHasNeighborPlaces();
//		for (Place place : places){  // 如果跟之前的一个好，则把当前位子加入待选位子
//			situation.virtualLocatePiece(place, otherSide);
//			int score;
//			// 判断搜索深度 和是否 胜利 , 评估的值大于一定分数
//			if (deep <= 1 || situation.isWin(place, otherSide)){
//				score = situation.evaluate(otherSide);
//			} else {
//				score = max(situation, place, deep - 1, alpha, best);
//			}
//			situation.virtualRemovePiece(place); // 移除刚才下的子
//			if (score > best){ // 找到一个更好的分，就把以前存的位子全部清除
//				best = score;
//			}
//			if (score > beta){ // beta剪枝
//				break;
//			}
//		}
//		return best;
//	}
	
	
//	public int max(Situation situation, Place lastplace, int deep, int alpha, int beta) {
//		// 判断搜索深度 和是否 胜利 , 评估的值大于一定分数
//		if (deep <= 0 || situation.isWin(lastplace, otherSide)){ //win(board)
//			// FIXME 当前分数大于 fill_TO_ONE
//			int v = situation.evaluate();
//			return v;
//		}
//		int best = MIN;
//		// 获取空位
//		List<Place> places = situation.getHasNeighborPlaces();
//		
//		for (Place place : places){  // 如果跟之前的一个好，则把当前位子加入待选位子
//			situation.virtualLocatePiece(place, thisSide);
//			int score = min(situation, place, deep - 1, best, beta); 
//			situation.virtualRemovePiece(place); // 移除刚才下的子
//			if (score > best){ // 找到一个更好的分，就把以前存的位子全部清除
//				best = score;
//			}
//			if (score > alpha){ // alpha剪枝
//				break;
//			}
//		}
//		return best;
//	}
//	
//
//	public int min(Situation situation, Place lastplace, int deep, int alpha, int beta) {
//		// 判断搜索深度 和是否 胜利 , 评估的值大于一定分数
//		if (deep <= 0 || situation.isWin(lastplace, thisSide)){ //win(board)
//			int v = situation.evaluate();
//			return v;
//		}
//		int best = MAX;
//		// 获取空位
//		List<Place> places = situation.getHasNeighborPlaces();
//		for (Place place : places){  // 如果跟之前的一个好，则把当前位子加入待选位子
//			situation.virtualLocatePiece(place, otherSide);
//			int score = max(situation, place, deep - 1, alpha, best);
//			situation.virtualRemovePiece(place); // 移除刚才下的子
//			if (score < best){ // 找到一个更好的分，就把以前存的位子全部清除
//				best = score;
//			}
//			if (score < beta){ // beta剪枝
//				break;
//			}
//		}
//		return best;
//	}
	
}


