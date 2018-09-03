package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import global.Config;
import javax.swing.JMenuBar;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel goBangPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new MainMenuBar();
		setJMenuBar(menuBar);
		
		setBounds(100, 100, (Config.BOARDLENGTH * Config.PIECEWIDTH + 16)*2, Config.BOARDLENGTH * Config.PIECEWIDTH + 39 + 24);
		
		goBangPanel = new GoBangPanel();
		goBangPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		goBangPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(goBangPanel);
	}

	public GoBangPanel getGoBangPanel() {
		return (GoBangPanel) this.goBangPanel;
	}
	
}
