package domain;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import entity.Pt;
import entity.Piece;
import entity.Place;

public class PieceFactory {

	private static ImageIcon Icon_white;
	private static ImageIcon Icon_black;

	public static Piece getPieceByPart(Pt part, Place place) {
		if (Pt.WHITE.equals(part)) {
			return getWhitePiece(place);
		} else if (Pt.BLACK.equals(part)) {
			return getBlackPiece(place);
		} else {
			System.out.println("请输入正确的类型!");
			return null;
		}
	}

	public static Piece getWhitePiece(Place place) {
		if (Icon_white == null) {
			Image image = Toolkit.getDefaultToolkit().getImage(PieceFactory.class.getResource("/image/white.png"));
			Icon_white = new ImageIcon(image);
		}
		return new Piece(place, Pt.WHITE, Icon_white);
	}

	public static Piece getBlackPiece(Place place) {
		if (Icon_black == null) {
			Image image = Toolkit.getDefaultToolkit().getImage(PieceFactory.class.getResource("/image/black.png"));
			Icon_black = new ImageIcon(image);
		}
		return new Piece(place, Pt.BLACK, Icon_black);
	}

}
