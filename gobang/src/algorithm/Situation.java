package algorithm;

import java.util.List;

import entity.Place;
import entity.Part;
import entity.Role;
import global.Config;
import util.validate.CpfUtilArr;

public class Situation implements Cloneable{

	public Situation() {
		board = new Part[Config.BOARDLENGTH][Config.BOARDLENGTH];
		// 获取先手方配置信息
		curPart = Config.firstPart;
	}
	
	/**
	 * 当前棋盘
	 */
	public Part[][] board = null;
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
	public Part lastPart = null;
	/**
	 * 当前的行走的势力
	 */
	private Part curPart = null;



	/**
	 * 判断当前局势是否胜利
	 * @return
	 */
	public boolean isWin(Place place, Part part){
		return Base.isWin(board, place, part);
	}

	/**
	 * 评估当前执棋势力落子最佳位置
	 * @param curPart 当前执棋势力
	 * @return 评估当前执棋势力落子最佳的位置
	 */
	public Place evaluatedPlace(){
		return new AlphaBeta().evaluatedPlace(curPart);
	}
	
	
	/**
	 * 评估当前局势
	 * @param boardSpace
	 * @param place
	 * @param part
	 * @return
	 */
	public int evaluate(Part thispt){
		return BoardEvaluate.evaluate(board, thispt);
	}
	
	/**
	 * 返回某位之上的棋子
	 * @param place
	 * @return
	 */
	public Part getPiece(Place place){
		return board[place.x][place.y];
	}
	
	/**
	 * 真实落子
	 * @param place
	 * @param part
	 */
	public boolean realLocatePiece(Place place, Part part){
		// 落子
		board[place.x][place.y] = part;
		// 势力
		this.lastPlace = place;
		this.lastPart = part;
		// 步数
		++ totalstep;
		if (Part.WHITE == part){
			whiteStep ++;
		} else {
			blackStep ++;
		}
		// 更改下步活动势力方
		curPart = Part.getOpposide(part);
		return isWin(place, part);
	}

	/**
	 * 落子
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
	public List<Place> getHeuristicPlaces(Part thispt) {
		return GenePlaces.getHeuristicPlaces(board, thispt);
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
	 * 获取当前下棋角色
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
