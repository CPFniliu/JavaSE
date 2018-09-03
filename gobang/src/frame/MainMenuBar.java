package frame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import global.Global;
import impl.LambdaMouseListener;

public class MainMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	private GoBangPanel gBPanel = null;
	
	public MainMenuBar() {
		addUserMenu();
		addGameMenu();
		addDebugMenu();
		addSettingMenu();
	}


	private void addGameMenu() {
		JMenu mu_game = new JMenu("game");
		add(mu_game);
		
		JMenuItem ml_reopen = new JMenuItem("重新开局");
		mu_game.add(ml_reopen);
		ml_reopen.addMouseListener((LambdaMouseListener) (e) -> {
			getGoBangPanel().init();
		});
	}

	private void addSettingMenu() {
		JMenu mu_setting = new JMenu("setting");
		add(mu_setting);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("难度");
		mu_setting.add(mntmNewMenuItem);
	}


	private void addDebugMenu() {
		JMenu mu_debug = new JMenu("Debug");
		add(mu_debug);
		
		JMenuItem ml_showScore = new JMenuItem("查看分数");
		ml_showScore.addMouseListener((LambdaMouseListener) (e) -> {
			getGoBangPanel().showScore();
		});
		mu_debug.add(ml_showScore);
		
		JMenuItem ml_importQB = new JMenuItem("COM GO");
		ml_importQB.addMouseListener((LambdaMouseListener) (e) -> {
			Global.setComRunnable(true);
			getGoBangPanel().run();
		});
		mu_debug.add(ml_importQB);
	}


	private void addUserMenu() {
		JMenu mn_user = new JMenu("user");
		add(mn_user);
		
		JMenuItem ml_userInfo = new JMenuItem("个人信息");
		mn_user.add(ml_userInfo);
		
		JMenuItem ml_exit = new JMenuItem("退出");
		mn_user.add(ml_exit);
	}
	
	
	private GoBangPanel getGoBangPanel(){
		if (gBPanel == null){
			gBPanel = ((MainFrame) MainMenuBar.this.getParent().getParent().getParent()).getGoBangPanel();
		}
		return gBPanel;
	}
	
}
