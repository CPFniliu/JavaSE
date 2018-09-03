package algorithm;

import java.util.Collection;

import entity.BoardScoreRecord;
import entity.Place;
import entity.Pt;
import entity.Score;
import util.validate.ExceptionUtil;

public class BoardEvaluate {

	/**
	 * ���ضԱ�����ʵ������, ����Ϊ��
	 * @param board
	 * @param thispt
	 * @return
	 */
	public static int evaluate(Pt[][] board, Pt thispt) {
		Pt otherPt = Pt.getOpposide(thispt);
		Collection<Place> places = GenePlaces.getHeuristicPlaces(board, thispt);
		BoardScoreRecord thisRecord = new BoardScoreRecord();
		BoardScoreRecord otherRecord = new BoardScoreRecord();
		for (Place place : places) {
			int thisScore = PointEvaluate.pointEvaluate(board, place, thispt);
			partScoreDispose(thisRecord, thisScore);
			int otherScore = PointEvaluate.pointEvaluate(board, place, otherPt);
			partScoreDispose(otherRecord, otherScore);
		}
		return getBoardScore(thisRecord, otherRecord);
	}
	
	
	private static void partScoreDispose(BoardScoreRecord record, int score) {
		if (score >= Score.MULTIPLE_THREE) {
			switch (score) {
			case Score.MUST_B_KILL:
				record.five = true; 
				break;
			case Score.KILL_TO_ONE:
				record.four = true;
				break;
			case Score.KILL_TO_1_2:
				record.three ++;
				record.b4 ++;
				break;
			case Score.KILL_TO_TWO:
				record.three += 2;
				break;
			default:
				ExceptionUtil.throwIllegalValue();
			}
		} else if (score >= Score.B4){
			record.b4 ++;
		} else if (score >= Score.THREE){
			record.three ++;
		}
		record.total += score;
	}
	
	
	private static int getBoardScore(BoardScoreRecord thisRecord, BoardScoreRecord otherRecord) {
		// �����ܲ����γɾ�ɱ
		if (thisRecord.five) {
			return Score.FIVE;
		} else if (otherRecord.five) {
			return - Score.FIVE;
		} else if (thisRecord.four || thisRecord.b4 >= 2){ // ˫���� �� ����
			return Score.FOUR;
		} else if (otherRecord.four || otherRecord.b4 >= 2){ // ˫���� �� ����
			return - Score.FOUR;
		} else if (thisRecord.b4 == 1 || thisRecord.three > 1){ // b4h3
			return Score.THREE_FOUR;
		} else if (otherRecord.b4 == 1 || otherRecord.three > 1){ // b4h3
			return - Score.THREE_FOUR;
		} else if (thisRecord.three >= 2){ // ˫��, 3��
			return Score.MULTIPLE_THREE;
		} else if (otherRecord.three >= 2){ // ˫��, 3��
			return Score.MULTIPLE_THREE;
		} else { // ���Ǳ�ɱ�巵���ܷ�
			return thisRecord.total - otherRecord.total;
		}
	}
	
}
