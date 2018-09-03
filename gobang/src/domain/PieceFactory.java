package domain;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import entity.Part;
import entity.Piece;
import entity.Place;

public class PieceFactory {

	private static ImageIcon Icon_white;
	private static ImageIcon Icon_black;

	public static Piece getPieceByPart(Part part, Place place) {
		if (Part.WHITE.equals(part)) {
			return getWhitePiece(place);
		} else if (Part.BLACK.equals(part)) {
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
		return new Piece(place, Part.WHITE, Icon_white);
	}

	public static Piece getBlackPiece(Place place) {
		if (Icon_black == null) {
			Image image = Toolkit.getDefaultToolkit().getImage(PieceFactory.class.getResource("/image/black.png"));
			Icon_black = new ImageIcon(image);
		}
		return new Piece(place, Part.BLACK, Icon_black);
	}

}
