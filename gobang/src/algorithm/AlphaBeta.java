package algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import entity.Place;
import entity.Pt;
import entity.Score;
import global.Config;
import global.Global;

/**
 * @author CPF
 * 1.˫�������Լ���Ϊ������ŷ�����.
 * 
 * �Ը�����������һ����ֵ���������������ֵ��Զ�Ǵ�һ�����������������۵ģ��췽����ʱ��һ���������ڷ�����ʱ��һ��������
 * ��ͨ��������ΪMax����������ֵ�������ʾ�Լ��������������ڶԷ�Min��˵������ѡ���ֵС���ŷ���
 * 
 * ��Negamax�����������AlphaBeta�е��������������ֵ�˭���������еġ�
 * ��Minimax����AlphaBeta�㷨�У��ֺ췽����ʱ������ֵΪ100���ֺڷ���������ֵ����100��
 * ����Negamax����AlphaBeta�㷨�У��ֺ췽����ʱ������ֵΪ100���ֺڷ�����ʱ����ֵҪΪ-100��
 */
public class AlphaBeta {
	
	private static final int MAX = Integer.MAX_VALUE;
	private static final int MIN = - MAX;
//	private static final int MIN = - Integer.MIN_VALUE; // note : ��Сֵ�ĸ�ֵ��Ȼ����Сֵ
//	private int total = 0;//�ܽڵ���
//	private int steps = 0;//�ܲ���
//	private int count = 0;//ÿ��˼���Ľڵ���
//	private int PVcut = 0;
//	private int ABcut = 0;//AB��֦����
//	private int cacheCount = 0;	//zobrist����ڵ�����
//	private int cacheGet = 0;	//zobrist������������
//	private int _checkmateDeep = Config.checkmateDeep;	//��ɱ���
	
