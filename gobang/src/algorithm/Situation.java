package algorithm;

import java.util.Collection;
import java.util.List;

import entity.Place;
import entity.Pt;
import entity.Role;
import global.Config;
import util.validate.CpfUtilArr;

public class Situation implements Cloneable{

	public Situation() {
		board = new Pt[Config.BOARDLENGTH][Config.BOARDLENGTH];
		scores = new int[Config.BOARDLENGTH][Config.BOARDLENGTH];
		// ��ȡ���ַ�������Ϣ
		curPart = Config.firstPart;
	}
	
	/**
	 * ��ǰ����
	 */
	public Pt[][] board = null;
	/**
	 * ��ǰ����
	 */
	public int[][] scores = null;
	/**
	 * ��ǰ�ߵĲ���
	 */
	public int totalstep;
	/**
	 * ���嵱ǰ�ߵĲ���
	 */
	public int blackStep;
	/**
	 * ���嵱ǰ�ߵĲ���
	 */
	public int whiteStep;
	/**
	 * �������ߵ�λ��
	 */
	public Place lastPlace = null;
	/**
	 * �������ߵ�����
	 */
	public Pt lastPart = null;
	/**
	 * ��ǰ�����ߵ�����
	 */
	private Pt curPart = null;



	/**
	 * �жϵ�ǰ�����Ƿ�ʤ��
	 * @return
	 */
	public boolean isWin(Place place, Pt part){
		return Base.isWin(board, place, part);
	}
	
	
	/**
	 * ������ǰ����
	 * @param boardSpace
	 * @param place
	 * @param part
	 * @return
	 */
	public int evaluate(Pt thispt){
		return BoardEvaluate.evaluate(board, thispt);
//		int sum = 0;
//		for (int i = Config.BOARDLENGTH - 1; i >= 0; i--) {
//			for (int j = Config.BOARDLENGTH - 1; j >= 0; j--) {
//				// ֻ�����λ
//				if (board[i][j] == null){
//					Place place = PlacePool.getPlace(i, j);
//					if (Base.hasNeighbor(board, place)) {
//						int whiteScore = PointEvaluate.pointEvaluate(board, place, Pt.W);
//						int blackScore = PointEvaluate.pointEvaluate(board, place, Pt.B);
//						sum += (whiteScore - blackScore);
//					}
//				}
//			}
//			
//			// TODO �ټ�һ������, ���������̾��ƽ�������
//			
//		}
//		return sum;
	}
	
	/**
	 * ����ĳλ֮�ϵ�����
	 * @param place
	 * @return
	 */
	public Pt getPiece(Place place){
		return board[place.x][place.y];
	}
	
	/**
	 * ��ʵ����
	 * @param place
	 * @param part
	 */
	public boolean realLocatePiece(Place place, Pt part){
		// ����
		board[place.x][place.y] = part;
		// ����
		this.lastPlace = place;
		this.lastPart = part;
		// ����
		++ totalstep;
		if (Pt.WHITE == part){
			whiteStep ++;
		} else {
			blackStep ++;
		}
		// �����²��������
		curPart = Pt.getOpposide(part);
		return isWin(place, part);
	}

	/**
	 * ����
	 * @param place
	 * @param part
	 */
	protected void virtualLocatePiece(Place place, Pt part){
		board[place.x][place.y] = part;
	}
	
	protected void virtualRemovePiece(Place place) {
		board[place.x][place.y] = null;
	}

	/**
	 * @return ��ȡ��ǰ�����Ͽ����µĵ��List����
	 */
	public List<Place> getHasNeighborPlaces() {
		return GenePlaces.getHasNeighborPlaces(board);
	}
	
	/**
	 * @return ��ȡ��ǰ�����Ͽ����µĵ��List����
	 */
	public List<Place> getAllBlankPlaces() {
		return GenePlaces.getAllBlankPlaces(board);
	}
	
	/**
	 * ����ʽ��������
	 * @return
	 */
	public Collection<Place> getHeuristicPlaces(Pt thispt) {
		return GenePlaces.getHeuristicPlaces(board, thispt);
	}
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	
	public void setScore(Place place, int score){
		scores[place.x][place.y] = score;
	}
	
	
	@Override
	public String toString() {
		StringBuilder strbdr = new StringBuilder();
		strbdr.append("board \n");
		strbdr.append(getBoardPrintString());
		strbdr.append("score \n");
		strbdr.append(getScorePrintString());
		return strbdr.toString();
	}
	
	
	public String getBoardPrintString(){
		Pt[][] boardCopy = CpfUtilArr.deepClone(board);
		CpfUtilArr.transposeMatrix(boardCopy);
		StringBuilder strbdr = new StringBuilder();
		Pt tmp[] = null;
		strbdr.append("\n " + " -- ");
		for (int r = 0; r < 15; r++){
			strbdr.append("-\t-" + String.format("%02d", r));
		}
		for (int i = 0; i < 15; i ++) {
			strbdr.append("\n " + i + " - ");
			tmp = boardCopy[i];
			for (int k=0; k < 15; k ++){
				strbdr.append("\t" + tmp[k]);
			}
		}
		return strbdr.toString();
	}
	
	public String getScorePrintString(){
		int[][] scoresCopy = CpfUtilArr.deepClone(scores);
		CpfUtilArr.transposeMatrix(scoresCopy);
		StringBuilder strbdr = new StringBuilder();
		int tmp[] = null;
		strbdr.append("\n " + " -- ");
		for (int r = 0; r < 15; r++){
			strbdr.append("-\t-" + String.format("%02d", r));
		}
		for (int i = 0; i < 15; i ++) {
			strbdr.append("\n " + i + " - ");
			tmp = scoresCopy[i];
			for (int k=0; k < 15; k ++){
				strbdr.append("\t" + tmp[k]);
			}
		}
		return strbdr.toString();
	}
	

	/**
	 * ��ȡ��ǰ�����ɫ
	 * @return
	 */
	public Role getCurRole() {
		return Config.getRole(curPart);
	}
	
	public Pt getCurPart() {
		return curPart;
	}

	public void setCurPart(Pt curPart) {
		this.curPart = curPart;
	}
}
