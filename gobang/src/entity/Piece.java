package entity;

import javax.swing.Icon;
import javax.swing.JLabel;

import global.Config;

public class Piece extends JLabel{

	private static final long serialVersionUID = 1L;

	private Part part; // ÊÆÁ¦

	public Piece(Place place, Part part, Icon icon) {
		this.setSize(Config.PIECEWIDTH, Config.PIECEWIDTH);
		this.setIcon(icon);
		setLocation(place.x * Config.PIECEWIDTH, place.y * Config.PIECEWIDTH);
		this.part = part;
	}

	public Part getPart() {
		return part;
	}
	
}
