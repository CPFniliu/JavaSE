package algorithm;

import entity.Place;
import entity.Part;
import entity.Score;
import entity.PointScoreDisposer;
import global.Config;
import util.validate.ExceptionUtil;

/**
 * @author CPF
 * ����ʽ���ۺ���
 * �����ר�Ÿ�ĳһ����λ��ֵģ����Ǹ��������̴�ֵ�
 * ������ֻ��ĳһ����ɫ���
 */
public class PointEvaluate {
	
	/**
	 * �����뾶
	 */
//	private static int EVALUTE_R = 3;


	
	/**
	 * С����
	 * @param num
	 * @param addend
	 * @return
	 */
	private static boolean isOutOfBroads(int num, int addend){
		switch (addend) {
		case  1 : return num >= Config.BOARDLENGTH;
		case -1 : return num <  0;
		case  0 : 
		default : return false;
		}
	}
	
	/**
	 * ����ʽ��������
	 * ��ʾ�ڵ�ǰλ����һ�����Ӻ�ķ���
	 * @param boardSpace
	 * @param x
	 * @param y
	 * @param part
	 */
	public static int pointEvaluate(final Part[][] boardSpace, final Place place , final Part part){
		PointScoreDisposer sDisposer = new PointScoreDisposer();
		int pline = subPointEvaluate(boardSpace, place.x, place.y, 1, 0, part);
		sDisposer.handleAwayOfPoint(pline);
	    pline = subPointEvaluate(boardSpace, place.x, place.y, 0, 1, part);
		sDisposer.handleAwayOfPoint(pline);
	    pline = subPointEvaluate(boardSpace, place.x, place.y, 1, 1, part);
		sDisposer.handleAwayOfPoint(pline);
		pline = subPointEvaluate(boardSpace, place.x, place.y, 1, -1, part);
		sDisposer.handleAwayOfPoint(pline);
		return sDisposer.getPointScore();
	}
	
	
	
