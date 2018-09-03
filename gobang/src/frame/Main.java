package frame;

import java.util.Arrays;

import algorithm.Base;
import algorithm.PointEvaluate;
import domain.PlacePool;
import entity.Place;
import entity.Pt;
import global.Config;

public class Main {

	Pt[][] board = null;
	
	int score[][][] = new int[3][15][15];
	
	private static Pt BLCK = Pt.BLACK;
	private static Pt WHIT = Pt.WHITE;
	
	
	public Main(){
		PlacePool.init();
		Pt[][] board0 = {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, WHIT, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, WHIT, null, null, null, null, null, WHIT, null, null, null, null},
				{null, null, null, null, null, BLCK, null, BLCK, BLCK, BLCK, null, null, null, null, null},
				{null, null, null, null, null, null, WHIT, null, BLCK, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, BLCK, null, BLCK, null, null, null, null, null},
				{null, null, null, null, null, null, BLCK, null, WHIT, null, WHIT, null, null, null, null},
				{null, null, null, null, null, WHIT, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				};
		board = board0;
	}

	public static void main(String[] args) {
		Main main = new Main();
//		boolean result = Base.isWin(main.board, PlacePool.getPlace(6, 7), Pt.B);
//		System.out.println(result);
//		main.evaluatePoint(7, 6, Pt.W);
//		main.evaluatePoint(6, 7, Pt.W);
//		main.evaluatePoint(7, 6, Pt.B);
//		main.evaluatePoint(6, 7, Pt.B);
//		
//		main.scoreClear();
//		int totle = main.evaluate();
//		System.out.println(" ===========   totle : " + totle);
//		main.scorePrint();
		System.out.println(PointEvaluate.subPointEvaluate(main.board, 3, 6, 1, 1, BLCK));
		System.out.println(PointEvaluate.subPointEvaluate(main.board, 3, 6, 1, 1, WHIT));
		System.out.println(PointEvaluate.subPointEvaluate(main.board, 4, 6, 0, 1, BLCK));
		System.out.println(PointEvaluate.subPointEvaluate(main.board, 4, 6, 0, 1, WHIT));
	}
	
	
	
	public void evaluatePoint(int i, int j, Pt part){
		int s = PointEvaluate.pointEvaluate(board, PlacePool.getPlace(i, j), part);
		System.out.println("(" + i + ", " + j + ") : " + s);
	}
	
	public int evaluate(){
		int sum = 0;
		for (int i = Config.BOARDLENGTH - 1; i >= 0; i--) {
			for (int j = Config.BOARDLENGTH - 1; j >= 0; j--) {
				// ÷ªº∆À„ø’Œª
				if (board[i][j] == null){
					Place place = PlacePool.getPlace(i, j);
					if (Base.hasNeighbor(board, place)) {
						int whiteScore = PointEvaluate.pointEvaluate(board, place, Pt.WHITE);
						score[0][i][j] = whiteScore;
						int blackScore = PointEvaluate.pointEvaluate(board, place, Pt.BLACK);
						score[1][i][j] = blackScore;
						int cha = whiteScore - blackScore;
						score[2][i][j] = cha;
						sum += cha;
					}
				}
			}
		}
		return sum;
	}

	
	public void scoreClear(){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 15; j ++) {
				Arrays.fill(score[i][j], 0);
			}
		}
	}
	

	public void scorePrint(){
		int[] tmp = null;
		for (int i = 0; i < 3; i++){
			System.err.println();
			if (i == 0) {
				System.out.println("\n white score:");
			} else if (i == 1) {
				System.out.println("\n black score:");
			} else if (i == 2) {
				System.out.println("\n white - black :");
			} 
			for (int r = 0; r < 15; r++){
				System.out.print("-\t-" + r);
			}
			for (int j = 0; j < 15; j ++) {
				System.out.print("\n " + j + " - ");
				tmp = score[i][j];
				for (int k=0; k < 15; k ++){
					System.out.print("\t" + tmp[k]);
				}
			}
		}
	}
}