	/**
	 * 
	 * �������ǵ���(max��)thisSide, ż������human(min��)otherSide
	 * 
	 * @param deep �������(һ��Ҫ��ż��, ��Ϊÿ��һ����Ҫ����һ�¶��ֵķ���)
	 * @param checkmateDeep
	 * @return
	 */
	public Place getEvaluatedPlace(Pt curPart){
		Pt oppopt = Pt.getOpposide(curPart);
		//�������
		int deep = Config.deep;
		// 1. ��ʼ����������
		int best = MIN;
//		count = 0;
//		ABcut = 0;
//		PVcut = 0;
		// ��������(����)
		Situation situation = Global.getSituation();
		// 2. ��ȡ�������ӵĿ�λ�б�
		// ���ɴ�ѡ���б����ǿ������ӵĿ�λ
		Collection<Place> places = situation.getHeuristicPlaces(curPart);
		if (places.isEmpty()){
			return null;
		}
		HashSet<Place> bestPlace = new HashSet<>();
		// 3. ��ȡ��ǰ������÷���
		int score;
		for (Place place : places){  // �����֮ǰ��һ���ã���ѵ�ǰλ�Ӽ����ѡλ��
			situation.virtualLocatePiece(place, curPart);  // ������һ����
			if (situation.isWin(place, curPart)){ // �����ǰ�����ܹ�ʤ��,���÷���Ϊ���,����ͨ����С���������score
				score = Score.MUST_B_KILL;
			} else {
				score = maxmin(situation, oppopt, deep - 1, -best); 
			}
			situation.setScore(place, score);
			situation.virtualRemovePiece(place); // �Ƴ��ղ��µ���
			if (score == best){
				bestPlace.add(place);
			}
			if (score > best){ // �ҵ�һ�����õķ֣��Ͱ���ǰ���λ��ȫ�����
				best = score;
				bestPlace.clear();
				bestPlace.add(place);
			}
		}
		int count = bestPlace.size();
		int ran = new Random().nextInt(count);
		return (Place) bestPlace.toArray()[ran];
	}
	
	
	public int maxmin(Situation situation, Pt pt, int deep, int alphabeta) {
		int best = MIN;
		// ��ȡ��λ
//		List<Place> places = situation.getHasNeighborPlaces();
		Collection<Place> places = situation.getHeuristicPlaces(pt);
		Pt oppopt = Pt.getOpposide(pt);
		
		for (Place place : places){  // �����֮ǰ��һ���ã���ѵ�ǰλ�Ӽ����ѡλ��
			situation.virtualLocatePiece(place, pt);
			int score;
			// �ж�������� ���Ƿ� ʤ�� , ������ֵ����һ������
			if (deep <= 1 || situation.isWin(place, pt)){
				score = situation.evaluate(pt);
			} else {
 				score = maxmin(situation, oppopt, deep - 1, -best); 
			}
			situation.virtualRemovePiece(place); // �Ƴ��ղ��µ���
			if (score > best){ // �ҵ�һ�����õķ֣��Ͱ���ǰ���λ��ȫ�����
				best = score;
			}
			if (score > alphabeta){ // alpha��֦
				break;
			}
		}
		return - best;
	}
//
//	public int min(Situation situation, Place lastplace, int deep, int alpha, int beta) {
//		int best = MIN;
//		// ��ȡ��λ
//		List<Place> places = situation.getHasNeighborPlaces();
//		for (Place place : places){  // �����֮ǰ��һ���ã���ѵ�ǰλ�Ӽ����ѡλ��
//			situation.virtualLocatePiece(place, otherSide);
//			int score;
//			// �ж�������� ���Ƿ� ʤ�� , ������ֵ����һ������
//			if (deep <= 1 || situation.isWin(place, otherSide)){
//				score = situation.evaluate(otherSide);
//			} else {
//				score = max(situation, place, deep - 1, alpha, best);
//			}
//			situation.virtualRemovePiece(place); // �Ƴ��ղ��µ���
//			if (score > best){ // �ҵ�һ�����õķ֣��Ͱ���ǰ���λ��ȫ�����
//				best = score;
//			}
//			if (score > beta){ // beta��֦
//				break;
//			}
//		}
//		return best;
//	}
	
	
//	public int max(Situation situation, Place lastplace, int deep, int alpha, int beta) {
//		// �ж�������� ���Ƿ� ʤ�� , ������ֵ����һ������
//		if (deep <= 0 || situation.isWin(lastplace, otherSide)){ //win(board)
//			// FIXME ��ǰ�������� fill_TO_ONE
//			int v = situation.evaluate();
//			return v;
//		}
//		int best = MIN;
//		// ��ȡ��λ
//		List<Place> places = situation.getHasNeighborPlaces();
//		
//		for (Place place : places){  // �����֮ǰ��һ���ã���ѵ�ǰλ�Ӽ����ѡλ��
//			situation.virtualLocatePiece(place, thisSide);
//			int score = min(situation, place, deep - 1, best, beta); 
//			situation.virtualRemovePiece(place); // �Ƴ��ղ��µ���
//			if (score > best){ // �ҵ�һ�����õķ֣��Ͱ���ǰ���λ��ȫ�����
//				best = score;
//			}
//			if (score > alpha){ // alpha��֦
//				break;
//			}
//		}
//		return best;
//	}
//	
//
//	public int min(Situation situation, Place lastplace, int deep, int alpha, int beta) {
//		// �ж�������� ���Ƿ� ʤ�� , ������ֵ����һ������
//		if (deep <= 0 || situation.isWin(lastplace, thisSide)){ //win(board)
//			int v = situation.evaluate();
//			return v;
//		}
//		int best = MAX;
//		// ��ȡ��λ
//		List<Place> places = situation.getHasNeighborPlaces();
//		for (Place place : places){  // �����֮ǰ��һ���ã���ѵ�ǰλ�Ӽ����ѡλ��
//			situation.virtualLocatePiece(place, otherSide);
//			int score = max(situation, place, deep - 1, alpha, best);
//			situation.virtualRemovePiece(place); // �Ƴ��ղ��µ���
//			if (score < best){ // �ҵ�һ�����õķ֣��Ͱ���ǰ���λ��ȫ�����
//				best = score;
//			}
//			if (score < beta){ // beta��֦
//				break;
//			}
//		}
//		return best;
//	}
	
}


