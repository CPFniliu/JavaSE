package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import algorithm.Compute;
import domain.PieceFactory;
import domain.PlacePool;
import entity.Place;
import entity.Role;
import global.Config;
import global.Global;
import impl.LambdaMouseListener;

public class GoBangPanel extends JPanel implements LambdaMouseListener {

	private static final long serialVersionUID = 1L;
	
	private JTextArea txtrFwertwerw = null;

	/**
	 * Create the panel.
	 */
	public GoBangPanel() {
		setLayout(null);
		addMouseListener(this);
		setBackground(SystemColor.inactiveCaption);
		init();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		final int halfXY = Config.PIECEWIDTH / 2;
		final int maxXY = Config.BOARDLENGTH * Config.PIECEWIDTH - halfXY;
		for (int i = maxXY; i > 0; i -= Config.PIECEWIDTH) {
			g.drawLine(halfXY, i, maxXY, i);
			g.drawLine(i, halfXY, i, maxXY);
		}
	}
	
	public void init(){
		removeAll();
		repaint();
		Global.init();
		init2();
		run();
	}
	
	private void init2(){
		txtrFwertwerw = new JTextArea();
		txtrFwertwerw.setBounds(510, 20, 500, 480);
		txtrFwertwerw.setBackground(SystemColor.gray);
		txtrFwertwerw.setVisible(true);
		add(txtrFwertwerw);
	}
	
	public void showScore(){
		txtrFwertwerw.setText(Global.getSituation().toString());
	}
	
	
	/**
	 * 运行
	 */
	protected void run(){
		new Thread(() -> {
			while (Global.isComRunnable()) {
				try {
					// 若当前执棋手是 COM
					if (Role.COM.equals(Global.getSituation().getCurRole())){
						// TODO 显示轮到COM来继续的信息
						// 获取COM计算的位置
						Place computedPalce = Compute.getEvaluatedPlace();
						if (computedPalce == null){
							isAnotherGame("已经没有可以下的位子,是否开启下一局", "阿拉拉");
						}
						// 落子
						boolean isWin = pushPiece(computedPalce);
						// 判断是否结束
						if (isWin){
							Global.setComRunnable(false);
							isAnotherGame("congrataulation", "再来一局");
						}
					} else {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					// 暂停COM
					Global.setComRunnable(false);
					JOptionPane.showMessageDialog(null, e.getMessage(), e.toString(), JOptionPane.ERROR_MESSAGE); 
					break;
				}
			}
		}).start();
	}
	
	/**
	 * 是否再来一局
	 */
	public void isAnotherGame(String title, String message){
		int checkresult = JOptionPane.showConfirmDialog(this, "再来一局", "congrataulation", JOptionPane.YES_NO_OPTION); 
		if(checkresult == JOptionPane.YES_OPTION){
			init();
		} 
	}
	

	/**
	 * 落子函数
	 * @param x
	 * @param y
	 */
	private boolean pushPiece(Place place) {
		GoBangPanel.this.add(PieceFactory.getPieceByPart(Global.getSituation().getCurPart(), place));
		boolean isWin = Global.getSituation().realLocatePiece(place, Global.getSituation().getCurPart());
		repaint();
		return isWin;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		switch (Config.mouseClickState) {
		case PLAY:
			if (e.getSource() instanceof GoBangPanel) {// 如果点击的是空白
				if (Role.MAN.equals(Global.getSituation().getCurRole())){// 若论到人下棋
					// 获取落子点
					Point point = e.getPoint();
					Place place = PlacePool.getPlace(point);
					// 如果点击的落子点位置没有棋子
					if (Global.getSituation().getPiece(place) == null) {
						// 添加棋子
						boolean isWin = pushPiece(place);
						// 判断是否结束
						if (isWin){
							Global.setComRunnable(false);
							isAnotherGame("congrataulation", "再来一局");
						}else {
							run();
						}
					}
					// 判断是否胜利
				}
			}
			break;
		case DEBUG_POINT_EVALUATE:
			break;
		default:
			throw new RuntimeException("This situation will never happen!");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
