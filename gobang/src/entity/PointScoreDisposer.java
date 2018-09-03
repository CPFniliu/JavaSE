package entity;

public class PointScoreDisposer {

	private int three;
	private int b4;
	private boolean four;
	private boolean five;
	
	public int total;
	
	public void handleAwayOfPoint(int score) {
		total += score;
		switch (score) {
		case Score.THREE:
			three ++;
			break;
		case Score.BLOCKED_FOUR:
			b4 ++;
			break;
		case Score.FOUR:
			four = true;
			break;
		case Score.FIVE:
			five = true;
			break;
		default:
		}
	}
	
	public int getPointScore() {
		// 3个活三  < 1个冲四活三
		// 2个活三  > 1个冲四
		// 看看能不能形成绝杀
		if (five) {
			return Score.MUST_B_KILL;
		} else if (four || b4 >= 2){ // 双阻四 和 活四
			return Score.KILL_TO_ONE;
		} else if (b4 == 1 || three > 1){ // b4h3
			return Score.KILL_TO_1_2;
		} else if (three >= 2){ // 双三, 3三
			return Score.KILL_TO_TWO;
		} else { // 不是必杀棋返回总分
			return total;
		}
	}
	
}
