package algorithm;

import java.util.List;

import entity.BoardScoreRecord;
import entity.Place;
import entity.Part;
import entity.Score;
import util.validate.ExceptionUtil;

public class BoardEvaluate {

	/**
	 * ���ضԱ�����ʵ������, ����Ϊ��
	 * @param board
	 * @param thispt
	 * @return
	 */
	public static int evaluate(Part[][] board, Part thispt) {
		Part otherPt = Part.getOpposide(thispt);
		List<Place> places = GenePlaces.getHeuristicPlaces(board, thispt);
		BoardScoreRecord thisRecord = new BoardScoreRecord();
		BoardScoreRecord otherRecord = new BoardScoreRecord();
		for (Place place : places) {
			int thisScore = PointEvaluate.pointEvaluate(board, place, thispt);
			partScoreDispose(thisRecord, thisScore);
			int otherScore = PointEvaluate.pointEvaluate(board, place, otherPt);
			partScoreDispose(otherRecord, otherScore);
		}
		// ���������ʱ���ɱ�岢û�м������ܷ��ڣ����Է���Ӧ�÷�Ϊ��ɱ���������ͨ����
		int totalScore;
		{
			// ��ͨ(��ǰ��������ȥ�Է�����Ϊ�������Ʒ���, ��Ϊ����Ҫ�ֵ���ǰ�����ӣ���˶Է���������Ҫ���ۿ۵�)
			totalScore = thisRecord.total - otherRecord.total / 2;
			
			// ��ȡ��ɱ������
			if (thisRecord.five) {
				totalScore += Score.FIVE;
			} else if (otherRecord.five) {
				totalScore -= Score.FIVE;
			} else if (thisRecord.four || thisRecord.b4 >= 2){ // ˫���� �� ����
				totalScore += Score.FOUR;
			} else if (otherRecord.four || otherRecord.b4 >= 2){ // ˫���� �� ����
				totalScore -= Score.FOUR;
			} else if (thisRecord.b4 == 1 || thisRecord.three > 1){ // b4h3
				totalScore += Score.THREE_FOUR;
			} else if (otherRecord.b4 == 1 || otherRecord.three > 1){ // b4h3
				totalScore -= Score.THREE_FOUR;
			} else if (thisRecord.three >= 2){ // ˫��, 3��
				totalScore += Score.MULTIPLE_THREE;
			} else if (otherRecord.three >= 2){ // ˫��, 3��
				totalScore -= Score.MULTIPLE_THREE;
			}
		}
		return totalScore;
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
		} else if (score >= Score.BLOCKED_FOUR){
			record.b4 ++;
		} else if (score >= Score.THREE){
			// ���ﲻ����out3, ��Ϊout3��û���γ�˫�����������
			record.three ++;
		} else {
			// ������Ϊ��ɱ�壬�򲻼����ܷ�
			record.total += score;
		}
	}
	
}
