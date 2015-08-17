package verbargo.gui;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import verbargo.music.Music;

public class vIntro extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public vIntro(){
        Icon icon = new ImageIcon("C:/Users/Samsung/workspace/Verbargo 1.5/vIntro.gif");
        JLabel label = new JLabel(icon);
        getContentPane().add(label);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Samsung\\workspace\\Verbargo 1.5\\VerbargoAppIcon.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle("Verbargo v1.5");
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	public static void main(String[] args){
		Music startup = new Music("vIntroMusic.wav", 0, false, 50);
		vIntro iv = new vIntro();
		startup.player.start();
		long b = System.currentTimeMillis();
		long e = System.currentTimeMillis();
		long diff = 0;
		while(diff < 18000){
			diff = e-b;
			e = System.currentTimeMillis();
		}
		iv.dispose();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerbargoGUI window = new VerbargoGUI(null);
					window.frmVerbargoV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}