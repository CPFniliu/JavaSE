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
		// 获取先手方配置信息
		curPart = Config.firstPart;
	}
	
	/**
	 * 当前棋盘
	 */
	public Pt[][] board = null;
	/**
	 * 当前分数
	 */
	public int[][] scores = null;
	/**
	 * 当前走的步数
	 */
	public int totalstep;
	/**
	 * 黑棋当前走的步数
	 */
	public int blackStep;
	/**
	 * 白棋当前走的步数
	 */
	public int whiteStep;
	/**
	 * 最后的行走的位置
	 */
	public Place lastPlace = null;
	/**
	 * 最后的行走的势力
	 */
	public Pt lastPart = null;
	/**
	 * 当前的行走的势力
	 */
	private Pt curPart = null;



	/**
	 * 判断当前局势是否胜利
	 * @return
	 */
	public boolean isWin(Place place, Pt part){
		return Base.isWin(board, place, part);
	}
	
	
	/**
	 * 评估当前局势
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
//				// 只计算空位
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
//			// TODO 再加一次评估, 对整个棋盘局势进行评估
//			
//		}
//		return sum;
	}
	
	/**
	 * 返回某位之上的棋子
	 * @param place
	 * @return
	 */
	public Pt getPiece(Place place){
		return board[place.x][place.y];
	}
	
	/**
	 * 真实落子
	 * @param place
	 * @param part
	 */
	public boolean realLocatePiece(Place place, Pt part){
		// 落子
		board[place.x][place.y] = part;
		// 势力
		this.lastPlace = place;
		this.lastPart = part;
		// 步数
		++ totalstep;
		if (Pt.WHITE == part){
			whiteStep ++;
		} else {
			blackStep ++;
		}
		// 更改下步活动势力方
		curPart = Pt.getOpposide(part);
		return isWin(place, part);
	}

	/**
	 * 落子
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
	 * @return 获取当前棋盘上可以下的点的List集合
	 */
	public List<Place> getHasNeighborPlaces() {
		return GenePlaces.getHasNeighborPlaces(board);
	}
	
	/**
	 * @return 获取当前棋盘上可以下的点的List集合
	 */
	public List<Place> getAllBlankPlaces() {
		return GenePlaces.getAllBlankPlaces(board);
	}
	
	/**
	 * 启发式搜索函数
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
	 * 获取当前下棋角色
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
