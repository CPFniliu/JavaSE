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
		// 3������  < 1�����Ļ���
		// 2������  > 1������
		// �����ܲ����γɾ�ɱ
		if (five) {
			return Score.MUST_B_KILL;
		} else if (four || b4 >= 2){ // ˫���� �� ����
			return Score.KILL_TO_ONE;
		} else if (b4 == 1 || three > 1){ // b4h3
			return Score.KILL_TO_1_2;
		} else if (three >= 2){ // ˫��, 3��
			return Score.KILL_TO_TWO;
		} else { // ���Ǳ�ɱ�巵���ܷ�
			return total;
		}
	}
	
}
