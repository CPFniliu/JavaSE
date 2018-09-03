package algorithm;

import java.util.List;

import entity.BoardScoreRecord;
import entity.Place;
import entity.Part;
import entity.Score;
import util.validate.ExceptionUtil;

public class BoardEvaluate {

	/**
	 * 返回对本方的实力评估, 本方为正
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
		// 再算分数的时候必杀棋并没有计算在总分内，所以分数应该分为必杀棋分数和普通分数
		int totalScore;
		{
			// 普通(当前方分数减去对方分数为己方优势分数, 因为马上要轮到当前方落子，因此对方的优势是要打折扣的)
			totalScore = thisRecord.total - otherRecord.total / 2;
			
			// 获取必杀棋增分
			if (thisRecord.five) {
				totalScore += Score.FIVE;
			} else if (otherRecord.five) {
				totalScore -= Score.FIVE;
			} else if (thisRecord.four || thisRecord.b4 >= 2){ // 双阻四 和 活四
				totalScore += Score.FOUR;
			} else if (otherRecord.four || otherRecord.b4 >= 2){ // 双阻四 和 活四
				totalScore -= Score.FOUR;
			} else if (thisRecord.b4 == 1 || thisRecord.three > 1){ // b4h3
				totalScore += Score.THREE_FOUR;
			} else if (otherRecord.b4 == 1 || otherRecord.three > 1){ // b4h3
				totalScore -= Score.THREE_FOUR;
			} else if (thisRecord.three >= 2){ // 双三, 3三
				totalScore += Score.MULTIPLE_THREE;
			} else if (otherRecord.three >= 2){ // 双三, 3三
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
			// 这里不计算out3, 因为out3若没有形成双三，则不如活三
			record.three ++;
		} else {
			// 若棋子为必杀棋，则不计入总分
			record.total += score;
		}
	}
	
}
