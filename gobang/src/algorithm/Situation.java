package algorithm;

import java.util.List;

import entity.Place;
import entity.Part;
import entity.Role;
import entity.Score;
import global.Config;
import util.validate.CpfUtilArr;

public class Situation implements Cloneable{

	public Situation() {
		board = new Part[Config.BOARDLENGTH][Config.BOARDLENGTH];
		steps = new int[3];
		// ��ȡ���ַ�������Ϣ
		curPart = Config.firstPart;
	}
	
	/**
	 * ��ǰ����
	 */
	public Part[][] board = null;
	/**
	 * [��ǰ�ߵĲ���, ���岽��, ���岽��]
	 */
	public int[] steps = null;
	/**
	 * �������ߵ�λ��
	 */
	public Place lastPlace = null;
	/**
	 * �������ߵ�����
	 */
	public Part lastPart = null;
	/**
	 * ��ǰ�����ߵ�����
	 */
	private Part curPart = null;



	/**
	 * �жϵ�ǰ�����Ƿ�ʤ��
	 * @return
	 */
	public boolean isWin(Place place, Part part){
//		return Base.isWin(board, place, part);
		return PointEvaluate.pointEvaluate(board, place, part) >= Score.KILL_TO_TWO;
	}
	
	
	/**
	 * �жϵ�ǰ�����Ƿ����ʤ��
	 * @return
	 */
	public boolean canWin(Place place, Part part){
		return PointEvaluate.pointEvaluate(board, place, part) >= Score.KILL_TO_TWO;
	}

	/**
	 * ������ǰִ�������������λ��
	 * @param curPart ��ǰִ������
	 * @return ������ǰִ������������ѵ�λ��
	 */
	public Place evaluatedPlace(){
		return new AlphaBeta().evaluatedPlace(curPart);
	}
	
	
	/**
	 * ������ǰ����
	 * @param boardSpace
	 * @param place
	 * @param part
	 * @return
	 */
	public int evaluate(Part thispt){
		return BoardEvaluate.evaluate(board, thispt);
	}
	
	/**
	 * ����ĳλ֮�ϵ�����
	 * @param place
	 * @return
	 */
	public Part getPiece(Place place){
		return board[place.x][place.y];
	}
	
	/**
	 * ��ʵ����
	 * @param place
	 * @param part
	 */
	public boolean realLocatePiece(Place place, Part part){
		// ����
		board[place.x][place.y] = part;
		// ����
		this.lastPlace = place;
		this.lastPart = part;
		// �����²��������
		curPart = Part.getOpposide(part);
		GenePlaces.updateSteps(board, steps);
		return isWin(place, part);
	}

	/**
	 * ����
	 * @param place
	 * @param part
	 */
	protected void virtualLocatePiece(Place place, Part part){
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
	 * ����ʽ��������
	 * @return
	 */
	public List<Place> getHeuristicPlaces(Part thispt, int deep) {
		return GenePlaces.getHeuristicPlaces(board, thispt, deep > Config.KILLDEEP);
	}
	
	/**
	 * ����Ϊ��
	 * @return
	 */
	public boolean isBoardBlank(){
		return steps[0] == 0;
	}
	

	/**
	 * ��������
	 * @return
	 */
	public boolean isBoardFull(){
		return steps[0] == Config.BOARDLENGTH * Config.BOARDLENGTH;
	}
	
	public Place getRandomCenterPlace() {
		return Base.getRandomCenterPlace(board);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	
	@Override
	public String toString() {
		StringBuilder strbdr = new StringBuilder();
		strbdr.append("board \n");
		strbdr.append(getBoardPrintString());
		return strbdr.toString();
	}
	
	
	public String getBoardPrintString(){
		Part[][] boardCopy = CpfUtilArr.deepClone(board);
		CpfUtilArr.transposeMatrix(boardCopy);
		StringBuilder strbdr = new StringBuilder();
		Part tmp[] = null;
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

	/**
	 * ��ȡ��ǰ�����ɫ
	 * @return
	 */
	public Role getCurRole() {
		return Config.getRole(curPart);
	}
	
	public Part getCurPart() {
		return curPart;
	}

	public void setCurPart(Part curPart) {
		this.curPart = curPart;
	}
}