	/**
	 * ��һ�����ӽ��д��
	 * (x, y)  :  Ϊ�����ĵط������ӵ�λ��
	 * (xaddend, yaddend)  :  ֵΪ(1,1) : �K ����, (1,0) : �� ����, (0,1) : �� ����, (1,-1) : �J ����
	 * 
	 * @param boardSpace  ���̾�������
	 * @param x  ���ӵ�x����
	 * @param y  ���ӵ�y����
	 * @param xaddend  ����������ʱ x ��ÿ�����ӵ�ֵ
	 * @param yaddend  ����������ʱ y ��ÿ�����ӵ�ֵ
	 * @param part  ��ǰ������������
	 */
	public static int subPointEvaluate(Part[][] boardSpace, int x, int y, int xaddend, int yaddend, Part part) {
		int count = 1; // �������ӵ�����������������ǰ[x��y]λ�õ������� ��
	    int block = 0; // �谭����
	    int empty = -1; // ��λλ�ã�-1���ʾ�ޣ���blockΪ1ʱ����λλ����block��ʼ����
		for (int i = x + xaddend, j = y + yaddend; true; i += xaddend, j += yaddend){
			// ��x��Խ�����y��Խ��
			if (isOutOfBroads(i, xaddend) || isOutOfBroads(j, yaddend)){
				++ block;
				break;
			}
			if (boardSpace[i][j] == null){
				if (empty == -1 && !(isOutOfBroads(i + xaddend, xaddend) || isOutOfBroads(j + yaddend, yaddend)) && part.equals(boardSpace[i + xaddend][j + yaddend])){
					empty = 0; // ��λĬ�ϴ�������ʼ��
					continue;
				}else {
					break;
				}
			} else if (boardSpace[i][j].equals(part)) {
				count ++;
				if (empty != -1) { // �����ڿ�λλ�ã����λλ��ҲҪ��һ
					empty ++;
				}
				continue;
			} else {
				block++;
				break;
			}
		}
		xaddend = - xaddend;
		yaddend = - yaddend;
		for (int i = x+xaddend, j = y+yaddend; true; i += xaddend, j += yaddend){
			// ��x��Խ�����y��Խ��
			if (isOutOfBroads(i, xaddend) || isOutOfBroads(j, yaddend)){
				if (block == 0 && empty != -1) { // ���������谭 && �������п�λ �����λλ��תΪ����������С����ʼ��
					empty = count - empty;
				}
				++ block;
				break;
			}
			if (boardSpace[i][j] == null){
				if (empty == -1 && !(isOutOfBroads(i + xaddend, xaddend) || isOutOfBroads(j + yaddend, yaddend)) && part.equals(boardSpace[i + xaddend][j + yaddend])){
					empty = count; // ���������Ƿ��Ѿ����谭��empty��ʱ����������
					continue;
				}else {
					break;
				}
			}else if (boardSpace[i][j].equals(part)){
				count ++ ;
				continue;
			}else {
				if (block == 0 && empty != -1) { // ���������谭 && �������п�λ �����λλ��תΪ����������С����ʼ��
					empty = count - empty;
				}
				block ++ ;
				break;
			}
		}
		return grade(count, empty, block);
	}

	
	/**
	 * ͨ�����������һ�����ӽ��д��
	 * @param count ������
	 * @param empty ��λλ�ã� ��λ�ǵڼ���, ��λ�������ǵ�0��, ֻ���� (-1, 1,2,3,4,....)��
	 * @param block �谭��  ֻ������0,1,2
	 * @return
	 */
	public static int grade(int count, int empty, int block){
		// count ������С�� empty + 1
		// ��λ�������ǵ�0��
		if (empty == -1) {
			if (count >= 5) {
				return Score.FIVE;
			}
		} else if (empty >= 5 || count - empty >= 5){
			// ���������ӵ�����>=5����ֱ�ӷ���
			return Score.FIVE;
		}
		switch (empty) {
		case -1: // �޿�λ
			if (block == 0){
				switch (count) { 
					case 1: return Score.ONE;
		        	case 2: return Score.TWO;
		        	case 3: return Score.THREE;
		        	case 4: return Score.FOUR;
				}
			}else if (block == 1){
				switch (count) {
			        case 1: return Score.BLOCKED_ONE;
			        case 2: return Score.BLOCKED_TWO;
			        case 3: return Score.BLOCKED_THREE;
			        case 4: return Score.BLOCKED_FOUR;
				}
			}
			break;
		case 1: // ��һ���ǿ�λ
			if (block == 0){
				switch (count) {
			        case 2: return Score.TWO/2;  		//   �� �� �� 
			        case 3: return Score.OUT_THREE;  	//   �� �� �� ��
			        case 4: return Score.BLOCKED_FOUR;	//   �� �� �� �� ��
			        case 5: return Score.FOUR;   		//   �� �� �� �� �� ��
				}
			}else if (block == 1){
				switch (count) {
			        case 2: return Score.BLOCKED_TWO;
			        case 3: return Score.BLOCKED_THREE;
			        case 4: return Score.BLOCKED_FOUR;
			        case 5: return Score.FOUR;
				}
			}
			break;
		case 2:
			if (block == 0){
				switch (count) {
		        case 3: return Score.OUT_THREE;		//  �� �� �� �� 
		        case 4: 							//  �� �� �� �� ��
		        case 5: return Score.BLOCKED_FOUR;	//  �� �� �� �� �� ��
		        case 6: return Score.FOUR;			//  �� �� �� �� �� �� ��
				}
			}else if (block == 1){
				switch (count) {
		        case 3: return Score.BLOCKED_THREE;
		        case 4: 
		        case 5: return Score.BLOCKED_FOUR;
		        case 6: return Score.FOUR;
				}
			}else if (block == 2){
				switch(count) {
				case 4:
				case 5:
				case 6: return Score.BLOCKED_FOUR;
				}
			}
			break;
		case 3:
			if (block == 0){
				switch (count) {
		        case 4:								//  �� �� �� �� �� 
		        case 5: 							//  �� �� �� �� �� ��
		        case 6: return Score.BLOCKED_FOUR;	//  �� �� �� �� �� �� ��
		        case 7: return Score.FOUR;			//  �� �� �� �� �� �� �� ��
				}
			}else if (block == 1){
				switch (count) {
		        case 4:
		        case 5:
		        case 6: return Score.BLOCKED_FOUR;
		        case 7: return Score.FOUR;
				}
			}else if (block == 2){
				return Score.BLOCKED_FOUR;
			}
			break;
		case 4:
			if (block == 0){
				return Score.FOUR;
			}else if (block == 1){
				switch (count) {
		        case 5:								//  �� �� �� �� �� �� 
		        case 6:								//  �� �� �� �� �� �� ��
		        case 7:	return Score.BLOCKED_FOUR;	//  �� �� �� �� �� �� �� ��
		        case 8: return Score.FOUR;			//  �� �� �� �� �� �� �� �� ��
				}
			}else if (block == 2){
				return Score.BLOCKED_FOUR;
			}
			break;
		default:
			System.err.println("count : " + count + " , empty : " + empty + " , block : " + block);
			ExceptionUtil.throwIllegalValue();
		}
		return 0;
	}
	
}
