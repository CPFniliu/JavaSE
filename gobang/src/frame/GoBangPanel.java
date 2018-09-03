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
	 * ����
	 */
	protected void run(){
		new Thread(() -> {
			while (Global.isComRunnable()) {
				try {
					// ����ǰִ������ COM
					if (Role.COM.equals(Global.getSituation().getCurRole())){
						// TODO ��ʾ�ֵ�COM����������Ϣ
						// ��ȡCOM�����λ��
						Place computedPalce = Compute.getEvaluatedPlace();
						if (computedPalce == null){
							isAnotherGame("�Ѿ�û�п����µ�λ��,�Ƿ�����һ��", "������");
						}
						// ����
						boolean isWin = pushPiece(computedPalce);
						// �ж��Ƿ����
						if (isWin){
							Global.setComRunnable(false);
							isAnotherGame("congrataulation", "����һ��");
						}
					} else {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					// ��ͣCOM
					Global.setComRunnable(false);
					JOptionPane.showMessageDialog(null, e.getMessage(), e.toString(), JOptionPane.ERROR_MESSAGE); 
					break;
				}
			}
		}).start();
	}
	
	/**
	 * �Ƿ�����һ��
	 */
	public void isAnotherGame(String title, String message){
		int checkresult = JOptionPane.showConfirmDialog(this, "����һ��", "congrataulation", JOptionPane.YES_NO_OPTION); 
		if(checkresult == JOptionPane.YES_OPTION){
			init();
		} 
	}
	

	/**
	 * ���Ӻ���
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
			if (e.getSource() instanceof GoBangPanel) {// ���������ǿհ�
				if (Role.MAN.equals(Global.getSituation().getCurRole())){// ���۵�������
					// ��ȡ���ӵ�
					Point point = e.getPoint();
					Place place = PlacePool.getPlace(point);
					// �����������ӵ�λ��û������
					if (Global.getSituation().getPiece(place) == null) {
						// �������
						boolean isWin = pushPiece(place);
						// �ж��Ƿ����
						if (isWin){
							Global.setComRunnable(false);
							isAnotherGame("congrataulation", "����һ��");
						}else {
							run();
						}
					}
					// �ж��Ƿ�ʤ��
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
